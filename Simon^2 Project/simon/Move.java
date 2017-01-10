package simon;
//
public class Move implements MoveInterfaceJimmy {
	
	private ButtonInterfaceJimmy b; 
	
	public ButtonInterfaceJimmy getButton() {
		// TODO Auto-generated method stub
		return b;
	}
	
	
	public Move(ButtonInterfaceJimmy b) {
		this.b = b;
	}


}
