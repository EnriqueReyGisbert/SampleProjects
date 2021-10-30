package histoApp.model;

public interface Observable<T> {						// Interfaz Observables (Patrón Observador)
	
	void addObserver(T o);
	void removeObserver(T o);
	
}
