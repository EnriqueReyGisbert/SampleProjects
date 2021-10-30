package histoApp.view;

import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import histoApp.controller.*;

public class AdminDisplay extends ScreenDisplay {						// Pantalla Gestión Administradores

	private static final long serialVersionUID = 1L;

	public AdminDisplay(Controller ctrl, SystemView frame) {
		super(ctrl, frame, null);
	}

	protected void createFrontPanel(Object tipo) {
		
		this.setLayout(null);
		
		// Botón SALIR
		
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(20,55,70,30);
		botonSalir.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ScreenDisplay.pilaPantallas.pop();
				AdminDisplay.this._frame.setDisplay("pantallaIdentificacion2.jpg", ScreenDisplay.pilaPantallas.peek());
			}
		});	
		
		// Botón AÑADIR FICHA
		
		JButton botonAnnadirFicha = new JButton("Añadir Ficha");
		botonAnnadirFicha.setBounds(90,160,310,70);
		botonAnnadirFicha.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				AddFileDialog f = new AddFileDialog((Frame) SwingUtilities.getWindowAncestor(botonAnnadirFicha));
				if(f.getStatus() == 1) {
					try {
						AddFileCommand c = new AddFileCommand(f.getID(), f.getImagen(), f.getImagenID(), f.getText(), f.getTextID(), f.getDoc(), f.getDocID(), f.getTipoFichas().toString(), f.getTiempo());
						c.execute(_app);
						JOptionPane.showMessageDialog(new JFrame(), "Ficha annadida correctamente", "Exito",
			    		        JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						JOptionPane.showMessageDialog(new JFrame(), "No se ha encontrado ningún documento de texto en la ruta indicada", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
					}
				} 
			}
		});	
		
		// Botón AÑADIR FICHA MAPA
		
		JButton botonFichaMapa = new JButton("Añadir Ficha al Mapa");
		botonFichaMapa.setBounds(90,260,310,70);
		botonFichaMapa.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				AddFileMapDialog f = new AddFileMapDialog((Frame) SwingUtilities.getWindowAncestor(botonFichaMapa));
				if(f.getStatus() == 1) {
					try {
						AddFileToMapCommand c = new AddFileToMapCommand(f.getID(), f.getx(), f.gety());
						if(c.execute(_app)) {
							JOptionPane.showMessageDialog(new JFrame(), "Ficha annadida correctamente al mapa", "Exito",
							        JOptionPane.INFORMATION_MESSAGE);
						}
						else {
							JOptionPane.showMessageDialog(new JFrame(), "No se encuentra la ficha con ID: " + f.getID(), "Exito",
							        JOptionPane.ERROR_MESSAGE);
						}
					} catch (HeadlessException e) {
						JOptionPane.showMessageDialog(new JFrame(), "Error al introducir los datos", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
					}	
				}
			}
		});	
		
		// Botón ELIMINAR USUARIO
		
		JButton botonEliminarUsuario = new JButton("Eliminar Usuario");
		botonEliminarUsuario.setBounds(90,360,310,70);
		botonEliminarUsuario.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				EliminateUserDialog f = new EliminateUserDialog((Frame) SwingUtilities.getWindowAncestor(botonEliminarUsuario));
				if(f.getStatus() == 1) {
					EliminateUserCommand c = new EliminateUserCommand(f.getID());
					c.execute(_app);
					JOptionPane.showMessageDialog(new JFrame(), "Listo. Ya no hay ningún usuario con ID: " + f.getID(), "Exito",
			    		        JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});	
		
		// Botón MODIFICAR FICHA
		
		JButton botonModificarFicha = new JButton("Modificar Ficha");
		botonModificarFicha.setBounds(90,460,310,70);
		botonModificarFicha.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				ModifyFileDialog f = new ModifyFileDialog((Frame) SwingUtilities.getWindowAncestor(botonModificarFicha));
				if(f.getStatus() == 1) {
					ModifyFileCommand c = null;
					if(f.getTipoCambio().equals("imagen"))
						c = new ModifyFileCommand(f.getID(), f.getCambioID(), f.getRoute(), null, null);
					else if(f.getTipoCambio().equals("doc"))
						c = new ModifyFileCommand(f.getID(), f.getCambioID(), null, null, f.getRoute());
					else if(f.getTipoCambio().equals("texto"))
						c = new ModifyFileCommand(f.getID(), f.getCambioID(), null, f.getRoute(), null);
					boolean b = false;
					try {
						b = c.execute(_app);
					} catch (FileNotFoundException e) {
						JOptionPane.showMessageDialog(new JFrame(), "File not found", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
					}
					if(b)
						JOptionPane.showMessageDialog(new JFrame(), "Ficha annadida correctamente", "Exito",
		    		        JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(new JFrame(), "No se ha encontrado la ficha indicada", "Error",
			    		        JOptionPane.ERROR_MESSAGE);
				} 
			}
		});
		
		this.add(botonSalir);
		this.add(botonFichaMapa);
		this.add(botonEliminarUsuario);
		this.add(botonAnnadirFicha);
		this.add(botonModificarFicha);
		
		this.setOpaque(false);
		
	}

}
