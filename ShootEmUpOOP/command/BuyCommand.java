package tp.p1.command;

import tp.p1.game.Game;

public class BuyCommand extends Command {
	
	public static final String CommandText = "buy";
	public static final String CommandTextMsg = "b";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Buy a superMissile";

	public BuyCommand() {
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		if(game.getPoints() >= 20) {
			game.addSuperLaser(true);
			game.receivePoints(- 20);
		}
		game.update();
		return false;
	}

}
