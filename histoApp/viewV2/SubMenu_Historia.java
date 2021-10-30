package histoApp.viewV2;

import histoApp.controller.Controller;

public class SubMenu_Historia extends SystemViewV2 {

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel BotoSalir;
    private javax.swing.JButton Civilizaciones;
    private javax.swing.JButton Territorios;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    

	public SubMenu_Historia(Controller ctrl) {
		super(ctrl);
	}

	@Override
	protected void initApp() {
		
		jPanel1 = new javax.swing.JPanel();
        Territorios = new javax.swing.JButton();
        Civilizaciones = new javax.swing.JButton();
        BotoSalir = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Territorios.setBackground(new java.awt.Color(118, 93, 105));
        Territorios.setFont(new java.awt.Font("Historycal Inline", 0, 80)); // NOI18N
        Territorios.setForeground(new java.awt.Color(255, 255, 255));
        Territorios.setText("TERRITORIOS");
        Territorios.setBorder(null);
        Territorios.setBorderPainted(false);
        jPanel1.add(Territorios, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 340, 120));

        Civilizaciones.setBackground(new java.awt.Color(118, 93, 105));
        Civilizaciones.setFont(new java.awt.Font("Historycal Inline", 0, 80)); // NOI18N
        Civilizaciones.setForeground(new java.awt.Color(255, 255, 255));
        Civilizaciones.setText("CIVILIZACIONES");
        Civilizaciones.setBorder(null);
        Civilizaciones.setBorderPainted(false);
        Civilizaciones.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new Submenu_Civilizacion(get_ctrl()));
            }
        });
        jPanel1.add(Civilizaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 390, 340, 120));

        BotoSalir.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
        BotoSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new MenuPrincipal(get_ctrl()));
            }
        });
        jPanel1.add(BotoSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel3.setFont(new java.awt.Font("Historycal Inline", 0, 150)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Opcion");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 250, -1));

        jLabel2.setFont(new java.awt.Font("Historycal Inline", 0, 110)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccione una");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 110, 420, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
	}

}
