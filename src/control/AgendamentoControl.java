package control;

import boundary.StrategyBoundary;
import entity.Agendamento;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class AgendamentoControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobrenome = new SimpleStringProperty("");
    public StringProperty aula = new SimpleStringProperty("");
    public ObjectProperty dataAgendamento = new SimpleObjectProperty(LocalDate.now());
    public ObjectProperty<LocalTime> horarioAgendamento = new SimpleObjectProperty();

    private ObservableList<Agendamento> listaView = FXCollections.observableArrayList();

    public Agendamento getEntity() {
        Agendamento a = new Agendamento();
        a.setId(id.get());
        a.setNome(nome.get());
        a.setSobrenome(sobrenome.get());
        a.setAula(aula.get());
        a.setDataAgendamento((LocalDate) dataAgendamento.get());
        a.setHorario((LocalTime) horarioAgendamento.get());

        return a;
    }

    public void setEntity(Agendamento a) {
        id.set((a.getId()));
        nome.set(a.getNome());
        sobrenome.set(a.getSobrenome());
        aula.set(a.getAula());
        dataAgendamento.set(a.getDataAgendamento());
        horarioAgendamento.set(a.getHorario());
        System.out.println(horarioAgendamento.toString());
    }

    public void novoAgendamento() {
        Agendamento a = getEntity();
        setEntity(a);
    }

    public void salvar() {
        Agendamento a = getEntity();

        if (a.getId() == 0) {
//            agendamentoDAO.adicionar(p);
            setEntity(new Agendamento());
        } else {
//            agendamentoDAO.atualizar(id.get(), a);
        }

//        atualizarListaView();
    }

    public void pesquisar() {
        listaView.clear();

//        List<Agendamento> encontrados = agendamentoDAO.pesquisarPorNome(nome.get());
//        listaView.addAll(encontrados);

    }

    public void remover(long id) {
//        agendamentoDAO.remover(id);
//        AtualizarListaView();
    }

    public void atualizarListaView() {
        listaView.clear();
//        listaView.addAll(AgendamentoDAO.pesquisarPorNome(""));
    }


}
