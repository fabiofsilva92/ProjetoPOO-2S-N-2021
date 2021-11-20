package DAO;

import entity.Equipamento;

import java.util.List;

public interface EquipamentoDAO {

    void adicionar(Equipamento e);

    List<Equipamento> pesquisarPorNome(String nome);

    void atualizar(long id, Equipamento e);

    void remover(long id);
}
