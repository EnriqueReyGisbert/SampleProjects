package histoApp.viewV2;

import histoApp.controller.*;

public class Bienvenido extends SystemViewV2 {

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel BackGround;
	private javax.swing.JLabel Bienvenidoa;
	private javax.swing.JLabel HistoApp;
	private javax.swing.JButton IniciarSesion;
	private javax.swing.JButton Registrarse;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JLabel o;

	public Bienvenido(Controller ctrl) {
		super(ctrl);
	}

	@Override
	protected void initApp() {

		jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        IniciarSesion = new javax.swing.JButton();
        Registrarse = new javax.swing.JButton();
        o = new javax.swing.JLabel();
        HistoApp = new javax.swing.JLabel();
        Bienvenidoa = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 560, 110, 10));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 560, 110, 20));
        
        IniciarSesion.setBackground(new java.awt.Color(118, 93, 105));
        IniciarSesion.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        IniciarSesion.setForeground(new java.awt.Color(255, 255, 255));
        IniciarSesion.setText("Iniciar Sesion");
        IniciarSesion.setBorder(null);
        IniciarSesion.setBorderPainted(false);
        IniciarSesion.setFocusPainted(false);
        IniciarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new IniciarSesion(get_ctrl()));
            }
        });
        jPanel1.add(IniciarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 580, 270, 90));
        
        
        Registrarse.setBackground(new java.awt.Color(118, 93, 105));
        Registrarse.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        Registrarse.setForeground(new java.awt.Color(255, 255, 255));
        Registrarse.setText("Registrarse");
        Registrarse.setBorder(null);
        Registrarse.setBorderPainted(false);
        Registrarse.setFocusPainted(false);
        Registrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new Registrarse(get_ctrl()));
            }
        });
        jPanel1.add(Registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 270, 90));

        o.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        o.setForeground(new java.awt.Color(255, 255, 255));
        o.setText("o");
        jPanel1.add(o, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, 20, 30));
        
        HistoApp.setFont(new java.awt.Font("Historycal Inline", 0, 140)); // NOI18N
        HistoApp.setForeground(new java.awt.Color(255, 255, 255));
        HistoApp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        HistoApp.setText("HISTOAPP");
        jPanel1.add(HistoApp, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 260, -1, -1));

        Bienvenidoa.setFont(new java.awt.Font("Historycal Inline", 0, 120)); // NOI18N
        Bienvenidoa.setForeground(new java.awt.Color(255, 255, 255));
        Bienvenidoa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Bienvenidoa.setText("BIENVENIDO A ");
        jPanel1.add(Bienvenidoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 140, 520, -1));

        BackGround.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(BackGround,new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));
        

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
        
	}
	
}
