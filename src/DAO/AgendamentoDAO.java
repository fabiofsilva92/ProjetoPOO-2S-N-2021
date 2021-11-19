package DAO;


import entity.Agendamento;

import java.util.List;

public interface AgendamentoDAO {

    void adicionar(Agendamento a);

    List<Agendamento> pesquisarPorNome(String nome);

    void atualizar(long id, Agendamento a);

    void remover(long id);
}
