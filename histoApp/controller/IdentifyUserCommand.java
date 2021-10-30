package histoApp.controller;

import histoApp.model.*;

public class IdentifyUserCommand extends Command {
	
	private String _id;
	private String _contrasenna;
	
	public static final String CommandText = "identifyuser";
	public static final String CommandTextMsg = "iu";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Identificar a un usuario";
	
	public IdentifyUserCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	public IdentifyUserCommand(String id, String contrasenna){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_id = id; _contrasenna = contrasenna;
	}
	
	// EXECUTE

	@Override
	public Boolean execute(App app) {
		if(app.buscarUsuario(_id, _contrasenna)) {
			System.out.println("Encontrado");
			return true;
		}
		else {
			System.out.println("No Encontrado");
			return false;
		}
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 3) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_id = commandWords[1];
				_contrasenna = commandWords[2];
			}
		}
		return comando;
	}
	
}
