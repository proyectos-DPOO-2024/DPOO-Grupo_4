package galeria.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.SolicitudTope;
import galeria.modelo.centroventas.Subasta;
import galeria.modelo.inventario.Escultura;
import galeria.modelo.inventario.Fotografia;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Impresion;
import galeria.modelo.inventario.Pieza;
import galeria.modelo.inventario.Pintura;
import galeria.modelo.inventario.Video;
import galeria.modelo.usuarios.Cliente;

public class InternalFrameManager {

    private JDesktopPane desktopPane;
    private Galeria galeria;
    private List<Pieza> listaPiezasPorIngresar;
    private List<String> listaCompradores;
    private List<Pago> listaPagos;
    private List<SolicitudTope> listaSolicitudesTope;

    public InternalFrameManager(JDesktopPane desktopPane, Galeria galeria, List<Pieza> listaPiezasPorIngresar, List<String> listaCompradores, List<Pago> listaPagos, List<SolicitudTope> listaSolicitudesTope) {
        this.desktopPane = desktopPane;
        this.galeria = galeria;
        this.listaPiezasPorIngresar = listaPiezasPorIngresar;
        this.listaCompradores = listaCompradores;
        this.listaPagos = listaPagos;
        this.listaSolicitudesTope = listaSolicitudesTope;
    }
    
    public InternalFrameManager(JDesktopPane desktopPane, Galeria galeria, List<Pieza> listaPiezasPorIngresar) {
        this.desktopPane = desktopPane;
        this.galeria = galeria;
        this.listaPiezasPorIngresar = listaPiezasPorIngresar;
    }
    
    public InternalFrameManager(JDesktopPane desktopPane, Galeria galeria) {
        this.desktopPane = desktopPane;
        this.galeria = galeria;
    }

    public void mostrarInternalFrameConfirmarPieza() {
        JInternalFrame internalFrame = new JInternalFrame("Confirmar Nueva Pieza por Consignación", true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        internalFrame.add(panel, BorderLayout.CENTER);

        String[] opciones = construirOpciones();

        JComboBox<String> comboBox = new JComboBox<>(opciones);
        panel.add(comboBox);

        JButton confirmarButton = new JButton("Confirmar");
        confirmarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int indexSeleccionado = comboBox.getSelectedIndex();
                if (indexSeleccionado != -1) {
                    Pieza piezaSeleccionada = listaPiezasPorIngresar.get(indexSeleccionado);
                    galeria.consignarPiezaNueva(piezaSeleccionada);
                    JOptionPane.showMessageDialog(internalFrame, "Pieza confirmada exitosamente");
                    internalFrame.dispose(); // Cierra el InternalFrame después de confirmar la pieza
                }
            }
        });
        panel.add(confirmarButton);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private String[] construirOpciones() {
        String[] opciones = new String[listaPiezasPorIngresar.size()];
        for (int i = 0; i < listaPiezasPorIngresar.size(); i++) {
            Pieza pieza = listaPiezasPorIngresar.get(i);
            opciones[i] = "Pieza #" + (i + 1) + ": " + pieza.getTitulo(); // Ajusta según el método de tu clase Pieza
        }
        return opciones;
    }
    public void mostrarInternalFrameVerificarCliente() {
        boolean busquedaManual = JOptionPane.showConfirmDialog(desktopPane, "¿Desea verificar un cliente manualmente?", "Verificar Cliente", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        if (busquedaManual) {
            String login = JOptionPane.showInputDialog(desktopPane, "Por favor ingrese el nombre de usuario del cliente que desea verificar:");
            if (login != null && !login.isEmpty()) {
                boolean existe = galeria.existeCliente(login);
                if (existe) {
                    Cliente cliente = galeria.getCliente(login);
                    if (cliente.isVerificado()) {
                        JOptionPane.showMessageDialog(desktopPane, "El cliente ingresado ya está verificado");
                    } else {
                        String topeStr = JOptionPane.showInputDialog(desktopPane, "Ingrese el tope de compras que desea asignar:");
                        try {
                            long tope = Long.parseLong(topeStr);
                            galeria.verificarNuevoComprador(cliente, tope);
                            JOptionPane.showMessageDialog(desktopPane, "Cliente verificado exitosamente");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(desktopPane, "Por favor ingrese un número válido para el tope");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(desktopPane, "El nombre de usuario ingresado no corresponde a ningún cliente registrado");
                }
            } else {
                JOptionPane.showMessageDialog(desktopPane, "Por favor ingrese un nombre de usuario válido");
            }
        }
        else {
			// Lógica cuando se elige no verificar manualmente
	        int numClientes = listaCompradores.size();
	        String[] opciones = new String[numClientes + 1];

	        Iterator<String> it = listaCompradores.iterator();
	        for (int i = 0; i < numClientes && it.hasNext(); i++) {
	            opciones[i] = it.next();
	        }
	        opciones[numClientes] = "Cancelar";
	        int opcionSeleccionada = JOptionPane.showOptionDialog(desktopPane, "No hay solicitudes de verificación pendientes", "Verificar Cliente",
	                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[numClientes]);

	        if (opcionSeleccionada != numClientes) {
	            Cliente clienteSeleccionado = galeria.getCliente(listaCompradores.get(opcionSeleccionada));
	            String topeStr = JOptionPane.showInputDialog(desktopPane, "Por favor ingrese el tope de compras que desea asignar:");
	            try {
	                long tope = Long.parseLong(topeStr);
	                galeria.verificarNuevoComprador(clienteSeleccionado, tope);
	                JOptionPane.showMessageDialog(desktopPane, "Cliente verificado exitosamente");
	            } catch (NumberFormatException ex) {
	                JOptionPane.showMessageDialog(desktopPane, "Por favor ingrese un número válido para el tope");
	            }
	        }
	    }
    }
    public void mostrarInternalFrameConfirmarEntrega() {
        JInternalFrame internalFrame = new JInternalFrame("Confirmar Entrega de Pieza Pagada", true, true, true, true);
        internalFrame.setSize(600, 400);
        internalFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        internalFrame.add(panel, BorderLayout.CENTER);

        JComboBox<String> comboBox = new JComboBox<>();
        for (Pago pago : listaPagos) {
            Pieza pieza = pago.getPieza();
            int tipo = pieza.getTipo();
            String tipoStr = "";

            switch (tipo) {
                case Pieza.PINTURA:
                    tipoStr = "Pintura";
                    break;
                case Pieza.IMPRESION:
                    tipoStr = "Impresión";
                    break;
                case Pieza.ESCULTURA:
                    tipoStr = "Escultura";
                    break;
                case Pieza.FOTOGRAFIA:
                    tipoStr = "Fotografía";
                    break;
                case Pieza.VIDEO:
                    tipoStr = "Video";
                    break;
            }

            String infoPieza = "Título: " + pieza.getTitulo() +
                    ". Propietario Actual: " + pago.getLoginVendedor() +
                    ". Propietario Destino: " + pago.getLoginComprador() +
                    ". Tipo: " + tipoStr;
            comboBox.addItem(infoPieza);
        }
        panel.add(comboBox);

        JButton confirmarButton = new JButton("Confirmar Entrega");
        confirmarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboBox.getSelectedIndex();
                if (selectedIndex != -1) {
                    Pago pagoSeleccionado = listaPagos.get(selectedIndex);
                    galeria.entregarPieza(pagoSeleccionado.getPieza(), pagoSeleccionado.getLoginComprador());
                    JOptionPane.showMessageDialog(internalFrame, "Pieza entregada exitosamente");
                    internalFrame.dispose();
                }
            }
        });
        panel.add(confirmarButton);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public void mostrarInternalFrameAmpliarTope() {
        boolean busquedaManual = JOptionPane.showConfirmDialog(desktopPane, "¿Desea extender el tope de un comprador manualmente?", "Ampliar Tope", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;

        if (busquedaManual) {
            String login = JOptionPane.showInputDialog(desktopPane, "Por favor ingrese el nombre de usuario del comprador al que desea extender su tope:");
            if (login != null && !login.isEmpty()) {
                boolean existe = galeria.existeCliente(login);
                if (existe) {
                    Cliente cliente = galeria.getCliente(login);
                    if (cliente.isVerificado()) {
                        long tope = -1;
                        while (tope < 0) {
                            String topeStr = JOptionPane.showInputDialog(desktopPane, "Por favor ingrese el nuevo tope de compras que desea asignar (este debe ser mayor al tope actual: " + cliente.getTopeCompras() + ")");
                            try {
                                tope = Long.parseLong(topeStr);
                                if (tope < cliente.getTopeCompras()) {
                                    JOptionPane.showMessageDialog(desktopPane, "El tope ingresado es menor al tope actual");
                                    tope = -1;
                                }
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(desktopPane, "Por favor ingrese un número válido para el tope");
                            }
                        }
                        galeria.asignarTope(cliente, tope);
                        JOptionPane.showMessageDialog(desktopPane, "Tope de compras ampliado exitosamente");
                    } else {
                        JOptionPane.showMessageDialog(desktopPane, "El cliente ingresado no está verificado aún");
                    }
                } else {
                    JOptionPane.showMessageDialog(desktopPane, "El nombre de usuario ingresado no corresponde a ningún cliente registrado");
                }
            } else {
                JOptionPane.showMessageDialog(desktopPane, "Por favor ingrese un nombre de usuario válido");
            }
        } else {
            if (listaSolicitudesTope.isEmpty()) {
                JOptionPane.showMessageDialog(desktopPane, "No hay solicitudes de extensión de tope de compras pendientes.");
            } else {
                int numSolicitudes = listaSolicitudesTope.size();
                String[] opciones = new String[numSolicitudes + 1];

                Iterator<SolicitudTope> it = listaSolicitudesTope.iterator();
                for (int i = 0; i < numSolicitudes && it.hasNext(); i++) {
                    SolicitudTope solicitud = it.next();
                    String loginCliente = solicitud.getLoginComprador();
                    opciones[i] = "Cliente #" + (i + 1) + ": " + loginCliente + ". Tope solicitado: " + solicitud.getTopeSolicitado();
                }
                opciones[numSolicitudes] = "Cancelar";

                int opcionSeleccionada = JOptionPane.showOptionDialog(desktopPane, "Clientes que solicitan extensión de tope", "Ampliar Tope", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, opciones[numSolicitudes]);

                if (opcionSeleccionada != numSolicitudes) {
                    SolicitudTope solicitudSeleccionada = listaSolicitudesTope.get(opcionSeleccionada);
                    Cliente compradorSeleccionado = galeria.getCliente(solicitudSeleccionada.getLoginComprador());
                    long tope = -1;
                    long topeMinimo = solicitudSeleccionada.getTopeSolicitado();
                    boolean salir = false;

                    while (tope < 0 && !salir) {
                        String topeStr = JOptionPane.showInputDialog(desktopPane, "Por favor ingrese el nuevo tope de compras que desea asignar (este debe ser mayor o igual al valor solicitado por el cliente: " + topeMinimo + "). Ingrese -1 para salir");
                        try {
                            tope = Long.parseLong(topeStr);
                            if (tope == -1) {
                                salir = true;
                            } else if (tope < topeMinimo) {
                                JOptionPane.showMessageDialog(desktopPane, "El tope ingresado es menor al valor de la oferta");
                                tope = -1;
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(desktopPane, "Por favor ingrese un número válido para el tope");
                        }
                    }
                    if (tope >= topeMinimo) {
                        galeria.asignarTope(compradorSeleccionado, tope);
                        listaSolicitudesTope.remove(opcionSeleccionada);
                        JOptionPane.showMessageDialog(desktopPane, "Tope de compras ampliado exitosamente");
                    }
                }
            }
        }
    }
    public void mostrarInternalFrameAgregarEmpleado() {
        JInternalFrame internalFrameLogin = new JInternalFrame("Agregar Nuevo Empleado - Login", true, true, true, true);
        internalFrameLogin.setSize(400, 150);
        internalFrameLogin.setLayout(new BorderLayout());

        JPanel panelLogin = new JPanel();
        internalFrameLogin.add(panelLogin, BorderLayout.CENTER);

        JLabel labelLogin = new JLabel("Ingrese el nombre de usuario del nuevo empleado:");
        panelLogin.add(labelLogin);

        JTextField textFieldLogin = new JTextField(20);
        panelLogin.add(textFieldLogin);

        JButton verificarButton = new JButton("Verificar");
        verificarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String login = textFieldLogin.getText();
                if (login.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrameLogin, "Por favor ingrese un nombre de usuario válido");
                } else {
                    boolean unico = galeria.comprobarLoginUnico(login);
                    if (unico) {
                        internalFrameLogin.dispose();
                        mostrarInternalFrameAgregarEmpleadoDatos(login);
                    } else {
                        JOptionPane.showMessageDialog(internalFrameLogin, "El nombre de usuario ya existe. Por favor, ingrese uno diferente.");
                    }
                }
            }
        });
        panelLogin.add(verificarButton);

        internalFrameLogin.setVisible(true);
        desktopPane.add(internalFrameLogin);
        try {
            internalFrameLogin.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void mostrarInternalFrameAgregarEmpleadoDatos(String login) {
        JInternalFrame internalFrameDatos = new JInternalFrame("Agregar Nuevo Empleado - Datos", true, true, true, true);
        internalFrameDatos.setSize(400, 300);
        internalFrameDatos.setLayout(new BorderLayout());

        JPanel panelDatos = new JPanel(new GridLayout(4, 2));

        JLabel labelPassword = new JLabel("Ingrese la contraseña del nuevo empleado:");
        panelDatos.add(labelPassword);
        JPasswordField passwordField = new JPasswordField(20);
        panelDatos.add(passwordField);

        JLabel labelTelefono = new JLabel("Ingrese el número telefónico del nuevo empleado:");
        panelDatos.add(labelTelefono);
        JTextField textFieldTelefono = new JTextField(20);
        panelDatos.add(textFieldTelefono);

        JLabel labelNombre = new JLabel("Ingrese el nombre del nuevo empleado:");
        panelDatos.add(labelNombre);
        JTextField textFieldNombre = new JTextField(20);
        panelDatos.add(textFieldNombre);

        JLabel labelRol = new JLabel("Seleccione el rol del nuevo empleado:");
        panelDatos.add(labelRol);
        String[] roles = { "Administrador", "Cajero", "Operador" };
        JComboBox<String> comboBoxRol = new JComboBox<>(roles);
        panelDatos.add(comboBoxRol);

        JButton agregarButton = new JButton("Agregar Empleado");
        agregarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordField.getPassword());
                String telefonoStr = textFieldTelefono.getText();
                String nombre = textFieldNombre.getText();
                int rolSeleccionado = comboBoxRol.getSelectedIndex() + 1; // Ajustar el índice del rol

                if (password.isEmpty() || telefonoStr.isEmpty() || nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrameDatos, "Por favor complete todos los campos.");
                    return;
                }

                try {
                    int telefono = Integer.parseInt(telefonoStr);
                    galeria.agregarNuevoEmpleado(login, password, telefono, nombre, rolSeleccionado);
                    JOptionPane.showMessageDialog(internalFrameDatos, "Empleado agregado exitosamente.");
                    internalFrameDatos.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(internalFrameDatos, "Por favor ingrese un número telefónico válido.");
                }
            }
        });

        internalFrameDatos.add(panelDatos, BorderLayout.CENTER);
        internalFrameDatos.add(agregarButton, BorderLayout.SOUTH);

        internalFrameDatos.setVisible(true);
        desktopPane.add(internalFrameDatos);
        try {
            internalFrameDatos.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public void mostrarInternalFrameVerHistorialPieza() {
        JInternalFrame internalFrameBusqueda = new JInternalFrame("Buscar Pieza", true, true, true, true);
        internalFrameBusqueda.setSize(400, 150);
        internalFrameBusqueda.setLayout(new BorderLayout());

        JPanel panelBusqueda = new JPanel();
        internalFrameBusqueda.add(panelBusqueda, BorderLayout.CENTER);

        JLabel labelBusqueda = new JLabel("Ingrese el título de la pieza que desea buscar:");
        panelBusqueda.add(labelBusqueda);

        JTextField textFieldBusqueda = new JTextField(20);
        panelBusqueda.add(textFieldBusqueda);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePieza = textFieldBusqueda.getText();
                if (nombrePieza.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrameBusqueda, "Por favor ingrese un título válido");
                } else {
                    if (galeria.existePieza(nombrePieza)) {
                        internalFrameBusqueda.dispose();
                        mostrarInternalFrameTransaccionesPieza(nombrePieza);
                    } else {
                        JOptionPane.showMessageDialog(internalFrameBusqueda, nombrePieza + " no ha sido registrada en la galería.");
                    }
                }
            }
        });
        panelBusqueda.add(buscarButton);

        internalFrameBusqueda.setVisible(true);
        desktopPane.add(internalFrameBusqueda);
        try {
            internalFrameBusqueda.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void mostrarInternalFrameTransaccionesPieza(String nombrePieza) {
        List<Pago> historialPieza = galeria.getCentroDeVentas().getHistorialPieza(nombrePieza);

        if (historialPieza == null || historialPieza.isEmpty()) {
            JOptionPane.showMessageDialog(desktopPane, nombrePieza + " no ha sido comprada/vendida.");
            return;
        }

        JInternalFrame internalFrameHistorial = new JInternalFrame("Historial de " + nombrePieza, true, true, true, true);
        internalFrameHistorial.setSize(600, 400);
        internalFrameHistorial.setLayout(new BorderLayout());

        JPanel panelHistorial = new JPanel();
        panelHistorial.setLayout(new BoxLayout(panelHistorial, BoxLayout.Y_AXIS));

        JLabel labelTitulo = new JLabel("Historial de transacciones de " + nombrePieza + ":");
        panelHistorial.add(labelTitulo);

        for (int i = 0; i < historialPieza.size(); i++) {
            Pago pago = historialPieza.get(i);
            JLabel labelTransaccion = new JLabel("Transacción #" + (i + 1) + ": " +
                    "Vendedor: " + pago.getLoginVendedor() + ", " +
                    "Comprador: " + pago.getLoginComprador() + ", " +
                    "Fecha: " + pago.getFecha());
            panelHistorial.add(labelTransaccion);
        }

        JScrollPane scrollPane = new JScrollPane(panelHistorial);
        internalFrameHistorial.add(scrollPane, BorderLayout.CENTER);

        internalFrameHistorial.setVisible(true);
        desktopPane.add(internalFrameHistorial);
        try {
            internalFrameHistorial.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public void mostrarInternalFrameVerHistorialArtista() {
        JInternalFrame internalFrameBusqueda = new JInternalFrame("Buscar Artista", true, true, true, true);
        internalFrameBusqueda.setSize(400, 150);
        internalFrameBusqueda.setLayout(new BorderLayout());

        JPanel panelBusqueda = new JPanel();
        internalFrameBusqueda.add(panelBusqueda, BorderLayout.CENTER);

        JLabel labelBusqueda = new JLabel("Ingrese el nombre del artista que desea buscar:");
        panelBusqueda.add(labelBusqueda);

        JTextField textFieldBusqueda = new JTextField(20);
        panelBusqueda.add(textFieldBusqueda);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombreArtista = textFieldBusqueda.getText();
                if (nombreArtista.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrameBusqueda, "Por favor ingrese un nombre válido");
                } else {
                    if (galeria.existeArtista(nombreArtista)) {
                        List<String> nombresPiezasArtista = galeria.getArtista(nombreArtista).getNombrePiezas();
                        if (nombresPiezasArtista.isEmpty()) {
                            JOptionPane.showMessageDialog(internalFrameBusqueda, nombreArtista + " no tiene piezas registradas");
                        } else {
                            internalFrameBusqueda.dispose();
                            mostrarInternalFrameTransaccionesVariasPiezas(nombreArtista, nombresPiezasArtista);
                        }
                    } else {
                        JOptionPane.showMessageDialog(internalFrameBusqueda, nombreArtista + " no ha sido registrado en la galería.");
                    }
                }
            }
        });
        panelBusqueda.add(buscarButton);

        internalFrameBusqueda.setVisible(true);
        desktopPane.add(internalFrameBusqueda);
        try {
            internalFrameBusqueda.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void mostrarInternalFrameTransaccionesVariasPiezas(String nombreArtista, List<String> nombresPiezas) {
        int numPiezas = nombresPiezas.size();
        String[] opciones = new String[numPiezas + 1];
        Iterator<String> it = nombresPiezas.iterator();
        for (int i = 0; i < numPiezas && it.hasNext(); i++) {
            opciones[i] = it.next();
        }
        opciones[numPiezas] = "Salir";

        int opcionSeleccionada = JOptionPane.showOptionDialog(desktopPane, 
            "Piezas de " + nombreArtista, 
            "Escoja la pieza de la que desee ver más información", 
            JOptionPane.DEFAULT_OPTION, 
            JOptionPane.PLAIN_MESSAGE, 
            null, 
            opciones, 
            opciones[numPiezas]);

        if (opcionSeleccionada == numPiezas) {
            // Do nothing, user chose to exit
        } else {
            mostrarInternalFrameTransaccionesPieza(nombresPiezas.get(opcionSeleccionada));
        }
    }
    public void mostrarInternalFrameVerHistorialCliente() {
        JInternalFrame internalFrameBusqueda = new JInternalFrame("Buscar Cliente", true, true, true, true);
        internalFrameBusqueda.setSize(400, 150);
        internalFrameBusqueda.setLayout(new BorderLayout());

        JPanel panelBusqueda = new JPanel();
        internalFrameBusqueda.add(panelBusqueda, BorderLayout.CENTER);

        JLabel labelBusqueda = new JLabel("Ingrese el nombre de usuario del cliente que desea consultar:");
        panelBusqueda.add(labelBusqueda);

        JTextField textFieldBusqueda = new JTextField(20);
        panelBusqueda.add(textFieldBusqueda);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String loginCliente = textFieldBusqueda.getText();
                if (loginCliente.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrameBusqueda, "Por favor ingrese un nombre de usuario válido");
                } else {
                    if (galeria.existeCliente(loginCliente)) {
                        internalFrameBusqueda.dispose();
                        mostrarInternalFrameHistorialCliente(loginCliente);
                    } else {
                        JOptionPane.showMessageDialog(internalFrameBusqueda, "No existe ningún cliente con el login seleccionado.");
                    }
                }
            }
        });
        panelBusqueda.add(buscarButton);

        internalFrameBusqueda.setVisible(true);
        desktopPane.add(internalFrameBusqueda);
        try {
            internalFrameBusqueda.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    protected void mostrarInternalFrameHistorialCliente(String loginCliente) {
        Cliente cliente = galeria.getCliente(loginCliente);

        if (!cliente.isVerificado()) {
            JOptionPane.showMessageDialog(desktopPane, "El cliente ingresado no es un comprador verificado.");
            return;
        }

        JInternalFrame internalFrameHistorial = new JInternalFrame("Historial de Cliente: " + loginCliente, true, true, true, true);
        internalFrameHistorial.setSize(600, 400);
        internalFrameHistorial.setLayout(new BorderLayout());

        JPanel panelHistorial = new JPanel();
        panelHistorial.setLayout(new BoxLayout(panelHistorial, BoxLayout.Y_AXIS));

        JLabel labelCompras = new JLabel("Historial de Compras:");
        panelHistorial.add(labelCompras);

        List<Pago> listaCompras = galeria.getCentroDeVentas().getHistorialCompras(loginCliente);
        long totalCompras = 0;
        if (!listaCompras.isEmpty()) {
            for (Pago pago : listaCompras) {
                JLabel labelTransaccion = new JLabel("Vendedor: " + pago.getLoginVendedor() + ", Fecha: " + pago.getFecha());
                panelHistorial.add(labelTransaccion);
                totalCompras += pago.getValor();
            }
            JLabel labelTotalCompras = new JLabel("Valor total de compras: " + totalCompras);
            panelHistorial.add(labelTotalCompras);
        } else {
            JLabel labelNoCompras = new JLabel("El cliente ingresado no ha comprado piezas.");
            panelHistorial.add(labelNoCompras);
        }

        JLabel labelVentas = new JLabel("Historial de Ventas:");
        panelHistorial.add(labelVentas);

        List<Pago> listaVentas = galeria.getCentroDeVentas().getHistorialVentas(loginCliente);
        long totalVentas = 0;
        if (!listaVentas.isEmpty()) {
            for (Pago pago : listaVentas) {
                JLabel labelTransaccion = new JLabel("Comprador: " + pago.getLoginComprador() + ", Fecha: " + pago.getFecha());
                panelHistorial.add(labelTransaccion);
                totalVentas += pago.getValor();
            }
            JLabel labelTotalVentas = new JLabel("Valor total de ventas: " + totalVentas);
            panelHistorial.add(labelTotalVentas);
        } else {
            JLabel labelNoVentas = new JLabel("El cliente ingresado no ha vendido piezas.");
            panelHistorial.add(labelNoVentas);
        }

        JLabel labelColeccion = new JLabel("Colección Actual:");
        panelHistorial.add(labelColeccion);

        List<String> listaNombresPiezasActuales = galeria.getPiezasActuales(loginCliente);
        long totalColeccion = 0;
        if (!listaNombresPiezasActuales.isEmpty()) {
            for (String nombrePieza : listaNombresPiezasActuales) {
                Pieza pieza = galeria.getPieza(nombrePieza);
                long precio = pieza.getPrecioUltimaVenta() >= 0 ? pieza.getPrecioUltimaVenta()
                        : (pieza.getPrecioVentaDirecta() >= 0 ? pieza.getPrecioVentaDirecta()
                        : pieza.getPrecioMinimoSubasta());
                totalColeccion += precio;
                JLabel labelPieza = new JLabel("Título: " + pieza.getTitulo() + ", Precio: " + precio);
                panelHistorial.add(labelPieza);
            }
            JLabel labelTotalColeccion = new JLabel("Valor total de colección: " + totalColeccion);
            panelHistorial.add(labelTotalColeccion);
        } else {
            JLabel labelNoColeccion = new JLabel("El cliente ingresado no posee piezas.");
            panelHistorial.add(labelNoColeccion);
        }

        JScrollPane scrollPane = new JScrollPane(panelHistorial);
        internalFrameHistorial.add(scrollPane, BorderLayout.CENTER);

        internalFrameHistorial.setVisible(true);
        desktopPane.add(internalFrameHistorial);
        try {
            internalFrameHistorial.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    public void mostrarInternalFrameExhibirPieza() {
        JInternalFrame internalFrame = new JInternalFrame("Exhibir Pieza", true, true, true, true);
        internalFrame.setSize(400, 200);
        internalFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        internalFrame.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Ingrese el nombre de la pieza que desea exhibir:");
        panel.add(label);

        JTextField textField = new JTextField(20);
        panel.add(textField);

        JButton exhibirButton = new JButton("Exhibir");
        exhibirButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePieza = textField.getText();
                Pieza pieza = galeria.getPieza(nombrePieza);

                if (pieza == null) {
                    JOptionPane.showMessageDialog(internalFrame, "La pieza " + nombrePieza + " no está registrada en la galería.");
                    return;
                }

                if (pieza.isEnBodega()) {
                    pieza.exhibir();
                    JOptionPane.showMessageDialog(internalFrame, "La pieza " + nombrePieza + " ha sido puesta en exhibición.");
                    internalFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(internalFrame, "La pieza seleccionada ya está siendo exhibida.");
                }
            }
        });
        panel.add(exhibirButton);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    public void mostrarInternalFrameGuardarPiezaEnBodega() {
        JInternalFrame internalFrame = new JInternalFrame("Guardar Pieza en Bodega", true, true, true, true);
        internalFrame.setSize(400, 200);
        internalFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        internalFrame.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Ingrese el nombre de la pieza que desea guardar en bodega:");
        panel.add(label);

        JTextField textField = new JTextField(20);
        panel.add(textField);

        JButton guardarButton = new JButton("Guardar en Bodega");
        guardarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePieza = textField.getText();
                Pieza pieza = galeria.getPieza(nombrePieza);

                if (pieza == null) {
                    JOptionPane.showMessageDialog(internalFrame, "La pieza " + nombrePieza + " no está registrada en la galería.");
                    return;
                }

                if (!pieza.isEnBodega()) {
                    pieza.guardarEnBodega();
                    JOptionPane.showMessageDialog(internalFrame, "La pieza " + nombrePieza + " ha sido guardada en bodega.");
                    internalFrame.dispose();
                } else {
                    JOptionPane.showMessageDialog(internalFrame, "La pieza seleccionada ya está en bodega.");
                }
            }
        });
        panel.add(guardarButton);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void mostrarInternalFrameRegistrarNuevaPieza(String loginPropietario) {
    	JInternalFrame internalFrame = new JInternalFrame("Registrar nueva pieza", true, true, true, true);
        internalFrame.setSize(200, 100);
        internalFrame.setLayout(new BorderLayout());

        JPanel panelTipo = new JPanel(new GridLayout(1, 2));
        
        JLabel labelRol = new JLabel("Seleccione el tipo de pieza:");
        panelTipo.add(labelRol);
        String[] tipos = { "Pintura", "Impresión", "Escultura", "Fotografía", "Video" };
        JComboBox<String> comboBoxTipo = new JComboBox<>(tipos);
        panelTipo.add(comboBoxTipo);
        
        
        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int tipo = comboBoxTipo.getSelectedIndex();
                
                if (tipo != -1) {
                	
                internalFrame.dispose();
                
                JInternalFrame infoFrame = new JInternalFrame("Información nueva pieza", true, true, true, true);
                infoFrame.setSize(600, 500);
                infoFrame.setLayout(new BorderLayout());
                
                JPanel panelInfoPieza = new JPanel(new GridLayout(6, 2));
                
                if (tipo == Pieza.PINTURA) {
                	
                	JLabel labelTitulo = new JLabel("Título:");
                	panelInfoPieza.add(labelTitulo);
                    JTextField tituloField = new JTextField(20);
                    panelInfoPieza.add(tituloField);
                    
                    JLabel labelArtista = new JLabel("Artista:");
                    panelInfoPieza.add(labelArtista);
                    JTextField artistaField = new JTextField(20);
                    panelInfoPieza.add(artistaField);
                	
                	JLabel labelEstilo = new JLabel("Estilo:");
                	panelInfoPieza.add(labelEstilo);
                    JTextField estiloField = new JTextField(20);
                    panelInfoPieza.add(estiloField);
                    
                    JLabel labelAlto = new JLabel("Alto:");
                    panelInfoPieza.add(labelAlto);
                    JTextField altoField = new JTextField(20);
                    panelInfoPieza.add(altoField);
                    
                    JLabel labelAncho = new JLabel("Ancho:");
                    panelInfoPieza.add(labelAncho);
                    JTextField anchoField = new JTextField(20);
                    panelInfoPieza.add(anchoField);
                    
                    JButton botonFinalizar = new JButton("Finalizar");
                    botonFinalizar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	
                        	if (galeria.existePieza(tituloField.getText())) {
                        		JOptionPane.showMessageDialog(internalFrame, "La pieza ingresada ya existe.");
                        	}
                        	else {
                        		
                        		Float alto = Float.parseFloat(altoField.getText());
                        		Float ancho = Float.parseFloat(anchoField.getText());
                        		
                        		Pintura pieza = new Pintura(tituloField.getText(), artistaField.getText(), loginPropietario, estiloField.getText(), alto, ancho);
                        		galeria.agregarPiezaNueva(pieza);
                        		JOptionPane.showMessageDialog(internalFrame, "Pieza registrada.");
                        	}
                        }
                    });
                    infoFrame.add(botonFinalizar, BorderLayout.SOUTH);
                    
                }
                
                if (tipo == Pieza.IMPRESION) {
                	
                	JLabel labelTitulo = new JLabel("Título:");
                	panelInfoPieza.add(labelTitulo);
                    JTextField tituloField = new JTextField(20);
                    panelInfoPieza.add(tituloField);
                    
                    JLabel labelArtista = new JLabel("Artista:");
                    panelInfoPieza.add(labelArtista);
                    JTextField artistaField = new JTextField(20);
                    panelInfoPieza.add(artistaField);
                	
                	JLabel labelOriginal = new JLabel("Original:");
                	panelInfoPieza.add(labelOriginal);
                    JCheckBox originalField = new JCheckBox();
                    panelInfoPieza.add(originalField);
                    
                    JLabel labelMetodo = new JLabel("Método de Creación:");
                	panelInfoPieza.add(labelMetodo);
                    JTextField metodoField = new JTextField(20);
                    panelInfoPieza.add(metodoField);
                	
                	JLabel labelAlto = new JLabel("Alto:");
                    panelInfoPieza.add(labelAlto);
                    JTextField altoField = new JTextField(20);
                    panelInfoPieza.add(altoField);
                    
                    JLabel labelAncho = new JLabel("Ancho:");
                    panelInfoPieza.add(labelAncho);
                    JTextField anchoField = new JTextField(20);
                    panelInfoPieza.add(anchoField);
                    
                    JButton botonFinalizar = new JButton("Finalizar");
                    botonFinalizar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	
                        	if (galeria.existePieza(tituloField.getText())) {
                        		JOptionPane.showMessageDialog(internalFrame, "La pieza ingresada ya existe.");
                        	}
                        	else {
                        		
                        		Float alto = Float.parseFloat(altoField.getText());
                        		Float ancho = Float.parseFloat(anchoField.getText());
                        		
                        		Impresion pieza = new Impresion(tituloField.getText(), artistaField.getText(), loginPropietario, originalField.isSelected(), metodoField.getText(), alto, ancho);
                        		galeria.agregarPiezaNueva(pieza);
                        		JOptionPane.showMessageDialog(internalFrame, "Pieza registrada.");
                        	}
                        }
                    });
                    infoFrame.add(botonFinalizar, BorderLayout.SOUTH);
                }
                
                if (tipo == Pieza.ESCULTURA) {
                	
                	JLabel labelTitulo = new JLabel("Título:");
                	panelInfoPieza.add(labelTitulo);
                    JTextField tituloField = new JTextField(20);
                    panelInfoPieza.add(tituloField);
                    
                    JLabel labelArtista = new JLabel("Artista:");
                    panelInfoPieza.add(labelArtista);
                    JTextField artistaField = new JTextField(20);
                    panelInfoPieza.add(artistaField);
                	
                	JLabel labelMaterial = new JLabel("Material de construcción:");
                	panelInfoPieza.add(labelMaterial);
                    JTextField materialField = new JTextField(20);
                    panelInfoPieza.add(materialField);
                	
                	JLabel labelAlto = new JLabel("Alto:");
                    panelInfoPieza.add(labelAlto);
                    JTextField altoField = new JTextField(20);
                    panelInfoPieza.add(altoField);
                    
                    JLabel labelAncho = new JLabel("Ancho:");
                    panelInfoPieza.add(labelAncho);
                    JTextField anchoField = new JTextField(20);
                    panelInfoPieza.add(anchoField);
                    
                    JLabel labelProfundidad = new JLabel("Profundidad:");
                    panelInfoPieza.add(labelProfundidad);
                    JTextField profundidadField = new JTextField(20);
                    panelInfoPieza.add(profundidadField);
                    
                    JButton botonFinalizar = new JButton("Finalizar");
                    botonFinalizar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	
                        	if (galeria.existePieza(tituloField.getText())) {
                        		JOptionPane.showMessageDialog(internalFrame, "La pieza ingresada ya existe.");
                        	}
                        	else {
                        		
                        		Float alto = Float.parseFloat(altoField.getText());
                        		Float ancho = Float.parseFloat(anchoField.getText());
                        		Float profundidad = Float.parseFloat(profundidadField.getText());
                        		
                        		Escultura pieza = new Escultura(tituloField.getText(), artistaField.getText(), loginPropietario, materialField.getText(), alto, ancho, profundidad);
                        		galeria.agregarPiezaNueva(pieza);
                        		JOptionPane.showMessageDialog(internalFrame, "Pieza registrada.");
                        	}
                        }
                    });
                    infoFrame.add(botonFinalizar, BorderLayout.SOUTH);
                }
                
                if (tipo == Pieza.FOTOGRAFIA) {
                	
                	JLabel labelTitulo = new JLabel("Título:");
                	panelInfoPieza.add(labelTitulo);
                    JTextField tituloField = new JTextField(20);
                    panelInfoPieza.add(tituloField);
                    
                    JLabel labelArtista = new JLabel("Artista:");
                    panelInfoPieza.add(labelArtista);
                    JTextField artistaField = new JTextField(20);
                    panelInfoPieza.add(artistaField);
                	
                	JLabel labelAlto = new JLabel("Alto:");
                    panelInfoPieza.add(labelAlto);
                    JTextField altoField = new JTextField(20);
                    panelInfoPieza.add(altoField);
                    
                    JLabel labelAncho = new JLabel("Ancho:");
                    panelInfoPieza.add(labelAncho);
                    JTextField anchoField = new JTextField(20);
                    panelInfoPieza.add(anchoField);
                    
                    JLabel labelColor = new JLabel("Color:");
                	panelInfoPieza.add(labelColor);
                    JCheckBox colorField = new JCheckBox();
                    panelInfoPieza.add(colorField);
                    
                    JButton botonFinalizar = new JButton("Finalizar");
                    botonFinalizar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	
                        	if (galeria.existePieza(tituloField.getText())) {
                        		JOptionPane.showMessageDialog(internalFrame, "La pieza ingresada ya existe.");
                        	}
                        	else {
                        		
                        		Float alto = Float.parseFloat(altoField.getText());
                        		Float ancho = Float.parseFloat(anchoField.getText());
                        		
                        		Fotografia pieza = new Fotografia(tituloField.getText(), artistaField.getText(), loginPropietario, alto, ancho, colorField.isSelected());
                        		galeria.agregarPiezaNueva(pieza);
                        		JOptionPane.showMessageDialog(internalFrame, "Pieza registrada.");
                        	}
                        }
                    });
                    infoFrame.add(botonFinalizar, BorderLayout.SOUTH);
                }
                
                if (tipo == Pieza.VIDEO) {
                	
                	JLabel labelTitulo = new JLabel("Título:");
                	panelInfoPieza.add(labelTitulo);
                    JTextField tituloField = new JTextField(20);
                    panelInfoPieza.add(tituloField);
                    
                    JLabel labelArtista = new JLabel("Artista:");
                    panelInfoPieza.add(labelArtista);
                    JTextField artistaField = new JTextField(20);
                    panelInfoPieza.add(artistaField);
                	
                	JLabel labelDuracion = new JLabel("Duración:");
                    panelInfoPieza.add(labelDuracion);
                    JTextField duracionField = new JTextField(20);
                    panelInfoPieza.add(duracionField);
                    
                    JLabel labelColor = new JLabel("Color:");
                	panelInfoPieza.add(labelColor);
                    JCheckBox colorField = new JCheckBox();
                    panelInfoPieza.add(colorField);
                    
                    JLabel labelMemoria = new JLabel("Memoria:");
                    panelInfoPieza.add(labelMemoria);
                    JTextField memoriaField = new JTextField(20);
                    panelInfoPieza.add(memoriaField);
                    
                    JButton botonFinalizar = new JButton("Finalizar");
                    botonFinalizar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	
                        	if (galeria.existePieza(tituloField.getText())) {
                        		JOptionPane.showMessageDialog(internalFrame, "La pieza ingresada ya existe.");
                        	}
                        	else {
                        		
                        		Float duracion = Float.parseFloat(duracionField.getText());
                        		Double memoria = Double.parseDouble(memoriaField.getText());
                        		
                        		Video pieza = new Video(tituloField.getText(), artistaField.getText(), loginPropietario, duracion, colorField.isSelected(), memoria);
                        		galeria.agregarPiezaNueva(pieza);
                        		JOptionPane.showMessageDialog(internalFrame, "Pieza registrada.");
                        	}
                        }
                    });
                    infoFrame.add(botonFinalizar, BorderLayout.SOUTH);
                }
                
                
                infoFrame.add(panelInfoPieza, BorderLayout.CENTER);
                
                infoFrame.setVisible(true);
                desktopPane.add(infoFrame);
                try {
                	internalFrame.setSelected(true);
                } catch (java.beans.PropertyVetoException ex) {
                    ex.printStackTrace();
                }
            }  
            }
        });

        internalFrame.add(panelTipo, BorderLayout.CENTER);
        internalFrame.add(botonContinuar, BorderLayout.SOUTH);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
        	internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    
    
    public void mostrarInternalFrameConsignarNuevaPieza(String loginPropietario, List<Pieza> piezasPorConsignar) {
    	JInternalFrame internalFrame = new JInternalFrame("Registrar nueva pieza", true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new BorderLayout());
        
        String[] tituloPiezas = new String[piezasPorConsignar.size()];
        
        Iterator<Pieza> it = piezasPorConsignar.iterator();
        
        for (int i = 0; it.hasNext(); i++) {
        	Pieza pieza = it.next();
        	tituloPiezas[i] = pieza.getTitulo();
        }
        

        JPanel panelConsignacion = new JPanel(new GridLayout(5, 2));
        
        JLabel labelPieza = new JLabel("Seleccione la pieza que desea consignar:");
        panelConsignacion.add(labelPieza);
        JComboBox<String> comboBoxPieza = new JComboBox<>(tituloPiezas);
        panelConsignacion.add(comboBoxPieza);
        
        JLabel labelFecha = new JLabel("Fecha hasta la cual desea consignar (formato: DD/MM/AAAA):");
        panelConsignacion.add(labelFecha);
        JTextField fechaField = new JTextField(20);
        panelConsignacion.add(fechaField);
        
        JLabel labelDirecta = new JLabel("Valor por venta directa (si no desea que se permita la venta directa, deje este espacio en blanco):");
        panelConsignacion.add(labelDirecta);
        JTextField directaField = new JTextField(20);
        panelConsignacion.add(directaField);
        
        JLabel labelInicio = new JLabel("Valor de inicio de subasta:");
        panelConsignacion.add(labelInicio);
        JTextField inicioField = new JTextField(20);
        panelConsignacion.add(inicioField);
        
        JLabel labelMinimo = new JLabel("Valor mínimo para cierre de subasta:");
        panelConsignacion.add(labelMinimo);
        JTextField minimoField = new JTextField(20);
        panelConsignacion.add(minimoField);
        
        
        
        JButton botonConsignar = new JButton("Consignar");
        botonConsignar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int indexPieza = comboBoxPieza.getSelectedIndex();
                
                if (indexPieza != -1) {
                	String tituloPieza = tituloPiezas[indexPieza];
                	Pieza piezaSeleccionada = galeria.getPieza(tituloPieza);
                	
                	Pieza piezaPorConsignar = null;
					
					if ( piezaSeleccionada.getTipo() == Pieza.PINTURA ) {
						piezaPorConsignar = new Pintura((Pintura) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.IMPRESION ) {
						piezaPorConsignar = new Impresion((Impresion) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.ESCULTURA ) {
						piezaPorConsignar = new Escultura((Escultura) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.FOTOGRAFIA ) {
						piezaPorConsignar = new Fotografia((Fotografia) piezaSeleccionada);
					}
					if ( piezaSeleccionada.getTipo() == Pieza.VIDEO ) {
						piezaPorConsignar = new Video((Video) piezaSeleccionada);
					}
					
					Long directa;
					if ( directaField.getText().equals("") ) {
						directa = (long) -1;
					}
					else {
						directa = Long.parseLong(directaField.getText());
					}
					
            		Long inicio = Long.parseLong(inicioField.getText());
            		Long minimo = Long.parseLong(minimoField.getText());
					
					piezaPorConsignar.setFechaTerminoConsignacion(fechaField.getText());
					piezaPorConsignar.setPrecioVentaDirecta(directa);
					piezaPorConsignar.setPrecioInicioSubasta(inicio);
					piezaPorConsignar.setPrecioMinimoSubasta(minimo);
					
					MenuAdministrador.listaPiezasPorIngresar.add(piezaPorConsignar);
					JOptionPane.showMessageDialog(internalFrame, "Solicitud de consignación registrada");
                }
            }
        });
                
         

        internalFrame.add(panelConsignacion, BorderLayout.CENTER);
        internalFrame.add(botonConsignar, BorderLayout.SOUTH);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
        	internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
    
    
    public void mostrarInternalFrameAmpliarTope(String loginPropietario) {
    	JInternalFrame internalFrame = new JInternalFrame("Registrar nueva pieza", true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new BorderLayout());
        
        JPanel panelConsignacion = new JPanel(new GridLayout(1, 2));
        
        JLabel labelTope = new JLabel("Nuevo tope deseado. Debe ser mayor al tope actual (" + Long.toString(galeria.getCliente(loginPropietario).getTopeCompras()) + "):");
        panelConsignacion.add(labelTope);
        JTextField topeField = new JTextField(20);
        panelConsignacion.add(topeField);
        
        JButton botonConfirmar = new JButton("Consignar");
        botonConfirmar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	if ( galeria.getCliente(loginPropietario).getTopeCompras() >= Long.parseLong(topeField.getText()) ) {
            		JOptionPane.showMessageDialog(internalFrame, "El valor ingresado es menor al tope actual.");
            	}
            	else {
	            	SolicitudTope sol = new SolicitudTope( loginPropietario, Long.parseLong(topeField.getText()) );
	            	MenuAdministrador1.listaSolicitudesTope.add(sol);
            	}
            }
        });
        
        internalFrame.add(panelConsignacion, BorderLayout.CENTER);
        internalFrame.add(botonConfirmar, BorderLayout.SOUTH);
        
    }
    
    public void mostrarInternalFrameOfertaSubasta() {
    	JInternalFrame internalFrame = new JInternalFrame("Registrar nueva pieza", true, true, true, true);
        internalFrame.setSize(400, 300);
        internalFrame.setLayout(new BorderLayout());
        
        Collection<Subasta> subastas = galeria.getCentroDeVentas().getMapaSubastas().values();
        
        Iterator<Subasta> it = subastas.iterator();
        String[] piezasSubastadas = new String[subastas.size()];
        
        for (int i = 0; it.hasNext(); i++) {
        	Subasta subasta = it.next();
        	piezasSubastadas[i] = subasta.getTituloPiezaSubastada();
        }

        JPanel panelSubastas = new JPanel(new GridLayout(1, 2));
        
        JLabel labelSubasta = new JLabel("Seleccione la subasta:");
        panelSubastas.add(labelSubasta);
        JComboBox<String> comboBoxSubastas = new JComboBox<>(piezasSubastadas);
        panelSubastas.add(comboBoxSubastas);
        
        
        JButton botonContinuar = new JButton("Continuar");
        botonContinuar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            	int index = comboBoxSubastas.getSelectedIndex();
                
                if (index != -1) {
                	
	                internalFrame.dispose();
	                
	                Subasta subasta = galeria.getCentroDeVentas().getMapaSubastas().get(piezasSubastadas[index]);
	                
	                JInternalFrame pujaFrame = new JInternalFrame("Puja subasta", true, true, true, true);
	                pujaFrame.setSize(400, 300);
	                pujaFrame.setLayout(new BorderLayout());
	                
	                JPanel panelPuja = new JPanel(new GridLayout(6, 2));
	                
	                JLabel labelLogin = new JLabel("Login del Cliente que realiza la oferta:");
	                panelPuja.add(labelLogin);
	                JTextField loginField = new JTextField(20);
	                panelPuja.add(loginField);
	                
	                JLabel labelValor = new JLabel("Valor de la puja (debe ser mayor a la puja máxima actual: " + subasta.getValorActual() + "):");
	                panelPuja.add(labelValor);
	                JTextField valorField = new JTextField(20);
	                panelPuja.add(valorField);
	                
	                JButton botonFinalizar = new JButton("Poner puja");
                    botonFinalizar.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                        	
                        	if ( subasta.getValorActual() >= Integer.parseInt(valorField.getText()) ) {
                        		JOptionPane.showMessageDialog(internalFrame, "La puja suministrada no excede la puja máxima actual.");
                        	}
                        	else {
                        		
                        		Integer valor = Integer.parseInt(valorField.getText());
                        		
                        		try {
									galeria.getCentroDeVentas().crearOfertaSubasta(subasta.getTituloPiezaSubastada(), loginField.getText(), valor);
								} catch (Exception e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
                        	}
                        }
                    });
	                
	                
                }
            }
        });

        internalFrame.add(panelSubastas, BorderLayout.CENTER);
        internalFrame.add(botonContinuar, BorderLayout.SOUTH);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
        	internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
