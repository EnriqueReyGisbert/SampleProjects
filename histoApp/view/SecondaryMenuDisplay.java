package histoApp.view;

import java.awt.event.*;
import javax.swing.JButton;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class SecondaryMenuDisplay extends ScreenDisplay {						// Pantalla Menu Secundario

	private static final long serialVersionUID = 1L;

	public SecondaryMenuDisplay(Controller ctrl, SystemView frame) {
		super(ctrl, frame, null);
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(null);
		
		// Botón SALIR
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(20,55,70,30);
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ScreenDisplay.pilaPantallas.pop();
				SecondaryMenuDisplay.this._frame.setDisplay("pantallaMenuPrincipal.jpg", ScreenDisplay.pilaPantallas.peek());
			}
		});
		
		// Botón MAPA IMPERIOS
		
		JButton botonImperios = new JButton("Mapa Imperios");
		botonImperios.setBounds(45,240,400,80);
		botonImperios.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				SecondaryMenuDisplay.this._frame.setDisplay("Mapa.jpg", new MapDisplay(_ctrl, SecondaryMenuDisplay.this._frame, TiposFichas.imperio));
			}
		});
		
		// Botón MAPA TERRITORIOS
		
		JButton botonTerritorios = new JButton("Mapa Territorios");
		botonTerritorios.setBounds(45,350,400,80);
		botonTerritorios.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				SecondaryMenuDisplay.this._frame.setDisplay("Mapa.jpg", new MapDisplay(_ctrl, SecondaryMenuDisplay.this._frame, TiposFichas.territorio));
			}
		});
		
		this.add(botonSalir);
		this.add(botonImperios);
		this.add(botonTerritorios);
		
		this.setOpaque(false);
		
	}
	
}
