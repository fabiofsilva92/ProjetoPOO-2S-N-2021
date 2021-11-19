package boundary;

import control.AgendamentoControl;
import entity.Agendamento;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class AgendamentoBoundary implements StrategyBoundary {

    private TextField txtid = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private TextField txtHorario = new TextField();
    private ComboBox<String> cmbAulas = new ComboBox<>();
    private DatePicker dataAgendamento = new DatePicker();
    //    private TextField horario = new TextField();
//    private Spinner<LocalTime> horario = new Spinner<>();
    private Button btnAdicionar = new Button("Adicionar");
    private Button btnPesquisar = new Button("Pesquisar");
    Spinner horario = new Spinner(8, 22, 00);


    private AgendamentoControl control = new AgendamentoControl();
    private TableView<Agendamento> table = new TableView<>();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private DateTimeFormatter dt = DateTimeFormatter.ofPattern("HH:mm");


    private void criarTabela() {

        ObservableList<String> aulas =
                FXCollections.observableArrayList("Natação", "Box", "Spinner", "Dança");
        cmbAulas.setItems(aulas);

        TableColumn<Agendamento, Long> col1 = new TableColumn<>("ID");
        col1.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Agendamento, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Agendamento, String> col3 = new TableColumn<>("Sobre Nome");
        col3.setCellValueFactory(new PropertyValueFactory<>("sobreNome"));

        TableColumn<Agendamento, String> col4 = new TableColumn<>("Data Agentamento");
        col4.setCellValueFactory((agend) -> {
            LocalDate n = agend.getValue().getDataAgendamento();
            String strData = n.format(this.dtf);
            return new ReadOnlyStringWrapper(strData);
        });

        TableColumn<Agendamento, String> col5 = new TableColumn<>("Horario");
        col5.setCellValueFactory(new PropertyValueFactory<>("horario"));

        TableColumn<Agendamento, String> col6 = new TableColumn<>("Ações");
        col6.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));
        col6.setCellFactory((tbCol) -> new TableCell<Agendamento, String>() {
            final Button btn = new Button("Remover");

            public void updateItem(String item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction(e -> {
                        Agendamento a = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.WARNING, "Você confirma a remoção do ID "
                                + a.getId() + " ?", ButtonType.OK, ButtonType.CANCEL);
                        Optional<ButtonType> clicado = alert.showAndWait();
                        if (clicado.isPresent() && clicado.get().equals(ButtonType.OK)) {
                            control.remover(a.getId());
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                }
            }
        });

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6);

        table.setItems(control.getLista());

        table.getSelectionModel().selectedItemProperty().addListener(
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

        txtid.setEditable(false);
        txtid.setDisable(true);

//        Spinner<LocalTime> day = new Spinner<LocalTime>(8, 22, 0);
//        //Setting the spinner editable
//        day.setEditable(true);

        SpinnerValueFactory<LocalTime> factory = new SpinnerValueFactory<LocalTime>() {
            {
                setValue(defaultValue());
//                setConverter(new LocalTimeStringConverter(DateTimeFormatter.ofPattern("HH:mm"), DateTimeFormatter.ofPattern("HH:mm")));
            }

            private LocalTime defaultValue() {
                return LocalTime.now().truncatedTo(ChronoUnit.HOURS);
            }

            @Override
            public void decrement(int steps) {
                LocalTime value = getValue();
                setValue(value == null ? defaultValue() : value.minusHours(steps));
            }

            @Override
            public void increment(int steps) {
                LocalTime value = getValue();
                setValue(value == null ? defaultValue() : value.plusHours(steps));
            }
        };

        horario.setValueFactory(factory);

        System.out.println(horario.getValue());

        Bindings.bindBidirectional(txtid.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobreNome);
        Bindings.bindBidirectional(cmbAulas.valueProperty(), control.aula);
        Bindings.bindBidirectional(dataAgendamento.valueProperty(), control.dataAgendamento);
        Bindings.bindBidirectional(txtHorario.textProperty(), control.horarioAgendamento);

        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtid, 1, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1, 1);

        panCampos.add(new Label("Sobre Nome"), 0, 2);
        panCampos.add(txtSobrenome, 1, 2);

        panCampos.add(new Label("Aulas"), 0, 3);
        panCampos.add(cmbAulas, 1, 3);

        panCampos.add(new Label("Data Agendamento"), 0, 4);
        panCampos.add(dataAgendamento, 1, 4);

        panCampos.add(new Label("Horario"), 0, 5);
        panCampos.add(horario, 1, 5);

        panCampos.add(btnAdicionar, 2, 5);
        panCampos.add(btnPesquisar, 3, 5);

        btnAdicionar.setOnAction(e -> {
            control.salvar();
        });

        btnPesquisar.setOnAction(e -> {
            control.pesquisar();
        });

        horario.setOnMouseClicked(e -> {
            //Setando o horario de um textproperty servindo como binding
            txtHorario.setText(horario.getValue().toString());
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();

        return (panPrincipal);
    }


}
