package galeria.interfaz;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
import galeria.persistencia.CentralPersistencia;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.LinkedList;

public class MenuPrincipal1 extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usuario;
    private JTextField contraseña;
    protected Galeria galeria;
    protected List<Pieza> listaPiezasPorIngresar;

    public static void main(String[] args) throws IOException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CentralPersistencia persistencia = new CentralPersistencia();
                    String[] archivos = new String[2];
                    archivos[0] = "./datos/galeria.json";
                    archivos[1] = "./datos/centro_ventas.json";
                    Galeria galeria = persistencia.cargarPrograma(archivos);
                    List<Pieza> listaPiezasPorIngresar = new LinkedList<>();
                    MenuPrincipal1 frame = new MenuPrincipal1(galeria, listaPiezasPorIngresar);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    protected MenuPrincipal1(Galeria galeria, List<Pieza> listaPiezasPorIngresar) {
        this.galeria = galeria;
        this.listaPiezasPorIngresar = listaPiezasPorIngresar;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 616, 333);
        contentPane = new JPanel();
        contentPane.setBackground(Color.LIGHT_GRAY);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        JButton iniciarSesion = new JButton("Iniciar Sesión");
        iniciarSesion.setBounds(250, 160, 151, 26);
        iniciarSesion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
        contentPane.setLayout(null);
        contentPane.add(iniciarSesion);

        JButton cerrarPrograma = new JButton("Cerrar Programa");
        cerrarPrograma.setBounds(250, 190, 151, 26);
        cerrarPrograma.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cerrarPrograma();
            }
        });
        contentPane.add(cerrarPrograma);

        JLabel titulo = new JLabel("Casa de Subastas");
        titulo.setBounds(250, 50, 151, 26);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(titulo);

        usuario = new JTextField();
        usuario.setBounds(250, 87, 151, 26);
        contentPane.add(usuario);
        usuario.setColumns(10);

        contraseña = new JTextField();
        contraseña.setBounds(250, 123, 151, 26);
        contentPane.add(contraseña);
        contraseña.setColumns(10);

        JLabel tiUsuario = new JLabel("Usuario:");
        tiUsuario.setBounds(159, 87, 81, 23);
        contentPane.add(tiUsuario);

        JLabel tiContraseña = new JLabel("Contraseña:");
        tiContraseña.setBounds(139, 125, 81, 23);
        contentPane.add(tiContraseña);
    }

    private void iniciarSesion() {
        String usu = usuario.getText();
        String con = contraseña.getText();
        int tipoUsuario = galeria.verificarLogin(usu, con);
        if (tipoUsuario < 0) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario ingresado no existe", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        } else if (tipoUsuario == 0) {
            JOptionPane.showMessageDialog(this, "La contraseña ingresada es incorrecta", "Error de inicio de sesión", JOptionPane.ERROR_MESSAGE);
        } else {
            // Proceso de inicio de sesión exitoso
            System.out.println("Texto ingresado en el campo 1: " + usu);
            System.out.println("Texto ingresado en el campo 2: " + con);
            // Si el tipo de usuario es 1 (administrador), abrir el menú de administrador
            if (tipoUsuario == 1) {
                MenuAdministrador1 menuAdmin = new MenuAdministrador1(galeria, listaPiezasPorIngresar);
                menuAdmin.setVisible(true);
            }
            if (tipoUsuario == 2) {
                MenuCajero1 menuCajero = new MenuCajero1(galeria, listaPiezasPorIngresar);
                menuCajero.setVisible(true);
            }
        }
    }

    private void cerrarPrograma() {
        System.exit(0);
    }
}
