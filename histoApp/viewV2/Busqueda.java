package histoApp.viewV2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import histoApp.baseDatos.Ficha;
import histoApp.controller.*;

public class Busqueda extends SystemViewV2{
	
	private static final long serialVersionUID = 1L;
	
	private List<Ficha> lista;
	private JPanel panel;

	public Busqueda(Controller ctrl,  List<Ficha> lista) {
		super(ctrl);
		this.lista = lista;
		aniadirFichasEncontrada();
		//fondo
		JLabel Background = new JLabel();
		Background.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        panel.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));
        getContentPane().add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));
	}

	protected void initApp() {
		
		panel = new JPanel();
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		
		JLabel botonSalir = new JLabel();
		botonSalir.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png"));
		botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
				    		  goToScreen(evt, new MenuPrincipal(get_ctrl()));
            }
            	
        });
		panel.add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 70));
		
		JLabel buscar = new JLabel();
		buscar.setFont(new java.awt.Font("Historycal Inline", 0, 50)); // NOI18N
        buscar.setForeground(new java.awt.Color(255, 255, 255));
        buscar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        buscar.setText("Buscar");
        panel.add(buscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(100,10,270, 50));

		JTextField textoBuscar = new JTextField();
		panel.add(textoBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110,100, 260, 50));
		
		JButton botonBuscar = new JButton();
		botonBuscar.setBackground(new java.awt.Color(118, 93, 105));
		botonBuscar.setBorder(null);
		botonBuscar.setBorderPainted(false);
		botonBuscar.setFocusPainted(false);
		botonBuscar.setFont(new java.awt.Font("Historycal Inline", 0, 40));
		botonBuscar.setText("BUSCAR");
		botonBuscar.setForeground(Color.WHITE);
		botonBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            @SuppressWarnings("null")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				if(!textoBuscar.getText().equals("")) {
					List<Ficha> f = null;
					SearchCommand c = new SearchCommand(textoBuscar.getText());
					f.add(c.execute(_app));
					for(int i = 0; i < f.size(); i++)
							System.out.println(f.get(i).getID());
					//Cargo la pantalla otra vez con la nueva lista
					goToScreen(evt, new Busqueda(get_ctrl(), f));
				}
			}
		});
	
		panel.add(botonBuscar,new org.netbeans.lib.awtextra.AbsoluteConstraints(150,600,130,50));
		
	}
	
	private void aniadirFichasEncontrada() {
		
		JPanel fichasBuscada = new JPanel(new FlowLayout(FlowLayout.CENTER, 0 , 15));
		fichasBuscada.setOpaque(false);
		try {
			
			int i = 0;
			while(i < 5 && i < lista.size() ) {
				Ficha f = lista.get(i);
				JButton aux = new JButton();
				aux.setBackground(new java.awt.Color(118, 93, 105));
				aux.setBorder(null);
		        aux.setBorderPainted(false);
		        aux.setFocusPainted(false);
				aux.setFont(new java.awt.Font("Historycal Inline", 0, 40));
				aux.setPreferredSize(new Dimension(300,50));
				aux.setText(f.getID());
				aux.setForeground(Color.WHITE);
				Border emptyBorder = BorderFactory.createEmptyBorder();
				aux.setBorder(emptyBorder);
				aux.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt){
		            	goToScreen(evt, new VerFicha(get_ctrl(), f));
					}
				});
				fichasBuscada.add(aux);
				i++;
				
			}
			
		}
		
		catch (NullPointerException a) {
		
		}
		
		panel.add(fichasBuscada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20,200, 425,375));
		
	}
	
}


