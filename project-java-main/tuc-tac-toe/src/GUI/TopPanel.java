package GUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import Controller.GController;

@SuppressWarnings("serial")
public class TopPanel extends GamePanel {
	private JButton quitBtn;
	private JButton addPlayer;
	private JButton doneBtn;
	private JButton readyBtn;
	
	public TopPanel(GController gc) {
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setBackground(new Color(51,117,120));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		quitBtn = new JButton("Quit App");
		quitBtn.setPreferredSize(new Dimension(100,40));
		quitBtn.addActionListener((e)->{gc.quit();});
		
		addPlayer = new JButton("Add Player");
		addPlayer.setPreferredSize(new Dimension(100, 40));
		addPlayer.setEnabled(true);
		addPlayer.addActionListener((e)->{gc.addPlayer();});
		
		readyBtn = new JButton("Start Game");
		readyBtn.setPreferredSize(new Dimension(100,40));
		readyBtn.setEnabled(false);
		readyBtn.addActionListener((e) -> {gc.startGame();});
		
		doneBtn = new JButton("Done");		
		doneBtn.setPreferredSize(new Dimension(100, 40));		
		doneBtn.setEnabled(false);
		doneBtn.addActionListener((e) ->{gc.endOfGame();});
		
		this.add(addPlayer);
		this.add(doneBtn);
		this.add(quitBtn);
		this.add(readyBtn);
		
	}
	
	public void afterGame() {
		addPlayer.setEnabled(true);
		readyBtn.setEnabled(true);
		doneBtn.setEnabled(false);
	}
	
	public String addPlayer() {
		String name;
		name=JOptionPane.showInputDialog("Add new player");
		return name;
	}
	
	public JButton getQuitBtn() {
		return quitBtn;
	}
	
	public JButton getReadyBtn() {
		return readyBtn;
	}

	public JButton getAddPlayer() {
		return addPlayer;
	}

	public JButton getDoneBtn() {
		return doneBtn;
	}

	
	
}

