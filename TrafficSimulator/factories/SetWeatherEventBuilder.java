package simulator.factories;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.misc.Pair;
import simulator.model.*;

public class SetWeatherEventBuilder extends Builder<Event> {

	public SetWeatherEventBuilder() {
		super("set_weather");
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws JSONException, Exception {
		JSONArray ja = data.getJSONArray("info");
		List<Pair<String, Weather>> cs = new ArrayList<Pair<String,Weather>>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject json = ja.getJSONObject(i);
			cs.add(new Pair<String,Weather>(json.getString("road"), Weather.valueOf(json.getString("weather"))));
		}
		return new SetWeatherEvent(data.getInt("time"), cs);
	}

}
