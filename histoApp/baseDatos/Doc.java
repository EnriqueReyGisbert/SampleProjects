package histoApp.baseDatos;

public class Doc extends Data {
	
	private String _routeToDoc;

	public Doc(String id, String routeToDoc) {
		super(id);
		_routeToDoc = routeToDoc;
	}
	
	public void setRoute(String routeToDoc) {
		_routeToDoc = routeToDoc;
	}
	
	public String getRoute() {
		return _routeToDoc;
	}
	
}
