package GUI;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.border.LineBorder;
import Controller.GController;

@SuppressWarnings("serial")
public class MainAreaPanel extends GamePanel {
	public static final String HOF = "HALL_OF_FAME";
	public static final String BOARD = "GAME_BOARD";
	
	HallofFame hof;
	BoardGui gb; //gameBoard
	CardLayout cards;
	
	public MainAreaPanel(GController gc) {
		super(gc);
		this.cards = new CardLayout();
		
		this.setLayout(this.cards);
		this.setPreferredSize(new Dimension(WIDTH-2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT - MainWindow.TOP_HEIGHT));
		this.setBackground(Color.WHITE);
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		
		hof = new HallofFame(this.gc);			
		gb = new BoardGui(this.gc);	
		this.add(HOF,hof);
		this.add(BOARD,gb);
	}
	
	public void showCard(String s) {		
		cards.show(this, s);		
	}

	public BoardGui getGb() {
		return gb;
	}

	public HallofFame getHof() {
		return hof;
	}

	

	
	
}
