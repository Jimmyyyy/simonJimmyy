package Simon;

import java.awt.Color;

import java.util.ArrayList;
import java.util.List;

import guiPractice.ClickableScreen;
import guiPractice.components.Action;
import guiPractice.components.Button;
import guiPractice.components.TextLabel;
import guiPractice.components.Visible;
import java.util.ArrayList;
import java.util.List;

public class SimonScreenSimon extends ClickableScreen implements Runnable {
	private ArrayList<MoveInterfaceSimon> mInterface;
	private ButtonInterfaceSimon[] bInterface;
	private TextLabel tLabel;
	private ProgressInterfaceSimon pInterface;
	private int roundNumber;
	private boolean acceptingInput;
	private int sequenceIndex;
	private int lastSelectedButton;
	private int numberOfButtons = 6;

	public SimonScreenSimon(int width, int height) {
		super(width, height);
		Thread app = new Thread(this);
		app.start();
	}
private void nextRound() {
		acceptingInput=false;
		roundNumber++;
		mInterface.add(randomMove());
	    pInterface.setRound(roundNumber);//		
       pInterface.setSequenceSize(mInterface.size());
		changeText("Simon's turn!");
		tLabel.setText("");
		playSequence();
		acceptingInput=true;
		sequenceIndex=0;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
	tLabel.setText("");
		nextRound();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	private void playSequence() {
		ButtonInterfaceSimon b=null;
		for(int i=0; i<mInterface.size();i++){
			if(b!=null)
				b.dim();
			
			b=mInterface.get(sequenceIndex).getButton();
			b.highlight();
			int sleepTime=1000*(int)Math.exp(roundNumber);
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
		tLabel = new TextLabel(130, 230, 300, 40, "Let's play Simon!");
		mInterface = new ArrayList<MoveInterfaceSimon>();
		// add 2 moves in order to start
		lastSelectedButton = -1;
		mInterface.add(randomMove());
		mInterface.add(randomMove());
		roundNumber = 0;
		viewObjects.add(pInterface);
		viewObjects.add(tLabel);

	}

	private MoveInterfaceSimon randomMove() {
		ButtonInterfaceSimon b;
		int randomNumber;
		while(true){
			randomNumber=(int)Math.random()*(bInterface.length-1);
			b=bInterface[randomNumber];
			if(b!=bInterface[lastSelectedButton]);
				break;
		}
		lastSelectedButton=randomNumber;
		return getMove(b);
	}

	private MoveInterfaceSimon getMove(ButtonInterfaceSimon buttonInterfaceSimon) {
		//partner does this

		return null;
	}

	private ProgressInterfaceSimon getProgress() {
		/*
		 * Placeholder until partner finishes implementation of
		 * ProgressInterface
		 */
		return null;
	}

	private void addButtons() {
		Color[] colors = {Color.yellow, Color.blue, Color.pink, Color.green, Color.red, Color.orange};
		for(int i= 0; i<numberOfButtons; i++){
			final ButtonInterfaceSimon b = getAButton();
			
			b.setColor(colors[i]);
			b.setX(getWidth()/2+100*(int)Math.cos(Math.PI/3*(i)));
			b.setY(getHeight()/2+100*(int)Math.sin(Math.PI/3*(i)));
			b.setAction(new Action(){
				public void act(){
					Thread blink = new Thread(new Runnable(){

						public void run(){
							b.highlight();
							
							try {
								Thread.sleep(800);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							b.dim();
						
						}
						
						});
					blink.start();
if(acceptingInput){
						//if the user's button matches sequence
						if(b==mInterface.get(
							sequenceIndex).getButton())
							sequenceIndex++;
						else{
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
	private ButtonInterfaceSimon getAButton() {//partner
		// TODO Auto-generated method stub
		return null;
	}
}
