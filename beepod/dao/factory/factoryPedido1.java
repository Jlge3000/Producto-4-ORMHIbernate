package beepod.dao.factory;

import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import beepod.modelo.ClienteHibernateORM;
import beepod.modelo.Pedido;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import java.util.List;


public class factoryPedido1 {



    /**
     * metodo para la inserción de un pedido, recibe el pedido del controlador
     * @param a
     * @throws DAOException
     */
    public void insertar(Pedido a) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();



        try{
            mysesion.beginTransaction();
            mysesion.save(a);
            StoredProcedureQuery query = mysesion.createStoredProcedureQuery("totalPedido");//llamamos al procedimiento almacenado
            query.execute();
            mysesion.getTransaction().commit();
            System.out.println("Pedido Realizado con Exito!!!");
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
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            StoredProcedureQuery query = session.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();
            String hqlQuery = "FROM Pedido WHERE enviado = '0' AND email = :valor1";
            List<Pedido> results = session.createQuery(hqlQuery, Pedido.class)
                    .setParameter("valor1", email)
                    .getResultList();

            session.close();

            return results;

        } finally {
            // Cierra la sesión de Hibernate
            sessionFactory.close();
        }

    }

    /**
     * metodo para obtener todos los pedidos pendientes sin tener en cuenta nada más.
     * @throws DAOException
     */
    public List<Pedido> obtenerTodosPendienes() throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {

            StoredProcedureQuery query = session.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();
            String hql = "FROM Pedido WHERE enviado = '0'";
            Query query1 = session.createQuery(hql, Pedido.class);
            // Ejecuta la consulta y obtien la lista de resultados
            List<Pedido> resultList = query1.getResultList();
            session.close();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            sessionFactory.close();
        }
    }

    /**
     * metodo para eliminar un pedido siempre y cuando no esté enviado
     * @param id
     * @throws DAOException
     */
    public void eliminar(int id) throws DAOException {///falta.....
//        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        try {
//
//            StoredProcedureQuery query = session.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
//            query.execute();
//            String hql = "FROM Pedido WHERE enviado = '1'";
//            Query query1 = session.createQuery(hql, Pedido.class);
//            // Ejecuta la consulta y obtien la lista de resultados
//            List<Pedido> resultList = query1.getResultList();
//            session.close();
//
//        } finally {
//            // Cierra la sesión de Hibernate
//            sessionFactory.close();
//        }


    }

    /**
     * metodo para obtener todos los pedidos enviados
     * @throws DAOException
     */
    public List<Pedido> obtenerTodosEnviados() throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {

            StoredProcedureQuery query = session.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();
            String hql = "FROM Pedido WHERE enviado = '1'";
            Query query1 = session.createQuery(hql, Pedido.class);
            // Ejecuta la consulta y obtien la lista de resultados
            List<Pedido> resultList = query1.getResultList();
            session.close();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            sessionFactory.close();
        }

    }

    /**
     * metodo que recibe un mail de un cliente y nos lista los pedidos de este cliente
     * @param email
     * @throws DAOException
     */
    public List<Pedido> obtenerEnviadosCliente(String email) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Pedido.class).addAnnotatedClass(Articulo.class).addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            StoredProcedureQuery query = session.createStoredProcedureQuery("cambiarEnvio");//llamamos al procedimiento almacenado
            query.execute();
            String hqlQuery = "FROM Pedido WHERE enviado = '1' AND email = :valor1";
            List<Pedido> results = session.createQuery(hqlQuery, Pedido.class)
                    .setParameter("valor1", email)
                    .getResultList();

            session.close();

            return results;

        } finally {
            // Cierra la sesión de Hibernate
            sessionFactory.close();
        }
    }
}

