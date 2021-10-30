package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;
import simulator.model.*;

public class NewCityRoadEventBuilder extends NewRoadEventBuilder {

	public NewCityRoadEventBuilder() {
		super("new_city_road");
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws JSONException, Exception {
		return new NewCityRoadEvent(data.getInt("time"), data.getString("id"), data.getString("src"), data.getString("dest"), 
				data.getInt("length"), data.getInt("co2limit"), data.getInt("maxspeed"), Weather.valueOf(data.getString("weather")));
	}
}
