package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchingStrategy {


	private int ticVerdeSeguidosSemaforo;
	
	public MostCrowdedStrategy(int timeSlot) {
		this.ticVerdeSeguidosSemaforo = timeSlot;
	}

	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime, int currTime) {

		if (roads.isEmpty())
			return -1;
		
		else if (currGreen == -1) {
			
			int max = 0;
		
			for (int i = 0; i < qs.size(); i++) {
				
				if (qs.get(i).size() > qs.get(max).size())
					max = i;
				
			}
			
			return max;
			
		}
		
		else if ((currTime - lastSwitchingTime) < this.ticVerdeSeguidosSemaforo)
			return currGreen;
		
		else {
			
			int max = currGreen + 1;
			
			for (int i = currGreen + 1; i < qs.size() + currGreen + 1; i++) {
				
				if (qs.get(i % qs.size()).size() > qs.get(max).size())
					max = i % qs.size();
				
			}
			
			return max;
			
		}
		
	}
	
}
