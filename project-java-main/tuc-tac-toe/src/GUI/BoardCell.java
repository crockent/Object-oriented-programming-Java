package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Controller.GController;

@SuppressWarnings("serial")
public class BoardCell extends GamePanel implements MouseListener {

	public static final int CELL_PADDING = 10;
	int row,col;
	public boolean highlighted;
	
	public BoardCell(GController gc,int row,int col) {
		super(gc);
		this.setBackground(new Color(140,178,208));
		this.row=row;
		this.col=col;
		this.addMouseListener(this);
		this.highlighted = false;
		this.setLayout(null);
	}

	

	@Override
	public void mouseClicked(MouseEvent e) {
		if(!(gc.getB().isEnded())) {
			System.out.println("Mouse clicked on"+ this.row + this.col);
			if(getBoard().inPlay()) {
				this.highlighted=false;
				paintComponent(getGraphics());
				getBoard().makeTheMove(row, col);
				gc.showTurn();
				repaint();
			}
		
			
		}
		
		
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!(gc.getB().isEnded())) {
			System.out.println("Mouse entered cell " + this.row + this.col);
			this.highlighted = true;
			repaint();
			// TODO Auto-generated method stub
		}
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(!(gc.getB().isEnded())) {
			System.out.println("Mouse exited on cell"+ this.row + "," + this.col);
			this.highlighted=false;
			repaint();
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		int mark = gc.getB().getBoardMark(this.row, this.col);
		Graphics2D g2d = (Graphics2D) g;
		int size = this .getSize().width - 2* CELL_PADDING;
		g2d.setStroke(new BasicStroke(6));
		if(mark == 0) {
			if(highlighted) {
				g2d.setColor(new Color(255,173,90));
				g2d.fillRect(CELL_PADDING, CELL_PADDING, size-1, size-1);
			}
			return;
		}else if(mark == 1) {
			g2d.setColor(Color.BLACK);
			g2d.drawLine(CELL_PADDING, CELL_PADDING, CELL_PADDING + size , CELL_PADDING+size);
			g2d.drawLine(CELL_PADDING+size, CELL_PADDING, CELL_PADDING, CELL_PADDING+size);
		}else{
			g2d.setColor(Color.RED);
			g2d.drawOval(CELL_PADDING, CELL_PADDING, size, size);
		}
		
		
		
	}



	public boolean isHighlighted() {
		return highlighted;
	}



	public void setHighlighted(boolean highlighted) {
		this.highlighted = highlighted;
	}
	
	
	
	

}
