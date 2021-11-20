package boundary;

import control.FuncionarioControl;
import entity.Funcionario;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class FuncionarioBoundary implements StrategyBoundary {

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private TextField txtCpf = new TextField();
    private DatePicker dtNascimento = new DatePicker();
    private DatePicker dtInicio = new DatePicker();
    private ComboBox<String> cmbSexo = new ComboBox<>();
    private TextField txtSalario = new TextField();
    private TextField txtDdd = new TextField();
    private TextField txtTelefone = new TextField();
    private TextField txtEmail = new TextField();

    private Button btnNovoFuncionario = new Button("Novo Funcionario");
    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");
    
    private TableView<Funcionario> table = new TableView<>();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    private void criarTabela() {

        ObservableList<String> items = FXCollections.observableArrayList("Masculino", "Feminino", "Prefiro não informar");
        cmbSexo.setItems(items);

        TableColumn<Funcionario, Long> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));


        TableColumn<Funcionario, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<>("nome"));


        TableColumn<Funcionario, String> col3 = new TableColumn<>("Sobrenome");
        col3.setCellValueFactory( new PropertyValueFactory<>("sobrenome"));


        TableColumn<Funcionario, String> col4 = new TableColumn<>("CPF");
        col4.setCellValueFactory( new PropertyValueFactory<>("cpf"));


        TableColumn<Funcionario, String> col5 = new TableColumn<>("Dt Nascimento");
        col5.setCellValueFactory( (funcionarioProp) -> {
            LocalDate n = funcionarioProp.getValue().getDataNascimento();
            String strData = n.format(this.dtf);
            return new ReadOnlyStringWrapper(strData);
        });

        TableColumn<Funcionario, String> col6 = new TableColumn<>("Dt Inicio");
        col6.setCellValueFactory( (funcionarioProp2) -> {
            LocalDate n2 = funcionarioProp2.getValue().getDataInicio();
            String strData2 = n2.format(this.dtf);
            return new ReadOnlyStringWrapper(strData2);
        });

        TableColumn<Funcionario, String> col7 = new TableColumn<>("Sexo");
        col7.setCellValueFactory( new PropertyValueFactory<>("sexo"));


        TableColumn<Funcionario, Double> col8 = new TableColumn<>("Salario");
        col8.setCellValueFactory( new PropertyValueFactory<>("salario"));

        TableColumn<Funcionario, String> col9 = new TableColumn<>("DDD");
        col9.setCellValueFactory(new PropertyValueFactory<>("ddd"));

        TableColumn<Funcionario, String> col10 = new TableColumn<>("NumTelefone");
        col10.setCellValueFactory(new PropertyValueFactory<>("telefone"));

        TableColumn<Funcionario, String> col11 = new TableColumn<>("E-mail");
        col11.setCellValueFactory(new PropertyValueFactory<>("email"));



        TableColumn<Funcionario, String> col12 = new TableColumn<>("Ações");
        col12.setCellValueFactory( new PropertyValueFactory<>("DUMMY"));
        col12.setCellFactory( (tbCol) ->
             new TableCell<Funcionario, String>() {
                final Button btn = new Button("Remover");
                public void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction((e) -> {
                            Funcionario f = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.WARNING, "Você confirma a remoção do Funcionario com Id: " + f.getId(), ButtonType.OK, ButtonType.CANCEL);
                            Optional<ButtonType> clicado = alert.showAndWait();
                            if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
                                control.remover(f.getId());
                            }


                        });
                        setGraphic(btn);
                        setText(null);
                    }
                }
             });


        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7, col8, col9, col10, col11, col12);
        table.setItems(control.getListaView());

        table
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(  (observable, antigo, novo) -> {
            if(novo != null ) {
                control.setEntity(novo);
            }
        }
        );
    }



    private FuncionarioControl control = new FuncionarioControl();

    @Override
    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        txtId.setEditable(false);
        txtId.setDisable(true);

        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobrenome);
        Bindings.bindBidirectional(txtCpf.textProperty(), control.cpf);
        Bindings.bindBidirectional(dtNascimento.valueProperty(), control.dataNascimento);
        Bindings.bindBidirectional(dtInicio.valueProperty(), control.dataInicio);
        Bindings.bindBidirectional(cmbSexo.valueProperty(), control.sexo);
        Bindings.bindBidirectional(txtSalario.textProperty(), control.salario, new NumberStringConverter());
        Bindings.bindBidirectional(txtDdd.textProperty(), control.ddd, new NumberStringConverter());
        Bindings.bindBidirectional(txtTelefone.textProperty(), control.telefone, new NumberStringConverter());
        Bindings.bindBidirectional(txtEmail.textProperty(), control.email);

        panCampos.add(new Label("ID"), 0, 0);
        panCampos.add(txtId, 1, 0);
        panCampos.add(btnNovoFuncionario, 2, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1, 1);
        panCampos.add(new Label("Sobrenome"), 2, 1);
        panCampos.add(txtSobrenome, 3, 1);
        panCampos.add(new Label("CPF"), 0, 2);
        panCampos.add(txtCpf, 1, 2);
        panCampos.add(new Label("Nascimento"), 0, 3);
        panCampos.add(dtNascimento, 1, 3);
        panCampos.add(new Label("Data de Inicio"), 2, 3);
        panCampos.add(dtInicio, 3, 3);
        panCampos.add(new Label("Sexo"), 0, 4);
        panCampos.add(cmbSexo, 1, 4);
        panCampos.add(new Label("Salario"), 0, 5);
        panCampos.add(txtSalario, 1, 5);
        panCampos.add(new Label("DDD"), 0, 6);
        panCampos.add(txtDdd, 1, 6);
        panCampos.add(new Label("Telefone"), 2, 6);
        panCampos.add(txtTelefone, 3, 6);
        panCampos.add(new Label("E-mail"), 0, 7);
        panCampos.add(txtEmail, 1, 7);

        panCampos.add(btnSalvar, 0, 8);
        panCampos.add(btnPesquisar, 1, 8);

        btnSalvar.setOnAction((e -> {
            control.salvar();
        }));

        btnPesquisar.setOnAction((event -> {
            control.pesquisar();
        }));

        btnNovoFuncionario.setOnAction( e -> {
            control.novoFuncionario();
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();
        return (panPrincipal);

    }

}
