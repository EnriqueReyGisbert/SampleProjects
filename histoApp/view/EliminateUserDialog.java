package histoApp.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EliminateUserDialog extends JDialog {						// Pantalla Eliminar Usuario

	private static final long serialVersionUID = 1L;
	
	private int _status;
	private JTextField id;								// Introducir Id
	
	EliminateUserDialog (Frame Parent) {
		super(Parent, true);
		initGUI();
	}
	
	private void initGUI() {
		
		_status = 0;
		
		setTitle("Eliminar un usuario");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel helpMsg = new JLabel("<html>Introduce el ID del usuario que desa eliminar</html>");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
		JLabel idLabel = new JLabel("Id");
		id = new JTextField();

		this.add(idLabel);
		this.add(id);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				EliminateUserDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!id.getText().equals("")) {
					_status = 1;
					EliminateUserDialog.this.setVisible(false);
				}
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(600, 160));
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
	
}
