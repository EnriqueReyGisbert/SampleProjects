package tp.p1.command;

import tp.p1.game.Game;
import Exceptions.*;

public class ShootCommand extends Command {

	public static final String CommandText = "shoot <super>";
	public static final String CommandTextMsg = "s <s>";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "UCM-Ship launches a missile.";
	private boolean superMissile;
	
	public ShootCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {
			game.shootLaser(this.superMissile);
		}
		catch(MissilInFlightException mife) {
			throw new CommandExecuteException("You cannot shoot that right now", mife);
		}
		if(this.superMissile)
			game.addSuperLaser(false); 
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		Command comando = null;
		if(commandWords.length == 1) {
			if(commandWords[0].equals("s") || commandWords[0].equals("shoot")) {
				this.superMissile = false;
				comando = this;
			}
		}
		else if(commandWords.length == 2) {
			if(commandWords[0].equals("s") || commandWords[0].equals("shoot")) {
				if(commandWords[1].equals("s") || commandWords[1].equals("super")) {
					this.superMissile = true;
					comando = this;
				}
			}
		}
		return comando;
	}
}
