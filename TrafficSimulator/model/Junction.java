package simulator.model;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class Junction extends SimulatedObject {
	
	private List<Road> carreterasEntrantes;
	private Map<Junction, Road> carreterasSalientes;
	private Map<Road, List<Vehicle>> carreteraCola;
	private List<List<Vehicle>> listaDeColas;
	private int indiceSemaforoVerde;
	private int ultimoPasoCambioSemaforo;
	private LightSwitchingStrategy estrategiaCambioSemaforo;
	private DequeuingStrategy estrategiaExtraerCola;
	
	private int x;
	private int y;

	Junction(String id, LightSwitchingStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
		
		super(id);
		
		if (lsStrategy != null && dqStrategy != null && xCoor >= 0 && yCoor >= 0) {
			this.estrategiaCambioSemaforo = lsStrategy;
			this.estrategiaExtraerCola = dqStrategy;
			this.x = xCoor;
			this.y = yCoor;
			this.indiceSemaforoVerde = -1;
			this.carreterasEntrantes = new ArrayList<Road>();
			this.carreterasSalientes = new HashMap<Junction, Road>();
			this.carreteraCola = new HashMap<Road, List<Vehicle>>();
			this.listaDeColas = new ArrayList<List<Vehicle>>();
		}
		
	}
	
	void addIncommingRoad(Road r) throws Exception {   
		
		if (r.getCruceDestino().equals(this)) {

			this.carreterasEntrantes.add(r);
			
			ArrayList<Vehicle> cola = new ArrayList<Vehicle>();
			listaDeColas.add(cola);
			carreteraCola.put(r, cola);
			
		}
		
		else
			throw new Exception("La carretera del método addIncommingRoad no es entrante");

		
	}

	void addOutGoingRoad(Road r) throws Exception {
		
		if (r.getCruceOrigen().equals(this) && !carreterasSalientes.containsKey(r.getCruceDestino())) {
			
			carreterasSalientes.put(r.getCruceDestino(), r);
			
		}
		
		else if (!r.getCruceOrigen().equals(this))
			throw new Exception("La carretera del método addOutGoingRoad no es saliente");
		
		else
			throw new Exception("La carretera del método addOutGoingRoad ya existe");
		
	}
	
	void enter(Road r, Vehicle v) throws Exception {
		
		int index = this.carreterasEntrantes.indexOf(r);
		this.listaDeColas.get(index).add(v);
		
	}
	
	Road roadTo(Junction j) {
		
		if (this.carreterasSalientes.containsKey(j))
			return this.carreterasSalientes.get(j);
		else
			return null;
		
	}
	
	void advance(int time) throws Exception {
		
		if (this.indiceSemaforoVerde != -1) {
			
			if (!listaDeColas.get(this.indiceSemaforoVerde).isEmpty()) {
				
				List<Vehicle> vehiculosAvanzar = this.estrategiaExtraerCola.dequeue(listaDeColas.get(this.indiceSemaforoVerde));
				listaDeColas.get(this.indiceSemaforoVerde).removeAll(vehiculosAvanzar);
			 
				for (int i = 0; i < vehiculosAvanzar.size(); i++)
					vehiculosAvanzar.get(i).moveToNextRoad();
			}
		}
		
		int nextGreen = this.estrategiaCambioSemaforo.chooseNextGreen(carreterasEntrantes, listaDeColas, indiceSemaforoVerde, ultimoPasoCambioSemaforo, time);
		
		if (nextGreen != this.indiceSemaforoVerde) {
			
			this.indiceSemaforoVerde = nextGreen;
			this.ultimoPasoCambioSemaforo = time;
			
		}

	}
	
	@SuppressWarnings("exports")
	public JSONObject report() {
		
		JSONObject reportJunction = new JSONObject();

		reportJunction.put("id", this._id);
		
		if (this.indiceSemaforoVerde != -1)
			reportJunction.put("green", this.carreterasEntrantes.get(indiceSemaforoVerde).getId());
		else
			reportJunction.put("green", "none");
		
		JSONArray arrayColasEntrantes = new JSONArray();
		
		for (int i = 0; i < this.carreterasEntrantes.size(); i++) {
			
			JSONObject c = new JSONObject();
			
			c.put("road", this.carreterasEntrantes.get(i).getId());
			
			JSONArray cv = new JSONArray();
			
			for (int j = 0; j < this.carreteraCola.get(this.carreterasEntrantes.get(i)).size(); j++)
				cv.put(this.carreteraCola.get(this.carreterasEntrantes.get(i)).get(j).toString());
			
			c.put("vehicles", cv);
			
			arrayColasEntrantes.put(c);
			
		}
		
		reportJunction.put("queues", arrayColasEntrantes);
		
		return reportJunction;
		
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getGreenLightIndex() {
		return this.indiceSemaforoVerde;
	}
	
	public List<Road> getInRoads(){
		return this.carreterasEntrantes;
	}
	
	public Road getRoadAtGreen() {
		return this.carreterasEntrantes.get(indiceSemaforoVerde);
	}
	
	public Map<Road, List<Vehicle>> getQueues(){
		return this.carreteraCola;
	}
	
}
