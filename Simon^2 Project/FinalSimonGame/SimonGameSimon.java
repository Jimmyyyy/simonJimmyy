package guiPractice.Simon;

import guiPractice.GUIApplication;

public class SimonGameSimon extends GUIApplication{

	public SimonGameSimon(int width,int height) {
		super(width, height);
	}
	@Override
	public void initScreen() {
		SimonScreenSimon simonScreen = new SimonScreenSimon(getWidth(), getHeight());
		setScreen(simonScreen);
				
	}
	public static void main(String[] args) {
		SimonGameSimon game = new SimonGameSimon(600, 500);
		Thread app = new Thread(game);
		app.start();
	}
}