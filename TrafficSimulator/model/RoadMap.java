package simulator.model;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;

public class RoadMap {
	
	private List<Junction> listaCruces;
	private List<Road> listaCarreteras;
	private List<Vehicle> listaVehiculos;
	private Map<String, Junction> mapaCruces;
	private Map<String, Road> mapaCarreteras;
	private Map<String, Vehicle> mapaVehiculos;
	
	RoadMap() {
		this.listaCruces = new ArrayList<Junction>();
		this.listaCarreteras = new ArrayList<Road>();
		this.listaVehiculos = new ArrayList<Vehicle>();
		this.mapaCruces = new HashMap<String, Junction>();
		this.mapaCarreteras = new HashMap<String, Road>();
		this.mapaVehiculos = new HashMap<String, Vehicle>();
	}
	
	void addJunction(Junction j) {
		
		if (!this.mapaCruces.containsKey(j.getId())) {
			
			this.listaCruces.add(j);
			this.mapaCruces.put(j.getId(), j);
			
		}
		
	}
	
	void addRoad(Road r) throws Exception {
		
		if (!this.mapaCarreteras.containsKey(r.getId())) { 
			
			if (this.listaCruces.contains(r.getCruceOrigen()) && this.listaCruces.contains(r.getCruceDestino())) {
				
				this.listaCarreteras.add(r);
				this.mapaCarreteras.put(r.getId(), r);
				
			}
			
			else
				throw new Exception("Alguno de los cruces que conecta la carretera a añadir no existe");
		}
		
	}
	
	void addVehicle(Vehicle v) throws Exception {
		
		if (!this.mapaVehiculos.containsKey(v.getId())) {
			
			if (v.itinerarioCorrecto()) {
				
				this.listaVehiculos.add(v);
				this.mapaVehiculos.put(v.getId(), v);
				
			}
			
			else
				throw new Exception("El itinerario de un vehículo no es correcto");
			
		}
		
	}
	
	public Junction getJunction(String id) {
		
		if (this.mapaCruces.containsKey(id))
			return this.mapaCruces.get(id);
		else
			return null;
		
	}
	
	public Road getRoad(String id) {
		
		if (this.mapaCarreteras.containsKey(id))
			return this.mapaCarreteras.get(id);
		else
			return null;
		
	}
	
	public Vehicle getVehicle(String id) {
		
		if (this.mapaVehiculos.containsKey(id))
			return this.mapaVehiculos.get(id);
		else
			return null;
		
	}
	
	public List<Junction> getJunctions(){
		
		return Collections.unmodifiableList(this.listaCruces);
		
	}
	
	public List<Road> getRoads(){
		
		return Collections.unmodifiableList(this.listaCarreteras);
		
	}
	
	public List<Vehicle> getVehicles(){
		
		return Collections.unmodifiableList(this.listaVehiculos);
		
	}
	
	public void advanceAll(int time) throws Exception {
		
		for (int i = 0; i < this.listaCruces.size(); i++) {
			this.listaCruces.get(i).advance(time);
		}
		
		for (int i = 0; i < listaCarreteras.size(); i++) {
			this.listaCarreteras.get(i).advance(time);
		}
		
	}
	
	void reset() {
		
		this.listaCruces.clear();
		this.listaCarreteras.clear();
		this.listaVehiculos.clear();
		
		this.mapaCruces.clear();
		this.mapaCarreteras.clear();
		this.mapaVehiculos.clear();
		
	}
	
	@SuppressWarnings("exports")
	public JSONObject report() {
		
		JSONObject reportMapa = new JSONObject();
		
		JSONArray j = new JSONArray();
		
		for(int i = 0; i < this.listaCruces.size(); i++)
			j.put(this.listaCruces.get(i).report());
		
		JSONArray c = new JSONArray();
		
		for(int i = 0; i < this.listaCarreteras.size(); i++)
			c.put(this.listaCarreteras.get(i).report());
		
		JSONArray v = new JSONArray();
		
		for(int i = 0; i < this.listaVehiculos.size(); i++)
			v.put(this.listaVehiculos.get(i).report());
		
		reportMapa.put("junctions", j);
		reportMapa.put("roads", c);
		reportMapa.put("vehicles", v);
		
		return reportMapa;
		
	}
}


