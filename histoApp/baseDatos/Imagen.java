package histoApp.baseDatos;

public class Imagen extends Data {
	
	private String _routeToImage;

	public Imagen(String id, String routeToImage) {
		super(id);
		_routeToImage = routeToImage;
	}
	
	public void setRoute(String routeToImage) {
		_routeToImage = routeToImage;
	}
	
	public String getRoute() {
		return _routeToImage;
	}

}
