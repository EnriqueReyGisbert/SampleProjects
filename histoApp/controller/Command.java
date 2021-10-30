package histoApp.controller;

import java.io.*;
import histoApp.model.*;

public abstract class Command {							// Patrón Command
	
	protected final String name;
	protected final String shortcut;
	private final String details;
	private final String help;
	protected static final String incorrectNumArgsMsg = "Incorrect number of arguments";
	protected static final String incorrectArgsMsg = "Incorrect argument format";
	
	public Command(String name, String shortcut, String details, String help){
		this.name = name;
		this.shortcut = shortcut;
		this.details = details;
		this.help = help;
	}
	
	
	public abstract Object execute(App app) throws IOException;				
	
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
