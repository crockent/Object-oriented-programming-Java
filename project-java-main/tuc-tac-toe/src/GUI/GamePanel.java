package GUI;

import javax.swing.JPanel;
import Controller.GController;
import Model.Board;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	protected GController gc;
	
	public GamePanel(GController gc) {
		super();
		this.gc=gc;
	}

	protected GController getGc() {
		return gc;
	}
	
	protected Board getBoard() {
		return gc.getB();
	}
}

