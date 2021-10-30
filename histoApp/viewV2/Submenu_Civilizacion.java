package histoApp.viewV2;

import java.util.List;

import javax.swing.JButton;

import histoApp.baseDatos.Ficha;
import histoApp.baseDatos.TiposFichas;
import histoApp.controller.Controller;

public class Submenu_Civilizacion extends SystemViewV2{

	private static final long serialVersionUID = 1L;
	
    private javax.swing.JLabel Fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    
    private List<Ficha> listaFichas;

	public Submenu_Civilizacion(Controller ctrl) {
		super(ctrl);
	}

	@Override
	protected void initApp() {
		
		listaFichas=_app.getFichas().getFichas();
		jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        int i=380;
		for(Ficha f : listaFichas) {
			if(f.getTipo().equals(TiposFichas.imperio)) {
				
				JButton botonFicha = new JButton(f.getID());
				botonFicha.setBackground(new java.awt.Color(118, 93, 105));
				botonFicha.setFont(new java.awt.Font("Historycal Inline", 0, 80)); // NOI18N
				botonFicha.setForeground(new java.awt.Color(255, 255, 255));
				botonFicha.setBorder(null);
				botonFicha.setBorderPainted(false);
				botonFicha.setFocusPainted(false);
				//botonFicha.setBounds(90,i,310,70);
				botonFicha.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		            	goToScreen(evt,new VerFicha(get_ctrl(),f));
		            }
		        });
				jPanel1.add(botonFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, i, 260, 110));
				
				i += 140;
			}
		}

        jLabel3.setFont(new java.awt.Font("Historycal Inline", 0, 150)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Civilizacion");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 460, -1));

        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 110)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Seleccione una ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 460, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new SubMenu_Historia(get_ctrl()));
            }
        });
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
        

        Fondo.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
	}

}
