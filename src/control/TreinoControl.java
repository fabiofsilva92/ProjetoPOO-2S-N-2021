package control;

import entity.Treino;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TreinoControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobreNome = new SimpleStringProperty("");
    public StringProperty tipo = new SimpleStringProperty("");
    public StringProperty musculo = new SimpleStringProperty("");
    public IntegerProperty aparelho = new SimpleIntegerProperty(0);
    public IntegerProperty repeticoes = new SimpleIntegerProperty(0);
    public IntegerProperty series = new SimpleIntegerProperty(0);

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

    public void limpar() {
        Treino t = new Treino();
        t.setId(0L);
        setEntity(t);
    }

    public void salvar() {
        Treino p = getEntity();


//        if (p.getId() == 0) {
//            petDAO.adicionar(p);
//            setEntity(new PetTradicional());
//        } else {
//            petDAO.atualizar(id.get(), p);
//        }

        atualizarListaView();
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
