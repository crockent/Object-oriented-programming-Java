package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.util.Arrays;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Controller.GController;

@SuppressWarnings("serial")
public class PlayerPanel extends GamePanel {
	
	private JButton selectPlayerBtn;
	int pos;
	private String currentPlayer;
	private JTextField plName;
	private JLabel xoIcon;
	private JTextArea playerStats;
	
	
	public PlayerPanel(GController gc, int pos) {
		super(gc);
		this.pos=pos;
		BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		this.setBackground(new Color(72,164,164));
	
		
		selectPlayerBtn = new JButton("Choose Player");
		selectPlayerBtn.setPreferredSize(new Dimension(150,40));
		selectPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn.addActionListener((e)->{changePlayer(this.pos);});
		this.add(selectPlayerBtn);
		
		
		plName = new JTextField();
		plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,40));
		plName.setAlignmentX(CENTER_ALIGNMENT);
		plName.setFont(new Font("MV Boli", Font.BOLD, 20));
		plName.setBackground(new Color(202,115,98));
		plName.setHorizontalAlignment(JTextField.CENTER);
		plName.setEnabled(false);
		this.add(plName);
		
		
		xoIcon = new JLabel();
		if(this.pos ==0) {
			ImageIcon x = new ImageIcon("X.png");
			
			xoIcon.setIcon(x);
			xoIcon.setBounds(300,100,100,100);
			this.add(xoIcon);
		}
		else if(this.pos==1) {
			ImageIcon o = new ImageIcon("0.png");
			xoIcon.setIcon(o);
			xoIcon.setBounds(300,100,100,100);
			this.add(xoIcon);
		}
		
		playerStats = new JTextArea(6,2);
		playerStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		playerStats.setAlignmentX(CENTER_ALIGNMENT);
		playerStats.setBackground(new Color(86,168,151));
		playerStats.setEnabled(true);
		playerStats.setEditable(false);
		playerStats.setVisible(true);
		playerStats.setForeground(Color.black);
		playerStats.setFont(new Font(Font.SERIF,Font.ITALIC,20));
		playerStats.setMargin(new Insets(5,5,5,5));
		playerStats.setVisible(true);
		this.add(playerStats);
	}
	
	
		public void changePlayer(int pos) {
			String[] allPlayers = gc.getPr().findPlayerName();
			Arrays.sort(allPlayers);
			
			String selectName = (String) JOptionPane.showInputDialog(this, 
					"Choose a Player...",
					"Player selection",
					JOptionPane.PLAIN_MESSAGE,
					null,
					allPlayers,
					currentPlayer
					);
			
			if(selectName != null) {
				if (selectName.equals(gc.getInGamePlayers()[pos==0?1:0])) {
					JOptionPane.showMessageDialog(gc.getMw(), 						
							"Player already selected",
							"Ooops...",
							JOptionPane.ERROR_MESSAGE);
					return;
				}	
				
				this.currentPlayer=selectName;	
				gc.selectPlayer(selectName,pos);
				this.plName.setText(currentPlayer);
				this.setPlayerStats(gc.getPr().findPlayer(currentPlayer).getStats());
				this.repaint();
			}
			
			
		}
		
		public void afterGame() {
			selectPlayerBtn.setEnabled(true);
			playerStats.setText(gc.getPr().findPlayer(currentPlayer).getStats());
	
		}
		
		public void setPlayerStats(String stats) {
			this.playerStats.setText(stats);
		}
		



		public GController getGc() {
			return gc;
		}


		public JButton getSelectPlayerBtn() {
			return selectPlayerBtn;
		}



		public int getPos() {
			return pos;
		}


		public String getCurrentPlayer() {
			return currentPlayer;
		}


		public JTextField getPlName() {
			return plName;
		}


		public JLabel getXoIcon() {
			return xoIcon;
		}


		public void setXoIcon(JLabel xoIcon) {
			this.xoIcon = xoIcon;
		}
		

	


		
		
		
	
	
}
