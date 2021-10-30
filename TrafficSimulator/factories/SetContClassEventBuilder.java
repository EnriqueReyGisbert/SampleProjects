package simulator.factories;

import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import simulator.misc.Pair;
import simulator.model.*;

public class SetContClassEventBuilder extends Builder<Event> {

	public SetContClassEventBuilder() {
		super("set_cont_class");
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws JSONException, Exception {
		JSONArray ja = data.getJSONArray("info");
		List<Pair<String,Integer>> cs = new ArrayList<Pair<String,Integer>>();
		for (int i = 0; i < ja.length(); i++) {
			JSONObject json = ja.getJSONObject(i);
			cs.add(new Pair<String, Integer>(json.getString("vehicle"), json.getInt("class")));
		}
		return new SetContClassEvent(data.getInt("time"), cs);
	}

}
