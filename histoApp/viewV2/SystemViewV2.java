package histoApp.viewV2;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import org.json.JSONException;
import histoApp.baseDatos.*;
import histoApp.controller.*;
import histoApp.model.*;

public abstract class SystemViewV2 extends JFrame implements AppObserver{
	
	private static final long serialVersionUID = 1L;
	
	protected Controller _ctrl;
	protected App _app;
	
	Ficha _ficha;
	protected TiposFichas _Tfichas;
	protected javax.swing.JLabel butSalir;
	protected javax.swing.JPanel jPanel1;
	
	public SystemViewV2(Controller ctrl) {
		super("HistoApp");
		set_ctrl(ctrl);
		get_ctrl().addObserver(this);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
		    @Override
		    public void windowClosing(WindowEvent event) {
		        exitProcedure();
		    }
		});
		initApp();
		this.setVisible(true);
	}
	
	public SystemViewV2() {
		
	}
	
	public SystemViewV2(Controller ctrl, Ficha ficha) {
		super("HistoApp");
		_ficha = ficha;
		set_ctrl(ctrl);
		get_ctrl().addObserver(this);
		initApp();
		this.setVisible(true);
		
	}
	
	public SystemViewV2(Controller ctrl, TiposFichas ficha) {
		
		super("HistoApp");
		_Tfichas = ficha;
		set_ctrl(ctrl);
		get_ctrl().addObserver(this);
		initApp();
		this.setVisible(true);
		
	}
	
	protected abstract void initApp();
	
	public void update(App app) {			
		_app = app;
	}
	
	protected void goToScreen(java.awt.event.MouseEvent evt, SystemViewV2 o) {
        o.setVisible(true);
        o.pack();
        o.setLocationRelativeTo(this);
        o.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
	}
	
	@SuppressWarnings("deprecation")
	protected void goToMap(String image, JPanel panel) {
		
		Image _imagen = loadImage(image).getScaledInstance(this.getWidth() - 15, this.getHeight() - 40, Image.SCALE_SMOOTH);
		
		JLayeredPane pane = new JLayeredPane();
		pane.setPreferredSize(new Dimension(this.getWidth() - 15, this.getHeight() - 40));
		
		JPanel imagen = new JPanel() {
			
			private static final long serialVersionUID = 1L;

			@Override
	         public void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            g.drawImage(_imagen, 0, 0, null);
	         }
		};

		imagen.setBounds(0, 0, this.getWidth() - 15, this.getHeight() - 40);
		
		panel.setBounds(0, 0, this.getWidth() - 15, this.getHeight() - 40);
		
		pane.add(imagen, new Integer(1));
		
		pane.add(panel, new Integer(2));
		
		this.setContentPane(pane);
		this.repaint();
		this.revalidate();
	}
	
	private void exitProcedure() {
		EndCommand c = new EndCommand();
		try {
			c.execute(_app);
		} catch (JSONException | IOException e) {
			JOptionPane.showMessageDialog(this, "No se ha podido guardar los cambios",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		System.exit(0);
	}
	
	private Image loadImage(String img) {						// Abrir Imagen
		Image i = null;
		try {
			return ImageIO.read(new File("resources/icons/" + img));
		} catch (IOException e) {
		}
		return i;
	}
	
	public Controller get_ctrl() {
		return _ctrl;
	}
	
	public void set_ctrl(Controller _ctrl) {
		this._ctrl = _ctrl;
	}

}
