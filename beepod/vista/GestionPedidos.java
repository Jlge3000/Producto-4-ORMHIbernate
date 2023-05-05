package beepod.vista;

import beepod.controlador.Controlador;
import beepod.dao.DAOException;

import java.io.IOException;
import java.util.Scanner;

/**
 * Clase para la gestión de los pedidos
 */
public class GestionPedidos {
    Controlador control = new Controlador();
    Scanner teclado = new Scanner(System.in);

    public GestionPedidos() {

    }

    /**
     * Inicio de la aplicación
     * @param control
     * @throws IOException
     * @throws DAOException
     */
    public void inicio(Controlador control) throws IOException, DAOException {
        boolean salir = false;
        char opcio;


        do {
            System.out.println("1. Añadir Pedido");
            System.out.println("2. Eliminar Pedido");
            System.out.println("3. Mostrar Pedidos pendientes");
            System.out.println("4. Mostrar Pedidos enviados");
            System.out.println("0. Salir");
            opcio = pedirOpcion();
            switch (opcio) {
                case '1':
                    datosPedido(control);
                    break;
                case '2':
                    datosPedidoEliminar(control);
                    break;
                case '3':
                    filtrarPedidoPendiente(control);
                    break;
                case '4':
                    filtrarPedidoEnviado(control);
                    break;
                case '0':
                    salir = true;
                    break;
                default:
                    System.out.println("Opción incorrecta!!");
            }
        } while (!salir);
    }

    /**
     * pedir opcion a seleccionar
     * @return
     */
    private char pedirOpcion() {
        String resp;
        System.out.println("Elige la opcion (1,2,3,4 o 0): ");
        resp = teclado.nextLine();
        if (resp.isEmpty()) {
            resp = " ";
        }
        return resp.charAt(0);
    }

    /**
     * introduccón datos del cliente
     * @param control
     * @throws IOException
     * @throws DAOException
     */
    public void datosPedido(Controlador control) throws IOException, DAOException {
        // Pedir el correo electrónico del cliente

        System.out.println("Introduce el correo electrónico del cliente: ");
        String email = teclado.nextLine();
        control.crearPedido(email);
    }

    /**
     * metodo para indicar el código del pedido a eliminar
     * @param control
     */
    public void datosPedidoEliminar(Controlador control) {
        try {
            System.out.println("Introduce el código del pedido: ");
            int idPedido = teclado.nextInt();
            teclado.nextLine();
            control.eliminarPedido(idPedido);
        }  catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }
    }

    /**
     * metodo para filtrar pedidos pendientes
     * @param control
     */
    public void filtrarPedidoPendiente(Controlador control) {

        try {
            System.out.println("Elige la opción: ('1' Para mostrar todos o '2' Para mostrar por cliente): ");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            if (opcion == 1) {
                control.filtrarPedidosPendientes();
            } else if (opcion == 2) {
                System.out.println("Introduce el email del cliente: ");
                String email = teclado.nextLine();
                control.filtrarPedidosPendientesPorNombreCliente(email);
            } else {
                teclado.nextLine();
                System.out.println("No se escogió una opción válida. Saliendo al menu...");
            }
        }  catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }

    }

    /**
     * metodo para filtrado de pedidos
     * @param control
     */
    public void filtrarPedidoEnviado(Controlador control) {

        try {
            System.out.println("Elige la opción: ('1' Para mostrar todos o '2' Para mostrar por cliente): ");
            int opcion = teclado.nextInt();
            teclado.nextLine();
            if (opcion == 1) {
                control.filtrarPedidosEnviados();
            } else if (opcion == 2) {
                System.out.println("Introduce el email del cliente: ");
                String email = teclado.nextLine();
                control.filtrarPedidosEnviadosPorNombreCliente(email);
            } else {
                teclado.nextLine();
                System.out.println("No se escogió una opción válida. Saliendo al menu...");
            }
        } catch (Exception e) {
            System.out.println("Se ha producido un error: " + e.getMessage());
        }

    }
}