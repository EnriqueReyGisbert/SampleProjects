package tp.p1.command;

import tp.p1.game.Game;
import Exceptions.*;

public class ShockwaveCommand extends Command {

	public static final String CommandText = "shockwave";
	public static final String CommandTextMsg = "w";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "UCM-Ship releases a shock wave.";
	
	public ShockwaveCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.shockWave();
		}
		catch(NoShockwaveException nse) {
			throw new CommandExecuteException("The UCMShip cannot use its shockwave", nse);
		}
		game.update();
		return true;
	}
}
