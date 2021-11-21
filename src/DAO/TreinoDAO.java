package DAO;

import entity.Treino;

import java.util.List;

public interface TreinoDAO {

    void adicionar(Treino t);

    List<Treino> pesquisarPorNome(String nome);

    void atualizar(long id, Treino t);

    void remover(long id);
}
