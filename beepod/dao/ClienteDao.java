package beepod.dao;

import beepod.modelo.Cliente;

import java.util.List;

public interface ClienteDao extends DAO<Cliente, String> {
    List<Cliente> obtenerTodosNormal();
    List<Cliente> obtenerTodosPremium();
}