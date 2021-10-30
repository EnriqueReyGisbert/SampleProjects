package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;
import simulator.model.*;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchingStrategy> {

	public MostCrowdedStrategyBuilder() {
		super("most_crowded_lss");
	}

	@Override
	protected LightSwitchingStrategy createTheInstance(JSONObject data) throws JSONException, Exception {
		if (data.has("timeslot"))
			return new MostCrowdedStrategy(data.getInt("timeslot"));
		else
			return new MostCrowdedStrategy(1);
	}

}
