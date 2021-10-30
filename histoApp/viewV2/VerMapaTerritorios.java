package histoApp.viewV2;

import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import histoApp.baseDatos.*;
import histoApp.controller.Controller;
import histoApp.controller.MoveTimeCommand;
import histoApp.model.*;

public class VerMapaTerritorios extends SystemViewV2 {

	private static final long serialVersionUID = 1L;

	private JSpinner spinner;
	
	private javax.swing.JLabel Fondo;
	private Mapa _mapa;
	private Map<String, JButton> mapaBotones;
	private JLabel botonSalir;
	
	public VerMapaTerritorios(Controller ctrl,TiposFichas _tipo) {
		super(ctrl,_tipo);
	}
	
	@Override
	public void update(App app) {
		_app=app;
		
	}
    
	protected void initApp() {
		
		try {
			
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	    getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		_mapa = _app.getMapa(_Tfichas);
		mapaBotones= new HashMap<String, JButton>();
		initialButtons(_mapa.getFichasAsociadas());
		
		
		}
		
		catch(NullPointerException e) {
			
		}
		
		
		botonSalir = new JLabel();
		botonSalir.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
		botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new MenuPrincipal(get_ctrl()));
            }
        });
        getContentPane().add(botonSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 70, 70));
        
		SpinnerNumberModel timeBarModel = new SpinnerNumberModel(1900, -2020, 2020, 100);
        spinner = new javax.swing.JSpinner(timeBarModel);
        getContentPane().add(spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, 50, 30));
       
    	spinner.addChangeListener(new ChangeListener() {
		public void stateChanged(ChangeEvent e) {
			MoveTimeCommand c = new MoveTimeCommand((Integer) spinner.getValue());
			c.execute(_app);
			paintButtons();
		}
    	});
    	
    	Fondo = new javax.swing.JLabel();
        Fondo.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/Mapa.jpg")); // NOI18N
        getContentPane().add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
       
        pack();
	}
	private void initialButtons(List<String> lista) {
		
		for(int i= 0; i < lista.size(); i++) {
			Ficha f = _app.getFichas().getFicha(lista.get(i));
			if(f.getTipo() == _Tfichas && f.getX() >= 0 && f.getY() >= 0) {
				
				javax.swing.JButton b = new javax.swing.JButton();
				b.setBackground(new java.awt.Color(118, 93, 105));
		        b.setFont(new java.awt.Font("Historycal Inline", 0, 20)); // NOI18N
		        b.setForeground(new java.awt.Color(255, 255, 255));
		        b.setText(f.getID());
		        b.setBorder(null);
		        b.setVisible(false);
		        getContentPane().add(b, new org.netbeans.lib.awtextra.AbsoluteConstraints(f.getX(), f.getY(), 90, 30));
		        
		        mapaBotones.put(f.getID(), b);
		       
		        b.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		            	goToScreen(evt,new VerFicha(get_ctrl(), f));
		            }
		        });
			
			}	
		}
	}
	
	private void paintButtons() {
		
		for (String f : _mapa.getFichasAsociadas()) {
			
			Ficha ficha = _app.getFichas().getFicha(f);
				
				if (ficha != null && ficha.getTipo().equals(_Tfichas) && ficha.getTiempo() - (int) spinner.getValue() > -100 && ficha.getTiempo() - (int) spinner.getValue() < 100 && ficha.getX() != -1 && ficha.getY() != -1) {
					mapaBotones.get(ficha.getID()).setVisible(true);
				}
				else mapaBotones.get(ficha.getID()).setVisible(false);
		}	
		
	}
	
}
