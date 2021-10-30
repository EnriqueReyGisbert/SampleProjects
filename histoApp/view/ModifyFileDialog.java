package histoApp.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ModifyFileDialog extends JDialog {						// Pantalla Modificar Ficha

	private static final long serialVersionUID = 1L;
	
	private int _status;
	private JTextField id;
	private JTextField idCambio;
	private DefaultComboBoxModel<String> tipoModel;
	private JComboBox<String> tipo;
	private JTextField routeCambio;

	public ModifyFileDialog(Frame Parent) {
		super(Parent, true);
		initGUI();
	}
	
	private void initGUI() {
		
		_status = 0;
		
		setTitle("Modificar Ficha");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel helpMsg = new JLabel("<html>Introduce los datos de la ficha que desea modificar</html>");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
		id = new JTextField();
		idCambio = new JTextField();
		routeCambio = new JTextField();
		tipoModel = new DefaultComboBoxModel<String>();
		tipo = new JComboBox<String>(tipoModel);
		tipoModel.addElement("imagen");
		tipoModel.addElement("doc");
		tipoModel.addElement("texto");
		mainPanel.add(new JLabel("id"));
		mainPanel.add(id);
		mainPanel.add(new JLabel("ID Cambio"));
		mainPanel.add(idCambio);
		mainPanel.add(new JLabel("Ruta Cambio"));
		mainPanel.add(routeCambio);
		mainPanel.add(new JLabel("Tipo"));
		mainPanel.add(tipo);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				ModifyFileDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!id.getText().equals("") && !idCambio.getText().equals("") && !routeCambio.getText().equals("") &&
				tipo.getSelectedItem() != null) {
					_status = 1;
					ModifyFileDialog.this.setVisible(false);
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
	
	public String getCambioID() {
		return idCambio.getText();
	}
	
	public String getRoute() {
		return routeCambio.getText();
	}
	
	public String getTipoCambio() {
		return (String) tipo.getSelectedItem();
	}
	
}
