package boundary;

import control.EquipamentoControl;
import entity.Equipamento;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.util.Optional;

public class EquipamentoBoundary implements StrategyBoundary{

    private TextField txtId = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtFabricante = new TextField();
    private ComboBox<String> cmbAno = new ComboBox<>();
    private TextField txtPreco = new TextField();

    private Button btnNovoEquipamento = new Button("Novo Equipamento");
    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private TableView<Equipamento> table = new TableView<>();

    private void criarTabela() {
        ObservableList<String> items = FXCollections.observableArrayList("2021", "2020", "2019", "2018", "2017", "2016", "2015", "2014", "2013");
        cmbAno.setItems(items);

        TableColumn<Equipamento, Long> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Equipamento, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<>("nome"));

        TableColumn<Equipamento, String> col3 = new TableColumn<>("Fabricante");
        col2.setCellValueFactory( new PropertyValueFactory<>("fabricante"));

        TableColumn<Equipamento, String> col4 = new TableColumn<>("Ano");
        col4.setCellValueFactory( new PropertyValueFactory<>("ano"));

        TableColumn<Equipamento, Double> col5 = new TableColumn<>("Preço");
        col5.setCellValueFactory( new PropertyValueFactory<>("preco"));

        TableColumn<Equipamento, String> col6 = new TableColumn<>("Ações");
        col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY"));
        col6.setCellFactory( (tbCol) ->
                new TableCell<Equipamento, String>() {
                    final Button btn = new Button("Remover");
                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction((z) -> {
                                Equipamento e = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING, "Você confirma a remoção do Equipamento com Id: " + e.getId(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
                                    control.remover(e.getId());
                                }


                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
        });

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6);
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

    private EquipamentoControl control = new EquipamentoControl();

    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        txtId.setEditable(false);
        txtId.setDisable(true);

        Bindings.bindBidirectional(txtId.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtFabricante.textProperty(), control.fabricante);
        Bindings.bindBidirectional(cmbAno.valueProperty(), control.ano, new NumberStringConverter());
        Bindings.bindBidirectional(txtPreco.textProperty(), control.preco, new NumberStringConverter());

        panCampos.add(new Label("ID"), 0, 0);
        panCampos.add(txtId, 1, 0);
        panCampos.add(btnNovoEquipamento, 2, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1, 1);
        panCampos.add(new Label("Fabricante"), 2, 1);
        panCampos.add(txtFabricante, 3, 1);
        panCampos.add(new Label("Ano"), 0, 2);
        panCampos.add(cmbAno, 1, 2);
        panCampos.add(new Label("Preço"), 0, 3);
        panCampos.add(txtPreco, 1, 3);

        panCampos.add(btnSalvar, 0, 4);
        panCampos.add(btnPesquisar, 1, 4);

        btnSalvar.setOnAction((e -> {
            control.salvar();
        }));

        btnPesquisar.setOnAction((event -> {
            control.pesquisar();
        }));

        btnNovoEquipamento.setOnAction( e -> {
            control.novoEquipamento();
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();
        return (panPrincipal);

    }


}
