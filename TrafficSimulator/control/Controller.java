package simulator.control;

import simulator.factories.*;
import simulator.model.*;
import java.io.*;
import org.json.*;

public class Controller {
	
	private TrafficSimulator trafficSimulator;
	private Factory<Event> eventsFactory;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) throws Exception {
		if(sim != null && eventsFactory != null) {
			this.trafficSimulator = sim;
			this.eventsFactory = eventsFactory;
		}
		else
			throw new Exception("Los argumentos del controlador son incorrectos");
	}
	
	public void loadEvents(InputStream in) throws JSONException, Exception {
		
		JSONObject jo = new JSONObject(new JSONTokener(in));				
		JSONArray ja = new JSONArray();
		ja = jo.getJSONArray("events");
		
		for (int i = 0; i < ja.length(); i++)
			this.addEvent(eventsFactory.createInstance(ja.getJSONObject(i)));

		
	}
	
	public void run(int n) throws Exception {
		this.run(n, null);
	}
	
	public void run(int n, OutputStream out) throws Exception {

		if(out == null) {
			
			for (int i = 0; i < n; i++)
				trafficSimulator.advance();
			
		}
		
		else {
			
			JSONArray ja = new JSONArray();
			
			for (int i = 0; i < n; i++) {
				
				trafficSimulator.advance();
				ja.put(trafficSimulator.report());
				
			}
			
			JSONObject jo = new JSONObject();
			jo.put("states", ja);
		
			out.write(jo.toString(2).getBytes());
			
		}
	}
	
	public void reset() {
		
		trafficSimulator.reset();
		
	}
	
	public void addObserver(TrafficSimObserver o) {
		
		this.trafficSimulator.addObserver(o);
		
	}
	
	public void removeObserver(TrafficSimObserver o) {
		
		this.trafficSimulator.removeObserver(o);
		
	}
	
	public void addEvent(Event e) {
		
		this.trafficSimulator.addEvent(e);
		
	}

}
