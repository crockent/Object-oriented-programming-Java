package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Controller.GController;

public class MainWindow extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static public final int WIDTH = 1500;
	static public final int HEIGHT = 900;
	static public final int TOP_HEIGHT = 80;
	static public final int PLAYER_WIDTH = 500;
	
	private PlayerPanel leftPanel;
	private PlayerPanel rightPanel;
	private TopPanel topPanel;
	private MainAreaPanel mainPanel;
	private GController gc;
	
	public MainWindow(GController gc) {
		super("TUC-TAC-TOE");
		this.gc = gc;
		Container c = getContentPane();
		c.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //sets size of main window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //exits out of application
		this.setResizable(false);//prevent frame from being resized
		this.getContentPane().setBackground(new Color(120,50,50));//set background frame color
		ImageIcon image = new ImageIcon("icon.jpg");
		this.setIconImage(image.getImage());// set applications icon
		this.setVisible(true);//make frame visible
		
		
		
		topPanel = new TopPanel(this.gc);
		this.add(topPanel,BorderLayout.PAGE_START);//adds topPanel in the Main window
		
		leftPanel = new PlayerPanel(this.gc,0);
		this.add(leftPanel,BorderLayout.LINE_START);
		
		rightPanel = new PlayerPanel(this.gc,1);
		this.add(rightPanel,BorderLayout.LINE_END);
		
		mainPanel = new MainAreaPanel(this.gc);
		this.add(mainPanel, BorderLayout.CENTER);
		
		this.pack();
		
		
	}
	
	public MainWindow(GController gc,boolean test) {
		super("TUC-TAC-TOE");
		this.gc = gc;
		Container c = getContentPane();
		c.setPreferredSize(new Dimension(WIDTH, HEIGHT)); //sets size of main window
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); //exits out of application
		this.setResizable(false);//prevent frame from being resized
		this.getContentPane().setBackground(new Color(120,50,50));//set background frame color
		ImageIcon image = new ImageIcon("logo.png");
		this.setIconImage(image.getImage());// set applications icon
		this.setVisible(false);//make frame visible
		
		
		
		topPanel = new TopPanel(this.gc);
		this.add(topPanel,BorderLayout.PAGE_START);//adds topPanel in the Main window
		
		leftPanel = new PlayerPanel(this.gc,0);
		this.add(leftPanel,BorderLayout.LINE_START);
		
		rightPanel = new PlayerPanel(this.gc,1);
		this.add(rightPanel,BorderLayout.LINE_END);
		
		mainPanel = new MainAreaPanel(this.gc);
		this.add(mainPanel, BorderLayout.CENTER);
		
		this.pack();
		
		
	}

	public PlayerPanel getLeftPanel() {
		return leftPanel;
	}

	public PlayerPanel getRightPanel() {
		return rightPanel;
	}

	public TopPanel getTopPanel() {
		return topPanel;
	}

	public MainAreaPanel getMainPanel() {
		return mainPanel;
	}

	public GController getGc() {
		return gc;
	}
	
	
	
	
	
	

	

}

