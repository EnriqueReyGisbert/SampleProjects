package histoApp.view;

import java.awt.event.*;
import javax.swing.*;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class MenuDisplay extends ScreenDisplay {							// Pantalla Menu Principal

	private static final long serialVersionUID = 1L;

	public MenuDisplay(Controller ctrl, SystemView frame) {
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
				MenuDisplay.this._frame.setDisplay("pantallaIdentificacion2.jpg", ScreenDisplay.pilaPantallas.peek());
			}
		});
		
		// Botón MENU SECUNDARIO

		JButton botonHistoria = new JButton("Historia");
		botonHistoria.setBounds(45,190,400,60);
		botonHistoria.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				 MenuDisplay.this._frame.setDisplay("pantallaMenuSecundario.jpg", new SecondaryMenuDisplay(_ctrl, MenuDisplay.this._frame));
			}
		});
		
		// Botón SUBMENU MITOLOGIA
		
		JButton botonMitologia = new JButton("Fichas Mitología");
		botonMitologia.setBounds(45,300,400,60);
		botonMitologia.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MenuDisplay.this._frame.setDisplay("submenuMitologia.jpg", new MitologiaDisplay(_ctrl, MenuDisplay.this._frame));
			}
		});
		
		// Botón MAPA BATALLAS
		
		JButton botonBatallas = new JButton("Mapa Batallas");
		botonBatallas.setBounds(45,412,400,60);
		botonBatallas.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MenuDisplay.this._frame.setDisplay("Mapa.jpg", new MapDisplay(_ctrl, MenuDisplay.this._frame, TiposFichas.batalla));
			}
		});
		
		// Botón BUSCADOR
		
		JButton botonBuscar = new JButton("Busqueda");
		botonBuscar.setBounds(200,580,100,60);
		botonBuscar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				MenuDisplay.this._frame.setDisplay("Buscador1.jpg", new SearchDisplay(_ctrl, MenuDisplay.this._frame));
			}
		});
		
		this.add(botonBuscar);
		this.add(botonSalir);
		this.add(botonHistoria);
		this.add(botonMitologia);
		this.add(botonBatallas);
		
		this.setOpaque(false);
		
	}
	
}
