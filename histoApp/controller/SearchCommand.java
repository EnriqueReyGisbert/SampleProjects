package histoApp.controller;

import histoApp.baseDatos.*;
import histoApp.model.*;

public class SearchCommand extends Command {
	
	private String _stringBuscar;
	
	public static final String CommandText = "search";
	public static final String CommandTextMsg = "s";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Buscar un String en las fichas";
	
	public SearchCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	public SearchCommand(String stringBuscar){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_stringBuscar = stringBuscar;
	}
	
	// EXECUTE
	
	@Override
	public Ficha execute(App app) {
		Ficha f = app.buscarString(_stringBuscar);
		if(f != null)
			System.out.println(f.getID());
		return f;
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 2) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_stringBuscar = commandWords[1];
			}
		}
		return comando;
	}
	
	
}
