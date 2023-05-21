package beepod.dao.factory;

import beepod.dao.ClienteDao;
import beepod.dao.DAOException;
import beepod.modelo.Cliente;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import javax.swing.*;
import java.util.List;

public class factoryClientesDAO implements ClienteDao {
    /**
     * Metodo para insertar un cliente en la BBDD
     * @param a
     * @throws DAOException
     */
    @Override
    public void insertar(Cliente a) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            mysesion.beginTransaction();
            mysesion.save(a);
            mysesion.getTransaction().commit();
            mysesion.close();
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "Error en el registro");
        }
        finally {
            sessionFactory.close();
        }

    }

    /**
     * No usado
     * @param a
     * @throws DAOException
     */
    @Override
    public void modificar(Cliente a) throws DAOException {

    }

    /**
     * No usado
     * @param a
     * @throws DAOException
     */
    @Override
    public void eliminar(Cliente a) throws DAOException {

    }

    /**
     * Metodo para listar todos los clientes
     * @return
     * @throws DAOException
     */
    @Override
    public List<Cliente> obtenerTodos() throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM Cliente";
            Query query = session.createQuery(hql, Cliente.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<Cliente> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    /**
     * Metodo para extraer los datos de un cliente a traves del mail
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Cliente obtener(String id) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            session.beginTransaction();
            Cliente cliente = session.get(Cliente.class, id);
            return cliente;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    /**
     * No usado
     * @param a
     * @return
     * @throws DAOException
     */
    @Override
    public boolean existe(Cliente a) throws DAOException {
        return false;
    }

    /**
     * Metodo para listar todos los clientes Standard
     * @return
     */
    public List<Cliente> obtenerTodosNormal() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM Cliente WHERE tipoCliente = 'Estandard'";
            Query query = session.createQuery(hql, Cliente.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<Cliente> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    /**
     * Metodo para listar todos los clientes premium
     * @return
     */
    public List<Cliente> obtenerTodosPremium() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Cliente.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM Cliente WHERE tipoCliente = 'Premium'";
            Query query = session.createQuery(hql, Cliente.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<Cliente> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }
}
