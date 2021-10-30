package histoApp.viewV2.viewAdmin;

import javax.swing.JPanel;

import histoApp.controller.Controller;
import histoApp.viewV2.Bienvenido;
import histoApp.viewV2.SystemViewV2;

public abstract class AdminView extends SystemViewV2 {

	private static final long serialVersionUID = 1L;
	
	protected javax.swing.JLabel BackGround;
	protected javax.swing.JLabel Salir;
	protected javax.swing.JPanel jPanel1;
	protected javax.swing.JLabel jLabel1;
	protected javax.swing.JButton ok;

	public AdminView(Controller ctrl) {
		super(ctrl);
	}
	
	@Override
	protected void initApp() {
		
		jPanel1 = new JPanel();
		jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
		addComponents(jPanel1);
		setBackground();
		getContentPane().add(jPanel1);
		
		pack();
	}
	
	protected void addExit (boolean MainAdminWindow) {
		Salir = new javax.swing.JLabel();
		Salir.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
        Salir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if(MainAdminWindow)
            		goToScreen(evt, new AdminDisplayv2(_ctrl));
            	else
            		goToScreen(evt, new Bienvenido(_ctrl));
            }
        });
        jPanel1.add(Salir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));
	}
	
	void addTitleandOk(String name) {
		ok.setBackground(new java.awt.Color(118, 93, 105));
        ok.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        ok.setForeground(new java.awt.Color(255, 255, 255));
        ok.setText("ok");
        ok.setBorderPainted(false);
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new AdminDisplayv2(get_ctrl()));
            }
        });
        jPanel1.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 750, 100, -1));
        
        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 70)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("<html>Introduzca Datos Ficha</html>");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 400, -1));
	}
	
	void setBackground() {
		BackGround = new javax.swing.JLabel();
		BackGround.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
	    jPanel1.add(BackGround, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 480, 850));
	}
	
	abstract void addComponents(JPanel panel);
}
