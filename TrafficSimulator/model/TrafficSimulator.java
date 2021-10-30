package simulator.model;

import java.util.*;
import org.json.*;
import simulator.misc.SortedArrayList;

public class TrafficSimulator implements Observable<TrafficSimObserver> {

	private RoadMap mapaDeCarreteras;
	private List<Event> listaDeEventos;
	private int tiempo;
	private List<TrafficSimObserver> observadores; 
	
	public TrafficSimulator() {
		this.mapaDeCarreteras = new RoadMap();
		this.listaDeEventos = new SortedArrayList<Event>();
		this.tiempo = 0;
		this.observadores = new ArrayList<TrafficSimObserver>();
	}
	
	public void addEvent(Event e) {
		
		this.listaDeEventos.add(e);
		
		for(TrafficSimObserver ob : observadores) {
			ob.onEventAdded(this.mapaDeCarreteras, this.listaDeEventos, e, this.tiempo);
		}
		
		
	}
	
	public void advance() throws Exception {
		
		this.tiempo++;
		
		for(TrafficSimObserver ob : observadores) {
			ob.onAdvanceStart(this.mapaDeCarreteras, this.listaDeEventos, this.tiempo);
		}
		
		for (int i = 0; i < this.listaDeEventos.size(); i++) {
			
			if (this.listaDeEventos.get(i).getTime() == this.tiempo) {
				
				this.listaDeEventos.get(i).execute(mapaDeCarreteras);
				this.listaDeEventos.remove(i);
				i--;
				
			}
			
		}
		
		this.mapaDeCarreteras.advanceAll(tiempo);	
		
		for(TrafficSimObserver ob : observadores) {
			ob.onAdvanceEnd(this.mapaDeCarreteras, this.listaDeEventos, this.tiempo);
		}
		
		
	}
	
	public void reset() {
		
		this.mapaDeCarreteras.reset();
		
		this.listaDeEventos.clear();
		
		this.tiempo = 0;
		
		for(TrafficSimObserver ob : observadores) {
			ob.onReset(this.mapaDeCarreteras, this.listaDeEventos, this.tiempo);
		}
		
		
	}
	
	@SuppressWarnings("exports")
	public JSONObject report() {
		
		JSONObject j = new JSONObject();
		j.put("time", this.tiempo);
		j.put("state", this.mapaDeCarreteras.report());
		return j;
		
	}

	@Override
	public void addObserver(TrafficSimObserver o) {
		
		this.observadores.add(o);
		
		for(TrafficSimObserver ob : observadores) {
			ob.onRegister(this.mapaDeCarreteras, this.listaDeEventos, this.tiempo);
		}
		
		
	}

	@Override
	public void removeObserver(TrafficSimObserver o) {
		this.observadores.remove(o);
	}
	
}
