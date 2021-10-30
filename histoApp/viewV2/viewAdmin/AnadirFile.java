package histoApp.viewV2.viewAdmin;

import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SpinnerNumberModel;

import histoApp.baseDatos.Ficha;
import histoApp.baseDatos.TiposFichas;
import histoApp.controller.AddFileCommand;
import histoApp.controller.Controller;
import histoApp.controller.SearchCommand;

public class AnadirFile extends AdminView{

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JTextField DocId;
    private javax.swing.JTextField ID;
    private javax.swing.JTextField ImagenId;
    private javax.swing.JTextField RutaImagen;
    private javax.swing.JTextField RutaTexto;
    private javax.swing.JTextField TextoId;
    private javax.swing.JTextField RutaDoc;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JButton ok;
    
    public AnadirFile(Controller ctrl) {
		super(ctrl);
	}
    
	@Override
	void addComponents(JPanel jPanel1) {
		SpinnerNumberModel timeBarModel = new SpinnerNumberModel(1900, -2020, 2020, 100);
		javax.swing.JSpinner spinner= new javax.swing.JSpinner(timeBarModel);
		ok=new javax.swing.JButton();
		jLabel1= new javax.swing.JLabel();
        RutaDoc = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        RutaImagen = new javax.swing.JTextField();
        DocId = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        ImagenId = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        RutaTexto = new javax.swing.JTextField();
        TextoId = new javax.swing.JTextField();
        ID = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator1 = new javax.swing.JSeparator();
        Salir = new javax.swing.JLabel();
        BackGround = new javax.swing.JLabel();

        JComboBox<TiposFichas> tipo;
		DefaultComboBoxModel<TiposFichas> tipoModel = new DefaultComboBoxModel<TiposFichas>();
        tipo =new JComboBox<TiposFichas>(tipoModel);
		for (TiposFichas w : TiposFichas.values())
			tipoModel.addElement(w);
        
        jPanel1.add(tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 680, 120, -1));
        jPanel1.add(spinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 680, 130, -1));
  
        ok = new JButton();
        ok.setBackground(new java.awt.Color(118, 93, 105));
        ok.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
        ok.setForeground(new java.awt.Color(255, 255, 255));
        ok.setText("ok");
        ok.setBorderPainted(false);
        ok.addMouseListener(new java.awt.event.MouseAdapter() {
            @SuppressWarnings("null")
			public void mouseClicked(java.awt.event.MouseEvent evt) {
            	List<Ficha> f = null;
				SearchCommand b = new SearchCommand(ID.getText());
				f.add(b.execute(_app));
					AddFileCommand c = new AddFileCommand(ID.getText(), RutaImagen.getText(),
		           			ImagenId.getText(), RutaTexto.getText(), TextoId.getText(), 
		            			RutaDoc.getText(), DocId.getText(),tipo.getSelectedItem().toString() , 300);
		            try {
						c.execute(_app);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(jPanel1, "Ficha no encontrada", "ERROR", JOptionPane.ERROR_MESSAGE);
					}
            	
            }
        });
        
        jPanel1.add(ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 750, 100, -1));

        RutaDoc.setBackground(new java.awt.Color(255, 255, 255, 0));
        RutaDoc.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        RutaDoc.setForeground(new java.awt.Color(255, 255, 255));
        RutaDoc.setText("Ruta Doc");
        RutaDoc.setBorder(null);
        RutaDoc.setOpaque(false);
        jPanel1.add(RutaDoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 610, 370, 50));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 360, 10));

        RutaImagen.setBackground(new java.awt.Color(255, 255, 255, 0));
        RutaImagen.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        RutaImagen.setForeground(new java.awt.Color(255, 255, 255));
        RutaImagen.setText("Ruta Imagen");
        RutaImagen.setBorder(null);
        RutaImagen.setOpaque(false);
        jPanel1.add(RutaImagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 470, 370, 50));

        DocId.setBackground(new java.awt.Color(255, 255, 255, 0));
        DocId.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        DocId.setForeground(new java.awt.Color(255, 255, 255));
        DocId.setText("Doc Id");
        DocId.setBorder(null);
        DocId.setOpaque(false);
        jPanel1.add(DocId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 540, 370, 50));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 360, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 360, 10));

        ImagenId.setBackground(new java.awt.Color(255, 255, 255, 0));
        ImagenId.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        ImagenId.setForeground(new java.awt.Color(255, 255, 255));
        ImagenId.setText("Imagen ID");
        ImagenId.setBorder(null);
        ImagenId.setOpaque(false);
        jPanel1.add(ImagenId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 400, 370, 50));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 360, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 360, 10));

        RutaTexto.setBackground(new java.awt.Color(255, 255, 255, 0));
        RutaTexto.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        RutaTexto.setForeground(new java.awt.Color(255, 255, 255));
        RutaTexto.setText("Ruta Texto");
        RutaTexto.setBorder(null);
        RutaTexto.setOpaque(false);
        jPanel1.add(RutaTexto, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 370, 50));
        
		TextoId.setBackground(new java.awt.Color(255, 255, 255, 0));
        TextoId.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        TextoId.setForeground(new java.awt.Color(255, 255, 255));
        TextoId.setText("TextoID");
        TextoId.setBorder(null);
        TextoId.setOpaque(false);
        jPanel1.add(TextoId, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 370, 50));
        
		ID.setBackground(new java.awt.Color(255, 255, 255, 0));
        ID.setFont(new java.awt.Font("Historycal Inline", 0, 60)); // NOI18N
        ID.setForeground(new java.awt.Color(255, 255, 255));
        ID.setText("Id");
        ID.setBorder(null);
        ID.setOpaque(false);
        jPanel1.add(ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, 370, 50));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, 360, 10));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 360, 10));
        
        addExit(true);
	}
		
	

}
