package histoApp.view;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class MitologiaDisplay extends ScreenDisplay {						// Pantalla Menu Mitología

	private static final long serialVersionUID = 1L;

	public MitologiaDisplay(Controller ctrl, SystemView frame) {
		super(ctrl, frame, null);
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(null);
		
		// Botón SALIR
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(350,50,70,30);
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ScreenDisplay.pilaPantallas.pop();
				MitologiaDisplay.this._frame.setDisplay("pantallaMenuPrincipal.jpg", ScreenDisplay.pilaPantallas.peek());
			}
		});
		
		// LISTA DE FICHAS MITOLOGIA
		
		List<Ficha> listaFichas = _app.getFichas().getFichas();
		
		int i = 165;
		for(Ficha f : listaFichas) {
			if(f.getTipo().equals(TiposFichas.mitologia)) {
				
				JButton botonFicha = new JButton(f.getID());
				botonFicha.setBounds(90,i,310,70);
				botonFicha.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						MitologiaDisplay.this._frame.setDisplay("Mapa.jpg", new FileDisplay(_ctrl, MitologiaDisplay.this._frame, f));
					}
				});
				this.add(botonFicha);
				
				i += 90;
			}
		}
		
		this.add(botonSalir);
		
		this.setOpaque(false);
		
	}

}
