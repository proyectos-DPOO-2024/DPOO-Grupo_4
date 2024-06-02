package galeria.interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.SolicitudTope;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class MenuAdministrador1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JDesktopPane desktopPane;
    private InternalFrameManager internalFrameManager;
    public static List<Pago> listaPagos = new LinkedList<Pago>();
    public static List<Pieza> listaPiezasPorIngresar = new LinkedList<Pieza>();
    public static List<String> listaCompradores = new LinkedList<String>();
    public static List<SolicitudTope> listaSolicitudesTope = new LinkedList<SolicitudTope>();
    

    public MenuAdministrador1(Galeria galeria, List<Pieza> listaPiezasPorIngresar) {
        setTitle("Menu Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        desktopPane = new JDesktopPane();
        contentPane.add(desktopPane, BorderLayout.CENTER);

        internalFrameManager = new InternalFrameManager(desktopPane, galeria, listaPiezasPorIngresar, listaCompradores, listaPagos, listaSolicitudesTope);

        // Otros componentes y botones
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10, 2, 10, 10));
        contentPane.add(panel, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Te damos la bienvenida como Administrador");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("¿En que te podemos ayudar?");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        panel.add(lblNewLabel_1);

       
        
                JButton confirmarPieza = new JButton("Confirmar nueva pieza por consignación");
                confirmarPieza.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameConfirmarPieza();
                    }
                });
                panel.add(confirmarPieza);
        
                JButton verificarCompradorButton = new JButton("Verificar nuevo comprador");
                verificarCompradorButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameVerificarCliente();
                    }
                });
                panel.add(verificarCompradorButton);
                JButton verHistorialClienteButton = new JButton("Ver Historial de Cliente");
                verHistorialClienteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameVerHistorialCliente();
                    }
                });
        
                JButton confirmarEntrega = new JButton("Confirmar entrega de pieza pagada");
                confirmarEntrega.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameConfirmarEntrega();
                    }
                });
                panel.add(confirmarEntrega);
        
        
                JButton ampliarTopeButton = new JButton("Ampliar Tope Comprador");
                ampliarTopeButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameAmpliarTope();
                    }
                });
                panel.add(ampliarTopeButton);
        
                JButton devolucion = new JButton("Realizar devolución de pieza");
                devolucion.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        // Lógica para realizar devolución de pieza
                        realizarDevolucionPieza();
                    }
                });
                panel.add(devolucion);
        
                JButton agregarEmpleadoButton = new JButton("Agregar Empleado");
                agregarEmpleadoButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameAgregarEmpleado();
                    }
                });
                panel.add(agregarEmpleadoButton);
        
                JButton verHistorialArtistaButton = new JButton("Ver Historial de Artista");
                verHistorialArtistaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameVerHistorialArtista();
                    }
                });
                
                JButton verHistorialPiezaButton = new JButton("Ver Historial de Pieza");
                verHistorialPiezaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        internalFrameManager.mostrarInternalFrameVerHistorialPieza();
                    }
                });
                panel.add(verHistorialPiezaButton);
                panel.add(verHistorialArtistaButton);
        panel.add(verHistorialClienteButton);

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


        JButton cerrarSesion = new JButton("Cerrar Sesión");
        cerrarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica para cerrar sesión
                cerrarSesion();
            }
        });
        panel.add(cerrarSesion);
    }




    private void realizarDevolucionPieza() {
        // Implementar lógica
    }

    protected void cerrarSesion() {
        // Cierra el internal frame actual
        this.dispose();
    }

}
