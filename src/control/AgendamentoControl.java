package control;

import conexao.AgendamentoDAOImplements;
import entity.Agendamento;
import DAO.AgendamentoDAO;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class AgendamentoControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobrenome = new SimpleStringProperty("");
    public StringProperty aula = new SimpleStringProperty("");
    public ObjectProperty dataAgendamento = new SimpleObjectProperty(LocalDate.now());
    public StringProperty horarioAgendamento = new SimpleStringProperty(getTime());

    private AgendamentoDAO agendamentoDAO = new AgendamentoDAOImplements();

    private ObservableList<Agendamento> listaView = FXCollections.observableArrayList();

    public Agendamento getEntity() {
        Agendamento a = new Agendamento();
        a.setId(id.get());
        a.setNome(nome.get());
        a.setSobrenome(sobrenome.get());
        a.setAula(aula.get());
        a.setDataAgendamento((LocalDate) dataAgendamento.get());
        a.setHorario(horarioAgendamento.get());

        return a;
    }

    public void setEntity(Agendamento a) {
        id.set((a.getId()));
        nome.set(a.getNome());
        sobrenome.set(a.getSobrenome());
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
        System.out.println("Sobrenome da control: " + a.getSobrenome());
        System.out.println("Aula da control: " + a.getAula());

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

//        List<entity.Agendamento> encontrados = agendamentoDAO.pesquisarPorNome(nome.get());
//        listaView.addAll(encontrados);

    }

    public void remover(long id) {
//        agendamentoDAO.remover(id);
//        AtualizarListaView();
    }

    public void atualizarListaView() {
        listaView.clear();
//        listaView.addAll(br.est.fatec.entity.conexao.DAO.AgendamentoDAO.pesquisarPorNome(""));
    }

    static LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.now());


    public static String getTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:00");
        return formatter.format(ldt);
    }


}
