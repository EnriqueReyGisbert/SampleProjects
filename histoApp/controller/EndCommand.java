package histoApp.controller;

import java.io.IOException;

import org.json.JSONException;

import histoApp.model.App;

public class EndCommand extends Command {
	
	public static final String CommandText = "end";
	public static final String CommandTextMsg = "e";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Terminar la sesión de la aplicación";
	
	public EndCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	
	// EXECUTE

	@Override
	public Boolean execute(App app) throws JSONException, IOException {
		app.loadDataAndFinish();
		return false;
	}
	
}
