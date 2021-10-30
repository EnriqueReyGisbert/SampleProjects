package histoApp.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;
import histoApp.baseUsuarios.*;
import histoApp.controller.*;

public class LogInDisplay extends ScreenDisplay {							// Pantalla Identificación Inicial

	private static final long serialVersionUID = 1L;

	public LogInDisplay(Controller ctrl, SystemView frame) {
		super(ctrl, frame, null);
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(null);
		
		JTextField idUsuario = new JTextField();
		JTextField contrasenna = new JTextField();
		idUsuario.setBounds(45, 280, 400, 50);
		contrasenna.setBounds(45, 370, 400, 50);
		
		// Botón SALIR
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(20,30,70,30);
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				try {
					EndCommand c = new EndCommand();
					c.execute(_app);
				} catch (IOException e) {
					
				}
				 System.exit(0);
			}
		});
		
		// Botón LOG IN
		
		JButton botonLogIn = new JButton("Log In");
		botonLogIn.setBounds(45,500,400,50);
		botonLogIn.addActionListener(new ActionListener() {
			   public void actionPerformed(ActionEvent ae){
			      String id = idUsuario.getText();
			      String lock = contrasenna.getText();
			      if(!id.equals("") && !lock.equals("")) {
			    	boolean found = false;
					IdentifyUserCommand c = new IdentifyUserCommand(id, lock);
					found = c.execute(_app);
				      if (found) {
				    	  Usuario u = _app.getUsuarios().getUsuario(id);
				    	  if(u.getType().equals("user"))
				    		  LogInDisplay.this._frame.setDisplay("pantallaMenuPrincipal.jpg", new MenuDisplay(_ctrl, LogInDisplay.this._frame));
				    	  else
				    		  LogInDisplay.this._frame.setDisplay("pantallaCarga.jpg", new AdminDisplay(_ctrl, LogInDisplay.this._frame));
				      }
				      else {
				    	  JOptionPane.showMessageDialog(new JFrame(), "Invalid Credentials", "Error",
				    		        JOptionPane.ERROR_MESSAGE);
				      }    
			      }
			   }
			});
		
		// Botón REGISTER
		
		JButton botonRegister = new JButton("Register");
		botonRegister.setBounds(45,100,400,50);
		botonRegister.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent ae){
		   RegisterDialog register = new RegisterDialog((Frame) SwingUtilities.getWindowAncestor(botonRegister));
		   		if (register.getStatus() == 1) {
		   			AddUserCommand c = new AddUserCommand(register.getNombre(), register.getContrasenna(), register.getApodo(), null, null);
					c.execute(_app);
				   	LogInDisplay.this._frame.setDisplay("pantallaMenuPrincipal.jpg", new MenuDisplay(_ctrl, LogInDisplay.this._frame));
		   		}
		   		else if(register.getStatus() == 2) {
		   			AddUserCommand c = new AddUserCommand(register.getNombre(), register.getContrasenna(), register.getApodo(), register.getCodigo(), register.getGrado());
					c.execute(_app);
		   		   	LogInDisplay.this._frame.setDisplay("pantallaCarga.jpg", new AdminDisplay(_ctrl, LogInDisplay.this._frame));
		   		
		   		}
			}
		});
		
		this.add(botonSalir);
		this.add(idUsuario);
		this.add(contrasenna);
		this.add(botonLogIn);
		this.add(botonRegister);
		
		this.setOpaque(false);
		
	}

}
