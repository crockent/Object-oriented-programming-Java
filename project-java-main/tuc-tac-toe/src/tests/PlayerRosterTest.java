package tests;
import static org.junit.jupiter.api.Assertions.*;


import java.io.File;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Controller.GController;
import Model.Board;
import Model.GameRecord;
import Model.Player;
import Model.PlayerRoster;

class PlayerRosterTest {

	PlayerRoster pr;
	Player p,p2;
	GController c ;
	
	@BeforeEach
	void setUp() throws Exception {
		c = new GController();
		c.startWithWindowForTests();
		
		File f = new File("tuctactoe.sar");
		f.delete();
		pr=new PlayerRoster(c);
		
		p=new Player("giannis",3,2,3,8,pr);
		p=new Player("mimis",10,0,2,12,pr);
		p=new Player("test",1,1,2,4,pr);
		p=new Player("test2",3,8,3,14,pr);
		p=new Player("antonis",0,0,0,0,pr);
		p2=new Player("manos",0,0,0,0,pr);
		pr.savePlayersIntoFile();
		
	}

	@Test
	void testReadWriteFiles() {
		PlayerRoster test;
		test = new PlayerRoster(c);
		assertEquals(pr.getListOfPlayers().getSizeOfArray(),test.getListOfPlayers().getSizeOfArray());
		for(int i =0;i<pr.getListOfPlayers().getSizeOfArray();i++) {
			assertEquals(pr.getListOfPlayers().getArray()[i].getName(), test.getListOfPlayers().getArray()[i].getName());
			assertEquals(pr.getListOfPlayers().getArray()[i].getLoses(), test.getListOfPlayers().getArray()[i].getLoses());
			assertEquals(pr.getListOfPlayers().getArray()[i].getNumOfGames(), test.getListOfPlayers().getArray()[i].getNumOfGames());
			assertEquals(pr.getListOfPlayers().getArray()[i].getScore(), test.getListOfPlayers().getArray()[i].getScore());
			assertEquals(pr.getListOfPlayers().getArray()[i].getTies(), test.getListOfPlayers().getArray()[i].getTies());
			assertEquals(pr.getListOfPlayers().getArray()[i].getWins(), test.getListOfPlayers().getArray()[i].getWins());
		}
		
		
	}
	
	
	@Test
	void testSort() {
		pr.fromBestPlayerToWorst();
		p = pr.getListOfPlayers().getArray()[0];
		for(int i =0;i<pr.getNumOfPlayers();i++) {
			System.out.println(pr.getListOfPlayers().getArray()[i].getName());
			System.out.println(pr.getListOfPlayers().getArray()[i].getScore());
		}
		assertEquals("mimis",p.getName());
	}
	
	@Test
	void testFindPlayerByName() {
		p=pr.findPlayer("test");
		assertEquals(50.0, p.getScore());
		assertEquals("test",p.getName());
		assertEquals(1,p.getWins());
		assertEquals(4,p.getNumOfGames());
	}
	
	@Test
	void testTheSortingBestGameToWorst() {
		GameRecord[] games=new GameRecord[6];
		Board b1 = new Board(p,p2,c);
		b1.setTurn(false);
		b1.makeTheMove(0, 0);
		b1.makeTheMove(0, 1);
		b1.makeTheMove(1, 1);
		b1.makeTheMove(1, 0);
		b1.makeTheMove(2, 2);
		assertEquals(1, p.getNumOfGames());
		//assertEquals(b1, p.getListOfLastGames()[0].getBoard());
		
		Board b2 = new Board(p,p2,c);
		b2.setTurn(false);
		b2.makeTheMove(0, 0);
		b2.makeTheMove(0, 1);
		b2.makeTheMove(1, 1);
		b2.makeTheMove(1, 0);
		b2.makeTheMove(2, 2);
		assertEquals(2, p.getNumOfGames());
		//assertEquals(b2, p.getListOfLastGames()[1].getBoard());
		
		assertTrue(p.checkIfGameAIsBetterThanB(p.getListOfLastGames()[0],p.getListOfLastGames()[1]));
		
		Board b3 = new Board(p,p2,c);
		b3.setTurn(true);
		b3.makeTheMove(0, 0);
		b3.makeTheMove(0, 1);
		b3.makeTheMove(1, 1);
		b3.makeTheMove(1, 0);
		b3.makeTheMove(2, 2);
		assertEquals(3, p.getNumOfGames());
		//assertEquals(b3, p.getListOfLastGames()[2].getBoard());
		
		Board b4 = new Board(p,p2,c);
		b4.setTurn(false);
		b4.makeTheMove(0, 0);
		b4.makeTheMove(0, 1);
		b4.makeTheMove(1, 1);
		b4.makeTheMove(1, 0);
		b4.makeTheMove(2, 2);
		assertEquals(4, p.getNumOfGames());
		//assertEquals(b4, p.getListOfLastGames()[3].getBoard());
		
		Board b5 = new Board(p,p2,c);
		b5.setTurn(false);
		b5.makeTheMove(0, 0);
		b5.makeTheMove(0, 1);
		b5.makeTheMove(1, 1);
		b5.makeTheMove(1, 0);
		b5.makeTheMove(2, 2);
		assertEquals(5, p.getNumOfGames());
		//assertEquals(b5, p.getListOfLastGames()[4].getBoard());
		
		for(int i=0;i<5;i++) {
			games[i]=p.getListOfLastGames()[i];
		}
		
		assertEquals(games[0], p.getListOfBestGames()[0]);
		assertEquals(games[1], p.getListOfBestGames()[1]);
		assertEquals(games[2], p.getListOfBestGames()[2]);
		assertEquals(games[3], p.getListOfBestGames()[3]);
		assertEquals(games[4], p.getListOfBestGames()[4]);
		
		Board b6 = new Board(p,p2,c);
		b6.setTurn(false);
		b6.makeTheMove(0, 0);
		b6.makeTheMove(0, 1);
		b6.makeTheMove(1, 1);
		b6.makeTheMove(1, 0);
		b6.makeTheMove(2, 2);
		assertEquals(6, p.getNumOfGames());
		
		games[5]=p.getListOfLastGames()[4];
		
		assertEquals(games[1], p.getListOfLastGames()[0]);
		assertEquals(games[2], p.getListOfLastGames()[1]);
		assertEquals(games[3], p.getListOfLastGames()[2]);
		assertEquals(games[4], p.getListOfLastGames()[3]);
		assertEquals(games[5], p.getListOfLastGames()[4]);
		
		assertEquals(games[3], p.getListOfBestGames()[0]);
		assertEquals(games[4], p.getListOfBestGames()[1]);
		assertEquals(games[5], p.getListOfBestGames()[2]);
		assertEquals(games[1], p.getListOfBestGames()[3]);
		assertEquals(games[0], p.getListOfBestGames()[4]);
		System.out.println(p.nameWithScore());
		System.out.println(p2.nameWithScore());
		
		
	}
	

}
