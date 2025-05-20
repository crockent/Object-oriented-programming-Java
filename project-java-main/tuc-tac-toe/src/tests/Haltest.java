package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import Controller.GController;
import Model.*;

class Haltest {
	Board b;
	MrBean mr;
	Hal hl;
	GController c;

	@BeforeEach
	void setUp() throws Exception {
		c = new GController();
		c.startWithWindowForTests();
		PlayerRoster pr = new PlayerRoster(c);
		try {
			mr = new MrBean(pr);
		} catch (Exception e) {
			// TODO: handle exception
			mr=(MrBean)pr.findPlayer("Mr.Bean");
		}
		
		try {
			hl = new Hal(pr);
		} catch (Exception e) {
			// TODO: handle exception
			hl=(Hal)pr.findPlayer("Hal");
		}
		
		
	}
	
	

	@Test
	void testFindTheBestMoveForX() {

		b = new Board(mr,hl,c);
		c.setB(b);
		b.setTurn(true);
		b.makeTheMoveForHal(0, 0);
		b.makeTheMoveForHal(1, 1);
		b.makeTheMoveForHal(1, 0);
		b.makeTheMoveForHal(0, 1);
		b.printBoard();
		System.out.println("");
		hl.playBest(b,true);
		b.printBoard();
		assertEquals(1, b.getBoard()[2][0]);
		
		
	}
	
	@Test
	void testFindTheBestMoveForXSecondBoard() {

		b = new Board(mr,hl,c);
		c.setB(b);
		b.setTurn(true);
		b.makeTheMoveForHal(0, 0);
		b.makeTheMoveForHal(1, 0);
		b.makeTheMoveForHal(1, 1);
		b.makeTheMoveForHal(0, 1);
		b.printBoard();
		System.out.println("");
		hl.playBest(b,true);
		b.printBoard();
		assertEquals(1, b.getBoard()[2][2]);
		
		
	}
	
	@Test
	void testFindTheBestMoveForO() {
		b = new Board(hl,mr,c);
		c.setB(b);
		b.setTurn(false);
		b.makeTheMoveForHal(0, 0);
		b.makeTheMoveForHal(1, 1);
		b.makeTheMoveForHal(1, 0);
		b.makeTheMoveForHal(0, 1);
		b.printBoard();
		System.out.println("");
		hl.playBest(b,false);
		b.printBoard();
		assertEquals(-1, b.getBoard()[2][0]);
		
	}

	@RepeatedTest(500)
	void testIfIsAbetableAsO() {
		b = new Board(mr,hl,c);
		c.setB(b);
		while(!(b.isEnded())) {
			if(b.isTurn()) {
				hl.playBest(b,true);
			}else {
				mr.makeTheMove(b);
				
			}
		}
		b.printBoard();
		System.out.println("\n"+"test for O winner: "+b.getWinner());
		assertNotEquals(-1, b.getWinner());;
	}
	
	@RepeatedTest(500)
	void testIfIsAbetableAsX() {
		b = new Board(hl,mr,c);
		c.setB(b);
		while(!(b.isEnded())) {
			if(b.isTurn()) {
				mr.makeTheMove(b);
			}else {
				hl.playBest(b,false);
			}
		}
		b.printBoard();
		System.out.println("\n"+"test for X winner: "+b.getWinner());
		assertNotEquals(1, b.getWinner());;
	}
	

}
