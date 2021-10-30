package histoApp.launcher;

import org.apache.commons.cli.*;
import histoApp.controller.*;
import histoApp.model.*;
import histoApp.view.*;
import histoApp.viewV2.*;
import java.util.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

@SuppressWarnings("unused")

public class Main {
	
	/*
	
	Argumentos para la ejecución:
	
	Si se quiere interactuar con la aplicación a través de la consola:
	
		-iu resources/files/usuarios.json -id resources/files/data.json -ou resources/files/outUsuarios.json -od resources/files/outData.json -m console
	 
	Si se desea visualizar la aplicación a través de la interfaz de usuario:
	
		-iu resources/files/usuarios.json -id resources/files/data.json -ou resources/files/outUsuarios.json -od resources/files/outData.json -m gui
	 
	 */
	

	private final static String _viewModeDefaultValue = "gui";
	private static String _datosFichas = null;
	private static String _datosUsuarios = null;
	private static String _outFichas = null;
	private static String _outUsuarios = null;
	private static String _viewMode = null;
	
	
	
	// GESTIÓN DE LOS ARGUMENTOS DE LA APLICACIÓN

	private static void parseArgs(String[] args) {		

		Options cmdLineOptions = buildOptions();

		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInUsersFileOption(line);
			parseInDataFileOption(line);
			parseOutUsersFileOption(line);
			parseOutDataFileOption(line);
			parseModeViewOption(line);

			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}
	
	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("iu").longOpt("inputUsers").hasArg().desc("Users file").build());
		cmdLineOptions.addOption(Option.builder("id").longOpt("inputData").hasArg().desc("Data file").build());
		cmdLineOptions.addOption(Option.builder("ou").longOpt("outputUsers").hasArg().desc("Output users file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("od").longOpt("outputData").hasArg().desc("Output data file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("m").longOpt("mode").hasArg().desc("View mode: 'gui' or 'console' (deafult value is 'gui')").build());
		
		return cmdLineOptions;
	}

	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInUsersFileOption(CommandLine line) throws ParseException {
		_datosUsuarios = line.getOptionValue("iu");
		if (_datosUsuarios == null) {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseInDataFileOption(CommandLine line) throws ParseException {
		_datosFichas = line.getOptionValue("id");
		if (_datosFichas == null) {
			throw new ParseException("An events file is missing");
		}
	}
	
	private static void parseOutUsersFileOption(CommandLine line) throws ParseException {
		_outUsuarios = line.getOptionValue("ou");
	}
	
	private static void parseOutDataFileOption(CommandLine line) throws ParseException {
		_outFichas = line.getOptionValue("od");
	}
	
	

	private static void parseModeViewOption(CommandLine line) throws ParseException {
		if (line.hasOption("m"))
			_viewMode = (String)(line.getOptionValue("m"));
		else
			_viewMode = _viewModeDefaultValue;
	}
	
	
	
	// INICIO DE LA APLICACIÓN
	
	private static void startBatchMode() throws Exception {				// Modo Consola
		
		Scanner escaner = new Scanner(System.in);
		InputStream inUsers = new FileInputStream(_datosUsuarios);
		OutputStream outUsers = _outUsuarios == null ? System.out: new FileOutputStream(new File(_outUsuarios));
		InputStream inData = new FileInputStream(_datosFichas);
		OutputStream outData = _outFichas == null ? System.out: new FileOutputStream(new File(_outFichas));
		App app = App.getInstance(inUsers, inData, outUsers, outData);
		Controller c = new Controller(app, escaner);
		c.runConsole();
		PrintStream pUsers = new PrintStream(outUsers);
		pUsers.println();
		PrintStream pData = new PrintStream(outData);
		pData.println();
	}
	
	private static void startGUIMode() throws Exception {				// Modo Interfaz Usuario
		
		InputStream inUsers = new FileInputStream(_datosUsuarios);
		OutputStream outUsers = _outUsuarios == null ? System.out: new FileOutputStream(new File(_outUsuarios));
		InputStream inData = new FileInputStream(_datosFichas);
		OutputStream outData = _outFichas == null ? System.out: new FileOutputStream(new File(_outFichas));
		App app = App.getInstance(inUsers, inData, outUsers, outData);
		Controller c = new Controller(app, null);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SystemView(c);
			}
		});
		PrintStream pUsers = new PrintStream(outUsers);
		pUsers.println();
		PrintStream pData = new PrintStream(outData);
		pData.println();
	}
	
	private static void start(String[] args) throws Exception {
		parseArgs(args);
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Historycal.otf")));
		if (_viewMode.equals("console"))
			startBatchMode();	
		else 
			startGUIMode();
	}
	
	
	// MAIN

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
