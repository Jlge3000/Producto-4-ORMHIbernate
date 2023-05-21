package beepod.vista;

import beepod.controlador.Controlador;
import beepod.dao.DAOException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase vista clientes que hereda de JFrame
 */
public class VistaClientes extends JFrame{


    private JTextField txtNombre;
    private JList listResultados;
    private JTextField txtEmail;
    private JTextField txtNif;
    private JTextField txtDomicilio;
    private JPanel panel1;
    private JComboBox comboTipoCliente;
    private JButton btnSalir;
    private JButton btnEstandard;
    private JButton btnInsertar;
    private JButton btnTodos;
    private JButton btnPremium;
    private JList list1;

    DefaultListModel model = new DefaultListModel<>();

    public VistaClientes(){
        super("Vista de Clientes");

        setContentPane(panel1);
        setBounds(500,300,900,600);
        /**
         * boton para salir de esta pantalla
         */
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
         * botón para insertar un cliente
         */
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//boton registrar
                Controlador control = new Controlador();
                int opcion;
                String nombre = txtNombre.getText();
                String domicilio = txtDomicilio.getText();
                String nif = txtNif.getText();
                String email = txtEmail.getText();
                String opcion1 = comboTipoCliente.getSelectedItem().toString();
                if ( opcion1 =="Estandard"){
                    opcion = 1;
                }else {
                    opcion = 2;
                }

                if (nombre ==""||domicilio ==""||nif ==""||email =="" ){
                    JOptionPane.showMessageDialog(null,"Faltan ingresar datos");
                }else{
                    try {

                        control.crearCliente(nombre, domicilio, nif, email, opcion);
                        limpiar();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Error en el registro");
                    }
                }




            }
        });
        /**
         * botón para listar todos los clientes
         */
        btnTodos.addActionListener(new ActionListener() {//ver todos los clientes
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();
                try {
                    model.addAll(control.listarTodosClientesGui());
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * botón para listar todos los clientes estandard
         */
        btnEstandard.addActionListener(new ActionListener() {//ver todos los Estandard
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();
                try {
                    model.addAll(control.listarClientesEstandardGui());
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * botón para listar todos los clientes premium
         */
        btnPremium.addActionListener(new ActionListener() {//ver todos los Estandard
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();
                try {
                    model.addAll(control.listarClientesPremiumdGui());
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

    }

    /**
     * Metodo para limpiar las cajas de texto
     */
    public void limpiar(){
        txtNombre.setText("");
        txtDomicilio.setText("");
        txtEmail.setText("");
        txtNif.setText("");
    }
}

