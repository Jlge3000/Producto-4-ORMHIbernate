package beepod.controlador;

import beepod.dao.*;
import beepod.dao.factory.*;
import beepod.modelo.*;
import beepod.vista.GestionClientes;

import java.io.IOException;

import java.util.List;
import java.util.Scanner;

public class Controlador {

    GestionClientes gestionClientes = new GestionClientes();
    Scanner s = new Scanner(System.in);


    public Controlador() {
    }


    /**
     * metodo creación articulos recibe parametros de vista y ejecuta metodos de factoryDao
     * @param codigo
     * @param descripcion
     * @param precioVenta
     * @param gastosEnvio
     * @param tiempoPreparacion
     * @throws IOException
     */
    public void crearArticulo(String codigo, String descripcion, float precioVenta, float gastosEnvio, long tiempoPreparacion) throws IOException {///metodo creado posteriormente para añadir a la BBDD
        try {
            Articulo articulo = new Articulo(codigo, descripcion, precioVenta, gastosEnvio, tiempoPreparacion);
            ArticuloDao dao = new factoryArticuloDAO();
            dao.insertar(articulo);
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el artículo: " + e.getMessage());
        }
    }

    /**
     * metodo listar todos los articulos, llama al metodo obtener todos de factoryArticuloDao
     * @throws IOException
     */
    public void listarArticulos() throws IOException {
        try{
            ArticuloDao dao = new factoryArticuloDAO();
            List<Articulo> articulos = dao.obtenerTodos();
            for (Articulo a: articulos){
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo creacion clientes, recibe parametros de la vista e interactua con factoryClienteEstandaraDao y factoryClientePremiumDao
     * @param nombre
     * @param domicilio
     * @param nif
     * @param email
     * @param opcion
     * @throws IOException
     */
    public void crearCliente(String nombre, String domicilio, String nif, String email, int opcion) throws IOException {
        try {
            if (opcion == 1) {
                Cliente clienteNormal = new Cliente(nombre, domicilio, nif, email, "Estandard");
                ClienteDao dao = new factoryClientesDAO();
                dao.insertar(clienteNormal);
                System.out.println("Cliente Estandar añadido.\n");
            }
            if (opcion == 2 ) {
                Cliente clientePremium = new Cliente(nombre, domicilio, nif, email, "Premium", 30, 0.2f);
                ClienteDao dao = new factoryClientesDAO();
                dao.insertar(clientePremium);
                System.out.println("Cliente Premium añadido.\n");
            }
        } catch (Exception e) {
            System.out.println("Ha ocurrido un error al crear el cliente: " + e.getMessage());
        }
    }

    /**
     * metodo listar todos los clientes Estandard de factoryClienteEstandarDAO
     * @throws IOException
     */
    public void listarClientesNormal() throws IOException {
        try{
            ClienteDao dao = new factoryClientesDAO();
            List<Cliente> clienteNormals = dao.obtenerTodosNormal();
            for (Cliente a: clienteNormals){
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo listar todos los clientes Premium de factoryClientePremiumDAO
     * @throws IOException
     */
    public void listarClientesPremium() throws IOException {
        try{
            ClienteDao dao = new factoryClientesDAO();
            List<Cliente> clienteNormals = dao.obtenerTodosPremium();
            for (Cliente a: clienteNormals){
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * Une las dos listas de clientes Estandard y Premium
     * @throws IOException
     */
    public void listarTodosClientes() throws IOException {
        try{
            ClienteDao dao = new factoryClientesDAO();
            List<Cliente> clienteNormals = dao.obtenerTodos();
            for (Cliente a: clienteNormals){
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * Metodo para listar todos los pedidos pendientes, interactua con el metodo obtenerTodosPendientes de factoryPedidoDAO
     * @throws IOException
     */
    public void filtrarPedidosPendientes() throws IOException {
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> pedidos = dao.obtenerTodosPendienes();
            for (Pedido a : pedidos) {
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }


    /**
     * Creacion del pedido, recibe los parametros de la vista, email, crea el pedido comprobando si existe el cliente
     * e interactua con factoryPedidoDAO para generar el pedido
     * @param email
     * @throws IOException
     * @throws DAOException
     */
    public void crearPedido(String email) throws IOException, DAOException {
        ClienteDao clienteDao = new factoryClientesDAO();
        ArticuloDao articuloDao = new factoryArticuloDAO();

        try {
            try{
                if (clienteDao.obtener(email) != null){
                    System.out.println("Cliente existe");
                    System.out.println("Introduzca el código del producto");
                    String codigo = s.nextLine();
                    try{
                        if (articuloDao.obtener(codigo)!= null) {
                            System.out.println("Introduzca la cantidad: ");
                            int cantidad = s.nextInt();
                            s.nextLine();
                            Pedido pedido = new Pedido(clienteDao.obtener(email), articuloDao.obtener(codigo), cantidad);
                            factoryPedidoDAO dao = new factoryPedidoDAO();
                            System.out.println("Pedido añadido!!");
                            dao.insertar(pedido);
                        }
                    }catch (Exception ex){
                        System.out.println("Error en el articulo "+ ex);
                    }
                } else {
                    System.out.println("Cliente no existe");
                    System.out.println("El cliente con correo electrónico " + email + " no existe.");
                    System.out.println("Creando nuevo cliente...");
                    GestionClientes gestionClientes = new GestionClientes();
                    gestionClientes.datosCliente(this);
                }
            }catch (Exception ex){
                System.out.println("Error en el registro: " + ex.getMessage());
            }
        } catch (Exception e) {
            System.out.println("Error en el registro: " + e.getMessage());
        }
    }

    /**
     * metodo filtrar los pedidos pendientes por cliente, recibe el email y muestra sus pedidos pendientes.
     * @param email
     * @throws IOException
     */
    public void filtrarPedidosPendientesPorNombreCliente(String email) throws IOException {
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> pedidos = dao.obtenerPendientesCliente(email);
            for (Pedido a : pedidos) {
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo eliminación pedido, para ello le indicamos el número de pedido
     * @param numPedido
     * @throws IOException
     */
    public void eliminarPedido (int numPedido) throws IOException {
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            if(dao.comprobarPedido(numPedido) > 0) {
            dao.eliminar(numPedido);
            System.out.println("Pedido eliminado correctamente!!");
            System.out.println("Pedidos pendientes ahora: ");
            List<Pedido> pedidos = dao.obtenerTodosPendienes();
            for (Pedido a : pedidos) {
                System.out.println(a.toString());
            }
            } else {
                System.out.println("Pedido no eliminable.");
            }
        }catch(Exception ex){
            System.out.println("No se ha podido eliminar el pedido, está ya enviado ");
        }
    }

    /**
     *metodo para mostrar los pedidos enviados.
     * @throws IOException
     */
    public void filtrarPedidosEnviados () throws IOException {
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> pedidos = dao.obtenerTodosEnviados();
            for (Pedido a : pedidos) {
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }

    /**
     * metodo para filtrar los pedidos por el mail del cliente que le enviamos desde la vista
     * @param email
     * @throws IOException
     */
    public void filtrarPedidosEnviadosPorNombreCliente(String email) throws IOException {
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> pedidos = dao.obtenerEnviadosCliente(email);
            for (Pedido a : pedidos) {
                System.out.println(a.toString());
            }
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
    }
}
