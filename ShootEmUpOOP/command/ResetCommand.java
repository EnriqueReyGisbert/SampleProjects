package tp.p1.command;

import tp.p1.game.Game;

public class ResetCommand extends Command {

	public static final String CommandText = "reset";
	public static final String CommandTextMsg = "r";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Starts a new game.";
	
	public ResetCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		game.reset();
		return true;
	}

}
