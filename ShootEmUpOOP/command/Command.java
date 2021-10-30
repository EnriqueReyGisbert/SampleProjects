package tp.p1.command;
import Exceptions.CommandExecuteException;
import tp.p1.game.*;
import java.io.*;

public abstract class Command {
	
	protected final String name;
	protected final String shortcut;
	private final String details ;
	private final String help;
	protected static final String incorrectNumArgsMsg = "Incorrect number of arguments";
	protected static final String incorrectArgsMsg = "Incorrect argument format";
	
	public Command(String name, String shortcut, String details, String help){
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	public abstract boolean execute(Game game) throws IOException, CommandExecuteException;				// return true to print
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if(commandWords.length == 1) {
			if(this.matchCommandName(commandWords[0]))
				comando = this;
		}
		return comando;
	}
	
	protected boolean matchCommandName(String name) {
		return (this.shortcut.equalsIgnoreCase(name) || this.name.equalsIgnoreCase(name));
	}
	
	public String helpText(){
		return details + " : " + help + "\n";
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getHelp() {
		return this.help;
	}
}