package tp.p1.command;

import tp.p1.game.Game;
import java.io.*;

public class SaveCommand extends Command{

	public static final String CommandText = "save <fileName>";
	public static final String CommandTextMsg = "sv <fileName>";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Saves the game in a file";
	public String archivo;
	
	public SaveCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws IOException {
		BufferedWriter outChars = null;
		this.archivo = this.archivo + ".dat";
		try {
			outChars = new BufferedWriter(new FileWriter(this.archivo));
			String l = game.serialize();
			outChars.write(l);
			outChars.newLine();
		}
		finally {
			if (outChars != null) {
				outChars.close();
				System.out.println("Game successfully saved in file " + this.archivo + ".Use the load command to reload it");
			}
		}
		return false;
	}
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if(commandWords.length == 2) {
			if((commandWords[0].equals("save") || commandWords[0].equals("sv"))) {
				comando = this;
				this.archivo = commandWords[1];
			}
		}
		return comando;
	}

}
