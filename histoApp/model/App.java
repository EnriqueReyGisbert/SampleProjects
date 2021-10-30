package histoApp.model;

import java.io.*;
import java.util.*;

import org.json.*;

import histoApp.baseDatos.*;
import histoApp.baseUsuarios.*;

public class App implements Observable<AppObserver>, IFachadaApp {			// Fachada Singleton (Observable)
	
	private boolean _isFinished = false;
	
	private ListaUsuarios _listaUsuarios;				// Lista de Usuarios (Data Access Object)
	private ListaFichas _listaFichas;					// Lista de Fichas (Data Access Object)
	private List<Mapa> _listaMapas;						// Lista de Mapas (Data Transfer Objects)
	
	private List<AppObserver> _listaObservadores;     	// Observadores (Patrón Observador)
	
	private OutputStream _outUsers;
	private OutputStream _outData;
	
	private static App unicaInstancia = null;			// Única instancia (Patrón Singleton)
	
	private static final String licencia = "0470";
	private static final String codigo = "0470";
	
	private App(InputStream inUsers, InputStream inData, OutputStream outUsers, OutputStream outData) throws FileNotFoundException, JSONException {
		
		// LECTURA DATOS + CREACIÓN DE OBJETOS TRANSFERENCIA + CRECIÓN DE DAO
		
		JSONObject joUsers = new JSONObject(new JSONTokener(inUsers));	
		JSONObject joData = new JSONObject(new JSONTokener(inData));	
		
		JSONArray jaUsers = joUsers.getJSONArray("usuarios");
		
		_listaUsuarios = new ListaUsuarios(jaUsers);
		
		JSONArray jaData = joData.getJSONArray("fichas");
		
		_listaFichas = new ListaFichas(jaData);
		
		_listaMapas = new ArrayList<Mapa>();
		
		JSONArray jaMaps = joData.getJSONArray("mapas");
		
		for (int i = 0; i < jaMaps.length(); i++) {
			
			JSONObject map = jaMaps.getJSONObject(i);
			
			Mapa mapa = new Mapa(map);
			
			_listaMapas.add(mapa);
			
		}
		
		_listaObservadores = new ArrayList<AppObserver>();
		
		_outUsers = outUsers;
		_outData = outData;
		
	}
	
	// AÑADIR FICHA
	
	public void annadirFicha(String id, String tipoFicha, String texto, String idTexto, String doc, String idDoc, String imagen, String idImagen, int tiempo) throws FileNotFoundException {
		
		Doc docFicha = new Doc(idDoc, doc);
		Imagen imagenFicha = new Imagen(idImagen, imagen);
		Texto textoFicha = new Texto(idTexto, texto);
		Ficha nuevaFicha = new Ficha(id, imagenFicha, docFicha, textoFicha, -1, -1, TiposFichas.valueOf(tipoFicha), tiempo);
		_listaFichas.annadirFicha(nuevaFicha);
		
		// Actualizar observadores
		
		actualizarObservadores();
		
	}
	
	// AÑADIR FICHA A MAPA
	
	public boolean annadirFichaMapa(String id, Integer x, Integer y) {
		Boolean encontrado = false;
		Ficha f = _listaFichas.getFicha(id);
		if (f != null) {
			encontrado = true;
			f.setX(x);
			f.setY(y);
		}
		
		// Actualizar observadores
		
		actualizarObservadores();
		
		return encontrado;
	}
	
	// MODIFICAR FICHA
	
	public boolean modificarFicha(String id, String _idCambio, String texto, String doc, String imagen) throws FileNotFoundException {
		
		boolean encontrado = false;
		
		Ficha f = _listaFichas.getFicha(id);
		
		if(f != null) {
			if (texto != null) {
				Texto text = new Texto(_idCambio, texto);
				f.setTexto(text);
				encontrado = true;
			}
			else if (doc != null) {
				Doc document = new Doc(_idCambio, doc);
				f.setDoc(document);
				encontrado = true;
			}
			else if (imagen != null) {
				Imagen image = new Imagen(_idCambio, imagen);
				f.setImagen(image);
				encontrado = true;
			}
		}
		
		// Actualizar observadores
		
		actualizarObservadores();
		
		return encontrado;
	}
	
	// AÑADIR USUARIO
	
	public void annadirUsuario(String id, String contrasenna, String apodo, String codigo, Integer grado) {
		
		Usuario usuario = null;
		if (codigo == null) {
			usuario = new Usuario(id, "user", contrasenna, apodo);
		}
		else {
			usuario = new Administrador(id, "administrator", contrasenna, apodo, codigo, grado);
		}
		_listaUsuarios.annadirUsuario(usuario);
		
		// Actualizar observadores
		
		actualizarObservadores();
		
	}
	
	// ELIMINAR USUARIO
	
	public void eliminarUsuario(String id) {
		
		_listaUsuarios.eliminarUsuario(id);
		
		// Actualizar observadores
		
		actualizarObservadores();
		
	}
	
	// BUSCAR USUARIO (IDENTIFICAR USUARIO)
	
	public boolean buscarUsuario(String id, String contrasenna) {

		Usuario usuario = _listaUsuarios.getUsuario(id);
		if (usuario != null) {
			return usuario.getContrasenna().equals(contrasenna);
		}
		return false;
		
	}
	
	// BUSCAR STRING (BUSCADOR)
	
	public Ficha buscarString(String stringBuscar) {
		
		List<Ficha> listaFichas = _listaFichas.getFichas();
		int i = 0;
		while(i < listaFichas.size()) {
			if (listaFichas.get(i).getTexto().getTexto().contains(stringBuscar)) {
				return listaFichas.get(i);
			}
			i++;
		}
		return null;
		
	}
	
	// MOVER BARRA TIEMPO
	
	public void mueveBarraTiempo(int p) {
		
		Mapa.setBarraTiempo(p);
		
		// Actualizar observadores
		
		actualizarObservadores();
		
	}
	
	// ACTUALIZACION DATOS + CERRAR APLICACIÓN
	
	public void loadDataAndFinish() throws JSONException, IOException {
		
		List<Usuario> listaUsuarios = _listaUsuarios.getUsuarios();
		List<Ficha> listaFichas = _listaFichas.getFichas();
		
		JSONArray writeUsers = new JSONArray();
		
		for (int i = 0; i < listaUsuarios.size(); i++) {
			
			JSONObject userWrite = new JSONObject();
			
			userWrite.put("type", listaUsuarios.get(i).getType());
			
			JSONObject informationUser = new JSONObject();
			
			informationUser.put("id", listaUsuarios.get(i).getID());
			informationUser.put("password", listaUsuarios.get(i).getContrasenna());
			informationUser.put("nickname", listaUsuarios.get(i).getApodo());
			
			if (listaUsuarios.get(i).getType().equals("administrator")) {
				
				informationUser.put("code", ((Administrador) listaUsuarios.get(i)).getCodigo());
				informationUser.put("degree", ((Administrador) listaUsuarios.get(i)).getGrado());
			
			}
			
			userWrite.put("information", informationUser);
			
			writeUsers.put(userWrite);
			
		}
		
		JSONObject usersInfor = new JSONObject();
		usersInfor.put("usuarios", writeUsers);
		_outUsers.write(usersInfor.toString(2).getBytes());
		
		JSONArray writeFiles = new JSONArray();
		
		for (int i = 0; i < listaFichas.size(); i++) {
			
			JSONObject fileWrite = new JSONObject();
	
			fileWrite.put("id", listaFichas.get(i).getID());
			
			JSONObject imageWrite = new JSONObject();
			imageWrite.put("id", listaFichas.get(i).getImagen().getID());
			imageWrite.put("route", listaFichas.get(i).getImagen().getRoute());
			fileWrite.put("image", imageWrite);

			JSONObject docWrite = new JSONObject();
			docWrite.put("id", listaFichas.get(i).getDoc().getID());
			docWrite.put("route", listaFichas.get(i).getDoc().getRoute());
			fileWrite.put("doc", docWrite);
			
			JSONObject textWrite = new JSONObject();
			textWrite.put("id", listaFichas.get(i).getTexto().getID());
			textWrite.put("route", listaFichas.get(i).getTexto().getRoute());
			fileWrite.put("text", textWrite);
			
			fileWrite.put("x", listaFichas.get(i).getX());
			fileWrite.put("y", listaFichas.get(i).getY());
			fileWrite.put("type", listaFichas.get(i).getTipo().toString());
			
			writeFiles.put(fileWrite);
			
		}
		
		JSONArray writeMaps = new JSONArray();
		
		for (int i = 0; i < _listaMapas.size(); i++) {
			
			JSONObject mapWrite = new JSONObject();
			
			JSONArray arrayFichas = new JSONArray();
			
			for (int j = 0; j < _listaMapas.get(i).getFichasAsociadas().size(); j++) {
				
				arrayFichas.put(_listaMapas.get(i).getFichasAsociadas().get(j));
				
			}
			
			mapWrite.put("fichasAsociadas", arrayFichas);

			mapWrite.put("titulo", _listaMapas.get(i).getTitulo());
			mapWrite.put("tipo", _listaMapas.get(i).getTipo().toString());
			
			writeMaps.put(mapWrite);
			
		}
		
		JSONObject dataInfor = new JSONObject();
		dataInfor.put("fichas", writeFiles);
		dataInfor.put("mapas", writeMaps);
		_outData.write(dataInfor.toString(2).getBytes());
		
		_isFinished = true;
		
	}
	
	public boolean getFinished() {
		return _isFinished;
	}
	
	// AÑADIR OBSERVADORES (Patrón Observador)

	@Override
	public void addObserver(AppObserver o) {
		
		_listaObservadores.add(o);
		
		// Actualizar observadores
		
		for (AppObserver ob : _listaObservadores) {
			ob.update(this);
		}
		
	}

	// ELIMINAR OBSERVADORES (Patrón Observador)
	
	@Override
	public void removeObserver(AppObserver o) {
		
		_listaObservadores.remove(o);
		
	}
	
	// ACTUALIZAR OBSERVADORES (Patrón Observador)
	
	private void actualizarObservadores() {
		for (AppObserver ob : _listaObservadores) {
			ob.update(this);
		}
	}
	
	public Mapa getMapa(TiposFichas tipo) {
		for (Mapa m : _listaMapas) {
			if(m.getTipo().equals(tipo)) {
				return m;
			}
		}
		return null;
	}
	
	public ListaFichas getFichas() {
		return _listaFichas;
	}
	
	public ListaUsuarios getUsuarios() {
		return _listaUsuarios;
	}
	
	public static String getLicencia() {
		return licencia;
	}
	
	public static String getCodigo() {
		return codigo;
	}
	
	// MÉTODOS SINGLETON (Patrón Singleton)
	
	private synchronized static void createInstance(InputStream inUsers, InputStream inData, OutputStream outUsers, OutputStream outData) throws FileNotFoundException, JSONException {
        if (unicaInstancia == null) { 
            unicaInstancia = new App(inUsers, inData, outUsers, outData);
        }
    }

    public static App getInstance(InputStream inUsers, InputStream inData, OutputStream outUsers, OutputStream outData) throws FileNotFoundException, JSONException {
        if (unicaInstancia == null) createInstance(inUsers, inData, outUsers, outData);
        return unicaInstancia;
    }

}
