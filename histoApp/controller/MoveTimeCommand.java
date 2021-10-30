package histoApp.controller;

import histoApp.model.*;

public class MoveTimeCommand extends Command {
	
	private Integer _tiempo;
	
	public static final String CommandText = "movetime";
	public static final String CommandTextMsg = "mt";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Modificar la barra de tiempo de un mapa";
	
	public MoveTimeCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	public MoveTimeCommand(Integer tiempo){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
		_tiempo = tiempo;
	}
	
	// EXECUTE

	@Override
	public Boolean execute(App app) {
		app.mueveBarraTiempo(_tiempo);
		return false;
	}
	
	// PARSE
	
	public Command parse(String[] commandWords) {
		Command comando = null;
		if (commandWords.length == 2) {
			if(this.matchCommandName(commandWords[0])) {
				comando = this;
				_tiempo = Integer.parseInt(commandWords[1]);
			}
		}
		return comando;
	}
	
	
}
