package control;

import conexaoDAO.EventoDAOImplements;
import entity.Evento;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public class EventoControl {

    EventoDAOImplements eventoDAO = new EventoDAOImplements();

    LongProperty id = new SimpleLongProperty(0);
    StringProperty nome = new SimpleStringProperty("");
    ObjectProperty data = new SimpleObjectProperty(LocalDate.now());
    StringProperty horario = new SimpleStringProperty("");

    private ObservableList<Evento> listaView = FXCollections.observableArrayList();

    public Evento getEntity(){
        Evento e = new Evento();
        e.setId(id.get());
        e.setNomeEvento(nome.get());
        e.setData((LocalDate) data.get());
        e.setHorario(horario.get());

        return e;
    }

    public void setEntity(Evento e){

        id.set(e.getId());
        nome.set(e.getNomeEvento());
        data.set(e.getData());
        horario.set(e.getHorario());

    }

    public void salvar(){
        Evento e = getEntity();
        if(e.getId()==0){
            //do something
            eventoDAO.adicionar(e);
            setEntity(new Evento());
        }else{
            //do something
            eventoDAO.atualizar(id.get(), e);
        }
        atualizarListaView();
    }

    public void pesquisar(){
        listaView.clear();
        List<Evento> encontrados = eventoDAO.pesquisarPorNome(nome.get());
        listaView.addAll(encontrados);
    }

    public void remover(long id){
        eventoDAO.remover(id);
        atualizarListaView();
    }

    private void atualizarListaView() {
        listaView.clear();
        listaView.addAll(eventoDAO.pesquisarPorNome(""));
    }

    public void novoEvento(){
        Evento e = new Evento();
        e.setId(0);
        setEntity(e);
    }

    public ObservableList<Evento> getListaEvento(){
        return listaView;
    }

}
