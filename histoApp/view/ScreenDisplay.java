package histoApp.view;

import java.util.*;
import javax.swing.*;
import histoApp.controller.*;
import histoApp.model.*;

public abstract class ScreenDisplay extends JPanel implements AppObserver {				// Pantalla
	
	private static final long serialVersionUID = 1L;
	
	Controller _ctrl;
	App _app;
	SystemView _frame;
	
	// Pila usada para controlar el flujo de pantallas de la aplicaci�n
	
	static Stack<JPanel> pilaPantallas = new Stack<JPanel>();
	
	
	public ScreenDisplay(Controller ctrl, SystemView frame, Object tipo) {
		
		_ctrl = ctrl;
		_frame = frame;
		
		// A�adir a la lista de Observadores (Patr�n Observador)
		
		_ctrl.addObserver(this);
		
		ScreenDisplay.pilaPantallas.push(this);
		createFrontPanel(tipo);
		
	}
	
	// Cada pantalla se organiza con el m�todo 'createFrontPanel' (Polimosfismo / Herencia / Abstract)
	
	protected abstract void createFrontPanel(Object tipo);

	@Override
	public void update(App app) {				// Update (Patr�n Observador)
		_app = app;
	}
	
}
