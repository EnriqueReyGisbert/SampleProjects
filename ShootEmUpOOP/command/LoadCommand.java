package tp.p1.command;

import java.io.*;
import Exceptions.*;
import tp.p1.game.Game;

public class LoadCommand extends Command{
	
	public static final String CommandText = "load <fileName>";
	public static final String CommandTextMsg = "ld <fileName>";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Loads game from a file";
	public String archivo;
	
	public LoadCommand() {
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	public boolean execute(Game game) throws CommandExecuteException {
		try {
			execute2(game);
		}
		catch(IOException ioe) {
			throw new CommandExecuteException("The selected file was not in the right format", ioe);
		}
		return true;
	}
	
	public boolean execute2(Game game) throws IOException {
		BufferedReader inChars = null;
		this.archivo = this.archivo + ".dat";
		Game security = game;
		Game aux = new Game(security.getLevel(), security.getRandom());
		try {
			inChars = new BufferedReader(new FileReader(this.archivo));
			inChars.readLine();
			inChars.readLine();
			aux.load(inChars);
			game.setGame(aux);
		}
		catch (FileContentsException fce) {
			game.setGame(security);
			throw new IOException();
		}
		finally {
			if (inChars != null) {
				inChars.close();
			}
		}
		System.out.println("Game successfully loaded from file " + this.archivo);
		return true;
	}
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if(commandWords.length == 2) {
			if(commandWords[0].equals("load") || commandWords[0].equals("ld")) {
				comando = this;
				this.archivo = commandWords[1];
			}
		}
		return comando;
	}

}
