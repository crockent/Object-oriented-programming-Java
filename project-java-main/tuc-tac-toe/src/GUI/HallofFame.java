package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import Model.Player;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import Controller.GController;

@SuppressWarnings("serial")
public class HallofFame extends GamePanel {

	private BoxLayout bx;
	private JLabel[] labels;
	private Player[] p;
	
	
	
	public HallofFame(GController gc) {
		super(gc);
		bx =new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setLayout(bx);
		setSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBackground(new Color(152,204,238));
		this.p = new Player[10];
		this.labels = new JLabel[10];
		
		for(int i=0; i<10; i++) {
			labels[i] = new JLabel();
			labels[i].setSize(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH -10, (MainWindow.HEIGHT-MainWindow.TOP_HEIGHT)/10);
			labels[i].setEnabled(false);
			labels[i].setFont(new Font(Font.SERIF,Font.ITALIC,35));
			labels[i].setAlignmentX(CENTER_ALIGNMENT);
			labels[i].setAlignmentY(CENTER_ALIGNMENT);
			this.add(labels[i]);
			this.repaint();
		}
		
			
		
		refresh();
		

		
		
	}
	
	public void refresh() {
		p = gc.getPr().findHallOfFame(10);

		if(gc.getPr().getNumOfPlayers()>10) {
			for(int i=0; i<10; i++) {
				labels[i].setText((i+1)+") "+ p[i].nameWithScore());
				this.repaint();
			}
		}else{
		for(int i=0; i<gc.getPr().getNumOfPlayers(); i++) {
			labels[i].setText((i+1)+") "+ p[i].nameWithScore());
			this.repaint();
			}
		}
	}
	
}