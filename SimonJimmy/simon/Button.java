package simon;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import guiPractice.components.Action;
import guiPractice.components.Component;

public class Button extends Component implements ButtonInterfaceJimmy {

	private static final int WIDTH = 150;
	private static final int HEIGHT = 150;
	private Action action;
	private Color c;
	private Color displayColor;
	private boolean highlight;
	
	public Button() {
		super(0,0,WIDTH,HEIGHT);
	}

	public boolean isHovered(int x, int y) {
		double distance = Math.sqrt(Math.pow(x-(getX()+WIDTH/2), 2)+Math.pow(y-(getY()+HEIGHT/2), 2));
		return distance < WIDTH/2;
	}

	public void act() {
		action.act();
	}



	public void setColor(Color color) {
		this.c = color;
		displayColor = c;
		update();
	}

	public void highlight() {
		if(c != null) displayColor = c;
		highlight = true;
		update();
	}

	public void dim() {
		displayColor = Color.pink;
		highlight = false;
		update();
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void update(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if(displayColor != null) g.setColor(displayColor);
		else g.setColor(Color.red);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.drawRect(0, 0, WIDTH-1, HEIGHT-1);
		if(highlight){
			g.setColor(Color.white);
			Polygon p = new Polygon();
			
			int s = (int)(5/8.0 * WIDTH);
			int t = (int)(1.0/5*HEIGHT)+4;
			g.fill(p);
		}
		
	}

	
	private String name;
	public void setName(String s){
		this.name = s;
	}
	
	public String toString(){
		return name;
	}

	@Override
	public ButtonInterfaceJimmy getAButton() {
		return null;
	}
	
}