package simonjim;
//
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import guiPractice.components.Component;
import guiPractice.components.Visible;

public class Progress extends Component implements ProgressInterfaceSimon {

	private static final int WIDTH = 100;
	private static final int HEIGHT = 120;

	private boolean gameOver;
	private String round;
	private String sequence;

	public Progress(int x, int y, int w, int h) {
		super(60, 60, WIDTH, HEIGHT);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void gameOver() {
		// TODO Auto-generated method stub
		gameOver = true;
		update();
	}

	@Override
	public void setRound(int roundNumber) {
		// TODO Auto-generated method stub
		round = "Round " + roundNumber;
		update();
	}

	@Override
	public void setSequenceSize(int size) {
		// TODO Auto-generated method stub
		sequence = "sequence" + size;
		update();
	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		FontMetrics fm = g.getFontMetrics();
		if (gameOver) {
			g.setColor(Color.orange);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			g.setColor(Color.white);
			String go = "GAME OVER!";
			

		
	}

}
