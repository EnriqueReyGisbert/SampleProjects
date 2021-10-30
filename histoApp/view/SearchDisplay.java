package histoApp.view;

import java.awt.event.*;
import javax.swing.*;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class SearchDisplay extends ScreenDisplay {							// Pantalla Buscador

	private static final long serialVersionUID = 1L;

	public SearchDisplay(Controller ctrl, SystemView frame) {
		super(ctrl, frame, null);
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(null);

		// Botón SALIR
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(20,600,70,30);
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ScreenDisplay.pilaPantallas.pop();
				SearchDisplay.this._frame.setDisplay("pantallaMenuPrincipal.jpg", SearchDisplay.pilaPantallas.peek());
			}
		});
		
		// TEXTO A BUSCAR
		
		JTextField textoBuscar = new JTextField();
		textoBuscar.setBounds(45,100, 400, 50);
		
		// Botón BUSCAR
		
		JButton botonBuscar = new JButton("Buscar");
		botonBuscar.setBounds(200,600,100,30);
		botonBuscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(!textoBuscar.getText().equals("")) {
					Ficha f = null;
					SearchCommand c = new SearchCommand(textoBuscar.getText());
					f = c.execute(_app);
					if(f != null)
						SearchDisplay.this._frame.setDisplay("Mapa.jpg", new FileDisplay(_ctrl, SearchDisplay.this._frame, f));
					else
						JOptionPane.showMessageDialog(new JFrame(), "Not found", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		this.add(botonBuscar);
		this.add(textoBuscar);
		this.add(botonSalir);
		
		this.setOpaque(false);
		
	}

}
