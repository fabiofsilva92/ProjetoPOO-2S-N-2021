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

import javax.management.timer.Timer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AgendamentoBoundary implements StrategyBoundary {

    private TextField txtid = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private ComboBox<String> cmbAulas = new ComboBox<>();
    private DatePicker dataAgendamento = new DatePicker();
    private TextField horario = new TextField();

    private Button btnAdicionar = new Button("Adicionar");
    private Button btnNovoHorario = new Button("Novo Horario");


    private AgendamentoControl control = new AgendamentoControl();
    private TableView<Agendamento> table = new TableView<>();
    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");


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


        table.getColumns().addAll(col1, col2, col3, col4);


    }


    @Override
    public Pane render() {

        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        Bindings.bindBidirectional(txtid.textProperty(), control.id, new NumberStringConverter());
        Bindings.bindBidirectional(txtNome.textProperty(), control.nome);
        Bindings.bindBidirectional(txtSobrenome.textProperty(), control.sobrenome);
        Bindings.bindBidirectional(cmbAulas.valueProperty(), control.aula);
        Bindings.bindBidirectional(dataAgendamento.valueProperty(), control.dataAgendamento);
        Bindings.bindBidirectional(horario.textProperty(), control.horarioAgendamento);

        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtid, 1, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNome, 1,1);

        panCampos.add(new Label("Sobre Nome"), 0, 2);
        panCampos.add(txtSobrenome, 1,2);

        panCampos.add(new Label("Aulas"), 0, 3);
        panCampos.add(cmbAulas, 1,3);

        panCampos.add(new Label("Data Agendamento"), 0, 4);
        panCampos.add(dataAgendamento, 1,4);

        panCampos.add(new Label("Horario"), 0, 5);
        panCampos.add(horario, 1, 5);


        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();

        return (panPrincipal);
    }
}
