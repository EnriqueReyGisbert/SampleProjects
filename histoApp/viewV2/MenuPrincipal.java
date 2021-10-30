package histoApp.viewV2;

import histoApp.baseDatos.TiposFichas;
import histoApp.controller.Controller;


public class MenuPrincipal extends SystemViewV2{

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel Background;
	private javax.swing.JButton BatallaButton;
	private javax.swing.JButton HistoButton2;
	private javax.swing.JButton MitoButton;
	private javax.swing.JLabel butExit;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JLabel butBusqueda;
	    

	public MenuPrincipal(Controller ctrl) {
		super(ctrl);
	}

	@Override
	protected void initApp() {
		
		jPanel1 = new javax.swing.JPanel();
        BatallaButton = new javax.swing.JButton();
        MitoButton = new javax.swing.JButton();
        HistoButton2 = new javax.swing.JButton();
        butExit = new javax.swing.JLabel();
        butBusqueda = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BatallaButton.setBackground(new java.awt.Color(118, 93, 105));
        BatallaButton.setFont(new java.awt.Font("Historycal Inline", 0, 50)); // NOI18N
        BatallaButton.setForeground(new java.awt.Color(255, 255, 255));
        BatallaButton.setText("Batallas");
        BatallaButton.setBorder(null);
        BatallaButton.setBorderPainted(false);
        BatallaButton.setFocusPainted(false);
        BatallaButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new VerMapaTerritorios(get_ctrl(),TiposFichas.batalla));
            }
        });
        jPanel1.add(BatallaButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 600, 230, 90));

        MitoButton.setBackground(new java.awt.Color(118, 93, 105));
        MitoButton.setFont(new java.awt.Font("Historycal Inline", 0, 50)); // NOI18N
        MitoButton.setForeground(new java.awt.Color(255, 255, 255));
        MitoButton.setText("Mitologia");
        MitoButton.setBorder(null);
        MitoButton.setBorderPainted(false);
        MitoButton.setFocusPainted(false);
        MitoButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new SubMenu_Mitologia(get_ctrl()));
            }
        });
        jPanel1.add(MitoButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 490, 230, 90));

        HistoButton2.setBackground(new java.awt.Color(118, 93, 105));
        HistoButton2.setFont(new java.awt.Font("Historycal Inline", 0, 50)); // NOI18N
        HistoButton2.setForeground(new java.awt.Color(255, 255, 255));
        HistoButton2.setText("Historia");
        HistoButton2.setBorder(null);
        HistoButton2.setBorderPainted(false);
        HistoButton2.setFocusPainted(false);
        HistoButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new SubMenu_Historia(get_ctrl()));
            }
        });
        jPanel1.add(HistoButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 380, 230, 90));

        butExit.setForeground(new java.awt.Color(255, 255, 255));
        butExit.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
        butExit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new Bienvenido(get_ctrl()));
            }
        });
        jPanel1.add(butExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));
        
        butBusqueda.setForeground(new java.awt.Color(255, 255, 255));
        butBusqueda.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/newSearch.png")); // NOI18N
        butBusqueda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new Busqueda(get_ctrl(), null));
            }
        });
        jPanel1.add(butBusqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 740, -1, -1));

        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 150)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OPCION");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 190, 300, -1));

        jLabel2.setFont(new java.awt.Font("Historycal Inline", 0, 110)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccione una ");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack(); 
		
	}

}
