package simulator.model;

public abstract class NewRoadEvent extends Event {
	
	protected String id, srcJun, destJun;
	protected int length, co2Limit, maxSpeed;
	Weather weather;

	public NewRoadEvent(int time, String id, String srcJun, String destJun, int length, int co2Limit, int maxSpeed, Weather weather) throws Exception {
		
		super(time);
		this.id = id;
		this.srcJun = srcJun;
		this.destJun = destJun;
		this.length = length;
		this.co2Limit = co2Limit;
		this.maxSpeed = maxSpeed;
		this.weather = weather;
		
	}

	abstract void execute(RoadMap map) throws Exception;

}
