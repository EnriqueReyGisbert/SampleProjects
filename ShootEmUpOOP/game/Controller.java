package tp.p1.game;
import java.util.Scanner;
import tp.p1.command.*;
import Exceptions.*;
import java.io.*;

public class Controller {
	
	public Controller(Game game, Scanner in) {        				
		this.game = game;
		this.in = in;
	}
	
	private Scanner in;
	private Game game;
	private String unknownCommandMsg = "Unknown command.", PROMPT = "Introduce your command: ";
	
	public void run() {	
		
		GamePrinter printer = PrinterTypes.BOARDPRINTER.getObject();
		printer.setGame(game);
		System.out.println(printer.toString());
		
		while (!game.isFinished()){
			GamePrinter printer2 = PrinterTypes.BOARDPRINTER.getObject();
			printer2.setGame(game);
			System.out.println(PROMPT);
			String[] words = in.nextLine().toLowerCase().trim().split("\\s+");
				try {
					Command command = CommandGenerator.parseCommand(words);
					if (command != null) { 
						if (command.execute(game))
							System.out.println(printer2.toString());
					}
					else {
						System.out.format(unknownCommandMsg);
						CommandParseException myException = new CommandParseException(); 
						throw new CommandParseException("The command introduced is invalid.", myException);
					}
				}
				catch (CommandParseException | CommandExecuteException | IOException ex) {
					 System.err.println('\n' + ex.getMessage() + '\n' + "Cause of exception:" + "  " + ex.getCause() + '\n');
				}
		}
	}
}

