package DAO;


import entity.Cliente;

import java.util.List;

public interface ClienteDAO {

    void adicionar(Cliente c);

    List<Cliente> pesquisarPorNome(String nome);

    void atualizar(long id, Cliente c);

    void remover(long id);
}
