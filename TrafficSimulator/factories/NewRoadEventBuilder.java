package simulator.factories;

import simulator.model.*;

public abstract class NewRoadEventBuilder extends Builder<Event> {

	public NewRoadEventBuilder(String type) {
		super(type);
	}

}
