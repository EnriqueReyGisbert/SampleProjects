package histoApp.viewV2.viewAdmin;

import javax.swing.JPanel;
import histoApp.controller.Controller;

public class AdminDisplayv2 extends AdminView{
	
	private static final long serialVersionUID = 1L;
	private javax.swing.JButton AddFicha;
    private javax.swing.JButton AddFichaToMap;
    private javax.swing.JButton EliminarUsuario;
    private javax.swing.JButton ModFicha;
    private javax.swing.JLabel jLabel1;
    
    public AdminDisplayv2(Controller ctrl) {
		super(ctrl);
	}
   
	@Override
	void addComponents(JPanel jPanel1) {
		
        ModFicha = new javax.swing.JButton();
        EliminarUsuario = new javax.swing.JButton();
        AddFichaToMap = new javax.swing.JButton();
        AddFicha = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        Salir = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();

        ModFicha.setBackground(new java.awt.Color(118, 93, 105));
        ModFicha.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        ModFicha.setForeground(new java.awt.Color(255, 255, 255));
        ModFicha.setText("Modificar Ficha");
        ModFicha.setBorderPainted(false);
        ModFicha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new ModifyFile(get_ctrl()));
            }
        });
        jPanel1.add(ModFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 670, 270, 80));

        EliminarUsuario.setBackground(new java.awt.Color(118, 93, 105));
        EliminarUsuario.setFont(new java.awt.Font("Historycal Inline", 0, 50)); // NOI18N
        EliminarUsuario.setForeground(new java.awt.Color(255, 255, 255));
        EliminarUsuario.setText("Eliminar Usuario");
        EliminarUsuario.setBorderPainted(false);
        EliminarUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new EliminarUsuario(get_ctrl()));
            }
        });
        jPanel1.add(EliminarUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 550, 270, 80));

        AddFichaToMap.setBackground(new java.awt.Color(118, 93, 105));
        AddFichaToMap.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        AddFichaToMap.setForeground(new java.awt.Color(255, 255, 255));
        AddFichaToMap.setText("Anadir Ficha al Mapa");
        AddFichaToMap.setBorderPainted(false);
        AddFichaToMap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new ChangeFileMap(get_ctrl()));
            }
        });
        jPanel1.add(AddFichaToMap, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 430, 270, 80));

        AddFicha.setBackground(new java.awt.Color(118, 93, 105));
        AddFicha.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        AddFicha.setForeground(new java.awt.Color(255, 255, 255));
        AddFicha.setText("Anadir Ficha");
        AddFicha.setBorderPainted(false);
        AddFicha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt, new AnadirFile(get_ctrl()));
            }
        });
        jPanel1.add(AddFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, 270, 80));
        
        

        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 120)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Admin Display");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, -1));
        
        addExit(false);

	}

}
