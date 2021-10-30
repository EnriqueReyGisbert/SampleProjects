package histoApp.model;

import java.io.*;
import histoApp.baseDatos.*;

public interface IFachadaApp {

	public void annadirFicha(String id, String tipoFicha, String texto, String idTexto, String doc, String idDoc, String imagen, String idImagen, int tiempo) throws FileNotFoundException;
	public boolean annadirFichaMapa(String id, Integer x, Integer y);
	public boolean modificarFicha(String id, String _idCambio, String texto, String doc, String imagen) throws FileNotFoundException;
	public void annadirUsuario(String id, String contrasenna, String apodo, String codigo, Integer grado);
	public void eliminarUsuario(String id);
	public boolean buscarUsuario(String id, String contrasenna);
	public Ficha buscarString(String stringBuscar);
	public void mueveBarraTiempo(int p);
	
}
