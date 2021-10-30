package histoApp.controller;

import histoApp.model.*;

public class AddFileToMapCommand extends Command {
	
	private String _id;
	private Integer _x;
	private Integer _y;
	
	public static final String CommandText = "addfiletomap";
	public static final String CommandTextMsg = "am";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Annadir una ficha a un mapa";
	
	public AddFileToMapCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	public AddFileToMapCommand(String id, Integer x, Integer y){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_id = id; _x = x; _y = y;	
	}
	
	// EXECUTE
	
	@Override
	public Boolean execute(App app) {
		return app.annadirFichaMapa(_id, _x, _y);
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 4) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_id = commandWords[1];
				_x = Integer.parseInt(commandWords[2]);
				_y = Integer.parseInt(commandWords[3]);
			}
		}
		return comando;
	}
	
}
