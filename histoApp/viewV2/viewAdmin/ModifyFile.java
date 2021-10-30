package histoApp.viewV2.viewAdmin;

import java.io.FileNotFoundException;
import javax.swing.*;
import histoApp.controller.*;

public class ModifyFile extends AdminView {
	
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JTextField ID;
	private javax.swing.JTextField IdCambio;
	private javax.swing.JTextField RutaCambio;
	private DefaultComboBoxModel<String> tipoModel;
	private JComboBox<String> tipo;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JSeparator jSeparator2;
	private javax.swing.JSeparator jSeparator3;
	
	public ModifyFile(Controller ctrl) {
		super(ctrl);
	}

	@Override
	void addComponents(JPanel jPanel1) {
		
		jLabel2 = new javax.swing.JLabel();
        RutaCambio = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        IdCambio = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        ok = new javax.swing.JButton();
        ID = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        Salir = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();
        
        addTitleandOk("<html>Introduzca Datos Ficha</html>");

		jLabel2.setFont(new java.awt.Font("Historycal Inline", 0, 50)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tipo");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 424, 120, 40));

        tipoModel = new DefaultComboBoxModel<String>();
		tipo = new JComboBox<String>(tipoModel);
		tipoModel.addElement("imagen");
		tipoModel.addElement("doc");
		tipoModel.addElement("texto");
        jPanel1.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 490, 380, 40));

        RutaCambio.setBackground(new java.awt.Color(255, 255, 255, 0));
        RutaCambio.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        RutaCambio.setForeground(new java.awt.Color(255, 255, 255));
        RutaCambio.setText("Ruta Cambio");
        RutaCambio.setBorder(null);
        RutaCambio.setOpaque(false);
        jPanel1.add(RutaCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 340, 370, 50));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 360, 10));

        IdCambio.setBackground(new java.awt.Color(255, 255, 255, 0));
        IdCambio.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        IdCambio.setForeground(new java.awt.Color(255, 255, 255));
        IdCambio.setText("ID Cambio");
        IdCambio.setBorder(null);
        IdCambio.setOpaque(false);
        jPanel1.add(IdCambio, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 260, 370, 50));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 360, 10));
        
        ok = new JButton();
        ok.setBackground(new java.awt.Color(118, 93, 105));
        ok.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        ok.setForeground(new java.awt.Color(255, 255, 255));
        ok.setText("ok");
        ok.setBorderPainted(false);
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	ModifyFileCommand c = new ModifyFileCommand();
            	try {
					c.execute(_app);
				} catch (FileNotFoundException e) {
					JOptionPane.showMessageDialog(jPanel1, "Ficha no encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
				}
            }
        });
        
        ID.setBackground(new java.awt.Color(255, 255, 255, 0));
        ID.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        ID.setForeground(new java.awt.Color(255, 255, 255));
        ID.setText("Id");
        ID.setBorder(null);
        ID.setOpaque(false);
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 370, 50));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 360, 10));
        
        addExit(true);
	}

}
