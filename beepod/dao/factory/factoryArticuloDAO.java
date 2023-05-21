package beepod.dao.factory;

import beepod.dao.ArticuloDao;
import beepod.dao.DAOException;
import beepod.modelo.Articulo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javax.persistence.Query;
import javax.swing.*;

import java.util.List;

public class factoryArticuloDAO implements ArticuloDao {
    @Override
    public void insertar(Articulo a) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
        Session mysesion = sessionFactory.openSession();

        try{
            mysesion.beginTransaction();
            mysesion.save(a);
            mysesion.getTransaction().commit();
            mysesion.close();
            JOptionPane.showMessageDialog(null, "Registro insertado correctamente");
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, "ERROR en el Registro, comprueba que el código no esté repetido");
        }
        finally {
            sessionFactory.close();
        }
    }

    @Override
    public void modificar(Articulo a) throws DAOException {

    }

    @Override
    public void eliminar(Articulo a) throws DAOException {

    }

    /**
     * Listado de todos los articulos
     * @return
     * @throws DAOException
     */
    @Override
    public List<Articulo> obtenerTodos() throws DAOException {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            String hql = "FROM Articulo";
            Query query = session.createQuery(hql, Articulo.class);

            // Ejecuta la consulta y obtien la lista de resultados
            List<Articulo> resultList = query.getResultList();

            return resultList;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    /**
     * Obtengo el articulo por su código
     * @param id
     * @return
     * @throws DAOException
     */
    @Override
    public Articulo obtener(String id) throws DAOException {
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
        Session session = sessionFactory.openSession();

        try {
            // Utiliza el método get de la sesión para obtener el artículo por su ID
            Articulo articulo = session.get(Articulo.class, id);
            return articulo;
        } finally {
            // Cierra la sesión de Hibernate
            session.close();
        }
    }

    @Override
    public boolean existe(Articulo a) throws DAOException {
        return false;
    }
}
