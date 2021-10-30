package tp.p1.command;

import tp.p1.game.*;

public class StringifyCommand extends Command {

	public static final String CommandText = "stringify";
	public static final String CommandTextMsg = "str";
	public static final String DetailsTextMsg = "";
	public static final String HelpTextMsg = "Show the game as a collection of Strings";

	public StringifyCommand() {
		super(CommandText, CommandTextMsg, DetailsTextMsg, HelpTextMsg);
	}

	@Override
	public boolean execute(Game game) {
		GamePrinter printer3 = PrinterTypes.SERIALIZER.getObject();
		printer3.setGame(game);
		System.out.println(printer3.toString());
		return false;
	}

}
