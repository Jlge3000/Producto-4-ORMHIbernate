package beepod.dao;

import beepod.modelo.ClienteHibernateORM;

import java.util.List;

public interface ClienteDao extends DAO<ClienteHibernateORM, String> {
    List<ClienteHibernateORM> obtenerTodosNormal();
    List<ClienteHibernateORM> obtenerTodosPremium();
}