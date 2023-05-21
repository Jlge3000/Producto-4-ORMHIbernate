package beepod.controlador;

import beepod.dao.*;
import beepod.dao.factory.*;
import beepod.modelo.*;

import javax.swing.*;
import java.io.IOException;

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
     * Metodo para listar los articulos
     * @return
     * @throws DAOException
     */
    public List<Articulo> listarArticulosGui() throws DAOException {
        ArticuloDao dao = new factoryArticuloDAO();
        List<Articulo> articulos = dao.obtenerTodos();
        return articulos;
    }

    /**
     * Metodo para listar todos los clientes
     * @return
     * @throws DAOException
     */
    public List<Cliente> listarTodosClientesGui() throws DAOException {
        ClienteDao dao = new factoryClientesDAO();
        List<Cliente> clientes = dao.obtenerTodos();

        return clientes;
    }

    /**
     * Metodo para listar todos los clientes Estandard
     * @return
     * @throws DAOException
     */
    public List<Cliente> listarClientesEstandardGui() throws DAOException {
        ClienteDao dao = new factoryClientesDAO();
        List<Cliente> clientes = dao.obtenerTodosNormal();
        return clientes;
    }

    /**
     * Metodo para listar todos los clientes Premium
     * @return
     * @throws DAOException
     */
    public List<Cliente> listarClientesPremiumdGui() throws DAOException {
        ClienteDao dao = new factoryClientesDAO();
        List<Cliente> clientes = dao.obtenerTodosPremium();
        return clientes;
    }

    /**
     * Metodo para listar los pedidos pendientes
     * @return
     * @throws DAOException
     */
    public List<Pedido> listarPedidosPendientesGui() throws DAOException {
        factoryPedidoDAO dao = new factoryPedidoDAO();
        List<Pedido> pedidos = dao.obtenerTodosPendienes();
        if (pedidos.isEmpty()){
            JOptionPane.showMessageDialog(null, "No existen pedidos Pendientes");
        }
        return pedidos;
    }

    /**
     * Metodo para listar los pedidos filtrandolos por el email del cliente
     * @param email
     * @return
     * @throws DAOException
     */
    public List<Pedido> listarPedidosPendientesClienteGui(String email) throws DAOException {
        ClienteDao cliente = new factoryClientesDAO();
        if (cliente.obtener(email)!= null){
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> pedidos = dao.obtenerPendientesCliente(email);
            if (pedidos.isEmpty()){
                JOptionPane.showMessageDialog(null, "No existen pedidos de este cliente");
            }
            return pedidos;
        }else{
            JOptionPane.showMessageDialog(null, "El cliente no existe");
            return null;
        }

    }


    /**
     * Metodo para listar todos los pedidos enviados
     * @return
     * @throws DAOException
     */
    public List<Pedido> listarEnviadosGui() throws DAOException {
        factoryPedidoDAO dao = new factoryPedidoDAO();
        List<Pedido> pedidos = dao.obtenerTodosEnviados();

        return pedidos;
    }

    /**
     * Metodo para listar los pedidos enviados por el email del cliente
     * @param email
     * @return
     * @throws DAOException
     */
    public List<Pedido> listarEnviadosClienteGui(String email) throws DAOException {
        ClienteDao cliente = new factoryClientesDAO();
        if (cliente.obtener(email)!= null){
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> pedidos = dao.obtenerEnviadosCliente(email);
            if (pedidos.isEmpty()){
                JOptionPane.showMessageDialog(null, "No existen pedidos de este cliente");
            }
            return pedidos;
        }else{
            JOptionPane.showMessageDialog(null, "El cliente no existe");
            return null;
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
            }
            if (opcion == 2 ) {
                Cliente clientePremium = new Cliente(nombre, domicilio, nif, email, "Premium", 30, 0.2f);
                ClienteDao dao = new factoryClientesDAO();
                dao.insertar(clientePremium);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Metodo para listar todos los pedidos pendientes, interactua con el metodo obtenerTodosPendientes de factoryPedido
     * @throws IOException
     */
    public void filtrarPedidosPendientes() throws IOException {


        try{
            factoryPedidoDAO dao = new factoryPedidoDAO();
            List<Pedido> list = dao.obtenerTodosPendienes();
            for (Pedido a : list){
                System.out.println(a.toString());
            }
        }catch(Exception ex){
        }
    }

    /**
     * metdoo para crear el pedido
     * @param email
     * @param codigo
     * @param cantidad
     * @throws IOException
     * @throws DAOException
     */
    public void crearPedidoGui(String email, String codigo, int cantidad) throws IOException, DAOException {
        ClienteDao cliente = new factoryClientesDAO();
        ArticuloDao articuloDao = new factoryArticuloDAO();
        try {
            try{
                if (cliente.obtener(email)!= null){
                    JOptionPane.showMessageDialog(null, "Cliente Existe");
                    try{
                        if (articuloDao.obtener(codigo)!= null) {
                            Pedido pedido = new Pedido(cliente.obtener(email), articuloDao.obtener(codigo), cantidad);
                            factoryPedidoDAO dao = new factoryPedidoDAO();
                            dao.insertar(pedido);
                            JOptionPane.showMessageDialog(null, "Pedido insertado");
                        }
                    }catch (Exception ex){
                        System.out.println("Error en el articulo "+ ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "El cliente no existe");

                }

            }catch (Exception ex){
                JOptionPane.showMessageDialog(null, "Error en el registro");

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el registro"+e);
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
            if (dao.comprobarPedido(numPedido) > 0){//comprobamos si ese id de pedido existe en la BBDD y no está enviado
                dao.eliminar(numPedido);
                filtrarPedidosPendientes();
            }else {

                filtrarPedidosPendientes();
            }
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null, "El pedido ya está enviado");
        }

    }
}
