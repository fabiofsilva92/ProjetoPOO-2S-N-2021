package boundary;

import control.ClienteControl;
import entity.Cliente;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import javafx.beans.binding.Bindings;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


public class ClienteBoundary implements StrategyBoundary{

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private TextField txtCPF = new TextField();
    private DatePicker txtNascimento = new DatePicker();
    private TextField txtDDD = new TextField();
    private TextField txtNumTelefone = new TextField();
    private TextField txtSexo = new TextField();

    private Button btnNovoCliente = new Button("Novo entity.Cliente");
    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private TableView<Cliente> table = new TableView<>();

    private ClienteControl control = new ClienteControl();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private void criarTabela(){

        TableColumn<Cliente, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Cliente, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Cliente, String> col3 = new TableColumn<>("Sobrenome");
        col3.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        TableColumn<Cliente, String> col4 = new TableColumn<>("CPF");
        col4.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        TableColumn<Cliente, String> col5 = new TableColumn<>("Nascimento");
        col5.setCellValueFactory((clienteProp) -> {
            LocalDate n = clienteProp.getValue().getDataNascimento();
            String strData = n.format(this.dtf);
            return new ReadOnlyStringWrapper(strData);
        });

        TableColumn<Cliente, String> col6 = new TableColumn<>("DDD");
        col6.setCellValueFactory(new PropertyValueFactory<>("ddd"));

        TableColumn<Cliente, String> col7 = new TableColumn<>("NumTelefone");
        col7.setCellValueFactory(new PropertyValueFactory<>("numTelefone"));

        TableColumn<Cliente, String> col8 = new TableColumn<>("Sexo");
        col8.setCellValueFactory(new PropertyValueFactory<>("sexo"));

        TableColumn<Cliente, String> col9 = new TableColumn<>("Ações");
        col9.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        col9.setCellFactory((tbCol) -> new TableCell<Cliente, String>(){
            final Button btn = new Button("Remover");

            public void updateItem(String item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction( (e) -> {
                        Cliente p = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "Você confirma a remoção do Pet Id " +
                                        p.getId(), ButtonType.OK, ButtonType.CANCEL);
                        Optional<ButtonType> clicado = alert.showAndWait();
                        if (clicado.isPresent() &&
                                clicado.get().equals(ButtonType.OK)) {
                            control.remover(p);
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9);

        table.setItems(control.getListaCliente());

        table.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    if(novo != null){
                        control.setEntity(novo);
                    }
                }
        );

    }

    @Override
    public Pane render() {

        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        txtId.setEditable(false);
        txtId.setDisable(true);
        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobrenome);
        Bindings.bindBidirectional(txtCPF.textProperty(), control.cpf);
        Bindings.bindBidirectional(txtNascimento.valueProperty(), control.dataNascimento);
        Bindings.bindBidirectional(txtDDD.textProperty(), control.ddd, new NumberStringConverter());
        Bindings.bindBidirectional(txtNumTelefone.textProperty(), control.numTelefone, new NumberStringConverter());
        Bindings.bindBidirectional(txtSexo.textProperty(), control.sexo);

        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtId, 1, 0);
        panCampos.add(btnNovoCliente, 5, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1, 1);

        panCampos.add(new Label("Sobrenome"), 2, 1);
        panCampos.add(txtSobrenome, 3, 1);

        panCampos.add(new Label("CPF"), 0, 3);
        panCampos.add(txtCPF, 1, 3);

        panCampos.add(new Label("Nascimento"), 0, 4);
        panCampos.add(txtNascimento, 1, 4);

        panCampos.add(new Label("DDD"), 0, 5);
        panCampos.add(txtDDD, 1, 5);

        panCampos.add(new Label("entity.Telefone"), 2, 5);
        panCampos.add(txtNumTelefone, 3, 5);

        panCampos.add(new Label("Sexo"), 0, 6);
        panCampos.add(txtSexo, 1, 6);

        panCampos.add(btnSalvar, 0, 7);
        panCampos.add(btnPesquisar, 1, 7);

        btnSalvar.setOnAction(e -> {
            control.salvar();
        });

        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
        });

        btnNovoCliente.setOnAction(e -> {
            control.novoCliente();
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();


        return panPrincipal;
    }
}
