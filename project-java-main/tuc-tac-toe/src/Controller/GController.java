package Controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.Timer;

import GUI.GamePanel;
import GUI.MainAreaPanel;
import GUI.MainWindow;
import Model.*;



public class GController extends WindowAdapter {
	MainWindow mw;
	PlayerRoster pr;
	Board b;
	Player p;
	GameRecord gr;
	MrBean mb;
	Hal hl;
	GamePanel gp;
	Timer timer= new Timer(1000,(event)->b.playingMode());
	
	String[] inGamePlayers = new String[2];
	Player[] players = new Player[2];
	int botX,botO;
	
	
	public GController() {
		
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	public void start() {
		this.pr = new PlayerRoster(this);
		try {
			this.mb = new MrBean(pr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			this.hl = new Hal(pr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.mw = new MainWindow(this);
		this.mw.addWindowListener(this);
		this.mw.setVisible(true);
		
	}
	
	public void startWithWindowForTests() {
		this.pr = new PlayerRoster(this);
		try {
			this.mb = new MrBean(pr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			this.hl = new Hal(pr);
		} catch (Exception e) {
			// TODO: handle exception
		}
		this.mw = new MainWindow(this,true);
		
		
	}
	
	public void quit() {	
		pr.savePlayersIntoFile();
		System.out.print("Antios");
		System.exit(0);
	}
	
	
	
	
	public void selectPlayer(String playerName,int pos) {
		selectInGamePlayers(playerName, pos);
		System.out.println("Player "+ pos + " " +playerName);
		this.mw.getTopPanel().getReadyBtn().setEnabled(gameReady());
	}
	
	public void startGame() {
		for(int i=0;i<2;i++) {
			players[i]=pr.findPlayer(inGamePlayers[i]);
		}
		this.b = new Board(players[1],players[0],this);
		this.mw.getTopPanel().getReadyBtn().setEnabled(false);
		this.mw.getMainPanel().showCard(MainAreaPanel.BOARD);
		this.mw.getTopPanel().getAddPlayer().setEnabled(false);
		this.mw.getLeftPanel().getSelectPlayerBtn().setEnabled(false);
		this.mw.getRightPanel().getSelectPlayerBtn().setEnabled(false);
		showTurn();
		
		timer.restart();
		
	}
	
	public void showTurn() {
		int mark = this.getB().getMoveMark();
		if(mark == 1) {
			this.getMw().getMainPanel().getGb().getTurn().setText("X Turn");
		}
		else if (mark ==-1) {
			this.getMw().getMainPanel().getGb().getTurn().setText("O Turn");
		}
		if(b.getWinner()==1) {
			this.getMw().getMainPanel().getGb().getTurn().setText("X Won");
		}
		else if(b.getWinner()==-1) {
			this.getMw().getMainPanel().getGb().getTurn().setText("O Won");
		}
		
	}

	public void addPlayer() {
		
		String name = this.mw.getTopPanel().addPlayer();
		this.p = new Player(this.pr,name);
		//this.p.checkTheNameIfTaken(this.pr, name);
		//p.setName(name);
		this.mw.getMainPanel().getHof().refresh();
		pr.savePlayersIntoFile();
	}
	
	public void selectInGamePlayers(String name, int pos) {
		if(pos<0 && pos>1) {
			return;
		}
		inGamePlayers[pos]=name;
	}
	
	public boolean gameReady() {
		return (inGamePlayers[0] != null && inGamePlayers[1] != null);
	}
	
	public void endOfGame() {

		
		this.mw.getTopPanel().afterGame();
		this.mw.getRightPanel().afterGame();
		this.mw.getLeftPanel().afterGame();
		this.mw.getMainPanel().getHof().refresh();
		this.mw.getMainPanel().showCard(MainAreaPanel.HOF);
		this.mw.getMainPanel().getGb().getCell().setHighlighted(false);
		
	}

	public MainWindow getMw() {
		return mw;
	}

	public PlayerRoster getPr() {
		return pr;
	}

	public Board getB() {
		return b;
	}

	public Player getP() {
		return p;
	}

	public GameRecord getGr() {
		return gr;
	}

	public String[] getInGamePlayers() {
		return inGamePlayers;
	}

	public Timer getTimer() {
		return timer;
	}

	public void setTimer(Timer timer) {
		this.timer = timer;
	}

	public void setB(Board b) {
		this.b = b;
	}
	
	
	
	
	
	
	
}
