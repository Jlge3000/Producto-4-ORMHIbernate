package beepod.dao.factory;

import beepod.dao.ClienteDao;
import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import beepod.modelo.ClienteHibernateORM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Query;
import java.util.List;

public class factoryClientesDAO implements ClienteDao {
    @Override
    public void insertar(ClienteHibernateORM a) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            mysesion.beginTransaction();
            mysesion.save(a);
            mysesion.getTransaction().commit();
            System.out.println("registro insertado");
            System.out.println(a);
            mysesion.close();
        }catch (Exception ex){

            System.out.println("Error al insertar: \n"+ex);
        }
        finally {
            sessionFactory.close();
        }

    }

    @Override
    public void modificar(ClienteHibernateORM a) throws DAOException {

    }

    @Override
    public void eliminar(ClienteHibernateORM a) throws DAOException {

    }

    @Override
    public List<ClienteHibernateORM> obtenerTodos() throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM ClienteHibernateORM";
            Query query = session.createQuery(hql, ClienteHibernateORM.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<ClienteHibernateORM> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    @Override
    public ClienteHibernateORM obtener(String id) throws DAOException {
        return null;
    }

    @Override
    public boolean existe(ClienteHibernateORM a) throws DAOException {
        return false;
    }

    public List<ClienteHibernateORM> obtenerTodosNormal() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM ClienteHibernateORM WHERE tipoCliente = 'Estandard'";
            Query query = session.createQuery(hql, ClienteHibernateORM.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<ClienteHibernateORM> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    public List<ClienteHibernateORM> obtenerTodosPremium() {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(ClienteHibernateORM.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM ClienteHibernateORM WHERE tipoCliente = 'Premium'";
            Query query = session.createQuery(hql, ClienteHibernateORM.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<ClienteHibernateORM> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }
}
