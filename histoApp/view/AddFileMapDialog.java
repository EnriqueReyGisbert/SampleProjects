package histoApp.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class AddFileMapDialog extends JDialog {							// Pantalla Añadir Ficha a Mapa
	
	private static final long serialVersionUID = 1L;
	
	private int _status;
	private JTextField id;									// Introducir Id
	private JSpinner x;										// Introducir Coord. X
	private JSpinner y;										// Introducir Coord. Y
	
	AddFileMapDialog (Frame Parent) {
		super(Parent, true);
		initGUI();
	}
	
	private void initGUI() {
		
		_status = 0;
		
		setTitle("A\\u00f1adir ficha a un mapa");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel helpMsg = new JLabel("<html>Introduce el ID y la posición de la ficha a a\\u00f1adir a un mapa</html>");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
		JLabel idLabel = new JLabel("Id");
		id = new JTextField();
		JLabel xLabel = new JLabel("Coordenada x");
		SpinnerNumberModel xModel = new SpinnerNumberModel(0, 0, 700, 10);
		x = new JSpinner(xModel);
		JLabel yLabel = new JLabel("Coordenada y");
		SpinnerNumberModel yModel = new SpinnerNumberModel(0, 0, 500, 10);
		y = new JSpinner(yModel);
		
		this.add(idLabel);
		this.add(id);
		this.add(xLabel);
		this.add(x);
		this.add(yLabel);
		this.add(y);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				AddFileMapDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!id.getText().equals("")) {
					_status = 1;
					AddFileMapDialog.this.setVisible(false);
				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(600, 250));
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
	
	public int getx() {
		return (int) x.getValue();
	}
	
	public int gety() {
		return (int) y.getValue();
	}
	
}
