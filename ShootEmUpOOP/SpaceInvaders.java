package tp.p1;
import tp.p1.game.*;
import java.util.*;

public class SpaceInvaders {
	
	public static void main(String[] args) {                                    
		Scanner escaner = new Scanner(System.in);
		int seed;
		Level level = Level.EASY;
		Game game;
		boolean exito = true;
		
		if (args.length <= 2) {
			
			if(args[0].toUpperCase().equals("EASY")) {
				level = Level.EASY;
			}
			else if(args[0].toUpperCase().equals("HARD")) {
				level = Level.HARD;
			}
			else if(args[0].toUpperCase().equals("INSANE")) {
				level = Level.INSANE;
			}
			else {
				exito = false;
				System.out.println("First argument is incorrect.");
			}
			try {
				if (exito) {
					if(args.length == 2) {
						seed = Integer.parseInt(args[1]);
						Random rnd = new Random(seed);
						game = new Game(level, rnd);
						game.initGame();
					}
					else {
						Random rnd = new Random();
						game = new Game(level, rnd);
						game.initGame();
					}
					Controller controller = new Controller(game, escaner);
					controller.run();
				}
			}
			catch(NumberFormatException nfe) {
				System.out.format(nfe.getMessage() + "%n%n");
			}
			
		}
		
		else {
			System.out.println("The number of arguments is incorrect.");
		}
	}
}
