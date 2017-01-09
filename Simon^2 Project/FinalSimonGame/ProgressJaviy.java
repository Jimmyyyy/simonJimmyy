package FinalSimonGame;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;

public class ProgressJaviy extends Component implements ProgressInterfaceSimon {

	private static final int WIDTH = 250;
	private static final int HEIGHT = 50;

	private String round;
	private String sequencelength;
	private boolean gameOver;

	public ProgressJaviy() {
		super(65,65,WIDTH,HEIGHT);
	}



	public void setRound(int roundNumber) {
		round = "THE ROUND IS AT "+roundNumber;
		update();
	}

	public void setSequenceSize(int size) {
		sequencelength = "THE SEQUENCE LENGTH IS "+size;
		update();
	}

	public void gameOver() {
		gameOver = true;
		update();
	}



	@Override
	public void update(Graphics2D g) {
		FontMetrics fm = g.getFontMetrics();
		if(gameOver){
			g.setColor(Color.pink);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);
			String go = "GAME OVER!";
			g.drawString(go, (WIDTH - fm.stringWidth(go))/2, 20);
			g.drawString(sequencelength, (WIDTH - fm.stringWidth(sequencelength))/2, 40);

		}else{
			g.setColor(Color.orange);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.black);
			g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
			if(round !=null && sequencelength != null){

				g.drawString(round, (WIDTH - fm.stringWidth(round))/2, 20);
				g.drawString(sequencelength, (WIDTH - fm.stringWidth(sequencelength))/2, 40);
			}
		}
	}

}
