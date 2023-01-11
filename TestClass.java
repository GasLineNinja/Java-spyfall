import java.util.ArrayList;

import junit.framework.TestCase;

public class TestClass extends TestCase{

	
	String[] skiLodge = {"Ski Patrol", "Lift Technician", "Tourist", "Ski Instructor", "Owner"};
	String[] bank = {"Teller", "Robber", "Customer", "Custodian", "Owner"};
	String[] beach = {"Surfer", "Lifeguard", "Tourist", "Fisher", "Park Ranger"};
	ArrayList<String> skiLodgeList;
	ArrayList<String> bankList;
	ArrayList<String> beachList;
	
	
	Spyfall test1, test2;
	ArrayList<Player> empty = new ArrayList<Player>();
	ArrayList<Player> fullPlayer = new ArrayList<Player>();
	ArrayList<Player> justSally = new ArrayList<Player>();
	
	Player sally = new Player("sally");
	Player joe = new Player("joe");
	Player peotr = new Player("peotr");
	Player juanita = new Player("juanita");
	
	Role spy = new Role();
	
	
	
	public void setUp(){
		
		
		test1 = new Spyfall();
		test2 = new Spyfall();
		
		justSally.add(sally);
		
		fullPlayer.add(sally);
		fullPlayer.add(joe);
		fullPlayer.add(peotr);
		fullPlayer.add(juanita);
		
		skiLodgeList = new ArrayList<String>();
		for(String s : skiLodge)
			skiLodgeList.add(s);
		bankList = new ArrayList<String>();
		for(String s : bank)
			bankList.add(s);
		beachList = new ArrayList<String>();
		for(String s : beach)
			beachList.add(s);
		
	}
	
	
	
	//testing constructor
	public void test01() {
		Spyfall newGame = new Spyfall();
		
		assertEquals("The game has not started yet", newGame.toString());
	}
	
	//testing containsLocation
	public void test02() {
		Spyfall newGame = new Spyfall();
		
		assertTrue(newGame.containsLocation("Bank"));
		assertTrue(newGame.containsLocation("Ski Lodge"));
		assertTrue(newGame.containsLocation("Beach"));
		assertFalse(newGame.containsLocation(""));
		assertFalse(newGame.containsLocation("Teller"));
	}
	
	//testing containsPlayer
	public void test03() {
		Spyfall newGame = new Spyfall();
		
		assertFalse(newGame.containsPlayer(sally));
		assertFalse(newGame.containsPlayer(joe));
		assertFalse(newGame.containsPlayer(peotr));
		assertFalse(newGame.containsPlayer(juanita));
	}
	
	
	//testing startGame
	public void test04() {
		
		boolean caught = false;
		try {
			test1.startGame(empty);
		}catch(IllegalArgumentException e){
			caught = true;
		}
		assertTrue(caught);
		
		caught = false;
		try {
		test1.startGame(null);
		}catch(IllegalArgumentException e){
			caught = true;
		}
		assertTrue(caught);
	}

	public void test05() {
		test1.startGame(justSally);
		
		assertTrue(test1.containsPlayer(sally));
		assertTrue(test1.containsPlayer(new Player("sally")));
		assertFalse(test1.containsPlayer(joe));
	}

	public void test06() {
		test1.startGame(fullPlayer);
		
		assertTrue(test1.containsPlayer(sally));
		assertTrue(test1.containsPlayer(joe));
		assertTrue(test1.containsPlayer(peotr));
		assertTrue(test1.containsPlayer(juanita));
	}
	
	//testing resetGame
	public void test07() {
		test1.startGame(fullPlayer);
		test1.resetGame();
		
		assertFalse(test1.containsPlayer(sally));
		assertFalse(test1.containsPlayer(joe));
		assertFalse(test1.containsPlayer(peotr));
		assertFalse(test1.containsPlayer(juanita));
		assertTrue(test1.containsLocation("Bank"));
		assertTrue(test1.containsLocation("Ski Lodge"));
		assertTrue(test1.containsLocation("Beach"));
		assertEquals("The game has not started yet", test1.toString());
	}
	
	
	//testing assignSpy
	public void test08() {
		test1.assignSpy(fullPlayer);
		
		int count = 0;
		for(Player p : fullPlayer)
			if(test1.containsPlayer(p))
				count++;
		assertEquals(1, count);
		
		test2.assignSpy(justSally);
		
		assertTrue(test2.containsPlayer(sally));
	}
	
	//testing assignNormalRoles
	public void test09() {
		
		
		test1.assignNormalRoles(fullPlayer);
		assertTrue(test1.containsPlayer(sally));
		assertTrue(test1.containsPlayer(joe));
		assertTrue(test1.containsPlayer(peotr));
		assertTrue(test1.containsPlayer(juanita));
	
		test2.assignSpy(fullPlayer);
		
		test2.assignNormalRoles(fullPlayer);

		assertTrue(test1.containsPlayer(sally));
		assertTrue(test1.containsPlayer(joe));
		assertTrue(test1.containsPlayer(peotr));
		assertTrue(test1.containsPlayer(juanita));
		
	}
	
	//testing getRole
	public void test10() {
		test1.assignSpy(justSally);
		assertEquals("This person is the spy!", test1.getRole(sally).toString());
	}

	public void test11() {
		test1.assignSpy(justSally);
		test1.assignNormalRoles(fullPlayer);
		assertEquals("This person is the spy!", test1.getRole(sally).toString());
		String joeString = test1.getRole(joe).toString();
		String peotrString = test1.getRole(peotr).toString();
		String juanitaString = test1.getRole(juanita).toString();
		assertNotSame(joeString, peotrString);
		assertNotSame(joeString, juanitaString);
		assertNotSame(juanitaString, peotrString);
		//locations should be the same
		assertEquals(joeString.substring(joeString.indexOf(" at a ")), peotrString.substring(peotrString.indexOf(" at a ")));
		assertEquals(joeString.substring(joeString.indexOf(" at a ")), juanitaString.substring(juanitaString.indexOf(" at a ")));
		assertEquals(juanitaString.substring(juanitaString.indexOf(" at a ")), peotrString.substring(peotrString.indexOf(" at a ")));
	}
	


	//testing checkIfSpy
	public void test12() {
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(joe));
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(sally));
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(peotr));
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(juanita));
		
		assertEquals(0, sally.getWins());
		assertEquals(0, joe.getWins());
		assertEquals(0, peotr.getWins());
		assertEquals(0, juanita.getWins());
	}

	public void test13() {
		test1.assignSpy(justSally);
		assertEquals("The Non-Spies Win!", test1.checkIfSpy(sally));
		assertEquals(0, sally.getWins());
	}
	
	public void test14() {
		test1.assignSpy(justSally);
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Non-Spies Win!", test1.checkIfSpy(sally));
		assertEquals(0, sally.getWins());
		assertEquals(1, joe.getWins());
		assertEquals(1, peotr.getWins());
		assertEquals(1, juanita.getWins());
	}
	
	public void test15() {
		test1.assignSpy(justSally);
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(joe));
		assertEquals(1, sally.getWins());
		assertEquals(0, joe.getWins());
		assertEquals(0, peotr.getWins());
		assertEquals(0, juanita.getWins());
	}
	
	public void test16() {
		test1.assignSpy(justSally);
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(joe));
		test1.assignSpy(justSally);
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Spy Wins!", test1.checkIfSpy(peotr));
		test1.assignSpy(justSally);
		test1.assignNormalRoles(fullPlayer);
		assertEquals("The Non-Spies Win!", test1.checkIfSpy(sally));
		assertEquals(2, sally.getWins());
		assertEquals(1, joe.getWins());
		assertEquals(1, peotr.getWins());
		assertEquals(1, juanita.getWins());
	}
	
	public void test17() {
		int oldCount = 0;
		for(int i=0; i<100; i++) {
			test1.startGame(fullPlayer);
			test1.checkIfSpy(sally);
			int count = 0;
			for(Player p : fullPlayer)
				count += p.getWins();
			int diff = count - oldCount;
			boolean correct = diff == 1 || diff == 3;
			assertTrue(correct);
			oldCount = count;
		}
	}
	
	public void test18() {
		test1.startGame(fullPlayer);
		boolean caught = false;
		try {
			test1.checkIfSpy(new Player("who am i?"));
		}catch(IllegalArgumentException e){
			caught = true;
		}
		assertTrue(caught);
	}
}
