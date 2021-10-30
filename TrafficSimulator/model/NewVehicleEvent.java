package simulator.model;

import java.util.*;

public class NewVehicleEvent extends Event {
	
	private String id;
	private int maxSpeed, contClass;
	private List<String> itinerary;

	public NewVehicleEvent(int time, String id, int maxSpeed, int contClass, List<String> itinerary) {
		
		super(time);
		this.id = id;
		this.maxSpeed = maxSpeed;
		this.contClass = contClass;
		this.itinerary = itinerary;
	
	}

	@Override
	void execute(RoadMap map) throws Exception {
		
		List<Junction> listJunctions = new ArrayList<Junction>();
		for(int i = 0; i < this.itinerary.size(); i++)
			listJunctions.add(map.getJunction(itinerary.get(i)));
		Vehicle v = new Vehicle(id, maxSpeed, contClass, listJunctions);
		map.addVehicle(v);
		v.moveToNextRoad();
	}
	
	public String toString() {
		return "New Vehicle '" + this.id + "'";
	}
	
}
