package histoApp.baseDatos;

import java.io.*;

public class Texto extends Data {
	
	private String _routeToTextFile;
	private String _texto;

	public Texto(String id, String routeToTextFile) throws FileNotFoundException {
		super(id);
		_routeToTextFile = routeToTextFile;
		
		File archivo = new File ("resources/texts/" + routeToTextFile);
		FileReader fr = null;
		fr = new FileReader (archivo);
		
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(fr);
		try {
			_texto = "<html>" + br.readLine() + "</html>";
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}

	public void setRoute(String routeToTextFile) {
		_routeToTextFile = routeToTextFile;
	}
	
	public String getRoute() {
		return _routeToTextFile;
	}
	
	public String getTexto() {
		return _texto;
	}

}
