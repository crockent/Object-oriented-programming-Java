package tests;

import static org.junit.jupiter.api.Assertions.*;

import Model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.GController;

class BoardTest {

	Board b;
	Player p1,p2;
	PlayerRoster pr;
	GController c;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new GController();
		c.startWithWindowForTests();
		pr = new PlayerRoster(c);
		p1 = new Player(pr);
		p2 = new Player(pr);
		b = new Board(p1,p2,c);
		
	}
	
	@Test
	void testIfTheNewBoardIsEmpty() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				assertEquals(0,b.getBoard()[i][j]);
			}
		}
		assertEquals(9, b.getEmptySpaces());
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				System.out.print(b.getBoard()[i][j]);
			}
			System.out.println();
		}
	}
	
	@Test
	void testMakeTheMove() {
		int emptyspaces=9;
		assertEquals(emptyspaces, b.getEmptySpaces());
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				b.makeTheMove(j, i);
				assertNotEquals(0, b.getBoard()[j][i]);
				emptyspaces--;
				assertEquals(emptyspaces, b.getEmptySpaces());
				
			}
		}
	}
	
	@Test
	void testIsTheGameEndedWithOnePlayerWin() {
		b.makeTheMove(0, 0);
		for(int i=1;i<3;i++) {
			b.makeTheMove(i, 1);
			b.makeTheMove(i, 0);
		}
		assertTrue(b.isEnded());
		assertNotEquals(0, b.getWinner());
		
	}
	
	
	@Test
	void testIsTheGameEndedTie() {
		b.makeTheMove(1, 1);
		b.makeTheMove(0, 0);
		b.makeTheMove(2, 2);
		b.makeTheMove(2, 0);
		b.makeTheMove(1, 0);
		b.makeTheMove(1, 2);
		b.makeTheMove(0, 1);
		b.makeTheMove(2, 1);
		b.makeTheMove(0, 2);
		assertTrue(b.isEnded());
		assertEquals(0, b.getWinner());
	}
	
	@Test
	void testTheStatsAfterGame(){
		for(int i=0;i<10;i++) {
			b = new Board(p1,p2,c);
			testIsTheGameEndedWithOnePlayerWin();
		}
		System.out.println(p1.getWins()+"/"+p1.getTies()+"/"+p1.getLoses()+" score: "+p1.getScore());
		assertEquals(10, p1.getNumOfGames());
		assertEquals(10, p2.getNumOfGames());
	}

}
