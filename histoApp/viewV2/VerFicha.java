package histoApp.viewV2;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.imageio.ImageIO;
import javax.swing.*;
import histoApp.baseDatos.*;
import histoApp.baseDatos.TiposFichas;
import histoApp.controller.Controller;
import histoApp.viewV2.SubMenu_Mitologia;

public class VerFicha extends SystemViewV2{

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel Imagen;
	private javax.swing.JButton MoreInfo;
	private javax.swing.JLabel Titulo;
	private javax.swing.JLabel Fondo;


	public VerFicha(Controller ctrl,Ficha ficha) {
		super(ctrl,ficha);
	}

	@Override
	protected void initApp() {
		
		jPanel1 = new javax.swing.JPanel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		
        MoreInfo = new javax.swing.JButton();
        Imagen = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();
        Titulo=new javax.swing.JLabel();

        MoreInfo.setBackground(new java.awt.Color(118, 93, 105));
        MoreInfo.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        MoreInfo.setForeground(new java.awt.Color(255, 255, 255));
        MoreInfo.setText("Mas Informacion");
        MoreInfo.setToolTipText("");
        MoreInfo.setBorder(null);
        MoreInfo.setFocusPainted(false);
        MoreInfo.addActionListener(new ActionListener(){
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
        jPanel1.add(MoreInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 250, -1));

        Titulo.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        Titulo.setForeground(new java.awt.Color(255, 255, 255));
        Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Titulo.setText("<html>"+_ficha.getID()+"</html>");
        Titulo.setToolTipText("");
        jPanel1.add(Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 70, 360, 60));
        
        JTextArea textArea = new JTextArea();
        textArea.setOpaque(false);
        textArea.setBackground(new java.awt.Color(255, 255, 255));
        textArea.setText(_ficha.getTexto().getTexto());
        textArea.setFont(new java.awt.Font("Historycal Inline", 0, 30));
        textArea.setForeground(new java.awt.Color(255, 255, 255));

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setOpaque(false);
        scrollPane.setBorder(null);
        jPanel1.add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 420, 410));
        
        Imagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Imagen.setIcon(new javax.swing.ImageIcon(loadImage(_ficha.getImagen().getRoute()))); // NOI18N
        jPanel1.add(Imagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 380, 190));
        
        addExitButton(null);
        Fondo.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 470, 920));
        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
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
	protected void addExitButton(SystemViewV2 v) {
		
	  butSalir= new javax.swing.JLabel();
	  butSalir.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
      butSalir.addMouseListener(new java.awt.event.MouseAdapter() {
          public void mouseClicked(java.awt.event.MouseEvent evt) {
          	if(_ficha.getTipo().equals(TiposFichas.mitologia)) {
          		goToScreen(evt,new SubMenu_Mitologia(get_ctrl()));
          	}
          	else if(_ficha.getTipo().equals(TiposFichas.batalla))
          	{
          		//Si es mapa
          		goToScreen(evt,new VerMapaTerritorios(get_ctrl(),TiposFichas.batalla));
          	}
          	else if(_ficha.getTipo().equals(TiposFichas.territorio))
          	{
          		goToScreen(evt, new SubMenu_Historia(_ctrl));
          	}
          	else if((_ficha.getTipo().equals(TiposFichas.imperio))) goToScreen(evt, new Submenu_Civilizacion(_ctrl));
          }
      });
      jPanel1.add(butSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 70, 70));
      
	}

}
