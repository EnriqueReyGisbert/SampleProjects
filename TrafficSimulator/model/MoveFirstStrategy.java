package simulator.model;

import java.util.*;

public class MoveFirstStrategy implements DequeuingStrategy {

	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> primerVehiculo = new ArrayList<Vehicle>();
		primerVehiculo.add(q.get(0));
		return primerVehiculo;
	}
	
}
