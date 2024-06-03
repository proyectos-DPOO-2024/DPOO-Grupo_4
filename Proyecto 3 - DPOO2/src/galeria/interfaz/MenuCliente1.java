/**
 * 
 */
package galeria.interfaz;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

        JLabel lblNewLabel_1 = new JLabel("¿En que te podemos ayudar?");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel_1);
        
        
	        JButton confirmarPieza = new JButton("Registrar nueva pieza");
	        confirmarPieza.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                internalFrameManager.mostrarInternalFrameRegistrarNuevaPieza(esteCliente.getLogin());
	            }
	        });
	        panel.add(confirmarPieza);

    }
}
