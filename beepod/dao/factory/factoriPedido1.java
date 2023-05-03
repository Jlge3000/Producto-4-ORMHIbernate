package beepod.dao.factory;

import beepod.dao.DAOException;
import beepod.modelo.Pedido;


public class factoriPedido1 {


    /**
     * Constructor, necesita una clase connection.
     * @param con
     */


    /**
     * metodo para la inserción de un pedido, recibe el pedido del controlador
     * @param a
     * @throws DAOException
     */
    public void insertar(Pedido a) throws DAOException {

    }

    /**
     * metodo para convertir el resultset en algo visible por pantalla.
     * @param rs
     * @throws SQLException
     */


    /**
     * Metodo para obtener los pedidos que tengan pendientes el cliente que viene definido por el mail.
     * @param email
     * @throws DAOException
     */

    public void obtenerPendientesCliente(String email) throws DAOException {


    }

    /**
     * metodo para obtener todos los pedidos pendientes sin tener en cuenta nada más.
     * @throws DAOException
     */
    public void obtenerTodosPendienes() throws DAOException {

        //return pedidos;
    }

    /**
     * metodo para eliminar un pedido siempre y cuando no esté enviado
     * @param id
     * @throws DAOException
     */
    public void eliminar(int id) throws DAOException {

    }

    /**
     * metodo para obtener todos los pedidos enviados
     * @throws DAOException
     */
    public void obtenerTodosEnviados() throws DAOException {

    }

    /**
     * metodo que recibe un mail de un cliente y nos lista los pedidos de este cliente
     * @param email
     * @throws DAOException
     */
    public void obtenerEnviadosCliente(String email) throws DAOException {


    }

    /**
     * metodo para actulizar el estado del pedido, para ello ejecuta el procedimiento almacenado de la BBDD cambiarEnvio.
     * @throws DAOException
     */
    public void actualizarPedidos() throws DAOException {


    }


}

