package control;

import DAO.EquipamentoDAO;
import conexaoDAO.EquipamentoDAOImplements;
import entity.Equipamento;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;

public class EquipamentoControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty fabricante = new SimpleStringProperty("");
    public IntegerProperty ano = new SimpleIntegerProperty(0);
    public DoubleProperty preco = new SimpleDoubleProperty(0);


    private ObservableList<Equipamento> listaview = FXCollections.observableArrayList();
    private EquipamentoDAO equipamentoDAO = new EquipamentoDAOImplements();


    public Equipamento getEntity() {
        Equipamento e = new Equipamento();
        e.setId(id.get());
        e.setNome(nome.get());
        e.setFabricante(fabricante.get());
        e.setAno(ano.get());
        e.setPreco(preco.get());

        return e;
    }

    public void setEntity(Equipamento e) {
        id.set(e.getId());
        nome.set(e.getNome());
        fabricante.set(e.getFabricante());
        ano.set(e.getAno());
        preco.set(e.getPreco());
    }

    public void novoEquipamento() {
        Equipamento e = new Equipamento();
        e.setId(0);
        setEntity(e);
    }


    public void salvar() {
        Equipamento e = getEntity();
        if (e.getId() == 0) {
            equipamentoDAO.adicionar(e);
            setEntity(new Equipamento());
        } else {
            equipamentoDAO.atualizar(id.get(),e);
        }
        atualizarListaView();
    }

    public void pesquisar() {
        listaview.clear();
        List<Equipamento> encontrados = equipamentoDAO.pesquisarPorNome(nome.get());
        listaview.addAll(encontrados);
    }

    public void remover(long id) {
        equipamentoDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView() {
        listaview.clear();
        listaview.addAll(equipamentoDAO.pesquisarPorNome(""));
    }

    public ObservableList<Equipamento> getListaView() {
        return listaview;
    }
}
