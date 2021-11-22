package control;

import DAO.TreinoDAO;
import conexaoDAO.TreinoDAOImplements;
import entity.Treino;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class TreinoControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobrenome = new SimpleStringProperty("");
    public StringProperty tipo = new SimpleStringProperty("");
    public StringProperty musculo = new SimpleStringProperty("");
    public IntegerProperty aparelho = new SimpleIntegerProperty(0);
    public IntegerProperty repeticoes = new SimpleIntegerProperty(0);
    public IntegerProperty series = new SimpleIntegerProperty(0);

    private TreinoDAO treinoDAO = new TreinoDAOImplements();

    private ObservableList<Treino> listaView = FXCollections.observableArrayList();
    private ObservableList<Treino> listaView2 = FXCollections.observableArrayList();


    public Treino getEntity() {
        Treino t = new Treino();
        t.setId(id.get());
        t.setNome(nome.get());
        t.setSobrenome(sobrenome.get());
        t.setTipo(tipo.get());
        t.setMusculo(musculo.get());
        t.setAparelho(aparelho.get());
        t.setRepeticoes(repeticoes.get());
        t.setSeries(series.get());

        System.out.println("GetEntity" + t.getSobrenome());
        return t;
    }

    public void setEntity(Treino t) {
        id.set(t.getId());
        nome.set(t.getNome());
        sobrenome.set(t.getSobrenome());
        tipo.set(t.getTipo());
        musculo.set(t.getMusculo());
        aparelho.set(t.getAparelho());
        repeticoes.set(t.getRepeticoes());
        series.set(t.getSeries());
    }

    public void limpar() {
        Treino t = new Treino();
        t.setId(0L);
        setEntity(t);
    }

    public void salvar() {
        Treino t = getEntity();

        System.out.println("Salvar Treino " + t.getId());

        if (t.getId() != 0) {
            treinoDAO.adicionar(t);
//            setEntity(new Treino());
//        } else {
            treinoDAO.atualizar(id.get(), t);
        }

        atualizarListaView();
    }

    public void pesquisar() {
        listaView.clear();
        List<Treino> encontrados = treinoDAO.pesquisarPorNome(nome.get());
        listaView.addAll(encontrados);
    }

    public void pesquisarTreino() {
        if (id.get() != 0) {
            listaView2.clear();
            List<Treino> encontrados2 = treinoDAO.pesquisarPorId(id.get());
            listaView2.addAll(encontrados2);
        }

    }

    public ObservableList<Treino> getLista() {
        return listaView;
    }

    public ObservableList<Treino> getLista2() {
        return listaView2;
    }

    public void remover(long id) {
        treinoDAO.remover(id);
        atualizarListaView();
    }

    //
    public void atualizarListaView() {
        listaView.clear();
        listaView.addAll(treinoDAO.pesquisarPorNome(""));

        listaView2.clear();
        listaView2.addAll(treinoDAO.pesquisarPorId(id.get()));
    }

}
