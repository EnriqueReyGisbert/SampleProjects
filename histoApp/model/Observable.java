package histoApp.model;

public interface Observable<T> {						// Interfaz Observables (Patr�n Observador)
	
	void addObserver(T o);
	void removeObserver(T o);
	
}
