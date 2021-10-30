package histoApp.controller;

import histoApp.model.*;

public class EliminateUserCommand extends Command {
	
	private String _id;
	
	public static final String CommandText = "eliminateuser";
	public static final String CommandTextMsg = "eu";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Eliminar a un usuario";
	
	public EliminateUserCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	public EliminateUserCommand(String id){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_id = id;
	}

	// EXECUTE
	
	@Override
	public Boolean execute(App app) {
		app.eliminarUsuario(_id);
		return false;
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 2) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_id = commandWords[1];
			}
		}
		return comando;
	}
	
}
