package tp.p1.command;
import tp.p1.game.*;

public class ExitCommand extends Command {
	
	public static final String CommandText = "exit";
	public static final String CommandTextMsg = "e";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "terminate the program.";
	
	public ExitCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		game.exit();
		return false;
	}

}
