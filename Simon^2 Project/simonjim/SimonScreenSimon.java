package simonjim;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.ClickableScreen;

public class SimonScreenSimon extends ClickableScreen implements Runnable{

	private TextLabel tLabel;
	private ButtonInterfaceSimon[] bInterface;
	private ProgressInterfaceSimon pInterface;
	private ArrayList<MoveInterfaceSimon> mInterface; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenSimon(int width, int height) {
		super(width, height);
		Thread screen = new Thread(this);
		screen.start();
	}
	
	public void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		pInterface.setRound(roundNumber);
		mInterface.add(randomMove());
		pInterface.setSequenceSize(mInterface.size());
		changeText("Simon's turn.");
		tLabel.setText("");
		playSequence();
		changeText("Your turn.");
		tLabel.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		tLabel.setText("");
		nextRound();
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
			tLabel.setText(string);
			Thread.sleep(1000);
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public void initAllObjects(List<Visible> viewObjects) {
		addButtons();
		pInterface = getProgress();
		tLabel = new TextLabel(250,90,300,40,"Let's play Simon!");
		mInterface = new ArrayList<MoveInterfaceSimon>();
		//add 2 moves in order to start
		lastSelectedButton = -1;
		mInterface.add(randomMove());
		mInterface.add(randomMove());
		roundNumber = 0;
		viewObjects.add(pInterface);
		viewObjects.add(tLabel);
	}

	private MoveInterfaceSimon randomMove() {
		int select = (int) (Math.random()*bInterface.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*bInterface.length);
		}
		lastSelectedButton = select;
		return new Move(bInterface[select]);
	}

	private ProgressInterfaceSimon getProgress() {
		return new Progress();
	}


	private void addButtons(){
		Color[] colors = {Color.yellow, Color.blue, Color.pink,Color.black,Color.red,Color.green};
		int buttonCount = 6;
		bInterface = new ButtonInterfaceSimon[buttonCount];

		for(int i = 0; i < buttonCount; i++ ){			
			bInterface[i] = getAButton();
			bInterface[i].setColor(colors[i]);
			bInterface[i].setX(75+buttonCount + 75*i);
			bInterface[i].setY(200);
			final ButtonInterfaceSimon b = bInterface[i];
			b.dim();
			bInterface[i].setAction(new Action() {

				public void act() {

					Thread blink = new Thread(new Runnable() {

						public void run() {
							b.highlight();
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							b.dim();

						}
					});
					blink.start();

					if(acceptingInput){
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

	public void gameOver() {
		pInterface.gameOver();
	}
	
	private ButtonInterfaceSimon getAButton() {
		return new Button();
	}

}
