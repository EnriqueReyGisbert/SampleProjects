package simulator.factories;

import java.util.*;
import org.json.JSONArray;
import org.json.JSONObject;
import simulator.model.*;

public class NewVehicleEventBuilder extends Builder<Event> {

	public NewVehicleEventBuilder() {
		super("new_vehicle");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		JSONArray ja = data.getJSONArray("itinerary");
		List<String> listaVehiculos = new ArrayList<String>();
		for (int i = 0; i < ja.length(); i++) {
			listaVehiculos.add(ja.getString(i));
		}
		return new NewVehicleEvent(data.getInt("time"), data.getString("id"), data.getInt("maxspeed"), data.getInt("class"), listaVehiculos);
	}

}
