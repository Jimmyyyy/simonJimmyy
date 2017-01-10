package simon;
//
import guiPractice.components.Visible;

public interface ProgressInterfaceJimmy extends Visible {
	void gameOver();

	void setRound(int roundNumber);

	void setSequenceSize(int size);
}
