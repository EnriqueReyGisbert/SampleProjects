package histoApp.view;

import java.awt.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import histoApp.controller.*;

public class SystemView extends JFrame {						// Clase Gestión Pantallas

	private static final long serialVersionUID = 1L;
	
	Controller _ctrl;

	public SystemView(Controller ctrl) {
		super("HistoApp");
		_ctrl = ctrl;
		initApp();
	}
	
	public void initApp() {										// Iniciar Interfaz Usuario
		
		// Dimensiones de la pantalla
		
		this.setBounds(450, 0, 500, 700);
		
		// Pantalla Inicial

		this.setDisplay("pantallaIdentificacion2.jpg", new LogInDisplay(_ctrl, this));

		this.setResizable(false);
		this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	private Image loadImage(String img) {						// Abrir Imagen
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}
	
	@SuppressWarnings("deprecation")
	public void setDisplay(String image, JPanel panel) {		// Fijar la pantalla actual
	
		Image _imagen = loadImage(image).getScaledInstance(this.getWidth() - 15, this.getHeight() - 40, Image.SCALE_SMOOTH);
		
		JLayeredPane pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(this.getWidth() - 15, this.getHeight() - 40));
		
		JPanel imagen = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			@Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(_imagen, 0, 0, null);
	         }
		};
		
		/*
		
		La pantalla de la interfaz de la aplicación consta de dos capas: 
		
		1. CAPA FONDO : Muestra la imagen de fondo de la pantalla
		
		2. CAPA INTERACCIÓN : Muestra botones, campos de texto, scrolls...
		
		 */
		
		// Capa Fondo
		
		imagen.setBounds(0, 0, this.getWidth() - 15, this.getHeight() - 40);
		
		// Capa Interacción
		
		panel.setBounds(0, 0, this.getWidth() - 15, this.getHeight() - 40);
		
		pane.add(imagen, new Integer(1));
		
		pane.add(panel, new Integer(2));
		
		this.setContentPane(pane);
		this.repaint();
		this.revalidate();
		
	}

	
}
