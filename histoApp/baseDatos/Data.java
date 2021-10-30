package histoApp.baseDatos;

public abstract class Data {
	
	private String _id;
	
	public Data(String id) {
		_id = id;
	}
	
	public String getID() {
		return _id;
	}
	
	public void setID(String id) {
		_id = id;
	}
	
	public abstract void setRoute(String route);
	
	public abstract String getRoute();

}
