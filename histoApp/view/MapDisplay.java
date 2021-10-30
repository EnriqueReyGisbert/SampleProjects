package histoApp.view;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class MapDisplay extends ScreenDisplay {							// Pantalla Mapa

	private static final long serialVersionUID = 1L;
	
	private TiposFichas _tipo;
	private Map<String, JButton> mapaBotones;
	private Mapa _mapa;
	private JSpinner spinner;
	private static final int _JRADIUS = 15;

	public MapDisplay(Controller ctrl, SystemView frame, TiposFichas tipo) {
		super(ctrl, frame, tipo);
	}
	
	public void paintComponent(Graphics graphics) {				// PINTAR MAPA
		super.paintComponent(graphics);
		Graphics2D g = (Graphics2D) graphics;
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		for (String f : _mapa.getFichasAsociadas()) {
			
			Ficha ficha = _app.getFichas().getFicha(f);
			
			if (ficha != null) {
				if(!mapaBotones.containsKey(ficha.getID())) {
					JButton botonFicha = new JButton();
					botonFicha.setBounds(ficha.getX() - 8, ficha.getY() - 8, _JRADIUS, _JRADIUS);
					botonFicha.addActionListener(new ActionListener(){
						public void actionPerformed(ActionEvent ae){
							 MapDisplay.this._frame.setDisplay("Mapa.jpg", new FileDisplay(_ctrl, MapDisplay.this._frame, ficha));
						}
					});	
					this.add(botonFicha);
					mapaBotones.put(ficha.getID(), botonFicha);
				}
				
				if (ficha != null && ficha.getTipo().equals(_tipo) && ficha.getTiempo() - (int) spinner.getValue() > -100 && ficha.getTiempo() - (int) spinner.getValue() < 100 && ficha.getX() != -1 && ficha.getY() != -1) {
					g.setColor(Color.BLUE);
					g.fillOval(ficha.getX() - _JRADIUS / 2, ficha.getY() - _JRADIUS / 2, _JRADIUS, _JRADIUS);
					g.setColor(Color.BLACK);
					g.drawString(ficha.getID(), ficha.getX() - 10, ficha.getY() - 10);
					mapaBotones.get(ficha.getID()).setVisible(true);
				}
				else {
					mapaBotones.get(ficha.getID()).setVisible(false);
				}
			}	
		}
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(null);
		
		_tipo = (TiposFichas) tipo;
		
		mapaBotones = new TreeMap<String, JButton>();
		
		_mapa = _app.getMapa(_tipo);
		
		// Botón SALIR
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(20,55,70,30);
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ScreenDisplay.pilaPantallas.pop();
				if (_mapa.getTipo().equals(TiposFichas.batalla))
					MapDisplay.this._frame.setDisplay("pantallaMenuPrincipal.jpg", ScreenDisplay.pilaPantallas.peek());
				else
					MapDisplay.this._frame.setDisplay("pantallaMenuSecundario.jpg", ScreenDisplay.pilaPantallas.peek());
			}
		});	
		
		// TITULO
		
		JLabel titulo = new JLabel(_mapa.getTitulo());
		titulo.setFont(new Font(Font.DIALOG, Font.BOLD, 10));
		titulo.setBounds(100, 55, 100, 30);
		
		// BARRA TIEMPO
		
		SpinnerNumberModel timeBarModel = new SpinnerNumberModel(1900, -2020, 2020, 100);
		spinner = new JSpinner(timeBarModel);
		spinner.setBounds(30 ,600,100,40);
		spinner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				MoveTimeCommand c = new MoveTimeCommand((Integer) spinner.getValue());
				c.execute(_app);
				MapDisplay.this.repaint();
			}
        });
		
		this.add(spinner);
		this.add(botonSalir);
		this.add(titulo);
		
		this.setOpaque(false);
		
	}

}
