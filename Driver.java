import java.util.ArrayList;
import java.util.Scanner;

public class Driver {

	
public static void main(String args[]) {
		
		
		ArrayList<Player> players = new ArrayList<Player>();
		Scanner scr = new Scanner(System.in);
		Spyfall gameInstance = new Spyfall();
		
		
		
		System.out.println("Enter a name for a player");
		String inpt = scr.nextLine();
		
		
		do {
			players.add(new Player(inpt));
			System.out.println("Enter a name for a player, Or type 'start' to start the game.");
			inpt = scr.nextLine();
			
		}while (!inpt.equalsIgnoreCase("start")||!(players.size()<6));
		
		
		do {
		
		gameInstance.startGame(players);
		
		
		System.out.println(gameInstance.toString());
		System.out.println();
		
		
		while(true) {
		try {
			System.out.println("type the name of the player everyone thinks is the spy");
			inpt = scr.nextLine();
			System.out.println(gameInstance.checkIfSpy(new Player(inpt)));
		break;
		}catch(IllegalArgumentException e) {	
		}
		}
		
		
		System.out.println("type start if you would like to play again");
		inpt = scr.nextLine();
		
		gameInstance.resetGame();
		
		}while (inpt.equalsIgnoreCase("start"));
		
		scr.close();
	}
}
