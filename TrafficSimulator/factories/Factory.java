package simulator.factories;

import org.json.JSONException;
import org.json.JSONObject;

public interface Factory<T> {
	
	public T createInstance(@SuppressWarnings("exports") JSONObject info) throws JSONException, Exception;
	
}
