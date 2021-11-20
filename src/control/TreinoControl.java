package control;

import entity.Treino;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreinoControl {

    LongProperty id = new SimpleLongProperty(0);
    StringProperty nome = new SimpleStringProperty("");
    StringProperty sobreNome = new SimpleStringProperty("");
    StringProperty tipo = new SimpleStringProperty("");
    StringProperty musculo = new SimpleStringProperty("");
    IntegerProperty aparelho = new SimpleIntegerProperty(0);
    IntegerProperty repeticoes = new SimpleIntegerProperty(0);
    IntegerProperty series = new SimpleIntegerProperty(0);

//    private TreinoDAO treinoDAO = new treinoDAOImplement();

    private ObservableList<Treino> listaView = FXCollections.observableArrayList();


    public Treino getEntity() {
        Treino t = new Treino();
        t.setId(id.get());
        t.setNome(nome.get());
        t.setsobreNome(sobreNome.get());
        t.setTipo(tipo.get());
        t.setMusculo(musculo.get());
        t.setAparelho(aparelho.get());
        t.setRepeticoes(repeticoes.get());
        t.setSeries(series.get());

        return t;
    }

    public void setEntity(Treino t) {
        id.set(t.getId());
        nome.set(t.getNome());
        sobreNome.set(t.getsobreNome());
        tipo.set(t.getTipo());
        musculo.set(t.getMusculo());
        aparelho.set(t.getAparelho());
        repeticoes.set(t.getRepeticoes());
        series.set(t.getSeries());
    }

    public void novoTreino() {
        Treino t = new Treino();
        t.setId(0L);
        setEntity(t);
    }

    public void salvar() {
        Treino t = getEntity();
//
//        if (t.getId() == 0) {
//            treinoDAO.adicionar(t);
//            setEntity(new Treino());
//        } else {
//            treinoDAO.atualizar(id.get(), t);
//        }

//        atualizarListaView();

    }

    public void pesquisar() {
//        listaView.clear();
//
//        List<Treino> encontrados = treinoDAO.pesquisarPorNome(nome.get());
//        listaView.addAll(encontrados);
    }

    public ObservableList<Treino> getLista() {
        return listaView;
    }

    public void remover(long id) {
//        treinoDAO.remover(id);
//        atualizarListaView();
    }

    //
    public void atualizarListaView() {
//        listaView.clear();
//        listaView.addAll(treinoDAO.pesquisarPorNome(""));
    }

}
