package Model;

@SuppressWarnings("serial")
public class Hal extends Player {
	boolean isX;
	
	/*
	 * X want to max
	 * O want to min
	 */
	
	public Hal(PlayerRoster pr) {
		super(pr,"Hal");
	}


	public static int miniMax(Board board, int depth, boolean isMax, int alpha, int beta) {
        int boardVal = checkIfEnded(board.getBoard())*10;

        //If the game is end this return the value of this sincouense(diadromis)
        if (Math.abs(boardVal) == 10 || depth == 0 || board.getEmptySpaces()==0) {
        	if(isMax) {
                return boardVal-depth;
        	}else {
                return boardVal+depth;
        	}

        }

        //Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < 3; row++) {
                    if (board.getBoard()[col][row]==0) {
                        board.makeTheMoveForHal(col,row);
                        highestVal = Math.max(highestVal, miniMax(board, depth - 1, false,alpha,beta));
                        board.undoTheMove(col,row);
                        alpha = Math.max(alpha, highestVal);
                        if (alpha >= beta) {
                            return highestVal;
                        }
                    }
                }
            }
            return highestVal;
        // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int col = 0; col < 3; col++) {
                for (int row = 0; row < 3; row++) {
                    if (board.getBoard()[col][row]==0) {
                        board.makeTheMoveForHal(col,row);
                        lowestVal = Math.min(lowestVal, miniMax(board, depth - 1, true,alpha,beta));
                        board.undoTheMove(col,row);
                        beta = Math.min(beta, lowestVal);
                        if (beta <= alpha) {
                            return lowestVal;
                        }
                    }
                }
            }
            return lowestVal;
        }
    }

	//this call the minimax and try to find the bestMove
    public static int[] getBestMove(Board board,boolean isX) {
    	int bestValue;
        int[] bestMove = new int[]{-1, -1};
        if(isX) {
        	bestValue = Integer.MIN_VALUE;
        }else {
        	bestValue = Integer.MAX_VALUE;
        }
        

        for (int col = 0; col < 3; col++) {
            for (int row = 0; row < 3; row++) {
                if (board.getBoard()[col][row]==0) {
                    board.makeTheMoveForHal(col,row);
                    int moveValue = miniMax(board, 9, !isX,Integer.MIN_VALUE,Integer.MAX_VALUE);
                    board.undoTheMove(col,row);
                    if(isX) {
                    	if (moveValue > bestValue) {
                            bestMove[0] = col;
                            bestMove[1] = row;
                            bestValue = moveValue;
                        }
                    }else {
                    	if (moveValue < bestValue) {
                            bestMove[0] = col;
                            bestMove[1] = row;
                            bestValue = moveValue;
                        }
                    }
                    
                }
            }
        }
        return bestMove;
    }
	
    //this is the method that play the best move on the board
    public void playBest(Board board,boolean isX) {
    	int[] best= new int[2];
    	best=getBestMove(board,isX);
    	board.makeTheMove(best[0], best[1]);
    }
	
    //because Hal use makeTheMoveForHal we don't check if game is ended so we recreate the chekfIfEnded here
    //without the gui
    public static int checkIfEnded(int[][] board) {
		int winner=0;
		for(int i=0;i<3;i++) {
			if((board[i][0]==board[i][1]) &&(board[i][1]==board[i][2]) && board[i][0]!=0 ) {
				winner=board[i][0];
				break;
			}
		}
		for(int i=0;i<3;i++) {
			if((board[0][i]==board[1][i]) &&(board[1][i]==board[2][i]) && board[0][i]!=0 ) {
				winner=board[0][i];	
				break;
			}

		}
		if((board[0][0]==board[1][1]) && (board[1][1]==board[2][2]) && board[0][0]!=0) {
			winner=board[0][0];
		}
		else if((board[0][2]==board[1][1]) && (board[1][1]==board[2][0]) && board[0][2]!=0) {
			winner=board[0][2];
		}
		return winner;
	}
	
}
