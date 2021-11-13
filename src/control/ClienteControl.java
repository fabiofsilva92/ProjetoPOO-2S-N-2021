package control;

import entity.Cliente;
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
    public IntegerProperty ddd = new SimpleIntegerProperty(0);
    public LongProperty numTelefone = new SimpleLongProperty(0);
    public StringProperty sexo = new SimpleStringProperty("");


    private ObservableList<Cliente> listaView = FXCollections.observableArrayList();
    private List<Cliente> listaCliente = new ArrayList<>();

    public Cliente getEntity(){
        Cliente c = new Cliente();
        c.setId(id.get());
        c.setNome(nome.get());
        c.setSobrenome(sobrenome.get());
        c.setCpf(cpf.get());
        c.setDataNascimento((LocalDate) dataNascimento.get());
        c.setTelefone(new Telefone(ddd.get(), numTelefone.get()));
        c.setSexo(sexo.get().charAt(0));
        return c;
    }

    public void setEntity(Cliente c) {
        id.set(c.getId());
        nome.set(c.getNome());
        sobrenome.set(c.getSobrenome());
        cpf.set(c.getCpf());
        dataNascimento.set(c.getDataNascimento());
        ddd.set(c.getTelefone().getDdd());
        numTelefone.set(c.getTelefone().getNumero());
        sexo.set(c.getSexo()+"");
    }

    public void salvar() {
        Cliente c = getEntity();
        listaCliente.add(c);
        atualizarListaView();
    }

    private void atualizarListaView() {
        listaView.clear();
        listaView.addAll(listaCliente);
    }

    public void remover(Cliente cliente) {
        listaCliente.remove(cliente);
        atualizarListaView();
    }

    public ObservableList<Cliente> getListaCliente() {
        return listaView;
    }


    public void pesquisar() {
        listaView.clear();
        for(Cliente cliente : listaCliente){
            if(cliente.getNome().contains(nome.get())){
                listaView.add(cliente);
            }
        }
    }

    public void novoCliente() {
        Cliente c = new Cliente();
        c.setId(listaCliente.size());
        setEntity(c);

    }
}
