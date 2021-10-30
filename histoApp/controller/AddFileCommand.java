package histoApp.controller;

import java.io.FileNotFoundException;

import histoApp.model.*;

public class AddFileCommand extends Command {
	
	private String _id;
	private String _imagen;
	private String _idImagen;
	private String _texto;
	private String _idTexto;
	private String _doc;
	private String _idDoc;
	private String _tipoFicha;
	private int _tiempo;
	
	public static final String CommandText = "addfile";
	public static final String CommandTextMsg = "af";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Annadir una ficha";
	
	public AddFileCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	public AddFileCommand(String id, String imagen, String idImagen, String texto, String idTexto, String doc, String idDoc, String tipoFicha, int tiempo) {
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_imagen = imagen; _id = id; _idImagen = idImagen; _texto = texto; _idTexto = idTexto; _doc = doc; _idDoc = idDoc; _tipoFicha = tipoFicha; _tiempo = tiempo;
	}
	
	// EXECUTE

	@Override
	public Boolean execute(App app) throws FileNotFoundException {
		app.annadirFicha(_id, _tipoFicha, _texto, _idTexto, _doc, _idDoc, _imagen, _idImagen, _tiempo);
		return false;
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length >= 4) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_id = commandWords[1];
				_tipoFicha = commandWords[2];
				try {
					_tiempo = Integer.parseInt(commandWords[3]);
				} catch (Exception e) {
					return null;
				}
				int i = 4;
				while(i < commandWords.length) {
					switch (commandWords[i]) {
					case "texto" :
						_idTexto = commandWords[i + 1];
						_texto = commandWords[i + 2];
						break;
					case "imagen":
						_idImagen = commandWords[i + 1];
						_imagen = commandWords[i + 2];
						break;
					case "doc":
						_idDoc = commandWords[i + 1];
						_doc = commandWords[i + 2];
						break;
					default:	
						break;
					}
					i += 3;
				}
			}
		}
		return comando;
	}
	
}
