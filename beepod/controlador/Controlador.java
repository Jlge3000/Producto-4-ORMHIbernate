package beepod.controlador;

import beepod.dao.*;
import beepod.dao.factory.*;
import beepod.modelo.*;
import javafx.application.Platform;
import javafx.scene.control.Alert;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controlador {

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
    public List<Articulo> listarArticulos() throws IOException {
        List<Articulo> articulos = new ArrayList<>();
        try {
            ArticuloDao dao = new factoryArticuloDAO();
            articulos = dao.obtenerTodos();
        } catch(Exception ex) {
            System.out.println("Error en SQL2" + ex);
        }
        return articulos;
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
    public List<Cliente> listarClientesNormal() throws IOException {
        List<Cliente> clienteNormals = new ArrayList<>();
        try{
            ClienteDao dao = new factoryClientesDAO();
            clienteNormals = dao.obtenerTodosNormal();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return clienteNormals;
    }

    /**
     * metodo listar todos los clientes Premium de factoryClientePremiumDAO
     * @throws IOException
     */
    public List<Cliente> listarClientesPremium() throws IOException {
        List<Cliente> clienteNormals = new ArrayList<>();
        try{
            ClienteDao dao = new factoryClientesDAO();
            clienteNormals = dao.obtenerTodosPremium();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return clienteNormals;
    }

    /**
     * Une las dos listas de clientes Estandard y Premium
     * @throws IOException
     */
    public List<Cliente> listarTodosClientes() throws IOException {
        List<Cliente> clienteNormals = new ArrayList<>();
        try{
            ClienteDao dao = new factoryClientesDAO();
            clienteNormals = dao.obtenerTodos();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return clienteNormals;
    }

    /**
     * Metodo para listar todos los pedidos pendientes, interactua con el metodo obtenerTodosPendientes de factoryPedidoDAO
     * @throws IOException
     */
    public List<Pedido> filtrarPedidosPendientes() throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            pedidos = dao.obtenerTodosPendienes();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return pedidos;
    }


    /**
     * Creacion del pedido, recibe los parametros de la vista, email, crea el pedido comprobando si existe el cliente
     * e interactua con factoryPedidoDAO para generar el pedido
     * @param email
     * @throws IOException
     * @throws DAOException
     */
    public void crearPedido(String email, String codigo, int cantidad) {
        ClienteDao clienteDao = new factoryClientesDAO();
        ArticuloDao articuloDao = new factoryArticuloDAO();

        try {
            if (clienteDao.obtener(email) != null){
                if (articuloDao.obtener(codigo)!= null) {
                    Pedido pedido = new Pedido(clienteDao.obtener(email), articuloDao.obtener(codigo), cantidad);
                    factoryPedidoDAO dao = new factoryPedidoDAO();
                    dao.insertar(pedido);
                }
                else {
                    Platform.runLater(() -> {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Artículo no encontrado.");
                        alert.showAndWait();
                    });
                }
            }
            else {
                Platform.runLater(() -> {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Información");
                    alert.setHeaderText(null);
                    alert.setContentText("El cliente con correo electrónico " + email + " no existe.\nPor favor, cree un nuevo cliente.");
                    alert.showAndWait();
                });
            }
        } catch (Exception e) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Error en el registro: " + e.getMessage());
                alert.showAndWait();
            });
        }
    }

    /**
     * metodo filtrar los pedidos pendientes por cliente, recibe el email y muestra sus pedidos pendientes.
     * @param email
     * @throws IOException
     */
    public List<Pedido> filtrarPedidosPendientesPorNombreCliente(String email) throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            pedidos = dao.obtenerPendientesCliente(email);
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return pedidos;
    }

    /**
     * metodo eliminación pedido, para ello le indicamos el número de pedido
     * @param numPedido
     * @throws IOException
     */
    public boolean eliminarPedido(int numPedido) throws IOException {
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            if(dao.comprobarPedido(numPedido) > 0) {
                dao.eliminar(numPedido);
                return true;
            } else {
                return false;
            }
        }catch(Exception ex){
            System.out.println("No se ha podido eliminar el pedido, está ya enviado");
            return false;
        }
    }

    /**
     *metodo para mostrar los pedidos enviados.
     * @throws IOException
     */
    public List<Pedido> filtrarPedidosEnviados() throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            pedidos = dao.obtenerTodosEnviados();
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return pedidos;
    }

    /**
     * metodo para filtrar los pedidos por el mail del cliente que le enviamos desde la vista
     * @param email
     * @throws IOException
     */
    public List<Pedido> filtrarPedidosEnviadosPorNombreCliente(String email) throws IOException {
        List<Pedido> pedidos = new ArrayList<>();
        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            pedidos = dao.obtenerEnviadosCliente(email);
        }catch(Exception ex){
            System.out.println("Error en SQL2"+ex);
        }
        return pedidos;
    }
}
