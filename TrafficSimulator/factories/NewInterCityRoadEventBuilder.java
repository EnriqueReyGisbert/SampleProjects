package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.*;

public class NewInterCityRoadEventBuilder extends NewRoadEventBuilder {

	public NewInterCityRoadEventBuilder() {
		super("new_inter_city_road");
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws JSONException, Exception {
		return new NewInterCityRoadEvent(data.getInt("time"), data.getString("id"), data.getString("src"), data.getString("dest"), 
				data.getInt("length"), data.getInt("co2limit"), data.getInt("maxspeed"), Weather.valueOf(data.getString("weather")));
	}

}
