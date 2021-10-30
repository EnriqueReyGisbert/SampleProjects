package histoApp.viewV2;

import javax.swing.JButton;
import histoApp.controller.Controller;

public abstract class  ScreenDisplayV2 extends SystemViewV2{

	SystemViewV2 _frame;
	private static final long serialVersionUID = 1L;
	
	public ScreenDisplayV2(Controller ctrl,SystemViewV2 frame, Object tipo) {
		set_ctrl(ctrl);
		_frame = frame;
		get_ctrl().addObserver(this);
		initApp();
	}
	
	protected void initApp() {
		JButton botonSalir = new JButton("Salir");
		botonSalir.setBounds(350,50,70,30);
		botonSalir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
            	goToScreen(evt,new SubMenu_Historia(get_ctrl()));
            }
        });
	}

}
