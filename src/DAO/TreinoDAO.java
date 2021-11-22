package DAO;

import entity.Treino;

import java.util.List;

public interface TreinoDAO {

    void adicionar(Treino t);

    List<Treino> pesquisarPorNome(String nome);
    List<Treino> pesquisarPorId(Long id);

    void atualizar(long id, Treino t);

    void remover(long id);
}
