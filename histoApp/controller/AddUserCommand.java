package histoApp.controller;

import histoApp.model.*;

public class AddUserCommand extends Command {
	
	private String _id;
	private String _contrasenna;
	private String _apodo;
	private String _codigo;
	private Integer _grado;
	
	public static final String CommandText = "adduser";
	public static final String CommandTextMsg = "au";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Annadir a un usuario";
	
	public AddUserCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	public AddUserCommand(String id, String contrasenna, String apodo, String codigo, Integer grado){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_id = id; _contrasenna = contrasenna; _apodo = apodo; _codigo = codigo; _grado = grado;
	}
	
	// EXECUTE

	@Override
	public Boolean execute(App app) {
		app.annadirUsuario(_id, _contrasenna, _apodo, _codigo, _grado);
		return false;
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if(commandWords.length == 4) {
			if(this.matchCommandName(commandWords[0])) {
				_id = commandWords[1];
				_contrasenna = commandWords[2];
				_apodo = commandWords[3];
				comando = this;
			}
		}
		else if(commandWords.length == 6) {
			if(this.matchCommandName(commandWords[0])) {
				_id = commandWords[1];
				_contrasenna = commandWords[2];
				_apodo = commandWords[3];
				_codigo = commandWords[4];
				_grado = Integer.parseInt(commandWords[5]);
				comando = this;
			}
		}
		return comando;
	}
	
}
