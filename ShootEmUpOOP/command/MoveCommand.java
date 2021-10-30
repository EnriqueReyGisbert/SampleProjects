package tp.p1.command;

import tp.p1.game.Game;
import Exceptions.*;

public class MoveCommand extends Command {

	public static final String CommandText = "move <left|right><1|2>";
	public static final String CommandTextMsg = "m <l|r><1|2>";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Moves UCM-Ship to the indicated direction.";
	private int direction; 
	
	public MoveCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		try {  
			game.move(this.direction);
		}
		catch(OutOfWorldException MyException) {
			throw new CommandExecuteException("You cannot move in this direction", MyException);
		}
		game.update();
		return true;
	}

	@Override
	public Command parse(String[] commandWords) {
		Command comando = null;
		if(commandWords.length == 3) {
			if(commandWords[0].equals("m") || commandWords[0].equals("move")) {
				if(commandWords[1].equals("l") || commandWords[1].equals("left")) {
					if(commandWords[2].equals("1")) {
						comando = this;
						this.direction = -1;
					}
					else if(commandWords[2].equals("2")) {
						comando = this;
						this.direction = -2;
					}
				}
				else if(commandWords[1].equals("r") || commandWords[1].equals("right")) {
					if(commandWords[2].equals("1")) {
						comando = this;
						this.direction = 1;
					}
					else if(commandWords[2].equals("2")) {
						comando = this;
						this.direction = 2;
					}
				}
			}
		}
		return comando;
	}
}
