package simonjim;
//
public class Move implements MoveInterfaceSimon {
	
	private ButtonInterfaceSimon b; 
	
	public ButtonInterfaceSimon getButton() {
		// TODO Auto-generated method stub
		return b;
	}
	
	
	public Move(ButtonInterfaceSimon b) {
		this.b = b;
	}


}
