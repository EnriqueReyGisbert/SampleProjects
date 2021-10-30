package histoApp.viewV2.viewAdmin;

import javax.swing.*;
import histoApp.controller.*;

public class ChangeFileMap extends AdminView{
	
	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel CoordX;
	private javax.swing.JLabel CoordY;
	private javax.swing.JTextField ID;
	private javax.swing.JSpinner coordX;
	private javax.swing.JSpinner coordY;
	private javax.swing.JSeparator jSeparator1;
	private javax.swing.JButton ok;
	
	public ChangeFileMap(Controller ctrl) {
		super(ctrl);
	}
	
	@Override
	void addComponents(JPanel panel) {
		jSeparator1 = new javax.swing.JSeparator();
		ID = new javax.swing.JTextField();
		ID.setBackground(new java.awt.Color(255, 255, 255, 0));
		ID.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
		ID.setForeground(new java.awt.Color(255, 255, 255));
		ID.setText("Id");
		ID.setBorder(null);
		ID.setOpaque(false);
		jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 370, 50));
		jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 360, 10));
		coordY = new javax.swing.JSpinner();
		jPanel1.add(coordY, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 420, 50, 40));
		
		CoordY= new javax.swing.JLabel();
		CoordY.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
		CoordY.setForeground(new java.awt.Color(255, 255, 255));
		CoordY.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		CoordY.setText("Coordenada Y");
		jPanel1.add(CoordY, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 390, 50));
		coordX = new javax.swing.JSpinner();
		jPanel1.add(coordX, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, 50, 40));
		
		CoordX= new javax.swing.JLabel();
		CoordX.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
		CoordX.setForeground(new java.awt.Color(255, 255, 255));
		CoordX.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		CoordX.setText("Coordenada X");
		jPanel1.add(CoordX, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 390, 50));	
	
		ok= new javax.swing.JButton();
		ok.setBackground(new java.awt.Color(118, 93, 105));
		ok.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
		ok.setForeground(new java.awt.Color(255, 255, 255));
		ok.setText("ok");
		ok.setBorderPainted(false);
		ok.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mouseClicked(java.awt.event.MouseEvent evt) {
			AddFileToMapCommand c = new AddFileToMapCommand(ID.getText(), (Integer) coordX.getValue(),(Integer) coordY.getValue());
            	if(!c.execute(_app)) {
            		JOptionPane.showMessageDialog(new JFrame(), "No se ha encontrado", "Error",
		    		        JOptionPane.ERROR_MESSAGE);
            	}
		}
			     });
		
		jPanel1.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 750, 100, -1));
		addExit(true);
	}
}
