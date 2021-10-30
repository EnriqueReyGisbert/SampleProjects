package histoApp.baseDatos;

public class Ficha {							// Data Transfer Object
	
	
	// ATRIBUTOS PRIVADOS
	
	private String _id;
	
	private Imagen _imagenAsociada;
	private Doc _docAsociado;
	private Texto _textoAsociado;
	
	private int _x = -1;
	private int _y = -1;
	
	private int _tiempo;
	
	private TiposFichas _tipoFicha;
	
	
	// CONSTRUCTOR
	
	public Ficha(String id, Imagen imagen, Doc doc, Texto texto, int x, int y, TiposFichas tipo, int tiempo) {
		_id = id;
		_imagenAsociada = imagen;
		_docAsociado = doc;
		_textoAsociado = texto;
		_x = x;
		_y = y;
		_tipoFicha = tipo;
		_tiempo = tiempo;
	}
	
	
	// SETTERS

	public void setID(String id) {
		_id = id;
	}

	public void setImagen(Imagen imagen) {
		_imagenAsociada = imagen;
	}

	public void setTexto(Texto texto) {
		_textoAsociado = texto;
	}

	public void setDoc(Doc doc) {
		_docAsociado = doc;
	}

	public void setX(int x) {
		_x = x;
	}

	public void setY(int y) {
		_y = y;
	}
	

	public void setTiempo(int tiempo) {
		_tiempo = tiempo;
	}

	public void setTipo(TiposFichas tipo) {
		_tipoFicha = tipo;
	}
	
	
	// GETTERS

	public String getID() {
		return _id;
	}

	public Imagen getImagen() {
		return _imagenAsociada;
	}

	public Texto getTexto() {
		return _textoAsociado;
	}

	public Doc getDoc() {
		return _docAsociado;
	}

	public int getX() {
		return _x;
	}

	public int getY() {
		return _y;
	}

	public TiposFichas getTipo() {
		return _tipoFicha;
	}

	public int getTiempo() {
		return _tiempo;
	}
	
}
