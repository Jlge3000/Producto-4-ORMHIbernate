package beepod.vista;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VistaInicial extends JFrame{
    private JButton btnPedidos;
    private JPanel panel1;
    private JButton btnArticulos;
    private JButton btnClientes;
    private JButton btnSalir;

    public VistaInicial(){
        super("Vista inicial");
        setContentPane(panel1);
        setBounds(500,300,900,600);
        btnArticulos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVista();
                VistaArticulos vista = new VistaArticulos();
                vista.setVisible(true);
                vista.setDefaultCloseOperation(EXIT_ON_CLOSE);

            }
        });


        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVista();
                VistaClientes vista = new VistaClientes();
                vista.setVisible(true);
                vista.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });

        btnPedidos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarVista();
                VistaPedidos vista = new VistaPedidos();
                vista.setVisible(true);
                vista.setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
        });
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public void cerrarVista(){
        setVisible(false);
    }
}
