package histoApp.baseUsuarios;

public class Administrador extends Usuario {				// Data Transfer Object			
	
	
	// ATRIBUTOS PRIVADOS
	
	private String _codigo;
	private int _gradoModificacion;
	
	
	// CONSTRUCTOR

	public Administrador(String id, String type, String contrasenna, String apodo, String codigo, int grado) {
		super(id, type, contrasenna, apodo);
		_codigo = codigo;
		_gradoModificacion = grado;
	}
	
	
	// SETTERS
	
	public void setCodigo(String codigo) {
		_codigo = codigo;
	}
	
	public void setGrado(int grado) {
		_gradoModificacion = grado;
	}
	
	
	// GETTERS
	
	public String getCodigo() {
		return _codigo;
	}

	public int getGrado() {
		return _gradoModificacion;
	}
	
}
