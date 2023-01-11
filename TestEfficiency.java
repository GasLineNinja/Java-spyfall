
import java.util.ArrayList;

import junit.framework.TestCase;

public class TestEfficiency extends TestCase{
	Spyfall game;
	ArrayList<Player> players;
	ArrayList<Player> toddOnly;
	Player todd = new Player("todd");
	
	public void setUp() {
		game = new Spyfall();
		players = new ArrayList<Player>();
		for(int i=0; i<1000000; i++)
			players.add(new Player(Integer.toString(i)));
		toddOnly = new ArrayList<Player>();
		toddOnly.add(todd);
	}
	
	public void test01() {
		game.assignNormalRoles(players);
		for(Player p : players)
			assertTrue(game.containsPlayer(p));
	}
	
	public void test02() {
		game.assignNormalRoles(players);
		game.checkIfSpy(new Player(Integer.toString(0)));
	}
	
	public void test03() {
		game.assignSpy(toddOnly);
		game.assignNormalRoles(players);
		game.checkIfSpy(todd);
	}
}
