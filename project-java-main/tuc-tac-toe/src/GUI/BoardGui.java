package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import Controller.GController;

@SuppressWarnings("serial")
public class BoardGui extends GamePanel{
	
	private BoardCell[][] cells;
	private BoardCell cell;
	private JLabel turn;
	
	public BoardGui(GController gc) {
		super(gc);
		
		this.setLayout(null);
		setSize(new Dimension(MainWindow.WIDTH-2*MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBackground(new Color(140,178,208));
		this.cells = new BoardCell[3][3];
		
		
		//creates every cell and gives dimensions
		for(int row=0; row<3; row++) { 
			for(int col=0; col<3; col++) {
				cell = new BoardCell(gc, row, col);
				cell.setBounds(boardZero().x+col*cellSize()+BoardCell.CELL_PADDING,
							boardZero().y+row*cellSize()+BoardCell.CELL_PADDING,
							cellSize()-2*BoardCell.CELL_PADDING,
							cellSize()-2*BoardCell.CELL_PADDING);
				this.add(cell);
				this.cells[row][col] = cell;
			}
		}
		
		turn = new JLabel();
		turn.setBounds(100,0,300,50);
		turn.setEnabled(false);
		turn.setBackground(Color.black);
		turn.setAlignmentX(CENTER_ALIGNMENT);
		turn.setAlignmentY(TOP_ALIGNMENT);
		turn.setHorizontalAlignment(SwingConstants.CENTER);
		turn.setFont(new Font("MV Boli",Font.PLAIN, 40));
		turn.setForeground(new Color(255,0,0));
		//turn.setText("Let the best Win");
		turn.setOpaque(true); // display background color
		this.add(turn);
		
	}
	
	private int cellSize() {
		int minSide = Integer.min(this.getWidth(),this.getHeight());
		return minSide/5;
		
	}
	
	private int boardSize() {
		
		return 3*cellSize();
	}
	
	private Point boardZero() {
		int x = (this.getWidth() - this.boardSize())/2;
		int y = (this.getHeight() - this.boardSize())/2;
		return new Point(x,y);
	}
	
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		this.drawGrid(g);
	}
	private void drawGrid(Graphics g) {
		Graphics2D g2d = (Graphics2D)g;
		g2d.setStroke(new BasicStroke(10));
		
		for(int i=1; i<3; i++) {
			//draw vertical line
			g2d.drawLine(boardZero().x +i*cellSize(), boardZero().y, boardZero().x+i*cellSize(), boardZero().y+boardSize());
			
			
			//draw horizontal line
			g2d.drawLine(boardZero().x, boardZero().y+i*cellSize(), boardZero().x+boardSize(), boardZero().y+i*cellSize());
		}
		
	}
	
	private void paintWinningLine(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		gc.getB().checkColumn();
		gc.getB().checkRow();
		gc.getB().checkDiagonal();
		
		if(gc.getB().isColTri()) {
			
			
			
		}
		else if(gc.getB().isRowTri()) {
			
			
			
			
		}
		else if(gc.getB().isDiagonalTri()) {
			
		}
		
		
		
	}
	
	public void endGame() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				cells[i][j].setEnabled(false);
			}
		}
	}

	public JLabel getTurn() {
		return turn;
	}

	public BoardCell[][] getCells() {
		return cells;
	}

	public BoardCell getCell() {
		return cell;
	}
	
	
	
	
	

	
	
	
	

	
}