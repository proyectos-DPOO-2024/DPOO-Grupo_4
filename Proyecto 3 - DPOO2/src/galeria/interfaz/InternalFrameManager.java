package galeria.interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.List;

import galeria.modelo.centroventas.Pago;
import galeria.modelo.centroventas.SolicitudTope;
import galeria.modelo.centroventas.Transaccion;
import galeria.modelo.inventario.Galeria;
import galeria.modelo.inventario.Pieza;
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
        JInternalFrame internalFrame = new JInternalFrame("Ver Historial de Pieza", true, true, true, true);
        internalFrame.setSize(400, 150);
        internalFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        internalFrame.add(panel, BorderLayout.CENTER);

        JLabel label = new JLabel("Ingrese el título de la pieza que desea buscar:");
        panel.add(label);

        JTextField textField = new JTextField(20);
        panel.add(textField);

        JButton buscarButton = new JButton("Buscar");
        buscarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombrePieza = textField.getText();
                if (nombrePieza.isEmpty()) {
                    JOptionPane.showMessageDialog(internalFrame, "Por favor ingrese un título válido");
                } else {
                    if (galeria.existePieza(nombrePieza)) {
                        mostrarTransaccionesPieza(nombrePieza);
                        internalFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(internalFrame, nombrePieza + " no ha sido registrada en la galería.");
                    }
                }
            }
        });
        panel.add(buscarButton);

        internalFrame.setVisible(true);
        desktopPane.add(internalFrame);
        try {
            internalFrame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

    private void mostrarTransaccionesPieza(String nombrePieza) {
        JInternalFrame internalFrameHistorial = new JInternalFrame("Historial de Pieza: " + nombrePieza, true, true, true, true);
        internalFrameHistorial.setSize(400, 300);
        internalFrameHistorial.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);

        List<Transaccion> transacciones = galeria.obtenerHistorialPieza(nombrePieza);
        if (transacciones.isEmpty()) {
            textArea.setText("No hay transacciones registradas para esta pieza.");
        } else {
            StringBuilder historial = new StringBuilder();
            for (Transaccion transaccion : transacciones) {
                historial.append("Fecha: ").append(transaccion.getFecha()).append("\n");
                historial.append("Vendedor: ").append(transaccion.getLoginVendedor()).append("\n");
                historial.append("Comprador: ").append(transaccion.getLoginComprador()).append("\n");
                historial.append("Precio: ").append(transaccion.getPrecio()).append("\n");
                historial.append("----------------------------\n");
            }
            textArea.setText(historial.toString());
        }

        JScrollPane scrollPane = new JScrollPane(textArea);
        internalFrameHistorial.add(scrollPane, BorderLayout.CENTER);

        internalFrameHistorial.setVisible(true);
        desktopPane.add(internalFrameHistorial);
        try {
            internalFrameHistorial.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
            e.printStackTrace();
        }
    }

}
