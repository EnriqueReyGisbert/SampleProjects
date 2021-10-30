package simulator.view;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

import simulator.model.*;
import simulator.model.Event;
import simulator.control.*;

public class JunctionsTableModel extends AbstractTableModel implements TrafficSimObserver {
	
	private List<Junction> _junctions;
	private String[] _colNames = { "Id", "Green", "Queues" };
	private Controller _ctrl;
	
	public JunctionsTableModel(Controller ctrl) {
		_ctrl = ctrl;
	}
	
	public void setEventsList(List<Junction> junctions) {
		_junctions = junctions;
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
		return _junctions == null ? 0 : _junctions.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object s = null;
		switch (columnIndex) {
		case 0:
			s = rowIndex; 
			break;
		case 1:
			s = _junctions.get(rowIndex).getId();
			break;
		case 2:
			s = _junctions.get(rowIndex).getRoadAtGreen().getId();
			if(s == null)
				s = "NONE";
			break;
		case 3:
			Map<Road, List<Vehicle>> aux = _junctions.get(rowIndex).getQueues();
			s = "";
			for(Entry<Road, List<Vehicle>> r : aux.entrySet()) {
				s += r.getKey().getId() + ":[";
				for(Vehicle v : r.getValue()) {
					s += v.getId() + ",";
				}
				s += "] ";
			}
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
