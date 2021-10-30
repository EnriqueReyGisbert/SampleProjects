package simulator.model;

import java.util.*;
import org.json.*;

public abstract class Road extends SimulatedObject {
	
	protected Junction origen;
	protected Junction destino;
	protected int longitud;
	protected int limiteActualVelocidad;
	protected int velocidadMaxima;
	protected int limiteContaminacion;
	protected Weather condicionesAmbientales;
	protected int contaminacionTotal;
	protected List<Vehicle> vehiculos;
	
	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) throws Exception {
		
		super(id);
		
		if (maxSpeed > 0 && contLimit >= 0 && length > 0 && srcJunc != null && destJunc != null && weather != null ) {
			this.velocidadMaxima = maxSpeed;
			this.limiteActualVelocidad = maxSpeed;
			this.limiteContaminacion = contLimit;
			this.longitud = length;
			this.origen = srcJunc;
			this.destino = destJunc;
			this.condicionesAmbientales = weather;
			this.vehiculos = new ArrayList<Vehicle>();
			srcJunc.addOutGoingRoad(this);
			destJunc.addIncommingRoad(this);
		}
		
		else
			throw new Exception("Los parámetros de entrada de una carretera son incorrectos");
			
			
	}
	
	void enter(Vehicle v) throws Exception {
		
		if (v.getLocation() == 0 && v.getSpeed() == 0)
			this.vehiculos.add(v);
		
		else
			throw new Exception("El vehículo a añadir no tiene las condiciones adecuadas");
		
	}
	
	void exit(Vehicle v) {
		
		this.vehiculos.remove(v);
		
	}
	
	void setWeather(Weather w) throws Exception {
		
		if (w != null)
			this.condicionesAmbientales = w;
		
		else
			throw new Exception("Las condiciones ambientales no están definidas");
		
	}
	
	void addContamination(int c) throws Exception {
		
		if (c >= 0)
			this.contaminacionTotal += c;
		
		else
			throw new Exception("La contaminación a añadir es errónea");
		
	}
	
	abstract void reduceTotalContamination();
	
	abstract void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v) throws Exception;
	
	void advance(int time) throws Exception {
		
		this.reduceTotalContamination();
		
		this.updateSpeedLimit();
		
		for(int i = 0; i < this.vehiculos.size(); i++) {
			
			if(this.vehiculos.get(i).getState() == VehicleStatus.TRAVELING)
				this.vehiculos.get(i).setSpeed(this.calculateVehicleSpeed(this.vehiculos.get(i)));
			this.vehiculos.get(i).advance(time);
			
		}
		
		Collections.sort(this.vehiculos, new Comparator<Vehicle>() {
			@SuppressWarnings("deprecation")
			public int compare(Vehicle v1, Vehicle v2) {
				return new Integer(v2.getLocation()).compareTo(new Integer(v1.getLocation()));
			}
		});
		
	}
	
	@SuppressWarnings("exports")
	public JSONObject report() {
		
		JSONObject reportRoad = new JSONObject();

		reportRoad.put("id", this._id);
		reportRoad.put("speedlimit", this.limiteActualVelocidad);
		reportRoad.put("weather", this.condicionesAmbientales.toString());
		reportRoad.put("co2", this.contaminacionTotal);
		
		JSONArray ar = new JSONArray();
		
		for(int i = 0; i < this.vehiculos.size(); i++) {
			ar.put(this.vehiculos.get(i).getId());
		}
		
		reportRoad.put("vehicles", ar);

		return reportRoad;
	}
	
	public int devLong() {
		return this.longitud;
	}
	
	public Junction getCruceDestino() {
		return this.destino;
	}
	
	public Junction getCruceOrigen() {
		return this.origen;
	}
	
	public List<Vehicle> getVehicles() {
		return Collections.unmodifiableList(new ArrayList<Vehicle>(this.vehiculos));
	}
	
	public int getTotalCO2() {
		return this.contaminacionTotal;
	}
	
	public int getCO2Limit() {
		return this.limiteContaminacion;
	}
	
	public String getWeather() {
		return this.condicionesAmbientales.toString();
	}
	
	public int getMaxSpeed() {
		return this.velocidadMaxima;
	}
	
	public int getSpeedLimit() {
		return this.limiteActualVelocidad;
	}
}
