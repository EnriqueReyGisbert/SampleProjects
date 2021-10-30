package simulator.model;

public class NewInterCityRoadEvent extends NewRoadEvent {

	public NewInterCityRoadEvent(int time, String id, String srcJun, String destJun, int length, int co2Limit, int maxSpeed, Weather weather) throws Exception {
		
		super(time, id, srcJun, destJun, length, co2Limit, maxSpeed, weather);
		
	}
	
	@Override
	void execute(RoadMap map) throws Exception {
		
		Junction src = map.getJunction(srcJun);
		Junction dest = map.getJunction(destJun);
		InterCityRoad r = new InterCityRoad(id, src, dest, maxSpeed, co2Limit, length, weather);
		map.addRoad(r);
		
	}

	public String toString() {
		return "New Inter City Road '" + this.id + "'";
	}
	
}
