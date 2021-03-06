package boundary;

import control.TreinoControl;
import entity.Treino;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.util.Optional;

public class TreinoBoundary implements StrategyBoundary {

    private TextField txtID = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private ComboBox<String> cmbTipo = new ComboBox<>();
    private TextField txtMusculo = new TextField();
    private TextField txtAparelho = new TextField();
    private TextField txtRepeticoes = new TextField();
    private TextField txtSeries = new TextField();

    private Button btnAdicionar = new Button("Salvar Treino");
    private Button btnPesquisarTreino = new Button("Pesquisar Treino");
    private Button btnPesquisar = new Button("Pesquisar Aluno");
    private Button btnLimpar = new Button("Limpar");

    private TreinoControl control = new TreinoControl(); //Composição

    private TableView<Treino> table1 = new TableView<>();
    private TableView<Treino> table2 = new TableView<>();

    private void criarTabela() {

        ObservableList<String> tipo =
                FXCollections.observableArrayList("A", "B", "C");
        cmbTipo.setItems(tipo);


        TableColumn<Treino, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Treino, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Treino, String> col3 = new TableColumn<>("Sobrenome");
        col3.setCellValueFactory(new PropertyValueFactory<>("sobrenome"));

        TableColumn<Treino, Double> col4 = new TableColumn<>("Tipo");
        col4.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Treino, Double> col5 = new TableColumn<>("Músculo");
        col5.setCellValueFactory(new PropertyValueFactory<>("musculo"));

        TableColumn<Treino, Double> col6 = new TableColumn<>("Aparelho");
        col6.setCellValueFactory(new PropertyValueFactory<>("aparelho"));

        TableColumn<Treino, Double> col7 = new TableColumn<>("Repetições");
        col7.setCellValueFactory(new PropertyValueFactory<>("repeticoes"));

        TableColumn<Treino, Double> col8 = new TableColumn<>("Series");
        col8.setCellValueFactory(new PropertyValueFactory<>("series"));


        TableColumn<Treino, String> col9 = new TableColumn<>("Ações");
        col9.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        col9.setCellFactory((tbcol) -> new TableCell<Treino, String>() {
            final Button btn = new Button("Remover");

            public void updateItem(String item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction((e) -> {
                        Treino p = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Você confirma a remoção " +
                                p.getId() + " ?", ButtonType.OK, ButtonType.CANCEL);
                        Optional<ButtonType> clicado = alert.showAndWait();
                        if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
                            control.remover(p.getId())
                            ;
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });

        table1.getColumns().addAll(col1, col2, col3);
        table2.getColumns().addAll(col1, col4, col5, col6, col7, col8, col9);

        table1.setItems(control.getLista());

        table1.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    if (novo != null) {
                        control.setEntity(novo);
                    }
                }
        );

        table2.setItems(control.getLista2());

        table2.getSelectionModel().selectedItemProperty().addListener(
                (obs, antigo, novo) -> {
                    if (novo != null) {
                        control.setEntity(novo);
                    }
                }
        );
    }


    @Override
    public Pane render() {

        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        panCampos.setHgap(5); //horizontal gap in pixels => that's what you are asking for
        panCampos.setVgap(5); //vertical gap in pixels
        panCampos.setPadding(new Insets(5, 5, 5, 5));
//        txtID.setEditable(false);
//        txtID.setDisable(true);

        Bindings.bindBidirectional(txtID.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobrenome);
        Bindings.bindBidirectional(cmbTipo.valueProperty(), control.tipo);
        Bindings.bindBidirectional(txtMusculo.textProperty(), control.musculo);
        Bindings.bindBidirectional(txtAparelho.textProperty(), control.aparelho, new NumberStringConverter());
        Bindings.bindBidirectional(txtRepeticoes.textProperty(), control.repeticoes, new NumberStringConverter());
        Bindings.bindBidirectional(txtSeries.textProperty(), control.series, new NumberStringConverter());


        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtID, 1, 0);
        panCampos.add(btnLimpar, 0, 3);
        panCampos.add(btnPesquisar, 1, 3);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1, 1);

        panCampos.add(new Label("Sobrenome"), 0, 2);
        panCampos.add(txtSobrenome, 1, 2);

        panCampos.add(new Label("Tipo do Treino"), 4, 0);
        panCampos.add(cmbTipo, 5, 0);

        panCampos.add(new Label("Categoria Másculo"), 4, 1);
        panCampos.add(txtMusculo, 5, 1);

        panCampos.add(new Label("Aparelho"), 4, 2);
        panCampos.add(txtAparelho, 5, 2);

        panCampos.add(new Label("repetições"), 4, 3);
        panCampos.add(txtRepeticoes, 5, 3);

        panCampos.add(new Label("Series"), 4, 4);
        panCampos.add(txtSeries, 5, 4);

        panCampos.add(btnAdicionar, 5, 5);
        panCampos.add(btnPesquisarTreino, 4, 5);


        btnAdicionar.setOnAction(e -> {
            control.salvar();
        });

        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
        });

        btnPesquisarTreino.setOnAction(e -> {
            control.pesquisarTreino();
        });

        btnLimpar.setOnAction(e -> {
            control.limpar();
        });


        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table1);
        panPrincipal.setBottom(table2);
        this.criarTabela();

        return (panPrincipal);
    }
}
