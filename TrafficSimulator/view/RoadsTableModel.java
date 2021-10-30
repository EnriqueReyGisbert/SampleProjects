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

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver {
	
	private List<Road> _roads;
	private String[] _colNames = { "Id", "Length", "Weather", "Max. Speed", "Speed Limit", "Total CO2", "CO2 Limit"};
	private Controller _ctrl;
	
	public RoadsTableModel(Controller ctrl) {
		_ctrl = ctrl;
	}
	
	public void setEventsList(List<Road> roads) {
		_roads = roads;
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
		return _roads == null ? 0 : _roads.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex; 
			break;
		case 1:
			s = _roads.get(rowIndex).getId();
			break;
		case 2:
			s = _roads.get(rowIndex).devLong();
			break;
		case 3:
			s = _roads.get(rowIndex).getWeather();
			break;
		case 4:
			s = _roads.get(rowIndex).getMaxSpeed();
			break;
		case 5:
			s = _roads.get(rowIndex).getSpeedLimit();
			break;
		case 6:
			s = _roads.get(rowIndex).getTotalCO2();
			break;
		case 7:
			s = _roads.get(rowIndex).getCO2Limit();
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
