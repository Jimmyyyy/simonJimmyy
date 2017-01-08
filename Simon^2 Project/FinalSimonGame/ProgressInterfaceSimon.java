package FinalSimonGame;

import guiPractice.components.Visible;

public interface ProgressInterfaceSimon extends Visible{

	void setRound(int roundNumber);

	void setSequenceSize(int size);

	void gameOver();

}