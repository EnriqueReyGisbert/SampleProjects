package histoApp.viewV2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import histoApp.baseUsuarios.Usuario;
import histoApp.controller.Controller;
import histoApp.controller.IdentifyUserCommand;
import histoApp.viewV2.viewAdmin.AdminDisplayv2;

public class IniciarSesion extends SystemViewV2{

	private static final long serialVersionUID = 1L;
	
    private javax.swing.JLabel Background;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton InitSesion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel butSalir;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nameField;
    
	
	public IniciarSesion(Controller ctrl) {
		super(ctrl);
	}
	
	protected void initApp() {
		
		jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        nameField = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        InitSesion = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        butSalir = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        //Registrarse
        
        jButton1.setBackground(new java.awt.Color(118, 93, 105));
        jButton1.setFont(new java.awt.Font("Historycal Inline", 0, 25)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrarse");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setFocusPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new Registrarse(get_ctrl()));
            }
        });
        
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 90, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 580, 340, 30));
        
        //Campo de nombre
        
        nameField.setBackground(new java.awt.Color(255, 255, 255, 0));
        nameField.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        nameField.setForeground(new java.awt.Color(255, 255, 255));
        nameField.setText("Introduzca nombre");
        nameField.setBorder(null);
        nameField.setOpaque(false);
        jPanel1.add(nameField, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 450, 280, 30));

        //Campo contrase�a
        
        jPasswordField1.setBackground(new java.awt.Color(255, 255, 255, 0));
        jPasswordField1.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPasswordField1.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField1.setText("jPasswordField1");
        jPasswordField1.setBorder(null);
        jPasswordField1.setOpaque(false);
        jPanel1.add(jPasswordField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 540, 350, 40));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 480, 340, 30));

        //Iniciar Sesion
        
        InitSesion.setBackground(new java.awt.Color(118, 93, 105));
        InitSesion.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        InitSesion.setForeground(new java.awt.Color(255, 255, 255));
        InitSesion.setText("Iniciar Sesion");
        InitSesion.setBorder(null);
        InitSesion.setBorderPainted(false);
        InitSesion.setFocusPainted(false);
        InitSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	
            	String id= nameField.getText();
            	@SuppressWarnings("deprecation")
				String ctrs= jPasswordField1.getText();
            	if(!id.equals("") && !ctrs.equals("") &&!id.equals("Introduzca nombre") && !ctrs.equals("jPasswordField1") ) {
			    	boolean found = false;
					IdentifyUserCommand c = new IdentifyUserCommand(id, ctrs);
					found = c.execute(_app);
				      if (found) {
				    	  Usuario u = _app.getUsuarios().getUsuario(id);
				    	  if(u.getType().equals("user"))
				    		  goToScreen(evt, new MenuPrincipal(get_ctrl()));
				    	  else
				    		  goToScreen(evt, new AdminDisplayv2(get_ctrl()));
				      }
				      else {
				    	  JOptionPane.showMessageDialog(new JFrame(), "Invalid Credentials", "Error",
				    		        JOptionPane.ERROR_MESSAGE);
				      }    
			      }
			   }
            	
            }
        		);// NOI18N
        jPanel1.add(InitSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 630, 210, 90));

        jLabel5.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Password");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 500, -1, -1));

        jLabel4.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Nombre");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, -1, -1));

        butSalir.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png"));
        butSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new Bienvenido(get_ctrl()));
            }
        });
        jPanel1.add(butSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLabel2.setFont(new java.awt.Font("Historycal Inline", 0, 180)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Sesion");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 210, 300, -1));

        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 159)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Inicio de ");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 100, -1, -1));

        Background.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(Background, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
	}
	
	
}
