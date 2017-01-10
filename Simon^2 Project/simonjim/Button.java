package simonjim;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class Button extends Component implements ButtonInterfaceSimon {

	private static final int WIDTH = 50;
	private static final int HEIGHT = 50;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;
	
	public Button(int x, int y, int w, int h) {
		super(x, y, w, h);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void act() {
		action.act();
	}

	@Override
	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	@Override
	public ButtonInterfaceSimon getAButton() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dim() {
		displayColor = Color.gray;
		highlight = false;
		update();
	}

	@Override
	public void setAction(Action action) {
		this.action = action;

	}

	@Override
	public void setColor(Color color) {
		this.c = color;
		displayColor = c;
		update();
	}

	@Override
	public void highlight() {
		if(c != null) displayColor = c;
		highlight = true;
		update();

	}

	@Override
	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			
			int s = (int)(5/8.0 * WIDTH);
			int t = (int)(1.0/5*HEIGHT)+4;
			p.addPoint(s-4, t-4);
			p.addPoint(s+7, t-2);
			p.addPoint(s+10, t);
			p.addPoint(s+14, t+10);
			p.addPoint(s+12, t+14);
			p.addPoint(s+8, t+3);
			g.fill(p);
		}
	}

}
