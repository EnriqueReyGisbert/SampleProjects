package histoApp.view;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.*;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class FileDisplay extends ScreenDisplay {					// Pantalla Ficha

	private static final long serialVersionUID = 1L;
	
	private Ficha _ficha;

	public FileDisplay(Controller ctrl, SystemView frame, Ficha ficha) {
		super(ctrl, frame, ficha);
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		_ficha = (Ficha) tipo;
		
		// Botón SALIR

		JButton botonSalir = new JButton("Salir");
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ScreenDisplay.pilaPantallas.pop();
				if(_ficha.getTipo().equals(TiposFichas.mitologia))
					FileDisplay.this._frame.setDisplay("submenuMitologia.jpg", ScreenDisplay.pilaPantallas.peek());
				else {
					if (ScreenDisplay.pilaPantallas.peek() instanceof SearchDisplay) {
						FileDisplay.this._frame.setDisplay("Buscador1.jpg", ScreenDisplay.pilaPantallas.peek());
					}
					else
						FileDisplay.this._frame.setDisplay("Mapa.jpg", ScreenDisplay.pilaPantallas.peek());
				}
			}
		});
		
		// Botón MAS INFORMACION
		
		JButton botonLink = new JButton("Mas información");
		botonLink.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				URI uri = null;
				try {
					uri = new URI(_ficha.getDoc().getRoute());
				} catch (URISyntaxException e) {
					JOptionPane.showMessageDialog(new JFrame(), "No se encuentra el archivo buscado", "Error",
		    		        JOptionPane.ERROR_MESSAGE);
				}
				open(uri);
			}
		});
		
		// TEXTO
		
		JLabel texto = new JLabel(_ficha.getTexto().getTexto());
		
		// IMAGEN
		
		Image _imagen = loadImage(_ficha.getImagen().getRoute());
		JLabel labelImage = new JLabel(new ImageIcon(_imagen));
		
		labelImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonLink.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonSalir.setAlignmentX(Component.CENTER_ALIGNMENT);
		texto.setAlignmentX(Component.CENTER_ALIGNMENT);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(labelImage);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(botonLink);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(botonSalir);
		this.add(Box.createRigidArea(new Dimension(0, 30)));
		this.add(texto);
		
		this.setOpaque(true);
		
	}
	
	private static void open(URI uri) {						// Abrir Link
	    if (Desktop.isDesktopSupported()) {
	         try {
	           Desktop.getDesktop().browse(uri);
	         } catch (IOException e) { 
	        	 e.printStackTrace();
	         }
	    }
   }
	
	private Image loadImage(String img) {					// Abrir Imagen
		Image i = null;
		try {
			return ImageIO.read(new File("resources/images/" + img));
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JFrame(), "No se encuentra la imagen asociada a la ficha", "Error",
    		        JOptionPane.ERROR_MESSAGE);
		}
		return i;
	}
	

}
