package tp.p1.command;

public class CommandGenerator {
	private static Command[] availableCommands = {
		new ListCommand(),
		new HelpCommand(),
		new ResetCommand(),
		new ExitCommand(),
		new ShootCommand(),
		new UpdateCommand(),
		new MoveCommand(),
		new ShockwaveCommand(),
		new BuyCommand(),
		new StringifyCommand(),
		new ListPrintersCommand(),
		new SaveCommand(),
		new LoadCommand()
	};
	
	public static Command parseCommand(String[] commandWords) {
		Command comando = null;
		int i = 0;
		while((i < availableCommands.length) && (comando == null)) {
			comando = availableCommands[i].parse(commandWords);
			i++;
		}
		return comando;
	}
	
	public static String commandHelp(){
		String cadena ="";
		for(int i = 0; i < availableCommands.length; i++) {
			cadena = cadena + availableCommands[i].getName() + ": " + availableCommands[i].getHelp() + '\n';
		}
		return cadena;
	}
}
