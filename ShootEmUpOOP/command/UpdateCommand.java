package tp.p1.command;

import tp.p1.game.Game;

public class UpdateCommand extends Command {					// HACER

	public static final String CommandText = "[none]";
	public static final String CommandTextMsg = "";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Skips one cycle.";
	
	public UpdateCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		game.update();
		return true;
	}
}
