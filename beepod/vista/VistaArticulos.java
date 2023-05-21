package beepod.vista;

import beepod.controlador.Controlador;
import beepod.dao.DAOException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaArticulos extends JFrame {

    private JPanel panel1;
    private JTextField txtCodigo;
    private JTextField txtDescripcion;
    private JTextField txtPrecioVenta;
    private JTextField txtCostesEnvio;
    private JTextField txtTiempoPreparacion;
    private JButton btnInsertar;
    private JButton btnMostrarArticulos;
    private JList listResultados;
    private JButton btnSalir;
    private JList list1;


    DefaultListModel model = new DefaultListModel<>();

    /**
     * Clase vista de articulos
      */
    public VistaArticulos(){
        super("Vista de Articulos");

        setContentPane(panel1);
        setBounds(500,300,900,600);


        /**
         * botón para insertar articulos
         */
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//boton registrar
                Controlador control = new Controlador();
                try {
                    String codigo = txtCodigo.getText();
                    String descripcion = txtDescripcion.getText();
                    float precioVenta = Float.parseFloat(txtPrecioVenta.getText());
                    float gastosEnvio = Float.parseFloat(txtCostesEnvio.getText());
                    long tiempoPreparacion = Long.parseLong(txtTiempoPreparacion.getText());
                    control.crearArticulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
                    limpiar();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error en el registro"+ex);
                }

            }
        });


        /**
         * boton para mostrar todos los articulos
         */
        btnMostrarArticulos.addActionListener(new ActionListener() {//boton listar
            @Override
            public void actionPerformed(ActionEvent e) {
                list1.setModel(model);
                model.removeAllElements();
                Controlador control = new Controlador();
                try {

                   model.addAll(control.listarArticulosGui());
                } catch (DAOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        /**
         * Boton para salir de esta pantalla
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
    }
    public void limpiar(){
        txtDescripcion.setText("");
        txtCodigo.setText("");
        txtPrecioVenta.setText("");
        txtCostesEnvio.setText("");
        txtTiempoPreparacion.setText("");

    }

}
