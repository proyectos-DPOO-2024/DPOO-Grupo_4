/**
 * 
 */
package galeria.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import galeria.interfaz.InternalFrameManager;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.usuarios.Cliente;

/**
 * 
 */
public class MenuCliente1 extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private InternalFrameManager internalFrameManager;
    private Cliente esteCliente;
    
    
    public MenuCliente1(Galeria galeria, List<Pieza> listaPiezasPorIngresar, String usuario) {
        setTitle("Menu Cliente");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);

        internalFrameManager = new InternalFrameManager(desktopPane, galeria, listaPiezasPorIngresar);
        esteCliente = galeria.getCliente(usuario);

        // Otros componentes y botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Te damos la bienvenida, " + esteCliente.getLogin());
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Tope de Compras: " + Long.toString(esteCliente.getTopeCompras()));
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel_1);
        
        
	        JButton confirmarPieza = new JButton("Registrar nueva pieza");
	        confirmarPieza.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameRegistrarNuevaPieza(esteCliente.getLogin());
	            }
	        });
	        panel.add(confirmarPieza);
	        
	        JButton consignarPieza = new JButton("Consignar pieza");
	        confirmarPieza.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	List<String> piezasCliente = galeria.getPiezasActuales(esteCliente.getLogin());
	        		List<Pieza> piezasPorConsignar = new LinkedList<Pieza>();
	        		
	        		Iterator<String> it = piezasCliente.iterator();
	        		
	        		if ( piezasCliente.isEmpty() ) {
	        			JOptionPane.showMessageDialog(panel, "No tiene piezas por consignar");
	        		}
	        		else {
	        			while ( it.hasNext() ) {
	        				String tituloPieza = it.next();
	        				Pieza pieza = galeria.getPieza(tituloPieza);
	        				if ( !pieza.isEnPosesion() ) {
	        					piezasPorConsignar.add(pieza);
	        				}
	        			}
	        			internalFrameManager.mostrarInternalFrameConsignarNuevaPieza(esteCliente.getLogin(), piezasPorConsignar);
	        		}
	            }
	        });
	        panel.add(consignarPieza);
	        
	        JButton verificacionComprador = new JButton("Aplicar para verificación de comprador");
	        verificacionComprador.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if ( esteCliente.isVerificado() ) {
	                	JOptionPane.showMessageDialog(panel, "Usted ya está verificado");
	                }
	                else {
	                	MenuAdministrador1.listaCompradores.add(esteCliente.getLogin());
	                	JOptionPane.showMessageDialog(panel, "Solicitud de verificación enviada con éxito.");
	                }
	            }
	        });
	        panel.add(verificacionComprador);
	        
	        JButton ampliarTope = new JButton("Aplicar para ampliación de tope de compras");
	        confirmarPieza.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameAmpliarTope(esteCliente.getLogin());
	            }
	        });
	        panel.add(ampliarTope);
	        
	        JButton verHistorialClienteButton = new JButton("Ver Historial de Cliente");
            verHistorialClienteButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    internalFrameManager.mostrarInternalFrameHistorialCliente(esteCliente.getLogin());
                }
            });
	        
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

    }
}
