package histoApp.viewV2.viewAdmin;

import javax.swing.*;
import histoApp.controller.*;

public class EliminarUsuario extends AdminView{

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JTextField ID;
    private javax.swing.JSeparator jSeparator1;
    
    public EliminarUsuario(Controller ctrl) {
		super(ctrl);
	}
    
	@Override
	void addComponents(JPanel jPanel1) {
		
		
        ok = new javax.swing.JButton();
        ID = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        Salir = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();

        ok = new JButton();
        ok.setBackground(new java.awt.Color(118, 93, 105));
        ok.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        ok.setForeground(new java.awt.Color(255, 255, 255));
        ok.setText("ok");
        ok.setBorderPainted(false);
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	EliminateUserCommand c = new EliminateUserCommand(ID.getText());
            	if(!c.execute(_app)) {
            		JOptionPane.showMessageDialog(jPanel1, "Usuario no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
            	}
            }
        });
        
        jPanel1.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 750, 100, -1));
		ID.setBackground(new java.awt.Color(255, 255, 255, 0));
        ID.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        ID.setForeground(new java.awt.Color(255, 255, 255));
        ID.setText("Id");
        ID.setBorder(null);
        ID.setOpaque(false);
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 370, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 360, 10));
        
        addExit(true);
	}
	
	

}
