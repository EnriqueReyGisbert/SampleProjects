package histoApp.view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class RegisterDialog extends JDialog {							// Pantalla Registro Usuario

	private static final long serialVersionUID = 1L;
	
	private int _status;
	private JTextField nombre;					// Introducir Nombre
	private JTextField contrasenna;				// Introducir Contraseña
	private JTextField apodo;					// Introducir Apodo
	private JTextField codigo;					// Introducir Codigo (Administrador)
	private JSpinner grado;						// Introducir Grado Modificación (Administrador)

	public RegisterDialog(Frame Parent) {
		super(Parent, true);
		initGUI();
	}
	
	private void initGUI() {
		
		_status = 0;
		
		setTitle("Registrarse");
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		setContentPane(mainPanel);
		
		JLabel helpMsg = new JLabel("<html>Introduce tus datos para registrarte como usuario en la aplicación</html>");
		helpMsg.setAlignmentX(CENTER_ALIGNMENT);

		mainPanel.add(helpMsg);

		mainPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		JPanel viewsPanel = new JPanel();
		viewsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(viewsPanel);
		
		nombre = new JTextField();
		contrasenna = new JTextField();
		apodo = new JTextField();
		codigo = new JTextField();
		SpinnerNumberModel gradoModel = new SpinnerNumberModel(0, 0, 10, 1);
		grado = new JSpinner(gradoModel);
		mainPanel.add(new JLabel("Nombre"));
		mainPanel.add(nombre);
		mainPanel.add(new JLabel("Contrasenna"));
		mainPanel.add(contrasenna);
		mainPanel.add(new JLabel("Apodo"));
		mainPanel.add(apodo);
		mainPanel.add(new JLabel("Codigo (Administrador)"));
		mainPanel.add(codigo);
		mainPanel.add(new JLabel("Grado (Administrador)"));
		mainPanel.add(grado);
		
		JPanel buttonsPanel = new JPanel();
		buttonsPanel.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(buttonsPanel);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_status = 0; 
				RegisterDialog.this.setVisible(false);
			}
		});
		buttonsPanel.add(cancelButton);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!nombre.getText().equals("") && !contrasenna.getText().equals("") && !apodo.getText().equals("")) {
					if(!codigo.getText().equals(""))
						_status = 2;
					else
						_status = 1;
					RegisterDialog.this.setVisible(false);
				}
				else
					 JOptionPane.showMessageDialog(new JFrame(), "Invalid Credentials", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
			}
		});
		buttonsPanel.add(okButton);

		setPreferredSize(new Dimension(600, 300));
		pack();
		setResizable(true);
		setVisible(true);
	}
	
	public int getStatus() {
		return _status;
	}
	
	public String getNombre() {
		return nombre.getText();
	}
	
	public String getContrasenna() {
		return contrasenna.getText();
	}
	
	public String getApodo() {
		return apodo.getText();
	}
	
	public String getCodigo() {
		return codigo.getText();
	}
	
	public Integer getGrado() {
		return (Integer) grado.getValue();
	}

}
