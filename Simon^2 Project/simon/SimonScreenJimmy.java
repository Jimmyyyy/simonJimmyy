package simon;
//
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


import guiPractice.components.Action;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import guiPractice.ClickableScreen;

public class SimonScreenJimmy extends ClickableScreen implements Runnable{

	private TextLabel label;
	private ButtonInterfaceJimmy[] button;
	private ProgressInterfaceJimmy progress;
	private ArrayList<MoveInterfaceJimmy> sequence; 
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;

	public SimonScreenJimmy(int width, int height) {
		super(width, height);
		Thread screen = new Thread(this);
		screen.start();
	}
	
	public void nextRound() {
		acceptingInput = false;
		roundNumber ++;
		progress.setRound(roundNumber);
		sequence.add(randomMove());
		progress.setSequenceSize(sequence.size());
		changeText("Simon's turn.");
		label.setText("");
		playSequence();
		changeText("  Your turn.");
		label.setText("");
		acceptingInput = true;
		sequenceIndex = 0;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		label.setText("");
		nextRound();
	}
	

	
	private void playSequence() {
		ButtonInterfaceJimmy b = null;
		for(MoveInterfaceJimmy m: sequence){
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
		progress = getProgress();
		label = new TextLabel(240,170,300,40,"Let's play Simon!");
		sequence = new ArrayList<MoveInterfaceJimmy>();
		//add 2 moves in order to start
		lastSelectedButton = -1;
		sequence.add(randomMove());
		sequence.add(randomMove());
		roundNumber = 0;
		viewObjects.add(progress);
		viewObjects.add(label);
	}

	private MoveInterfaceJimmy randomMove() {
		int select = (int) (Math.random()*button.length);
		while(select == lastSelectedButton){
			select = (int) (Math.random()*button.length);
		}
		lastSelectedButton = select;
		return new Move(button[select]);
	}

	private ProgressInterfaceJimmy getProgress() {
		return new Progress();
	}


	private void addButtons(){
		Color[] colors = {Color.green,Color.red,Color.blue,Color.yellow};
		int buttonCount = 4;
		button = new ButtonInterfaceJimmy[buttonCount];
		int[][] coords = {{150,200}, {300,200}, {150,50}, {300,50}};
		
		for(int i = 0; i < buttonCount; i++ ){			
			button[i] = getAButton();
			button[i].setColor(colors[i]);
			button[i].setX(coords[i][0]);
			button[i].setY(coords[i][1]);
			final ButtonInterfaceJimmy b = button[i];
			b.dim();
			button[i].setAction(new Action() {

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
						if(b==sequence.get(sequenceIndex).getButton()){
							sequenceIndex++;
						}else{
							progress.gameOver();
							return;
						}
					}
					if(sequence.size()==sequenceIndex){
						Thread nextRound = new Thread(SimonScreenJimmy.this);
						nextRound.start();
					}
				}

			});
			viewObjects.add(b);
		}
	}

	public void gameOver() {
		progress.gameOver();
	}
	
	private ButtonInterfaceJimmy getAButton() {
		return new Button();
	}

}
