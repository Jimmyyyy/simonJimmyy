package simon;
//
import java.awt.Color;

import guiPractice.components.Action;
import guiPractice.components.Clickable;

public interface ButtonInterfaceJimmy extends Clickable {
	ButtonInterfaceJimmy getAButton(); // not sure if this is right
void dim();
	void setAction(Action action);
	
	void setColor(Color color);

	void setX(int i);

	void setY(int i);

	void highlight();

}
