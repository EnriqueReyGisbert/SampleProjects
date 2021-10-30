package simulator.model;

import java.util.List;
import simulator.misc.Pair;

public class SetContClassEvent extends Event {
	
	private List<Pair<String,Integer>> cs;
	
	public SetContClassEvent(int time, List<Pair<String,Integer>> cs) throws Exception {
		
		super(time);
		if(cs != null)
			this.cs = cs;
		else
			throw new Exception("No se puede poner correctamente el límite de contaminación");
		
	}

	@Override
	void execute(RoadMap map) throws Exception {
		
		for(int i = 0; i < cs.size(); i++) {
			
			map.getVehicle(cs.get(i).getFirst()).setContaminationClass(cs.get(i).getSecond());
			
		}
		
	}
	
	public String toString() {
		return "Modified the contamination class of all the roads";
	}
	

}
