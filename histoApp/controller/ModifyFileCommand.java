package histoApp.controller;

import java.io.FileNotFoundException;

import histoApp.model.*;

public class ModifyFileCommand extends Command {
	
	private String _id;
	private String _idCambio;
	private String _imagen;
	private String _texto;
	private String _doc;
	
	public static final String CommandText = "modifyfile";
	public static final String CommandTextMsg = "mf";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Modificar una ficha";
	
	public ModifyFileCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	public ModifyFileCommand(String id, String idCambio, String imagen, String texto, String doc){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_id = id; _idCambio = idCambio; _imagen = imagen; _texto = texto; _doc = doc;
	}

	// EXECUTE
	
	@Override
	public Boolean execute(App app) throws FileNotFoundException {
		app.modificarFicha(_id, _idCambio, _texto, _doc, _imagen);
		return false;
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 5) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_id = commandWords[1];
				_idCambio = commandWords[2];
				switch (commandWords[3]) {
				case "texto" :
					_texto = commandWords[4];
					break;
				case "imagen":
					_imagen = commandWords[4];
					break;
				case "doc":
					_doc = commandWords[4];
					break;
				default:	
					break;
				}
			}
		}
		return comando;
	}
	
}
