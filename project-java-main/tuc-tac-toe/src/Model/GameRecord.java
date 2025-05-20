package Model;

import java.io.Serializable;
import java.time.LocalDate;


@SuppressWarnings("serial")
public class GameRecord implements Serializable {
	private Player x,o;
	private Player winner=null;
	private int winnerResult;
	private float scoreX,scoreO;
	private LocalDate date;
	
	//without date this option is to use for when the game is over
	public GameRecord(Board b) {
		this.x=b.getxP();
		this.o=b.getoP();
		scoreX=b.getxP().getScore();
		scoreO=b.getoP().getScore();
		date=LocalDate.now();
		winnerResult=b.getWinner();
		if(b.getWinner()==1) {
			winner=b.getxP();
		}else if(b.getWinner()==-1) {
			winner=b.getoP();
		}
		
	}
	/*
	public GameRecord(Board b,LocalDate d) {
		this.x=b.getxP();
		this.o=b.getoP();
		scoreX=b.getxP().getScore();
		scoreO=b.getoP().getScore();
		date=d;
		if(b.getWinner()==1) {
			winner=b.getxP();
		}else if(b.getWinner()==-1) {
			winner=b.getoP();
		}
	}
	*/
	//this return one sting with the info of the games-used in player panels
	public String getGameInfo() {
		StringBuilder sb=new StringBuilder("");
		sb.append(x.getName()).append("(").append(scoreX).append(")  vs  ").append(o.getName()).append("(").append(scoreO).append(") (");
		if(winner!=null) {
			sb.append("Winner: ").append(winner.getName()).append(")");
		}else {
			sb.append("Tie)");
		}
		return sb.toString();
	}

	/****Getters/Setters****/
	public Player getX() {
		return x;
	}

	public void setX(Player x) {
		this.x = x;
	}

	public Player getO() {
		return o;
	}

	public void setO(Player o) {
		this.o = o;
	}

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}

	public float getScoreX() {
		return scoreX;
	}

	public void setScoreX(float scoreX) {
		this.scoreX = scoreX;
	}

	public float getScoreO() {
		return scoreO;
	}

	public void setScoreO(float scoreO) {
		this.scoreO = scoreO;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getWinnerResult() {
		return winnerResult;
	}

	public void setWinnerResult(int winnerResult) {
		this.winnerResult = winnerResult;
	}
	
	

	
	
}
