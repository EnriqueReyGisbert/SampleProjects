package histoApp.baseDatos;

import java.util.*;
import org.json.*;

public class Mapa {							// Data Transfer Object
	
	
	// ATRIBUTOS PRIVADOS
	
	private List<String> _listaFichas;
	private static int _barraTiempo;
	private String _titulo;
	private TiposFichas _tipoMapa;
	
	
	// CONSTRUCTOR

	public Mapa(JSONObject jo) {
		
		
		/*
		
		JSONOject for map looks like this:
		
		"fichasAsociadas"        : [
        	"batallaCovadonga"
      	],
      	"titulo"                 : "mapabatallas",
      	"tipo"                   : "batalla"
		
		*/
		
		
		_listaFichas = new ArrayList<String>();
		
		JSONArray fichas = jo.getJSONArray("fichasAsociadas");
		
		for(int i = 0; i < fichas.length(); i++) {
			_listaFichas.add(fichas.getString(i));
		}

		_titulo = jo.getString("titulo");
		_tipoMapa = TiposFichas.valueOf(jo.getString("tipo"));
		
	}
	
	
	// SETTERS

	public void setFichasAsociadas(List<String> listaFichas) {
		_listaFichas = listaFichas;
	}

	public void setTitulo(String titulo) {
		_titulo = titulo;
	}

	public static void setBarraTiempo(int tiempo) {
		_barraTiempo = tiempo;
	}
	
	public void setTipo(TiposFichas tipo) {
		_tipoMapa = tipo;
	}


	// GETTERS
	
	public List<String> getFichasAsociadas() {
		return _listaFichas;
	}

	public String getTitulo() {
		return _titulo;
	}

	public static int getBarraTiempo() {
		return _barraTiempo;
	}

	public TiposFichas getTipo() {
		return _tipoMapa;
	}
	
}
