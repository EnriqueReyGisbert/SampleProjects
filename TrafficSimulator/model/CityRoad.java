package simulator.model;

public class CityRoad extends Road{

	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
		
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		
	}

	@Override
	void reduceTotalContamination() {
		
		int x = 2;

		if (this.condicionesAmbientales == Weather.WINDY || this.condicionesAmbientales == Weather.STORM)
			x = 10;

		this.contaminacionTotal -= x;
		
		if (this.contaminacionTotal < 0)
			this.contaminacionTotal = 0;
		
	}

	@Override
	void updateSpeedLimit() {
		
		// empty by default
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) throws Exception {
		
		int s = this.limiteActualVelocidad;
		int f = v.getContaminationDegree();
		
		return (int)( ( (11.0-f) / 11.0 ) * s );
		
	}

}
