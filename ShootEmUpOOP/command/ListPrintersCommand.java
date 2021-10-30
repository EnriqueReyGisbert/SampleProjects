package tp.p1.command;

import Exceptions.CommandExecuteException;
import tp.p1.game.Game;
import tp.p1.game.PrinterTypes;

public class ListPrintersCommand extends Command{

	public static final String CommandText = "listPrinters";
	public static final String CommandTextMsg = "lp";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "shows the list of printer types";
	
	public ListPrintersCommand(){
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}
	@Override
	public boolean execute(Game game) throws CommandExecuteException {
		System.out.println(PrinterTypes.printerHelp(game));
		return false;
	}

}
