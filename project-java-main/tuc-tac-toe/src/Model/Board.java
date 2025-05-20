package Model;

import java.io.Serializable;
import java.util.Random;
import Controller.GController;

@SuppressWarnings("unused")
public class Board {
	//constands
	final int TIE=0;
	final int X=1;
	final int O=-1;
	final boolean X_TURN=true;
	final boolean O_TURN=false;
	
	Random r = new Random();
	private int[][] board;
	/* X = -1
	 * O =  1
	 * empty space = 0 
	 */
	private int emptySpaces;
	private int winner;
	
	private boolean isEnded,isColTri,isRowTri,isDiagonalTri;
	private int whereIsTriliza; //in diagonal triliza if 1 the triliza is (00,11,22) if 2 then the triliza is (02,11,20)
	private boolean xTurn;
	private Player oP,xP;
	private MrBean mb;
	private Hal hl;
	private GController c;
	/*if turn = ...
	 * true  = X
	 * false = O
	 */
	private int[][] moveOrder;
	
	//constractor with players(this used to start the game)
	public Board(Player o,Player x,GController c) {
		board = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j]=0;
			}
		}
		emptySpaces=9;
		winner=0;
		isEnded=isColTri=isRowTri=isDiagonalTri=false;
		xTurn=r.nextBoolean();
		moveOrder=new int[9][3];
		this.oP=o;
		this.xP=x;
		this.c=c;
	}
	
	//constractor without players(i don't remember where we used it or if we use it)
	public Board(GController c) {
		board = new int[3][3];
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				board[i][j]=0;
			}
		}
		emptySpaces=9;
		winner=0;
		isEnded=isColTri=isRowTri=isDiagonalTri=false;
		xTurn=r.nextBoolean();
		moveOrder=new int[9][3];
		this.c=c;
	}

	//make 2 board equal
	public void makeEquals(Board b) {
		setBoard(b.getBoard());
		emptySpaces=b.getEmptySpaces();
		winner=b.getWinner();
		isEnded=b.isEnded;
		xTurn=b.xTurn;
		moveOrder=b.getMoveOrder();
	}

	//this run when the game is over. here we end the game we add the game at the both players record,
	//we change the gui and we save the players into the file
	public void endedGame() {
		isEnded=true;
		oP.addInListOfLast(this);
		xP.addInListOfLast(this);
		oP.updateStats(this);
		xP.updateStats(this);
		c.getMw().getTopPanel().getDoneBtn().setEnabled(true);
		c.getPr().savePlayersIntoFile();
	}
	
	//method to check if the cell is valid
	public void checkIfCellIsValid(int row, int col) {
		if(row< 0  || col<0 || row>2 || col>2 ) {
			throw new IndexOutOfBoundsException(row + "," + col + "this is not valid board cell");
		}
	}
	
	//method to check if the the cell is available for move
	public void checkIfCellIsAvailable(int row , int col){
		checkIfCellIsValid(row, col);
		if(board[row][col] == 0) {
			return;
		}else {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	//***************End game********************* 
	//we check every row
	public boolean checkRow() {
		//check the row
		for(int i=0;i<3;i++) {
			if((board[i][0]==board[i][1]) &&(board[i][1]==board[i][2]) && board[i][0]!=0 ) {
				isRowTri=true;
				whereIsTriliza=i;
				winner=board[i][0];
				return true;
			}
		}
		return false;
	}
	
	//we check every column
	public boolean checkColumn()  {
		for(int i=0;i<3;i++) {
			
			if((board[0][i]==board[1][i]) &&(board[1][i]==board[2][i]) && board[0][i]!=0 ) {
				isColTri=true;
				whereIsTriliza=i;
				winner=board[0][i];	
				return true;
			}
		}
		return false;
	}
	//we check both diagones
	public boolean checkDiagonal() {
		//check first diagone
		if((board[0][0]==board[1][1]) && (board[1][1]==board[2][2]) && board[0][0]!=0) {
			winner=board[0][0];
			whereIsTriliza=1;
			isDiagonalTri=true;
			return true;
		}
		//check second diagone
		else if((board[0][2]==board[1][1]) && (board[1][1]==board[2][0]) && board[0][2]!=0) {
			winner=board[0][2];
			whereIsTriliza=2;
			isDiagonalTri=true;
			return true;
		}
	return false;
	}
	
	//we check if the game is over by calling the above methonds or if all cells is full 
	public void checkIfTheGameIsEnded() {
		if(checkColumn()||checkRow()||checkDiagonal()||emptySpaces==0) {
			endedGame();
		}
	}
	
	//we undo the last move, we use this in Hal
	public void undoTheMove(int row,int col) {
		board[row][col] = 0;
		emptySpaces++;
		xTurn = !xTurn;
		//isEnded=false;
		//winner=0;
	}
	
	//turn, with this method we play in the board
	public void makeTheMove(int row,int col) {
		checkIfCellIsAvailable(row, col);
		board[row][col] = getMoveMark();
		emptySpaces--;
		xTurn = !xTurn;
		checkIfTheGameIsEnded();
		c.showTurn();
		c.getTimer().restart();
	}
	
	//and this methond is similar with makeTheMove but this used by Hal or in some UnitTest 
	//The differens is this method don't have gui and don't check if the game is over
	public void makeTheMoveForHal(int row,int col) {
		checkIfCellIsAvailable(row, col);
		board[row][col] = getMoveMark();
		emptySpaces--;
		xTurn = !xTurn;
	}
	
	//this method is the method responsibole for the bots. with this the bots can play
	public void playingMode() {
		if(!isEnded) {
			if(isTurn()) {
				if(xP.getName().equals("Mr.Bean") ) {
					mb=(MrBean)xP;
					mb.makeTheMove(this);
				}else if(xP.getName().equals("Hal")) {
					hl=(Hal)xP;
					hl.playBest(this, true);;
				}
			}else {
				if(oP.getName().equals("Mr.Bean") ) {
					mb=(MrBean)oP;
					mb.makeTheMove(this);
				}else if(oP.getName().equals("Hal")) {
					hl=(Hal)oP;
					hl.playBest(this, false);
				}
			}
			c.getMw().repaint();
		}	
	}

	//this print the board in the console, used in UnitTest
	public void printBoard() {
		for(int i=0;i<3;i++) {
			System.out.println("");
			for(int j=0;j<3;j++) {
				System.out.print(getBoard()[i][j]+ "");
			}
		}
	}
	
	/********************Setters Getters**************************/
	public int getBoardMark(int row, int col) {
		return board[row][col];
	}
	public boolean inPlay() {
		return board != null;
	}
	
	public boolean noPlay() {
		return !inPlay();
	}
	public int getMoveMark() {
		return xTurn? X: O;
	}
	
	public int[][] getBoard() {
		return board;
	}
	public void setBoard(int[][] board) {
		this.board = board;
	}
	public int getEmptySpaces() {
		return emptySpaces;
	}
	public void setEmptySpaces(int emptySpaces) {
		this.emptySpaces = emptySpaces;
	}
	public int getWinner() {
		return winner;
	}
	public void setWinner(int winner) {
		this.winner = winner;
	}
	public boolean isEnded() {
		return isEnded;
	}
	public void setEnded(boolean isEnded) {
		this.isEnded = isEnded;
	}
	public boolean isTurn() {
		return xTurn;
	}
	public void setTurn(boolean turn) {
		this.xTurn = turn;
	}
	
	public int[][] getMoveOrder() {
		return moveOrder;
	} 

	public void setMoveOrder(int[][] moveOrder) {
		this.moveOrder = moveOrder;
	}

	public Player getoP() {
		return oP;
	}

	public void setoP(Player oP) {
		this.oP = oP;
	}

	public Player getxP() {
		return xP;
	}

	public void setxP(Player xP) {
		this.xP = xP;
	}

	public GController getC() {
		return c;
	}

	public void setC(GController c) {
		this.c = c;
	}

	public boolean isColTri() {
		return isColTri;
	}

	public void setColTri(boolean isColTri) {
		this.isColTri = isColTri;
	}

	public boolean isRowTri() {
		return isRowTri;
	}

	public void setRowTri(boolean isRowTri) {
		this.isRowTri = isRowTri;
	}

	public boolean isDiagonalTri() {
		return isDiagonalTri;
	}

	public void setDiagonalTri(boolean isDiagonalTri) {
		this.isDiagonalTri = isDiagonalTri;
	}

	public int getWhereIsTriliza() {
		return whereIsTriliza;
	}

	public void setWhereIsTriliza(int whereIsTriliza) {
		this.whereIsTriliza = whereIsTriliza;
	}
	
	
}
