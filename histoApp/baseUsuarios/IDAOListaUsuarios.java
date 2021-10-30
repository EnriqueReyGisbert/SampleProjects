package histoApp.baseUsuarios;

import java.util.List;

public interface IDAOListaUsuarios {						// Interfaz ListaUsuarios (Patrón DAO)

	public void annadirUsuario(Usuario usuario);
	public void eliminarUsuario(String id);
	public int buscarUsuario(String id);
	public Usuario getUsuario(String id);
	public List<Usuario> getUsuarios();
	
}
