package simulator.view;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import simulator.model.*;
import simulator.model.Event;
import simulator.control.*;
import simulator.misc.Pair;

public class ControlPanel extends JPanel implements TrafficSimObserver {
	
	Controller _ctrl;
	RoadMap roadMap;
	private boolean _stopped;
	private int ticks;
	JToolBar toolBar;
	
	public ControlPanel(Controller ctrl) {
		_ctrl = ctrl;
		toolBar = new JToolBar();
		initGUI();
	}
	
	public void initGUI() {
		
		// CARGAR FICHERO EVENTOS (JFileChooser)
		
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
		JLabel openLabel = new JLabel();
		openLabel.setIcon(new ImageIcon("resources/icons/" + "open.png"));
		JButton openButton = new JButton();
		openButton.add(openLabel);
		openButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent event) {
				JDialog dialog = new JDialog();
				int result = fileChooser.showOpenDialog(dialog);
				if (result == JFileChooser.APPROVE_OPTION) {
				    File selectedFile = fileChooser.getSelectedFile();
				    if(selectedFile.exists()) {
				    	_ctrl.reset();
					    try {
							_ctrl.loadEvents(new DataInputStream(new FileInputStream(selectedFile)));

						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Error al cargar los eventos del fichero seleccionado",
								      "OPERATION FAILED", JOptionPane.ERROR_MESSAGE);
						}
				    }
				    else
				    	JOptionPane.showMessageDialog(null, "No existe el fichero seleccionado",
							      "OPERATION FAILED", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		toolBar.add(openButton);
		
		// CAMBIAR CONT CLASS VEHICULO (JDialog)
		
		JLabel contClassLabel = new JLabel();
		contClassLabel.setIcon(new ImageIcon("resources/icons/" + "co2class.png"));
		JButton contClassButton = new JButton();
		contClassButton.add(contClassLabel);
		contClassButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent event) {
				
				ChangeCO2ClassDialog dialogContClass = new ChangeCO2ClassDialog((Frame) SwingUtilities.getWindowAncestor(contClassButton));

				int status = dialogContClass.open(roadMap); 
				
				if (status == 1) {
					List<Pair<String,Integer>> lista = new ArrayList<Pair<String,Integer>>();
					lista.add(new Pair<String, Integer>(dialogContClass.getVehicle().getId(), dialogContClass.getContClass()));
					try {
						SetContClassEvent newEvent = new SetContClassEvent(dialogContClass.getTicks(), lista);
						_ctrl.addEvent(newEvent);
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				
			}
		}); 
		toolBar.add(contClassButton);
		
		// CAMBIAR COND ATMOSFÉRICAS CARRETERA (JDialog)
		
		JLabel atmosLabel = new JLabel();
		atmosLabel.setIcon(new ImageIcon("resources/icons/" + "weather.png"));
		JButton atmosButton = new JButton();
		atmosButton.add(atmosLabel);
		atmosButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent event) {
				
				ChangeWeatherDialog dialogWeather = new ChangeWeatherDialog((Frame) SwingUtilities.getWindowAncestor(atmosButton));
				
				int status = dialogWeather.open(roadMap);
				
				if(status == 1) {
					List<Pair<String,Weather>> lista = new ArrayList<Pair<String,Weather>>();
					lista.add(new Pair<String, Weather>(dialogWeather.getRoad().getId(), dialogWeather.getWeather()));
					try {
						SetWeatherEvent newEvent = new SetWeatherEvent(dialogWeather.getTicks(), lista);
						_ctrl.addEvent(newEvent);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
			}
		}); 
		toolBar.add(atmosButton);
		
		// EJECUTAR
		
		JLabel executeLabel = new JLabel();
		executeLabel.setIcon(new ImageIcon("resources/icons/" + "run.png"));
		JButton executeButton = new JButton();
		executeButton.add(executeLabel);
		executeButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent event) {
				enableToolBar(false);
				run_sim(ticks);
				
			}
		}); 
		toolBar.add(executeButton);
		
		// PARAR
		
		JLabel stopLabel = new JLabel();
		stopLabel.setIcon(new ImageIcon("resources/icons/" + "stop.png"));
		JButton stopButton = new JButton();
		stopButton.add(stopLabel);
		stopButton.addActionListener(new ActionListener() {				
			public void actionPerformed(ActionEvent event) {
				
				stop();
				
			}
		}); 
		toolBar.add(stopButton);
		
		
		// NUMERO DE TICKS
		
		SpinnerNumberModel spinner = new SpinnerNumberModel();
		
		// SALIDA

		this.add(toolBar);
		
	}
	
	public void update(RoadMap map) {
		
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}
	
	private void run_sim(int n) {
		
		if (n > 0 && !_stopped) {
			
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				// TODO show error message
				_stopped = true;
				return;
			}
			
			SwingUtilities.invokeLater(new Runnable() {
				
				public void run() {
					run_sim(n - 1);
				}
				
			});
			
		} else {
			enableToolBar(true);
			_stopped = true;
		}
		
	}
	
	@SuppressWarnings("deprecation")
	private void enableToolBar(boolean b) {
		this.toolBar.enable(b);
		// hacer que stop sí se pueda pulsar
	}

	private void stop() {
		_stopped = true;
	}
	
}
