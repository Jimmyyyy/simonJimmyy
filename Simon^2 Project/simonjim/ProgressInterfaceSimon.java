package simonjim;
//
import guiPractice.components.Visible;

public interface ProgressInterfaceSimon extends Visible {
	void gameOver();

	void setRound(int roundNumber);

	void setSequenceSize(int size);
}
