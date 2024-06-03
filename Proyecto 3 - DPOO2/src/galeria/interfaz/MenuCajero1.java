package galeria.interfaz;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.SolicitudTope;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuCajero1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private InternalFrameManager internalFrameManager;
    public static List<Pago> listaPagos = new LinkedList<Pago>();
    public static List<Pieza> listaPiezasPorIngresar = new LinkedList<Pieza>();
    public static List<String> listaCompradores = new LinkedList<String>();
    public static List<SolicitudTope> listaSolicitudesTope = new LinkedList<SolicitudTope>();

    /**
     * Create the frame.
     */
    public MenuCajero1(Galeria galeria, List<Pieza> listaPiezasPorIngresar) {
        desktopPane = new JDesktopPane();
        internalFrameManager = new InternalFrameManager(desktopPane, galeria, listaPiezasPorIngresar, listaCompradores, listaPagos, listaSolicitudesTope);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 354);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Cajero");
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(259, 37, 112, 26);
        contentPane.add(lblNewLabel);

        JButton pagoDirecta = new JButton("Registrar pago por venta directa");
        pagoDirecta.setBounds(202, 75, 206, 26);
        contentPane.add(pagoDirecta);
        pagoDirecta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		MenuPasarela menuPasarela = new MenuPasarela();
        		String pasarela = menuPasarela.getPasarela();
        		MenuPago menuPago = new MenuPago(pasarela);
                mostrarInternalFrameRegistrarPagoPorVentaDirecta();
            }
        });

        JButton pagoSubasta = new JButton("Registrar pago por subasta");
        pagoSubasta.setBounds(202, 103, 206, 26);
        contentPane.add(pagoSubasta);
        pagoSubasta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
        		MenuPasarela menuPasarela = new MenuPasarela();
        		String pasarela = menuPasarela.getPasarela();
        		MenuPago menuPago = new MenuPago(pasarela);

            	mostrarInternalFrameRegistrarPagoPorSubasta();
            }
        });

        JButton historialPieza = new JButton("Ver historia pieza");
        historialPieza.setBounds(202, 133, 206, 26);
        contentPane.add(historialPieza);
        historialPieza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                internalFrameManager.mostrarInternalFrameVerHistorialPieza();
            }
        });

        JButton historialArtista = new JButton("Ver historia artista");
        historialArtista.setBounds(202, 162, 206, 26);
        contentPane.add(historialArtista);
        historialArtista.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                internalFrameManager.mostrarInternalFrameVerHistorialArtista();
            }
        });

        JButton exhibirPieza = new JButton("Exhibir pieza");
        exhibirPieza.setBounds(202, 192, 206, 26);
        contentPane.add(exhibirPieza);
        exhibirPieza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Aquí deberías implementar la lógica para exhibir la pieza
            	internalFrameManager.mostrarInternalFrameExhibirPieza();
            }
        });

        JButton guardarBodega = new JButton("Guardar pieza en bodega");
        guardarBodega.setBounds(202, 222, 206, 26);
        contentPane.add(guardarBodega);
        guardarBodega.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	internalFrameManager.mostrarInternalFrameGuardarPiezaEnBodega();
            }
        });

        JButton cerrarSesion = new JButton("Cerrar sesión");
        cerrarSesion.setBounds(202, 253, 206, 26);
        contentPane.add(cerrarSesion);
        cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        contentPane.add(desktopPane);
        desktopPane.setBounds(0, 0, 600, 320); // Ajusta las dimensiones según sea necesario
    }

    // Métodos para mostrar los internal frames (puedes implementar la lógica específica en estos métodos)
    private void mostrarInternalFrameRegistrarPagoPorVentaDirecta() {
        // Implementa la lógica para mostrar el internal frame correspondiente
    }

    private void mostrarInternalFrameRegistrarPagoPorSubasta() {
        // Implementa la lógica para mostrar el internal frame correspondiente
    }


    private void cerrarSesion() {
        dispose();
    }
}
