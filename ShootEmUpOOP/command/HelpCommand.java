package tp.p1.command;

import tp.p1.game.Game;

public class HelpCommand extends Command {

	public static final String CommandText = "help";
	public static final String CommandTextMsg = "h";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "shows the list of valid commands and their functions.";
	
	public HelpCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println(CommandGenerator.commandHelp());
		game.update();
		return false;
	}
	
}
