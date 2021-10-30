package simulator.factories;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import simulator.model.*;

public class NewJunctionEventBuilder extends Builder<Event> {
	
	Factory<LightSwitchingStrategy> ls;
	Factory<DequeuingStrategy> dq;

	public NewJunctionEventBuilder(Factory<LightSwitchingStrategy> lssFactory, Factory<DequeuingStrategy> dqsFactory) {
		super("new_junction");
		this.ls = lssFactory;
		this.dq = dqsFactory;
	}

	@Override
	protected Event createTheInstance(JSONObject data) throws JSONException, Exception {
		JSONArray ja = data.getJSONArray("coor");
		return new NewJunctionEvent(data.getInt("time"), data.getString("id"), ls.createInstance(data.getJSONObject("ls_strategy")), dq.createInstance(data.getJSONObject("dq_strategy")) , ja.getInt(0), ja.getInt(1));
	}
	
}
