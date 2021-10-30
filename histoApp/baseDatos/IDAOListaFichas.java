package histoApp.baseDatos;

import java.util.*;

public interface IDAOListaFichas {						// Interfaz ListaFichas (Patrón DAO)

	public void annadirFicha(Ficha ficha);
	public void eliminarFicha(String id);
	public int buscarFicha(String id);
	public Ficha getFicha(String id);
	public List<Ficha> getFichas();
	
}
