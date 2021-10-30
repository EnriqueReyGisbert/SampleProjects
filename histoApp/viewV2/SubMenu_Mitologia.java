package histoApp.viewV2;

import java.util.*;
import javax.swing.JButton;
import histoApp.baseDatos.*;
import histoApp.controller.*;

public class SubMenu_Mitologia extends SystemViewV2 {

	private static final long serialVersionUID = 1L;
	
	private javax.swing.JLabel BackButtonMitology;
    private javax.swing.JLabel Fondo;
    private javax.swing.JButton Griega;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    
    private Map<javax.swing.JButton , Ficha> _mapaBotonFicha;
    private List<Ficha> listaFichas;

	public SubMenu_Mitologia(Controller ctrl) {
		super(ctrl);
	}
	
	protected void initApp() {
		
		jPanel1= new javax.swing.JPanel();
		_mapaBotonFicha= new HashMap<javax.swing.JButton , Ficha>();
		listaFichas=_app.getFichas().getFichas();
		
        Griega = new javax.swing.JButton();
        BackButtonMitology = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        Fondo = new javax.swing.JLabel();
        for(Ficha f : listaFichas)
		{
			if(f.getTipo().equals(TiposFichas.mitologia))
			{
				String name = f.getID();
				if(name.equals("mitologiaGriega"))
					_mapaBotonFicha.put(Griega, f);
			}
		}

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        BackButtonMitology.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/botonsalir.png")); // NOI18N
        BackButtonMitology.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                goToScreen(evt,new MenuPrincipal(get_ctrl()));
            }
        });
        jPanel1.add(BackButtonMitology, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 80, 80));
        int i=380;
		for(Ficha f : listaFichas) {
			if(f.getTipo().equals(TiposFichas.mitologia)) {
				
				JButton botonFicha = new JButton(f.getID().substring(9, f.getID().length()));
				botonFicha.setBackground(new java.awt.Color(118, 93, 105));
				botonFicha.setFont(new java.awt.Font("Historycal Inline", 0, 40)); // NOI18N
				botonFicha.setForeground(new java.awt.Color(255, 255, 255));
				botonFicha.setBorder(null);
				botonFicha.setBorderPainted(false);
				botonFicha.setFocusPainted(false);
				botonFicha.addMouseListener(new java.awt.event.MouseAdapter() {
		            public void mouseClicked(java.awt.event.MouseEvent evt) {
		            	goToScreen(evt,new VerFicha(get_ctrl(),f));
		            }
		        });
				jPanel1.add(botonFicha, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, i, 260, 110));
				
				i += 140;
			}
		}

        jLabel2.setFont(new java.awt.Font("Historycal Inline", 0, 110)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Seleccione una");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 110, 420, -1));

        jLabel1.setFont(new java.awt.Font("Historycal Inline", 0, 150)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Opcion");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 250, -1));

        Fondo.setIcon(new javax.swing.ImageIcon("resources/GUI/Disenos/background.png")); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, -1, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 460, 840));

        pack();
	}

}
