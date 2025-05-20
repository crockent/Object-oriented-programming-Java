package Model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Player implements Serializable{
	private String name;
	private int numOfGames,wins,loses,ties;
	private float score;
	private GameRecord[] listOfBestGames,listOfLastGames;

	//constractor with full stats used in some unitTest
	public Player(String n,int w,int l,int t,int num,PlayerRoster pr) {
		name=n;
		wins=w;
		loses=l;
		ties=t;
		numOfGames=num;
		updateScore();
		listOfBestGames=new GameRecord[5];
		listOfLastGames=new GameRecord[5];
		pr.addPlayer(this);
	}
	//constractor
	public Player(PlayerRoster pr,String n) {
		checkTheNameIfTaken(pr,n);
		name=n;
		numOfGames=wins=loses=ties=0;
		score=0f;
		pr.addPlayer(this);
		listOfBestGames=new GameRecord[5];
		listOfLastGames=new GameRecord[5];
	}
	//constractor used in some unitTest
	public Player(PlayerRoster pr) {
		name="";
		numOfGames=wins=loses=ties=0;
		score=0f;
		pr.addPlayer(this);
		listOfBestGames=new GameRecord[5];
		listOfLastGames=new GameRecord[5];
	}
	
	//This update the stats after every game
	public void updateStats(Board b) {
		if(b.getWinner()==b.O && b.getoP().equals(this)) {
			//if this players is O and O wins update wins +1
			wins++;
		}else if(b.getWinner()==b.X && b.getoP().equals(this)) {
			//if this players is O and X wins update loses +1
			loses++;
		}else if(b.getWinner()==b.X && b.getxP().equals(this)) {
			//if this players is X and X wins update wins +1
			wins++;
		}else if(b.getWinner()==b.O && b.getxP().equals(this)) {
			//if this players is X and O wins update loses +1
			loses++;
		}else {
			ties++;
		}
		numOfGames++;
		updateScore();
	}
	
	public void updateScore() {
		score=50*((2f*wins + ties)/numOfGames);
		
	}
	
	//check if the name is taken by other player
	//and check if the name is aproved by the diagrafes tis askisis
	public void checkTheNameIfTaken(PlayerRoster pr,String n) {
		int counter=0;
		n=n.trim();
		for(int i=0;i<pr.getNumOfPlayers();i++) {
			if(n.equals(pr.findPlayerName()[i])) {
				break;
			}else {
				counter++;
			}
		}
		if(n.length()<21 && counter==pr.getNumOfPlayers() && !n.equals("")) {
			name=n;
		}else {
			throw new IllegalArgumentException("name is taken");
		}
	}
	
	public void addInListOfLast(Board b) {
		if(numOfGames<5) {
			listOfLastGames[numOfGames] = new GameRecord(b);
		}else {
			for(int i =0;i<4;i++) {
				listOfLastGames[i]=listOfLastGames[i+1];
			}
			listOfLastGames[4]= new GameRecord(b);
		}	
		addInListOfBest(b);
	}
	
	
	
	public void addInListOfBest(Board b) {
		GameRecord[] temp = new GameRecord[6];
		GameRecord swapTemp;
		
		//check if the list is full
		if(numOfGames<5) {
			listOfBestGames[numOfGames] = listOfLastGames[numOfGames];
		}else {
			for(int i=0;i<5;i++) {
				temp[i]=listOfBestGames[i];
			}
			temp[5] = listOfLastGames[4];
			//if its full we sort from best to worst and check from worst to best to see
			//if the last game is better than the games in list
			//sortListWithBest();
			for(int i=0;i<5;i++) {
				for(int j=i+1;j<6;j++) {
					if((checkIfGameAIsBetterThanB(temp[j], temp[i]))) {
						swapTemp=temp[j];
						temp[j]=temp[i];
						temp[i]=swapTemp;
						//break;
					}
				}
			}
			for(int i=0;i<5;i++) {
				listOfBestGames[i]=temp[i];
			}
		}
			
	}
		
	public boolean checkIfGameAIsBetterThanB(GameRecord a,GameRecord b) {
		boolean toReturn=true;
		if(this.equals(a.getWinner()) && (this.equals(b.getWinner()))){
			if(this.equals(a.getO()) && this.equals(b.getO())) {
				if(a.getScoreX() < b.getScoreX()) {
					toReturn= false;
				}else if(a.getScoreX() == b.getScoreX()) {
					if(a.getDate().isBefore(b.getDate())) {
						toReturn= false;
					}
				}
			}else if(this.equals(a.getO()) && this.equals(b.getX())) {
				if(a.getScoreX() < b.getScoreO()) {
					toReturn= false;
				}else if(a.getScoreX() == b.getScoreO()) {
					if(a.getDate().isBefore(b.getDate())) {
						toReturn= false;
					}
				}
			}else if(this.equals(a.getX()) && this.equals(b.getX())) {
				if(a.getScoreO() < b.getScoreO()) {
					return false;
				}else if(a.getScoreO() == b.getScoreO()) {
					if(a.getDate().isBefore(b.getDate())) {
						toReturn= false;
					}
				}
			}else if(this.equals(a.getX()) && this.equals(b.getO())) {
				if(a.getScoreO() < b.getScoreX()) {
					toReturn= false;
				}else if(a.getScoreO() == b.getScoreX()) {
					if(a.getDate().isBefore(b.getDate())) {
						toReturn= false;
					}
				}
			}
		}else if(this.equals(a.getO()) && this.equals(b.getO())) {
			if(a.getWinnerResult() > b.getWinnerResult()) {
				toReturn= false;			
				}
		}else if(this.equals(a.getX()) && this.equals(b.getX())) {
			if(a.getWinnerResult() < b.getWinnerResult()) {
				toReturn= false;
			}
		}else if(this.equals(a.getO()) && this.equals(b.getX())) {
			if(a.getWinnerResult() > (b.getWinnerResult()*(-1))) {
				toReturn= false;
			}
		}else {//x vs o , best 1 best -1
			if(a.getWinnerResult() < (b.getWinnerResult()*(-1))) {
				toReturn= false;
			}
		}
		return toReturn;
	}
	
	public void sortListWithBest() {
		GameRecord worst=listOfLastGames[0];
		for(int i=1;i<5;i++) {
			if(!(checkIfGameAIsBetterThanB(listOfBestGames[i], worst))) {
				worst=listOfBestGames[i];
			}
		}
	}

	//this return one string with the stats of the player-used in playerPanel
	public String getStats() {
		StringBuilder sb=new StringBuilder("");
		sb.append("Total:").append("\t").append(numOfGames).append("\n").
		append("Wins:").append("\t").append(wins).append("\n").append("Ties:").append("\t").append(ties).append("\n").
		append("Loses:").append("\t").append(loses).append("\n\n").
		append("Last Games: \n");
		if(numOfGames>4) {
			for(int i=0;i<5;i++) {
				sb.append(listOfLastGames[i].getGameInfo()).append("\n");
			}
		}else {
			for(int i=0;i<numOfGames;i++) {
				sb.append(listOfLastGames[i].getGameInfo()).append("\n");
			}
		}
		
		sb.append("\n\nBest Games: \n");
		if(numOfGames>4) {
			for(int i=0;i<5;i++) {
				sb.append(listOfBestGames[i].getGameInfo()).append("\n");
			}
		}else {
			for(int i=0;i<numOfGames;i++) {
				sb.append(listOfBestGames[i].getGameInfo()).append("\n");
			}
		}
		return sb.toString();
	}
	
	public String nameWithScore() {
		String text=name+"    "+String.format("%.2f", score)+" ("+numOfGames+")";
		return text;
	}
	
	//SETTERS GETTERS
	public int getNumOfGames() {
		return numOfGames;
	}

	public void setNumOfGames(int numOfGames) {
		this.numOfGames = numOfGames;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public int getTies() {
		return ties;
	}

	public void setTies(int ties) {
		this.ties = ties;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public GameRecord[] getListOfBestGames() {
		return listOfBestGames;
	}

	public void setListOfBestGames(GameRecord[] listOfBestGames) {
		this.listOfBestGames = listOfBestGames;
	}

	public GameRecord[] getListOfLastGames() {
		return listOfLastGames;
	}

	public void setListOfLastGames(GameRecord[] listOfLastGames) {
		this.listOfLastGames = listOfLastGames;
	}
	
	
	
}
