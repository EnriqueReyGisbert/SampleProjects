package histoApp.controller;

public class CommandGenerator {									// Factoría
	
	
	// LISTA DE COMANDOS
	
	private static Command[] availableCommands = {				
		new AddFileCommand(),
		new AddFileToMapCommand(),
		new AddUserCommand(),
		new EliminateUserCommand(),
		new IdentifyUserCommand(),
		new ModifyFileCommand(),
		new MoveTimeCommand(),
		new SearchCommand(),
		new EndCommand()
	};
	
	
	// PARSEO Y CREACIÓN DE COMANDO
		
	public static Command parseCommand(String[] commandWords) {
		Command comando = null;
		int i = 0;
		while((i < availableCommands.length) && (comando == null)) {
			comando = availableCommands[i].parse(commandWords);
			i++;
		}
		return comando;
	}

	
	// MENSAJE DE AYUDA DE COMANDO
		
	public static String commandHelp(){
		String cadena ="";
		for(int i = 0; i < availableCommands.length; i++) {
			cadena = cadena + availableCommands[i].getName() + ": " + availableCommands[i].getHelp() + '\n';
		}
		return cadena;
	}
	
	
}
