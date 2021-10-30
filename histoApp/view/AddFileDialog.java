package histoApp.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import histoApp.baseDatos.*;

public class AddFileDialog extends JDialog {						// Pantalla Añadir Ficha
	
	private static final long serialVersionUID = 1L;
	
	private int _status;
	private JTextField id;										// Introducir Id
	private JTextField texto;									// Introducir ruta acceso Texto
	private JTextField textoID;									// Introducir Id Texto
	private JTextField imagen;									// Introducir ruta acceso Imagen
	private JTextField imagenID;								// Introducir Id Imagen
	private JTextField doc;										// Introducir ruta acceso Documento
	private JTextField docID;									// Introducir Id Documento
	private JSpinner tiempo;									// Introducir tiempo
	private JComboBox<TiposFichas> tipo;						// Introducir tipo
	private DefaultComboBoxModel<TiposFichas> tipoModel;		
	
	public AddFileDialog(Frame Parent) {
		super(Parent, true);
		initGUI();
	}
	
	private void initGUI() {
		
		_status = 0;
		
		setTitle("A\\u00f1adir Ficha");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel helpMsg = new JLabel("<html>Introduce los datos de la ficha que desea a\\u00f1adir</html>");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
		id = new JTextField();
		texto = new JTextField();
		textoID = new JTextField();
		imagen = new JTextField();
		imagenID = new JTextField();
		doc = new JTextField();
		docID = new JTextField();
		SpinnerNumberModel tiempoModel = new SpinnerNumberModel();
		tiempo = new JSpinner(tiempoModel);
		tipoModel = new DefaultComboBoxModel<TiposFichas>();
		tipo = new JComboBox<TiposFichas>(tipoModel);
		for (TiposFichas w : TiposFichas.values())
			tipoModel.addElement(w);
		mainPanel.add(new JLabel("id"));
		mainPanel.add(id);
		mainPanel.add(new JLabel("TextoID"));
		mainPanel.add(textoID);
		mainPanel.add(new JLabel("Ruta Texto"));
		mainPanel.add(texto);
		mainPanel.add(new JLabel("ImagenID"));
		mainPanel.add(imagenID);
		mainPanel.add(new JLabel("Ruta Imagen"));
		mainPanel.add(imagen);
		mainPanel.add(new JLabel("Doc ID"));
		mainPanel.add(docID);
		mainPanel.add(new JLabel("Ruta Doc"));
		mainPanel.add(doc);
		mainPanel.add(new JLabel("Tiempo"));
		mainPanel.add(tiempo);
		mainPanel.add(new JLabel("Tipo"));
		mainPanel.add(tipo);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				AddFileDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!id.getText().equals("") && !imagen.getText().equals("") && !imagenID.getText().equals("") &&
				!texto.getText().equals("") && !textoID.getText().equals("") && !doc.getText().equals("") 
				&& !docID.getText().equals("") && tipo.getSelectedItem() != null) {
					_status = 1;
					AddFileDialog.this.setVisible(false);
				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(600, 450));
		pack();
		setResizable(true);
		setVisible(true);
	}

	public int getStatus() {
		return _status;
	}
	
	public String getID() {
		return id.getText();
	}
	
	public String getImagenID() {
		return imagenID.getText();
	}
	
	public String getImagen() {
		return imagen.getText();
	}
	
	public String getDocID() {
		return docID.getText();
	}
	
	public String getDoc() {
		return doc.getText();
	}
	
	public String getTextID() {
		return textoID.getText();
	}
	
	public String getText() {
		return texto.getText();
	}
	
	public TiposFichas getTipoFichas() {
		return (TiposFichas) tipo.getSelectedItem();
	}
	
	public int getTiempo() {
		return (int) tiempo.getValue();
	}

}
