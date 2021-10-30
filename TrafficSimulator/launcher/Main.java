package simulator.launcher;

import java.util.*;
import java.io.*;
import javax.swing.*;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import simulator.control.*;
import simulator.factories.*;
import simulator.model.*;
import simulator.view.*;

public class Main {

	private final static Integer _timeLimitDefaultValue = 10;
	private final static String _viewModeDefaultValue = "gui";
	private static String _inFile = null;
	private static String _outFile = null;
	private static Factory<Event> _eventsFactory = null;
	private static Integer _timeTic = 0;
	private static String _viewMode = null;

	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseTimeTicOption(line);
			parseModeViewOption(line);

			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
	
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

		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("time").hasArg().desc("Ticks to the simulator's main loop (default value is 10)").build());
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

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null) {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}
	
	private static void parseTimeTicOption(CommandLine line) throws ParseException {
		if (line.hasOption("t"))
			_timeTic = Integer.parseInt(line.getOptionValue("t"));
	}
	
	private static void parseModeViewOption(CommandLine line) throws ParseException {
		if (line.hasOption("m"))
			_viewMode = (String)(line.getOptionValue("m"));
		else
			_viewMode = _viewModeDefaultValue;
	}
	
	private static void initFactories() {
		
		// LightSwitchingStartegy Factory
		
		List<Builder<LightSwitchingStrategy>> lsbs = new ArrayList<>();
		lsbs.add(new RoundRobinStrategyBuilder());
		lsbs.add(new MostCrowdedStrategyBuilder());
		Factory<LightSwitchingStrategy> lssFactory = new BuilderBasedFactory<>(lsbs);
		
		// DequeuingStrategy Factory
		
		List<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
		dqbs.add(new MoveFirstStrategyBuilder());
		dqbs.add(new MoveAllStrategyBuilder());
		Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(dqbs);
		
		// Events Factory
		
		List<Builder<Event>> ebs = new ArrayList<>();
		ebs.add(new NewJunctionEventBuilder(lssFactory, dqsFactory));
		ebs.add(new NewCityRoadEventBuilder());
		ebs.add(new NewInterCityRoadEventBuilder());
		ebs.add(new NewVehicleEventBuilder());
		ebs.add(new SetWeatherEventBuilder());
		ebs.add(new SetContClassEventBuilder());
		_eventsFactory = new BuilderBasedFactory<>(ebs);

	}

	private static void startBatchMode() throws Exception {
		
		InputStream in = new FileInputStream(_inFile);
		OutputStream out = _outFile == null ? System.out: new FileOutputStream(new File(_outFile));
		TrafficSimulator sim = new TrafficSimulator();
		Controller c = new Controller(sim, _eventsFactory);
		c.loadEvents(in);
		if (_timeTic == 0)
			c.run(_timeLimitDefaultValue, out);
		else
			c.run(_timeTic, out);
		PrintStream p = new PrintStream(out);
		p.println();
		
	}
	
	private static void startGUIMode() throws Exception {
		
		TrafficSimulator sim = new TrafficSimulator();
		Controller c = new Controller(sim, _eventsFactory);
		if(_inFile != null) {
			InputStream in = new FileInputStream(_inFile);
			c.loadEvents(in);
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainWindow(c);
			}
		});
		
	}
	
	private static void start(String[] args) throws Exception {
		initFactories();
		parseArgs(args);
		if (_viewMode.equals("console"))
			startBatchMode();
		else 
			startGUIMode();
	}

	// example command lines:
	
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
