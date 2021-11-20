package boundary;

import entity.Treino;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class TreinoBoundary implements StrategyBoundary {

    private TextField txtID = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobreNome = new TextField();
    private ComboBox<String> cmbTipo = new ComboBox<>();
    private TextField txtMusculo = new TextField();
    private TextField txtAparelho = new TextField();
    private TextField txtRepeticoes = new TextField();
    private TextField txtSeries = new TextField();

    private Button btnAdicionar = new Button("Salvar Treino");
    private Button btnPesquisar = new Button("Pesquisar Aluno");
    private Button btnLimpar = new Button("Limpar");

//    private TreinoControl control = new TreinoControl(); //Composição

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

        TableColumn<Treino, String> col3 = new TableColumn<>("Sobre Nome");
        col3.setCellValueFactory(new PropertyValueFactory<>("sobreNome"));

        TableColumn<Treino, Double> col4 = new TableColumn<>("Tipo");
        col4.setCellValueFactory(new PropertyValueFactory<>("tipo"));

        TableColumn<Treino, Double> col5 = new TableColumn<>("Músculo");
        col4.setCellValueFactory(new PropertyValueFactory<>("musculo"));

        TableColumn<Treino, Double> col6 = new TableColumn<>("Aparelho");
        col4.setCellValueFactory(new PropertyValueFactory<>("aparelho"));

        TableColumn<Treino, Double> col7 = new TableColumn<>("Repetições");
        col4.setCellValueFactory(new PropertyValueFactory<>("repeticoesQTD"));

        TableColumn<Treino, Double> col8 = new TableColumn<>("Series");
        col4.setCellValueFactory(new PropertyValueFactory<>("series"));

//
//        TableColumn<Treino, String> col9 = new TableColumn<>("Ações");
//        col6.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
//        col6.setCellFactory((tbcol) -> new TableCell<Treino, String>() {
//            final Button btn = new Button("Remover");
//
//            public void updateItem(String item, boolean empty) {
//                if (empty) {
//                    setGraphic(null);
//                    setText(null);
//                } else {
//                    btn.setOnAction((e) -> {
//                        Treino p = getTableView().getItems().get(getIndex());
//                        Alert alert = new Alert(Alert.AlertType.WARNING, "Você confirma a remoção " +
//                                p.getId() + " ?", ButtonType.OK, ButtonType.CANCEL);
//                        Optional<ButtonType> clicado = alert.showAndWait();
//                        if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
//                            control.remover(p.getId())
//                            ;
//                        }
//                    });
//                    setGraphic(btn);
//                    setText(null);
//                }
//            }
//        });

        table1.getColumns().addAll(col1, col2, col3);
        table2.getColumns().addAll(col4, col5, col6, col7, col8);

//        table1.setItems(control.getLista());

//        table1.getSelectionModel().selectedItemProperty().addListener(
//                (obs, antigo, novo) -> {
//                    if (novo != null) {
//                        control.setEntity(novo);
//                    }
//                }
//        );
    }


    @Override
    public Pane render() {
        return null;
    }
}
