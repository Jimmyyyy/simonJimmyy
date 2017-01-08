package FinalSimonGame;

public class getMove implements MoveInterfaceSimon {

	private ButtonInterfaceSimon b; 
	
	public getMove(ButtonInterfaceSimon b) {
		this.b = b;
	}

	public ButtonInterfaceSimon getButton() {
		return b;
	}

}