package simon;

import guiPractice.GUIApplication;

public class SimonGameJimmy extends GUIApplication{

	public SimonGameJimmy(int width,int height) {
		super(width, height);
	}

	public void initScreen() {
		SimonScreenJimmy simonScreen = new SimonScreenJimmy(getWidth(), getHeight());
		setScreen(simonScreen);
				
	}
	public static void main(String[] args) {
		SimonGameJimmy game = new SimonGameJimmy(600, 500);
		Thread app = new Thread(game);
		app.start();
	}
}
