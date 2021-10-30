package tp.p1.command;

import tp.p1.game.Game;

public class ListCommand extends Command {

	public static final String CommandText = "list";
	public static final String CommandTextMsg = "l";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Prints the list of available ships.";
	
	public ListCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		System.out.println();
		System.out.println("[R]egular ship: Points: 5 - Harm: 0 - Shield: 2");
		System.out.println("[D]estroyer ship: Points: 10 - Harm: 1 - Shield: 1");
		System.out.println("[O]vni: Points: 25 - Harm: 0 - Shield: 1");
		System.out.println("^__^: Harm: 1 - Shield: 3");
		game.update();
		return false;
	}

}
