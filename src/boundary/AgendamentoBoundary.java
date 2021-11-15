package boundary;

import control.AgendamentoControl;
import entity.Agendamento;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import javax.management.timer.Timer;
import java.time.format.DateTimeFormatter;

public class AgendamentoBoundary implements StrategyBoundary {

    private TextField txtid = new TextField();
    private TextField txtNome = new TextField();
    private TextField txtSobrenome = new TextField();
    private ComboBox<String> cmbAulas = new ComboBox<>();
    private DatePicker dataAgendamento = new DatePicker();
    private Timer horario = new Timer();

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


        table.getColumns().add(col1);


    }


    @Override
    public Pane render() {

        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();

        Bindings.bindBidirectional(txtid.textProperty(), control.id, new NumberStringConverter());

        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtid, 1, 0);


        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();

        return (panPrincipal);
    }
}
