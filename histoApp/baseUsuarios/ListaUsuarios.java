package histoApp.baseUsuarios;

import java.util.*;
import org.json.*;

public class ListaUsuarios implements IDAOListaUsuarios {						// Data Access Object

	
	// LISTA DE DATA TRANSFER OBJECT
	
	private List<Usuario> listaUsuarios;
	
	
	// CONSTRUCTOR
	
	public ListaUsuarios(JSONArray ja) {
		
		/*
		
		JSONObject for user looks like this:
		
		"type" : "user"
		"information" : {
			"id" 		   : "userID",
        	"password"     : "userPassword,
        	"nickname"     : "userNickname"
        }

         
         JSONObject for administrator looks like this:
         
        "type" : "administrator"
		"information" : {
			"id" 		   : "userID",
        	"password"     : "userPassword,
        	"nickname"     : "userNickname",
            "code"         : "adminCode",
            "degree"       : 2
        }


		*/
		
		listaUsuarios = new ArrayList<Usuario>();
		
		for (int i = 0; i < ja.length(); i++) {
			
			JSONObject specificUser = ja.getJSONObject(i);
			
			if (specificUser.getString("type").equals("user")) {
				
				JSONObject infor = specificUser.getJSONObject("information");
				
				Usuario user = new Usuario(infor.getString("id"), specificUser.getString("type"), 
						infor.getString("password"), infor.getString("nickname"));
					
				listaUsuarios.add(user);
					
			}
			
			else if (specificUser.getString("type").equals("administrator")) {
			
				JSONObject infor = specificUser.getJSONObject("information");
				
				Administrador admin = new Administrador(infor.getString("id"), specificUser.getString("type"),
						infor.getString("password"), infor.getString("nickname"),
						infor.getString("code"), infor.getInt("degree"));
					
				listaUsuarios.add(admin);
					
			}
			
		}
		
		
	}
	
	
	// METODOS DE LA INTERFAZ DE DAO

	@Override
	public void annadirUsuario(Usuario usuario) {
		listaUsuarios.add(usuario);		
	}

	@Override
	public void eliminarUsuario(String id) {
		int i = buscarUsuario(id);
		if (i != -1)
			listaUsuarios.remove(i);
	}

	@Override
	public int buscarUsuario(String id) {
		int i = 0;
		while(i < listaUsuarios.size()) {
			if (listaUsuarios.get(i).getID().equals(id)) {
				return i;
			}
			i++;
		}
		return -1;
	}

	@Override
	public Usuario getUsuario(String id) {
		int i = buscarUsuario(id);
		if (i != -1)
			return listaUsuarios.get(i);
		else 
			return null;
	}


	@Override
	public List<Usuario> getUsuarios() {
		return listaUsuarios;
	}
	
	
}
