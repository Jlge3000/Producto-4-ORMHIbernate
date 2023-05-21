package beepod.vista;

import beepod.controlador.Controlador;
import beepod.dao.DAOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

public class VistaPedidos extends JFrame{
    private JTextField txtEmail;
    private JButton btnInsertar;
    private JButton eliminarPedidoButton;
    private JButton verTodosPendientesButton;
    private JButton verTodosEnviadosButton;
    private JTextField txtArticul;
    private JTextField txtCantidad;
    private JList list1;
    private JPanel panel1;
    private JButton btnPendientesCliente;
    private JButton btnEnviadosCliente;
    private JButton btnSalir;
    private JTextField txtIdPedido;

    DefaultListModel model = new DefaultListModel<>();

    /**
     * Vista de los pedidos
     */
    public VistaPedidos(){
        super("Vista de Pedidos");

        setContentPane(panel1);
        setBounds(500,300,900,600);
        eliminarPedidoButton.setEnabled(false);
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                VistaInicial vista = new VistaInicial();
                vista.setVisible(true);
                vista.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        });
        /**
         * botón para insertar un pedido
         */
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//recogemos todos los datos de las cajas de txto
                Controlador control = new Controlador();
                String email = txtEmail.getText();
                String codigo = txtArticul.getText();
                int cantidad = Integer.parseInt(txtCantidad.getText());
                try {
                    control.crearPedidoGui(email, codigo, cantidad);
                    limpiar();
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el registro");
                    throw new RuntimeException(ex);
                } catch (DAOException ex) {
                    JOptionPane.showMessageDialog(null, "Error en el registro");
                    throw new RuntimeException(ex);
                }

            }
        });
        /**
         * botón para ver todos los pedidos pendientes
         */
        verTodosPendientesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();
                try {
                    limpiar();
                    model.addAll(control.listarPedidosPendientesGui());
                    eliminarPedidoButton.setEnabled(true);
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        /**
         * Botón para ver todos los pedidos enviados
         */
        verTodosEnviadosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();
                try {
                    limpiar();
                    eliminarPedidoButton.setEnabled(false);//desactivamos el botón de eliminar pedidos
                    model.addAll(control.listarEnviadosGui());
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * botón para ver los pendientes por el email del cliente
         */
        btnPendientesCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();

                try {
                    limpiar();
                    model.addAll(control.listarPedidosPendientesClienteGui(email));
                    eliminarPedidoButton.setEnabled(true);//habilitamos el botón para poder eliminar un pedido
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });


        /**
         * Boton para ver los pedidos enviados de un cliente por su email
         */
        btnEnviadosCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = txtEmail.getText();
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();

                try {
                    limpiar();
                    eliminarPedidoButton.setEnabled(false);//desactivamos el botón para eliminar un peido
                    model.addAll(control.listarEnviadosClienteGui(email));
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * eliminar un pedido
         */
        eliminarPedidoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controlador control = new Controlador();
                int id = Integer.parseInt(txtIdPedido.getText());
                try {
                    control.eliminarPedido(id);
                    JOptionPane.showMessageDialog(null, "Pedido Eliminado¡¡¡ ");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error en la eliminación "+ex);
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * accion de seleccion de un pedido para eliminarle
         */
        list1.addListSelectionListener(new ListSelectionListener() {//seleccionar el número del pedido a eliminar
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    try{
                        String selectedValue = list1.getSelectedValue().toString();
                        // obtener el objeto seleccionado de la JList
                        Object selectedObject = list1.getSelectedValue();
                        // convertir el objeto a una cadena
                        String selectedLine = selectedObject.toString();
                        // dividir la cadena en sus campos y obtener el tercer campo
                        String[] fields = selectedLine.split(" ");
                        String idField = fields[2].trim();//obtenemos el campo del id del pedido
                        txtIdPedido.setText(idField);
                    }catch (Exception ex){

                    }
                }
            }
        });
    }

    /**
     * metodo para limpiar las cajas de texto
     */
    public void limpiar(){
        txtArticul.setText("");
        txtCantidad.setText("");
        txtEmail.setText("");
        txtIdPedido.setText("");
    }

}
