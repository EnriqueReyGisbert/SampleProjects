package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveAllStrategy implements DequeuingStrategy {

	public List<Vehicle> dequeue(List<Vehicle> q) {
		List<Vehicle> primerVehiculo = new ArrayList<Vehicle>();
		primerVehiculo.addAll(q);
		return primerVehiculo;
	}

}
