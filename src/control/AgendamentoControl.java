package control;

import conexaoDAO.AgendamentoDAOImplements;
import entity.Agendamento;
import DAO.AgendamentoDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class AgendamentoControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobreNome = new SimpleStringProperty("");
    public StringProperty aula = new SimpleStringProperty("");
    public ObjectProperty dataAgendamento = new SimpleObjectProperty(LocalDate.now());
    public StringProperty horarioAgendamento = new SimpleStringProperty(getTime());

    private AgendamentoDAO agendamentoDAO = new AgendamentoDAOImplements();

    private ObservableList<Agendamento> listaView = FXCollections.observableArrayList();
    static LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.now());

    public Agendamento getEntity() {
        Agendamento a = new Agendamento();
        a.setId(id.get());
        a.setNome(nome.get());
        a.setSobreNome(sobreNome.get());
        a.setAula(aula.get());
        a.setDataAgendamento((LocalDate) dataAgendamento.get());
        a.setHorario(horarioAgendamento.get());

        return a;
    }

    public void setEntity(Agendamento a) {
        id.set((a.getId()));
        nome.set(a.getNome());
        sobreNome.set(a.getSobreNome());
        aula.set(a.getAula());
        dataAgendamento.set(a.getDataAgendamento());
        horarioAgendamento.set(a.getHorario());
    }

    public void novoAgendamento() {
        Agendamento a = getEntity();
        setEntity(a);
    }

    public void salvar() {
        Agendamento a = getEntity();

        System.out.println("horario da control: " + horarioAgendamento.get());
        System.out.println("Nome da control: " + nome.get());
        System.out.println("Sobrenome da control: " + a.getSobreNome());
        System.out.println("Aula da control: " + a.getAula());

        if (a.getId() == 0) {
            agendamentoDAO.adicionar(a);
            setEntity(new Agendamento());
        } else {
            agendamentoDAO.atualizar(id.get(), a);
        }

        atualizarListaView();
    }

    public void pesquisar() {
        listaView.clear();

        List<Agendamento> encontrados = agendamentoDAO.pesquisarPorNome(nome.get());
        listaView.addAll(encontrados);
    }

    public ObservableList<Agendamento> getLista() {
        return listaView;
    }


    public void remover(long id) {
        agendamentoDAO.remover(id);
        atualizarListaView();
    }

    public void atualizarListaView() {
        listaView.clear();
        listaView.addAll(agendamentoDAO.pesquisarPorNome(" "));
    }


    public static String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:00");
        return formatter.format(ldt);
    }


}
