package histoApp.viewV2;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import histoApp.controller.AddUserCommand;
import histoApp.controller.Controller;
import histoApp.model.App;
import histoApp.viewV2.viewAdmin.AdminDisplayv2;

public class Registrarse extends SystemViewV2{
	
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel BAckground;
    private javax.swing.JLabel BotonSalirRegistrar;
    private javax.swing.JLabel Contra_aa;
    private javax.swing.JLabel Contra_ab1;
    private javax.swing.JLabel Licencia;
    private javax.swing.JTextField LicenciaField;
    private javax.swing.JLabel Mail;
    private javax.swing.JTextField CampoNombre;
    private javax.swing.JLabel Name;
    private javax.swing.JTextField MailCampo;
    private javax.swing.JLabel Registrarse;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordField2;
    private javax.swing.JPasswordField jPasswordField3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTextField CodigoFiels;
    int _status;
	
	public Registrarse(Controller ctrl) {
		super(ctrl);
	}

	@Override
	protected void initApp() {
		
		jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jCheckBox1 = new javax.swing.JCheckBox();
        CodigoFiels = new javax.swing.JTextField();
        Licencia = new javax.swing.JLabel();
        LicenciaField = new javax.swing.JTextField();
        jSeparator5 = new javax.swing.JSeparator();
        Contra_ab1 = new javax.swing.JLabel();
        Contra_aa = new javax.swing.JLabel();
        jPasswordField3 = new javax.swing.JPasswordField();
        jSeparator4 = new javax.swing.JSeparator();
        Mail = new javax.swing.JLabel();
        jPasswordField2 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Name = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        MailCampo = new javax.swing.JTextField();
        CampoNombre = new javax.swing.JTextField();
        BotonSalirRegistrar = new javax.swing.JLabel();
        Registrarse = new javax.swing.JLabel();
        BAckground = new javax.swing.JLabel();
		
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

//        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(1, 1, 2, 1));
//        jSpinner1.setToolTipText("");
//        jSpinner1.setBorder(null);
//        jSpinner1.setFocusable(false);
//        jSpinner1.setOpaque(false);
//        jPanel1.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 650, 50, 30));

        jButton1.setBackground(new java.awt.Color(118, 93, 105));
        jButton1.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Registrar");
        jButton1.setBorder(null);
        jButton1.setFocusPainted(false);
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            @SuppressWarnings("deprecation")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
            	if (!CampoNombre.getText().equals("") && !MailCampo.getText().equals("") && !jPasswordField2.getText().equals("") && !jPasswordField3.getText().equals("") && jPasswordField2.getText().equals(jPasswordField3.getText()) ) {
					if(LicenciaField.getText().equals(App.getLicencia())) {
	            		if(CodigoFiels.getText().equals(App.getCodigo())) {
	            			AddUserCommand c = new AddUserCommand(CampoNombre.getText(), jPasswordField2.getText(),MailCampo.getText(), CodigoFiels.getText(), 2);
							if(c.execute(_app))
								goToScreen(evt, new AdminDisplayv2(get_ctrl()));
							else JOptionPane.showMessageDialog(new JFrame(), "User already exists", "Error",
				    		        JOptionPane.ERROR_MESSAGE);
	            		}
						else {
							AddUserCommand c = new AddUserCommand(CampoNombre.getText(), jPasswordField2.getText(),MailCampo.getText(), null, 1);
							if(c.execute(_app))
								goToScreen(evt, new MenuPrincipal(get_ctrl()));
							else JOptionPane.showMessageDialog(new JFrame(), "User already exists", "Error",
				    		        JOptionPane.ERROR_MESSAGE); 
						}
				   			
					}   
					else {
	            		JOptionPane.showMessageDialog(new JFrame(), "Invalid Licence", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
	            	}
				   		
				   		
            	}
            	else {
							 JOptionPane.showMessageDialog(new JFrame(), "Invalid Credentials", "Error",
					    		        JOptionPane.ERROR_MESSAGE);
		        }
            	
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 750, 170, 60));
     
        jButton2.setBackground(new java.awt.Color(118, 93, 105));
        jButton2.setFont(new java.awt.Font("Historycal Inline", 0, 25)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Iniciar Sesion");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setFocusPainted(false);
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new IniciarSesion(get_ctrl()));
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 110, 30));

//        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
//        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
//        jLabel1.setText("GRADO MOD");
//        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 650, 110, 40));

        jCheckBox1.setBackground(new java.awt.Color(255, 255, 255, 0));
        jCheckBox1.setFont(new java.awt.Font("Historycal Inline", 0, 30)); // NOI18N
        jCheckBox1.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBox1.setText("He leido y acepto las condiciones de uso");
        jCheckBox1.setOpaque(false);
        jPanel1.add(jCheckBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 700, 370, -1));

        CodigoFiels.setBackground(new java.awt.Color(255, 255, 255, 0));
        CodigoFiels.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        CodigoFiels.setForeground(new java.awt.Color(204, 204, 204));
        CodigoFiels.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        CodigoFiels.setText("CODIGO");
        CodigoFiels.setBorder(null);
        CodigoFiels.setOpaque(false);
        jPanel1.add(CodigoFiels, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 650, 120, 40));

        Licencia.setBackground(new java.awt.Color(255, 255, 255));
        Licencia.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        Licencia.setForeground(new java.awt.Color(255, 255, 255));
        Licencia.setText("Licencia");
        jPanel1.add(Licencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 540, -1, -1));
        
        LicenciaField.setBackground(new java.awt.Color(255, 255, 255, 0));
        LicenciaField.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        LicenciaField.setForeground(new java.awt.Color(255, 255, 255));
        LicenciaField.setText("Introducir Licencia");
        LicenciaField.setBorder(null);
        LicenciaField.setOpaque(false);
        jPanel1.add(LicenciaField, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 590, 330, -1));

        jSeparator5.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 630, 370, 10));

        Contra_ab1.setBackground(new java.awt.Color(255, 255, 255));
        Contra_ab1.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        Contra_ab1.setForeground(new java.awt.Color(255, 255, 255));
        Contra_ab1.setText("Re-enter Password");
        jPanel1.add(Contra_ab1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 450, -1, -1));

        Contra_aa.setBackground(new java.awt.Color(255, 255, 255));
        Contra_aa.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        Contra_aa.setForeground(new java.awt.Color(255, 255, 255));
        Contra_aa.setText("Password");
        jPanel1.add(Contra_aa, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, -1, -1));

        jPasswordField3.setBackground(new java.awt.Color(255, 255,255, 0));
        jPasswordField3.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPasswordField3.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField3.setText("jPasswordField2");
        jPasswordField3.setBorder(null);
        jPasswordField3.setOpaque(false);
        jPanel1.add(jPasswordField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 490, 290, 40));

        jSeparator4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 530, 370, 30));

        Mail.setBackground(new java.awt.Color(255, 255, 255));
        Mail.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        Mail.setForeground(new java.awt.Color(255, 255, 255));
        Mail.setText("Mail");
        jPanel1.add(Mail, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, -1, -1));

        jPasswordField2.setBackground(new java.awt.Color(255,255, 255, 0));
        jPasswordField2.setFont(new java.awt.Font("Tahoma", 0, 20)); // NOI18N
        jPasswordField2.setForeground(new java.awt.Color(255, 255, 255));
        jPasswordField2.setText("jPasswordField2");
        jPasswordField2.setBorder(null);
        jPasswordField2.setOpaque(false);
        jPanel1.add(jPasswordField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 400, 290, 40));

        jSeparator1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 370, 30));

        jSeparator2.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, 370, 10));

        Name.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        Name.setForeground(new java.awt.Color(255, 255, 255));
        Name.setText("Nombre");
        jPanel1.add(Name, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jSeparator3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, 370, 10));

        MailCampo.setBackground(new java.awt.Color(255, 255, 255, 0));
        MailCampo.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        MailCampo.setForeground(new java.awt.Color(255, 255, 255));
        MailCampo.setText("Introducir Mail");
        MailCampo.setBorder(null);
        MailCampo.setOpaque(false);
        jPanel1.add(MailCampo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 310, 330, -1));

        CampoNombre.setBackground(new java.awt.Color(255, 255, 255, 0));
        CampoNombre.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        CampoNombre.setForeground(new java.awt.Color(255, 255, 255));
        CampoNombre.setText("Introducir Nombre");
        CampoNombre.setBorder(null);
        CampoNombre.setOpaque(false);
        jPanel1.add(CampoNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 220, 330, -1));

        BotonSalirRegistrar.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
        BotonSalirRegistrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new Bienvenido(get_ctrl()));
            }
        });
        jPanel1.add(BotonSalirRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        Registrarse.setFont(new java.awt.Font("Historycal Inline", 0, 120)); // NOI18N
        Registrarse.setForeground(new java.awt.Color(255, 255, 255));
        Registrarse.setText("Registrarse");
        jPanel1.add(Registrarse, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, -1, -1));

        BAckground.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(BAckground, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
	}


}
