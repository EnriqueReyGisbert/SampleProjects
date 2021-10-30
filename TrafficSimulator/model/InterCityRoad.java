package simulator.model;

public class InterCityRoad extends Road {

	InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
		
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
		
	}
	

	@Override
	void reduceTotalContamination() {
		
		int x = 2;

		if (this.condicionesAmbientales == Weather.CLOUDY)
			x = 3;
		else if (this.condicionesAmbientales == Weather.RAINY)
			x = 10;
		else if (this.condicionesAmbientales == Weather.WINDY)
			x = 15;
		else if (this.condicionesAmbientales == Weather.STORM)
			x = 20;
			
		this.contaminacionTotal = (int)(((100.0 - x)/(100.0))*this.contaminacionTotal);
		
		if (this.contaminacionTotal < 0)
			this.contaminacionTotal = 0;
		
	}

	@Override
	void updateSpeedLimit() {
		
		if (this.contaminacionTotal > this.limiteContaminacion)
			this.limiteActualVelocidad = (int)(this.velocidadMaxima*0.5);
		
		else
			this.limiteActualVelocidad = this.velocidadMaxima;
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) throws Exception {
		
		if (this.condicionesAmbientales != Weather.STORM)
			return this.limiteActualVelocidad;
		
		else
			return (int)(this.limiteActualVelocidad*0.8);
		
	}
	
}
