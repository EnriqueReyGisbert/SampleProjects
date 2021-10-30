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

public class ChangeCO2ClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private int _status;
	private JComboBox<Vehicle> _vehicles;
	private JComboBox<Integer> _CO2;
	private JSpinner _ticks;
	private SpinnerNumberModel _ticksModel;
	private DefaultComboBoxModel<Vehicle> _vehiclesModel;
	private DefaultComboBoxModel<Integer> _CO2Model;

	public ChangeCO2ClassDialog(Frame parent) {
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

		_vehiclesModel = new DefaultComboBoxModel<>();
		_CO2Model = new DefaultComboBoxModel<>();
		_vehicles = new JComboBox<Vehicle>(_vehiclesModel);
		_CO2 = new JComboBox<Integer>(_CO2Model);
		_ticksModel = new SpinnerNumberModel(1, 1, 1000, 1);
		_ticks = new JSpinner(_ticksModel);

		viewsPanel.add(_vehicles);
		viewsPanel.add(_CO2);
		viewsPanel.add(_ticks);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				ChangeCO2ClassDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (_vehiclesModel.getSelectedItem() != null) {
					_status = 1;
					ChangeCO2ClassDialog.this.setVisible(false);
				}
				
				if (_CO2Model.getSelectedItem() != null) {
					_status = 1;
					ChangeCO2ClassDialog.this.setVisible(false);
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

		_vehiclesModel.removeAllElements();
		for (Vehicle v : map.getVehicles())
			_vehiclesModel.addElement(v);

		_CO2Model.removeAllElements();
		for (int i = 0; i <= 10; i++)
			_CO2Model.addElement(i);
		
		setLocation(getParent().getLocation().x + 10, getParent().getLocation().y + 10);

		setVisible(true);
						
		return _status;
	}

	Vehicle getVehicle() {
		return (Vehicle) _vehiclesModel.getSelectedItem();
	}
	
	Integer getContClass() {
		return (Integer) _CO2Model.getSelectedItem();
	}
	
	Integer getTicks() {
		return (Integer) _ticks.getValue();
	}

	
}

