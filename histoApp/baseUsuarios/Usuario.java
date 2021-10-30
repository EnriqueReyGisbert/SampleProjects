package histoApp.baseUsuarios;

public class Usuario {							// Data Transfer Object
	
	
	// ATRIBUTOS PRIVADOS
	
	private String _id;
	private String _contrasenna;
	private String _apodo;
	private String _type;
	
	
	// CONSTRUCTOR
	
	public Usuario(String id, String type, String contrasenna, String apodo) {
		_id = id;
		_contrasenna = contrasenna;
		_apodo = apodo;
		_type = type;
	}

	
	// SETTERS
	
	public void setID(String id) {
		_id = id;	
	}

	public void setContrasenna(String contrasenna) {
		_contrasenna = contrasenna;
	}

	public void setApodo(String apodo) {
		_apodo = apodo;
	}
	
	public void setType(String type) {
		_type = type;
	}
	
	
	// GETTERS

	public String getID() {
		return _id;
	}

	public String getContrasenna() {
		return _contrasenna;
	}

	public String getApodo() {
		return _apodo;
	}
	
	public String getType() {
		return _type;
	}
	
}
