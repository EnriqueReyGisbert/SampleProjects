package simulator.model;

import java.util.*;
import simulator.misc.Pair;

public class SetWeatherEvent extends Event {
	
	private List<Pair<String, Weather>> ws;

	public SetWeatherEvent(int time, List<Pair<String, Weather>> ws) throws Exception {
		
		super(time);
		
		if (ws!= null)
			this.ws = ws;
		else
			throw new Exception("No se puede poner correctamente el tiempo");
		
	}

	@Override
	void execute(RoadMap map) throws Exception {
		
		for(int i = 0; i < ws.size(); i++) {
			map.getRoad(ws.get(i).getFirst()).setWeather(ws.get(i).getSecond());
		}
		
	}
	
	public String toString() {
		return "Modified the weather of all the roads";
	}
	
	
}
