package simulator.view;

import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import simulator.model.*;
import simulator.model.Event;
import simulator.control.*;

public class ChangeWeatherDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;

	private int _status;
	private JComboBox<Road> _roads;
	private JComboBox<Weather> _weather;
	private JSpinner _ticks;
	private SpinnerNumberModel _ticksModel;
	private DefaultComboBoxModel<Road> _roadsModel;
	private DefaultComboBoxModel<Weather> _weatherModel;

	public ChangeWeatherDialog(Frame parent) {
		super(parent, true);
		initGUI();
	}

	private void initGUI() {

		_status = 0;

		setTitle("Change CO2 Class");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);

		JLabel helpMsg = new JLabel("Select your favorite");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));

		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);

		_roadsModel = new DefaultComboBoxModel<>();
		_weatherModel = new DefaultComboBoxModel<>();
		_roads = new JComboBox<Road>(_roadsModel);
		_weather = new JComboBox<Weather>(_weatherModel);
		_ticksModel = new SpinnerNumberModel(1, 1, 1000, 1);
		_ticks = new JSpinner(_ticksModel);

		viewsPanel.add(_roads);
		viewsPanel.add(_weather);
		viewsPanel.add(_ticks);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				ChangeWeatherDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (_roadsModel.getSelectedItem() != null) {
					_status = 1;
					ChangeWeatherDialog.this.setVisible(false);
				}
				
				if (_weatherModel.getSelectedItem() != null) {
					_status = 1;
					ChangeWeatherDialog.this.setVisible(false);
				}
				
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(500, 200));
		pack();
		setResizable(false);
		setVisible(false);
	}

	public int open(RoadMap map) {

		_roadsModel.removeAllElements();
		for (Road r : map.getRoads())
			_roadsModel.addElement(r);

		_weatherModel.removeAllElements();
		for (Weather w : Weather.values())
			_weatherModel.addElement(w);
		
		setLocation(getParent().getLocation().x + 10, getParent().getLocation().y + 10);

		setVisible(true);
						
		return _status;
	}

	Road getRoad() {
		return (Road) _roadsModel.getSelectedItem();
	}
	
	Weather getWeather() {
		return (Weather) _weatherModel.getSelectedItem();
	}
	
	Integer getTicks() {
		return (Integer) _ticks.getValue();
	}

	
}
