package tests;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import Controller.GController;
import Model.Board;
import Model.MrBean;
import Model.PlayerRoster;

class MrBeanTest {

	Board b;
	MrBean ai;
	GController c;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new GController();
		c.startWithWindowForTests();
		PlayerRoster pr = new PlayerRoster(c);
		try {
			ai = new MrBean(pr);
		} catch (Exception e) {
			// TODO: handle exception
			ai=(MrBean)pr.findPlayer("Mr.Bean");
		}
		b = new Board(ai,ai,c);
	}

	@Test
	void testIfThisPlayerHasNameMrBean() {
		assertEquals("Mr.Bean",ai.getName());
	}
	
	@Test
	@RepeatedTest(10)
	void testIfAiCanMakeTheMoves() {
		while(!(b.isEnded())) {
			ai.makeTheMove(b);
		}
		assertTrue(b.isEnded());
		for(int i=0;i<3;i++) {
			System.out.println("");
			for(int j=0;j<3;j++) {
				System.out.print(b.getBoard()[i][j]+ "");
			}
		}
		System.out.println("");
		System.out.println(b.getWinner());
		
	}

}
