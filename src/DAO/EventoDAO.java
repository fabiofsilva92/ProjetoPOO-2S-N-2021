package DAO;

import entity.Evento;

import java.util.List;

public interface EventoDAO {
    void adicionar(Evento e);
    List<Evento> pesquisarPorNome(String nome);
    void atualizar(long id, Evento e);
    void remover(long id);
}
