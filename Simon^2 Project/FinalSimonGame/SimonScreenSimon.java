package FinalSimonGame;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.ClickableScreen;

public class SimonScreenSimon extends ClickableScreen implements Runnable{

	private TextLabel label;
	private ButtonInterfaceSimon[] bInterface;
	private ProgressInterfaceSimon pInterface;
	private ArrayList<MoveInterfaceSimon> mInterface; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenSimon(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}

	@Override
	public void run(){
		label.setText("");
		nextRound();
	}

	public void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		pInterface.setRound(roundNumber);
		mInterface.add(randomMove());
		pInterface.setSequenceSize(mInterface.size());
		changeText("It is now Simon's turn.");
		label.setText("");
		playSequence();
		changeText("It is now your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	
	private void playSequence() {
		ButtonInterfaceSimon b = null;
		for(MoveInterfaceSimon m: mInterface){
			if(b!=null)b.dim();
			b = m.getButton();
			b.highlight();
			int sleepTime=1000;//*(int)Math.exp(roundNumber);
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		b.dim();
	}

	private void changeText(String string) {
		try {
			label.setText(string);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		addButtons();
		pInterface = getProgress();
		label = new TextLabel(220,150,300,40,"Let's play Simon!");
		mInterface = new ArrayList<MoveInterfaceSimon>();
		//add 2 moves in order to start
		lastSelectedButton = -1;
		mInterface.add(randomMove());
		mInterface.add(randomMove());
		roundNumber = 0;
		viewObjects.add(pInterface);
		viewObjects.add(label);
	}

	private MoveInterfaceSimon randomMove() {
		int select = (int) (Math.random()*bInterface.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*bInterface.length);
		}
		lastSelectedButton = select;
		return new getMove(bInterface[select]);
	}

	private ProgressInterfaceSimon getProgress() {
		return new ProgressJaviy();
	}
	
	private ButtonInterfaceSimon getAButton() {
		return new ButtonJaviy();
	}

	
	public void gameOver() {
		pInterface.gameOver();
	}

	private void addButtons(){
		int numberOfButtons = 6;
		Color[] colors = {Color.yellow, Color.blue, Color.pink, Color.green, Color.red, Color.orange};
		bInterface = new ButtonInterfaceSimon[numberOfButtons];
		for(int i = 0; i < numberOfButtons; i++ ){			
			bInterface[i] = getAButton();
			bInterface[i].setColor(colors[i]);
			bInterface[i].setX(100 + (int)(100*i));//Math.cos(i*2*Math.PI/(numberOfButtons))));
			if(i%2==0) bInterface[i].setY(250);// - (int)(100*Math.sin(i*2*Math.PI/(numberOfButtons))));
			else bInterface[i].setY(200);
			final ButtonInterfaceSimon b = bInterface[i];
			b.dim();
			b.setAction(new Action() {

				public void act() {
					if(acceptingInput){
					Thread blink = new Thread(new Runnable() {

						public void run() {
							b.highlight();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();

						}
					});
					blink.start();

					
						if(b==mInterface.get(sequenceIndex).getButton()){
							sequenceIndex++;
						}else{
							pInterface.gameOver();
							return;
						}
					}
					if(mInterface.size()==sequenceIndex){
						Thread nextRound = new Thread(SimonScreenSimon.this);
						nextRound.start();
					}
				}

			});
			viewObjects.add(b);
		}
	}

}
