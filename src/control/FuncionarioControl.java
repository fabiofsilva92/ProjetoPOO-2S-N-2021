package control;

import DAO.FuncionarioDAO;
import conexaoDAO.FuncionarioDAOImpl;
import entity.Funcionario;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.time.LocalDate;
import java.util.List;

public class FuncionarioControl {

    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobrenome = new SimpleStringProperty("");
    public StringProperty cpf = new SimpleStringProperty("");
    public ObjectProperty dataNascimento = new SimpleObjectProperty(LocalDate.now());
    public ObjectProperty dataInicio = new SimpleObjectProperty(LocalDate.now());
    public StringProperty sexo = new SimpleStringProperty("");
    public DoubleProperty salario = new SimpleDoubleProperty(0);
    public IntegerProperty ddd = new SimpleIntegerProperty(0);
    public LongProperty telefone = new SimpleLongProperty(0);
    public StringProperty email = new SimpleStringProperty("");

    private ObservableList<Funcionario> listaview = FXCollections.observableArrayList();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAOImpl();


        public Funcionario getEntity() {
        Funcionario f = new Funcionario();
        f.setId(id.get());
        f.setNome(nome.get());
        f.setSobrenome(sobrenome.get());
        f.setCpf(cpf.get());
        f.setDataNascimento((LocalDate)dataNascimento.get());
        f.setDataInicio((LocalDate)dataInicio.get());
        f.setSexo(sexo.get());
        f.setSalario(salario.get());
        f.setDdd(ddd.get());
        f.setTelefone(telefone.get());
        f.setEmail(email.get());
        return f;
    }

    public void setEntity(Funcionario f) {
        id.set(f.getId());
        nome.set(f.getNome());
        sobrenome.set(f.getSobrenome());
        cpf.set(f.getCpf());
        dataNascimento.set(f.getDataNascimento());
        dataInicio.set(f.getDataInicio());
        sexo.set(f.getSexo());
        salario.set(f.getSalario());
        ddd.set(f.getDdd());
        telefone.set(f.getTelefone());
        email.set(f.getEmail());
    }

    public void novoFuncionario() {
            Funcionario f = new Funcionario();
            f.setId(0);
            setEntity(f);
    }


    public void salvar() {
        Funcionario f = getEntity();
        if (f.getId() == 0) {
            funcionarioDAO.adicionar(f);
            setEntity(new Funcionario());
        } else {
            funcionarioDAO.atualizar(id.get(),f);
        }
        atualizarListaView();
    }

    public void pesquisar() {
            listaview.clear();
            List<Funcionario> encontrados = funcionarioDAO.pesquisarPorNome(nome.get());
            listaview.addAll(encontrados);
    }

    public void remover(long id) {
            funcionarioDAO.remover(id);
            atualizarListaView();
    }

    public void atualizarListaView() {
        listaview.clear();
        listaview.addAll(funcionarioDAO.pesquisarPorNome(""));
    }

    public ObservableList<Funcionario> getListaView() {
            return listaview;
    }
}
