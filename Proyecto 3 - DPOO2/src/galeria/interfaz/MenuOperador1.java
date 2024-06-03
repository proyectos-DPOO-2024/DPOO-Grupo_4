/**
 * 
 */
package galeria.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import galeria.modelo.inventario.Galeria;

/**
 * 
 */
public class MenuOperador1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private InternalFrameManager internalFrameManager;

	public MenuOperador1(Galeria galeria) {
		setTitle("Menu Operador");
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setBounds(100, 100, 800, 600);
	    contentPane = new JPanel();
	    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(contentPane);
	    contentPane.setLayout(new BorderLayout(0, 0));
	
	    desktopPane = new JDesktopPane();
	    contentPane.add(desktopPane, BorderLayout.CENTER);
	
	    internalFrameManager = new InternalFrameManager(desktopPane, galeria);
	
	    // Otros componentes y botones
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(10, 2, 10, 10));
	    contentPane.add(panel, BorderLayout.NORTH);
	
	    JLabel lblNewLabel = new JLabel("Te damos la bienvenida como Operador");
	    lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(lblNewLabel);
	
	    JLabel lblNewLabel_1 = new JLabel("¿En que te podemos ayudar?");
	    lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
	    panel.add(lblNewLabel_1);
	    
	    
		    JButton ofertaSubastaButton = new JButton("Añadir oferta a subasta");
		    ofertaSubastaButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameOfertaSubasta();
	            }
	        });
	        panel.add(ofertaSubastaButton);
	    
	    	JButton exhibirPiezaButton = new JButton("Exhibir Pieza");
	        exhibirPiezaButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameExhibirPieza();
	            }
	        });
	        panel.add(exhibirPiezaButton);
	
	        JButton guardarEnBodegaButton = new JButton("Guardar Pieza en Bodega");
	        guardarEnBodegaButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameGuardarPiezaEnBodega();
	            }
	        });
	        panel.add(guardarEnBodegaButton);
	    
	    	JButton verHistorialArtistaButton = new JButton("Ver Historial de Artista");
	        verHistorialArtistaButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameVerHistorialArtista();
	            }
	        });
	        panel.add(verHistorialArtistaButton);
	        
	        JButton verHistorialPiezaButton = new JButton("Ver Historial de Pieza");
	        verHistorialPiezaButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameVerHistorialPieza();
	            }
	        });
	        panel.add(verHistorialPiezaButton);
	        
	        JButton cerrarSesion = new JButton("Cerrar Sesión");
            cerrarSesion.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Lógica para cerrar sesión
                    cerrarSesion();
                }
            });

    }
    
    
    protected void cerrarSesion() {
        // Cierra el internal frame actual
        this.dispose();
    }

}
