package boundary;

import control.EventoControl;
import entity.Evento;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class EventoBoundary implements StrategyBoundary{

    private TextField txtId = new TextField();
    private TextField txtNomeEvento = new TextField();
    private DatePicker txtData = new DatePicker();
    private TextField txtHorario = new TextField();
    private TextField txtDuracao = new TextField();

    private Button btnNovoEvento = new Button("Novo Evento");
    private Button btnSalvar = new Button("Salvar");
    private Button btnPeqsuisar = new Button("Pesquisar");

    private EventoControl control = new EventoControl();

    private TableView<Evento> table = new TableView<Evento>();

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private void criarTabela(){

        TableColumn<Evento, Long> col1= new TableColumn<>("Id");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Evento, String> col2= new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nomeEvento"));

        TableColumn<Evento, String> col3= new TableColumn<>("Data");
        col3.setCellValueFactory( (eventoProp) -> {
            LocalDate n = eventoProp.getValue().getData();
            String strData = n.format(this.dtf);
            return new ReadOnlyStringWrapper(strData);
        });

        TableColumn<Evento, String> col4 = new TableColumn<>("Horario");
        col4.setCellValueFactory(new PropertyValueFactory<>("horario"));

        TableColumn<Evento, Double> col5 = new TableColumn<>("Duração");
        col5.setCellValueFactory(new PropertyValueFactory<>("duracao"));

        TableColumn<Evento, String> col6 = new TableColumn<>("Ações");
        col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col6.setCellFactory( (tbCol) ->
                new TableCell<Evento, String>() {
                    final javafx.scene.control.Button btn = new javafx.scene.control.Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction( (e) -> {
                                Evento p = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Evento Id " +
                                                p.getId(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    control.remover(p.getId());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                }
        );

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6);

        table.setItems(control.getListaEvento());

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
        Bindings.bindBidirectional(txtNomeEvento.textProperty(), control.nome);
        Bindings.bindBidirectional(txtData.valueProperty(), control.data);
        Bindings.bindBidirectional(txtHorario.textProperty(), control.horario);
        Bindings.bindBidirectional(txtDuracao.textProperty(), control.duracao, new NumberStringConverter());

        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtId, 1, 0);
        panCampos.add(btnNovoEvento, 2, 0);

        panCampos.add(new Label("Nome do Evento"),0, 1);
        panCampos.add(txtNomeEvento, 1,1);

        panCampos.add(new Label("Data: "), 0, 2);
        panCampos.add(txtData, 1, 2);

        panCampos.add(new Label("Horario"), 0 , 3);
        panCampos.add(txtHorario, 1, 3);

        panCampos.add(new Label("Duração"), 0 , 4);
        panCampos.add(txtDuracao, 1, 4);

        btnSalvar.setOnAction(e -> {
            control.salvar();
        });

        btnPeqsuisar.setOnAction(e -> {
            control.pesquisar();
        });

        btnNovoEvento.setOnAction(e -> {
            control.novoEvento();
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();

        return panPrincipal;
    }
}
