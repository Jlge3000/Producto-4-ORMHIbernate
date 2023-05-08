package beepod.dao.factory;

import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import beepod.modelo.ClienteHibernateORM;
import beepod.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.StoredProcedureQuery;
import java.util.List;


public class factoryPedido1 {


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
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).
                addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            mysesion.beginTransaction();
            mysesion.save(a);
            StoredProcedureQuery query = mysesion.createStoredProcedureQuery("totalPedido");//llamamos al procedimiento almacenado
            query.execute();
            mysesion.getTransaction().commit();
            System.out.println("Pedido numero: " + a.getNumPedido() + " Realizado con exito!!\n");
            mysesion.close();
        }catch (Exception ex){
            System.out.println("Error al insertar: \n"+ex);
        }
        finally {
            sessionFactory.close();
        }
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

    public List<Pedido> obtenerPendientesCliente(String email) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).
                addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            StoredProcedureQuery query = mysesion.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();

            String hqlQuery = "FROM Pedido WHERE enviado = '0' AND email = :valor1";

            List<Pedido> results = mysesion.createQuery(hqlQuery, Pedido.class).setParameter("valor1", email).getResultList();

            mysesion.close();

            return results;
        } finally {
            sessionFactory.close();
        }
    }

    /**
     * metodo para obtener todos los pedidos pendientes sin tener en cuenta nada más.
     * @throws DAOException
     */
    public List<Pedido> obtenerTodosPendienes() throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).
                addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            StoredProcedureQuery query = mysesion.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();

            String hqlQuery = "FROM Pedido WHERE enviado = '0'";

            List<Pedido> results = mysesion.createQuery(hqlQuery, Pedido.class).getResultList();

            mysesion.close();

            return results;
        } finally {
            sessionFactory.close();
        }
    }

    /**
     * metodo para eliminar un pedido siempre y cuando no esté enviado
     * @param id
     * @throws DAOException
     */
    public void eliminar(int id) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).
                addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();
    }

    /**
     * metodo para obtener todos los pedidos enviados
     * @throws DAOException
     */
    public List<Pedido> obtenerTodosEnviados() throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).
                addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            StoredProcedureQuery query = mysesion.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();

            String hqlQuery = "FROM Pedido WHERE enviado = '1'";

            List<Pedido> results = mysesion.createQuery(hqlQuery, Pedido.class).getResultList();

            mysesion.close();

            return results;
        } finally {
            sessionFactory.close();
        }
    }

    /**
     * metodo que recibe un mail de un cliente y nos lista los pedidos de este cliente
     * @param email
     * @throws DAOException
     */
    public List<Pedido> obtenerEnviadosCliente(String email) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).
                addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            StoredProcedureQuery query = mysesion.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();

            String hqlQuery = "FROM Pedido WHERE enviado = '1' AND email = :valor1";

            List<Pedido> results = mysesion.createQuery(hqlQuery, Pedido.class).setParameter("valor1", email).getResultList();

            mysesion.close();

            return results;
        } finally {
            sessionFactory.close();
        }
    }

    /**
     * metodo para actulizar el estado del pedido, para ello ejecuta el procedimiento almacenado de la BBDD cambiarEnvio.
     * @throws DAOException
     */
    public void actualizarPedidos() throws DAOException {
    }
}

