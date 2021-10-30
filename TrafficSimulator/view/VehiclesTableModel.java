package simulator.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import simulator.model.*;
import simulator.model.Event;
import simulator.control.*;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver {
	
	private List<Vehicle> _vehicles;
	private String[] _colNames = { "Id", "Location", "Itinerary", "CO2 Class", "Max Speed", "Speed", "Total CO2", "Distance" };
	private Controller _ctrl;
	
	public VehiclesTableModel(Controller ctrl) {
		_ctrl = ctrl;
	}
	
	public void setEventsList(List<Vehicle> vehicles) {
		_vehicles = vehicles;
		fireTableStructureChanged();
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	@Override
	public String getColumnName(int col) {
		return _colNames[col];
	}

	@Override
	public int getColumnCount() {
		return _colNames.length;
	}

	@Override
	public int getRowCount() {
		return _vehicles == null ? 0 : _vehicles.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex; 
			break;
		case 1:
			s = _vehicles.get(rowIndex).getId();
			break;
		case 2:
			s = _vehicles.get(rowIndex).getRoad().getId() + ":" + _vehicles.get(rowIndex).getLocation();
			break;
		case 3:
			s = "[";
			List<Junction> aux = _vehicles.get(rowIndex).getItinerary();
			for(Junction j : aux) {
				s += j.getId() + ",";
			}
			s += "]";
			break;
		case 4:
			s = _vehicles.get(rowIndex).getContaminationDegree();
			break;
		case 5:
			s = _vehicles.get(rowIndex).getMaxSpeed();
			break;
		case 6:
			s = _vehicles.get(rowIndex).getSpeed();
			break;
		case 7:
			s = _vehicles.get(rowIndex).getCO2();
			break;
		case 8:
			s = _vehicles.get(rowIndex).getDistance();
			break;
		}
		return s;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
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

}
