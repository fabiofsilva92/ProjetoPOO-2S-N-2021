package control;

import DAO.ClienteDAO;
import conexaoDAO.ClienteDAOImplements;
import entity.Cliente;
import entity.Funcionario;
import entity.Telefone;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClienteControl {


    public LongProperty id = new SimpleLongProperty(0);
    public StringProperty nome = new SimpleStringProperty("");
    public StringProperty sobrenome = new SimpleStringProperty("");
    public StringProperty cpf = new SimpleStringProperty("");
    public ObjectProperty dataNascimento = new SimpleObjectProperty(LocalDate.now());
    //    public IntegerProperty ddd = new SimpleIntegerProperty(0);
    public StringProperty telefone = new SimpleStringProperty("11-99999-9999");
    public StringProperty sexo = new SimpleStringProperty("");

    private ClienteDAO clienteDAO = new ClienteDAOImplements();
    private ObservableList<Cliente> listaView = FXCollections.observableArrayList();

    public Cliente getEntity() {
        Cliente c = new Cliente();
        c.setId(id.get());
        c.setNome(nome.get());
        c.setSobrenome(sobrenome.get());
        c.setCpf(cpf.get());
        c.setDataNascimento((LocalDate) dataNascimento.get());
        c.setTelefone(telefone.get());
        c.setSexo(sexo.get());
        return c;
    }

    public void setEntity(Cliente c) {
        id.set(c.getId());
        nome.set(c.getNome());
        sobrenome.set(c.getSobrenome());
        cpf.set(c.getCpf());
        dataNascimento.set(c.getDataNascimento());
        telefone.set(c.getTelefone());
        sexo.set(c.getSexo());
    }

    public void salvar() {
        Cliente c = getEntity();

        if (c.getId() == 0) {
            clienteDAO.adicionar(c);
            setEntity(new Cliente());
        } else {
            clienteDAO.atualizar(id.get(), c);
        }

        atualizarListaView();
    }

    private void atualizarListaView() {
        listaView.clear();
        listaView.addAll(clienteDAO.pesquisarPorNome(""));
    }

    public void remover(long id) {
        clienteDAO.remover(id);
        atualizarListaView();
    }

    public ObservableList<Cliente> getListaCliente() {
        return listaView;
    }


    public void pesquisar() {
        listaView.clear();
        List<Cliente> encontrados = clienteDAO.pesquisarPorNome(nome.get());
        listaView.addAll(encontrados);

    }

    public void novoCliente() {
        Cliente c = new Cliente();
        c.setId(0);
        setEntity(c);

    }
}
