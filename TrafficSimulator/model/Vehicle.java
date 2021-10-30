package simulator.model;

import java.util.*;
import org.json.JSONObject;

public class Vehicle extends SimulatedObject {
	
	private final static int MAX_CONTAMINACION  = 10;

	private List<Junction> itinerario;
	private int velocidadMaxima;
	private int velocidadActual;
	private VehicleStatus estado;
	private Road carreteraAsociada;
	private int localizacion;
	private int gradoContaminacion;
	private int contaminacionTotal;
	private int distancioTotalRecorrida;
	private int indiceCruce;

	// CONSTRUCTOR
	Vehicle(String id, int maxSpeed, int contClass, List<Junction> itinerary) throws Exception {

		super(id);

		if (maxSpeed > 0 && 0 <= contClass && contClass <= 10 && itinerary.size() >= 2) {
			this._id = id;
			this.velocidadMaxima = maxSpeed;
			this.gradoContaminacion = contClass;
			this.itinerario = Collections.unmodifiableList(new ArrayList<Junction>(itinerary));
			this.estado = VehicleStatus.PENDING;
		}

		else
			throw new Exception("Los argumentos de un vehículo no son adecuados");

	}
	
	boolean itinerarioCorrecto() {
		
		boolean correcto = true;
		
		for(int i = 0; i < this.itinerario.size() - 1; i++) {
			if (this.itinerario.get(i).roadTo(this.itinerario.get(i + 1)) == null)
				correcto = false;
		}
		
		return correcto;
		
	}

	// initializes velocidadActual to the minimum between s and velocidadMaxima, if s < 0
	void setSpeed(int s) throws Exception {

		if (s < 0)
			throw new Exception("Argumento del método setSpeed incorrecto");

		if (s < this.velocidadMaxima)
			this.velocidadActual = s;
		else
			this.velocidadActual = this.velocidadMaxima;

	}

	// initializes gradoContaminacion to the value c between 0 and 10
	void setContaminationClass(int c) throws Exception {

		if (c < 0 || MAX_CONTAMINACION < c)
			throw new Exception("Argumento del método setContaminationClass incorrecto");

		this.gradoContaminacion = c;

	}

	// ADVANCE
	void advance(int time) throws Exception {

		if (this.estado == VehicleStatus.TRAVELING) {

			int localizacionPrevia = this.localizacion;

			if (this.velocidadActual + this.localizacion < this.carreteraAsociada.devLong())
				this.localizacion += this.velocidadActual;
			else {
				this.localizacion = this.carreteraAsociada.devLong();
			}

			this.distancioTotalRecorrida += this.localizacion - localizacionPrevia;
			
			int c = this.gradoContaminacion * (this.localizacion - localizacionPrevia);

			this.contaminacionTotal += c;

			this.carreteraAsociada.addContamination(c);

			if (this.localizacion == this.carreteraAsociada.devLong()) {
				this.carreteraAsociada.getCruceDestino().enter(this.carreteraAsociada, this);
				this.estado = VehicleStatus.WAITING;
				this.velocidadActual = 0;
			}

		}

	}

	// NEXT ROAD
	void moveToNextRoad() throws Exception {

		if (this.estado == VehicleStatus.PENDING || this.estado == VehicleStatus.WAITING) {

			if (this.estado == VehicleStatus.WAITING) {
				this.carreteraAsociada.exit(this);
			}

			this.localizacion = 0;
			this.velocidadActual = 0;
			
			if (this.indiceCruce != this.itinerario.size() - 1) {
				Road next = this.itinerario.get(this.indiceCruce)
						.roadTo(this.itinerario.get(this.indiceCruce + 1));
				this.carreteraAsociada = next;
				this.carreteraAsociada.enter(this);
				this.estado = VehicleStatus.TRAVELING;
				this.indiceCruce++;
			} 
			
			else
				this.estado = VehicleStatus.ARRIVED;

		}

		else
			throw new Exception("Un vehículo no tiene el estado adecuado en el método moveToNextRoad");

	}

	// JSON GENERATOR
	@SuppressWarnings("exports")
	public JSONObject report() {

		JSONObject reportVehicle = new JSONObject();

		reportVehicle.put("id", this._id);
		reportVehicle.put("speed", this.velocidadActual);
		reportVehicle.put("distance", this.distancioTotalRecorrida);
		reportVehicle.put("co2", this.contaminacionTotal);
		reportVehicle.put("class", this.gradoContaminacion);
		reportVehicle.put("status", this.estado.toString());

		if (this.estado == VehicleStatus.WAITING || this.estado == VehicleStatus.TRAVELING) {

			reportVehicle.put("road", this.carreteraAsociada.getId());
			reportVehicle.put("location", this.localizacion);

		}

		return reportVehicle;
	}
	
	public int getSpeed() {
		return this.velocidadActual;
	}
	
	public int getLocation() {
		return this.localizacion;
	}
	
	public int getContaminationDegree() {
		return this.gradoContaminacion;
	}
	
	public void setState(VehicleStatus v) {
		this.estado = v;
	}
	
	public VehicleStatus getState() {
		return this.estado;
	}
	
	public Road getRoad() {
		return this.carreteraAsociada;
	}
	
	public List<Junction> getItinerary(){
		return this.itinerario;
	}
	
	public int getMaxSpeed() {
		return this.velocidadMaxima;
	}
	
	public int getCO2() {
		return this.contaminacionTotal;
	}
	
	public int getDistance() {
		return this.distancioTotalRecorrida;
	}

}
