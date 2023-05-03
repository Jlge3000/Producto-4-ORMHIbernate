package beepod.controlador;


import beepod.dao.ArticuloDao;
import beepod.dao.DAOException;
import beepod.dao.factory.factoryArticuloDAO1;
import beepod.modelo.Articulo;

import java.util.Scanner;


public class Mai1 {
    public static void main(String[] args) throws DAOException {
//        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Articulo.class).buildSessionFactory();
//        Session mysesion = sessionFactory.openSession();

//        try{
//            Articulo articulo = new Articulo("tdfggg9", " es otra prueba", 12, 12, 1);
//            mysesion.beginTransaction();
//            mysesion.save(articulo);
//            mysesion.getTransaction().commit();
//            System.out.println("registro insertado");
//            System.out.println(articulo);
//            mysesion.close();
//        }catch (Exception ex){
//
//            System.out.println("Error al insertar"+ex);
//        }
//        finally {
//            sessionFactory.close();
//        }
        Scanner s = new Scanner(System.in);

        String id;
        System.out.println("introduce un código: ");
        id = s.nextLine();

        Articulo articulo = new Articulo();
        ArticuloDao articuloDao = new factoryArticuloDAO1();
        articulo = articuloDao.obtener(id);
        if (articulo != null){
            System.out.println(articulo);
        }else {
            System.out.println("El articulo no existe");
        }

//        Articulo articulo1 = new Articulo("tdfggg9", " es otra prueba", 12, 12, 1);
//        ArticuloDao articuloDao1 = new factoryArticuloDAO1();
//        if (articuloDao1.existe(articulo1)){
//            System.out.println("El articulo existe");
//        }else{
//            System.out.println("El articulo no existe");
//        }

    }


}
