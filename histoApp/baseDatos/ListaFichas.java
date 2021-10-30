package histoApp.baseDatos;

import java.io.*;
import java.util.*;
import org.json.*;

public class ListaFichas implements IDAOListaFichas {				// Data Access Object
	
	
	// LISTA DE DATA TRANSFER OBJECT
	
	private List<Ficha> listaFichas;
	
	
	// CONSTRUCTOR
	
	public ListaFichas(JSONArray ja) throws FileNotFoundException, JSONException {
		
		/*
		
		JSONObject for file looks like this:
		
		"id" : "fileID",
        "image"     : {
        	"id"        : "idImage",
        	"route"     : "routeToImage",
        }
        "doc"       : {
        	"id"        : "idDoc",
        	"route"     : "routeToDoc"
        }
        "text"      : {
        	"id"        : "idText",
        	"route"     : "routeToText"
        }
        "tiempo"    : 1910,
        "x"         : 400,
        "y"         : 500,
        "type"      : "mitologia"
		
		*/
		
		listaFichas = new ArrayList<Ficha>();
		
		for (int i = 0; i < ja.length(); i++) {
			
			JSONObject specificFile = ja.getJSONObject(i);
			
			JSONObject imageJSON = specificFile.getJSONObject("image");
			Imagen image = new Imagen(imageJSON.getString("id"), imageJSON.getString("route"));
			
			JSONObject docJSON = specificFile.getJSONObject("doc");
			Doc doc = new Doc(docJSON.getString("id"), docJSON.getString("route"));
			
			JSONObject textJSON = specificFile.getJSONObject("text");
			Texto text = new Texto(textJSON.getString("id"), textJSON.getString("route"));
			
			Ficha file = new Ficha(specificFile.getString("id"), image, doc, text,
					specificFile.getInt("x"), specificFile.getInt("y"), 
					TiposFichas.valueOf(specificFile.getString("type")), specificFile.getInt("tiempo"));
			
			listaFichas.add(file);
			
		}
		
	}
	
	
	// METODOS DE LA INTERFAZ DE DAO

	@Override
	public void annadirFicha(Ficha ficha) {
		listaFichas.add(ficha);
	}

	@Override
	public void eliminarFicha(String id) {
		int i = buscarFicha(id);
		if (i != -1)
			listaFichas.remove(i);
	}

	@Override
	public int buscarFicha(String id) {
		int i = 0;
		while(i < listaFichas.size()) {
			if (listaFichas.get(i).getID().equals(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public Ficha getFicha(String id) {
		int i = buscarFicha(id);
		if (i != -1)
			return listaFichas.get(i);
		else
			return null;
	}

	@Override
	public List<Ficha> getFichas() {
		return listaFichas;
	}

}
