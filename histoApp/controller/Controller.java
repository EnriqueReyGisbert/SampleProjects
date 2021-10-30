package histoApp.controller;

import java.io.IOException;
import java.util.*;
import histoApp.model.*;

public class Controller {
	
	private App app;
	private Scanner in;
	private String unknownCommandMsg = "Unknown command.", PROMPT = "Introduce your command: ";
	
	public Controller(App app, Scanner in) {
		this.app = app;
		this.in = in;
	}
	
	
	// EJECUCIÓN POR CONSOLA
	
	public void runConsole() throws IOException {
		
		while(!app.getFinished()) {
			
			System.out.println(PROMPT);
			String words = in.nextLine();
			this.doCommand(words);
			
		}
		
	}
	
	public void doCommand(String words) throws IOException {
		
		String[] wordsCommand = words.trim().split("\\s+");
		
		// Generar el comando con la factoría 'CommandGenerator'

		Command command = CommandGenerator.parseCommand(wordsCommand);
					
		// Llamar al método 'execute' del comando, si existe
					
		if (command != null)
			command.execute(app);
		else {
			try {
				System.out.format(unknownCommandMsg);
				Exception myException = new Exception(); 
				throw new Exception("The command introduced is invalid.", myException);
			} catch (Exception ex) {
				 System.err.println('\n' + ex.getMessage() + '\n' + "Cause of exception:" + "  " + ex.getCause() + '\n');
			}
		}		
			
	}
	
	
	// AÑADIR Y ELIMINAR OBSERVADORES DE APP (Patrón Observador)

	public void addObserver(AppObserver o) {
		this.app.addObserver(o);
	}
	
	public void removeObserver(AppObserver o) {
		this.app.removeObserver(o);
	}

	
}


