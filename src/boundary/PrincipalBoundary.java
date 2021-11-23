package boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class PrincipalBoundary extends Application implements EventHandler<ActionEvent> {

    private BorderPane panePrincipal = new BorderPane();


    Map<String, StrategyBoundary> telas = new HashMap<>();

    public PrincipalBoundary() {
        telas.put("Criar/Consultar Cliente", new ClienteBoundary());
        telas.put("Créditos", new CreditoBoundary());
        telas.put("Agendamento Aulas", new AgendamentoBoundary());
        telas.put("Criar/Consultar Funcionário", new FuncionarioBoundary());
        telas.put("Criar/Consultar Equipamentos", new EquipamentoBoundary());
        telas.put("Criar/Consultar Treino", new TreinoBoundary());
        telas.put("Criar / Consultar Evento", new EventoBoundary());
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 800, 600);

        MenuBar menuPrincipal = new MenuBar();
        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cliente");
        Menu menuAjuda = new Menu("Ajuda");
        Menu menuAgendamento = new Menu("Agendamento Aulas");
        Menu menuEvento = new Menu("Eventos");


        MenuItem itemSair = new MenuItem("Sair");
        MenuItem itemCadastro = new MenuItem("Criar/Consultar Cliente");
        MenuItem itemTreino = new MenuItem("Criar/Consultar Treino");
        MenuItem itemCadastroFunc = new MenuItem("Criar/Consultar Funcionário");
        MenuItem itemCadastroEquip = new MenuItem("Criar/Consultar Equipamentos");
        MenuItem itemCreditos = new MenuItem("Créditos");
        MenuItem itemAgendamento = new MenuItem("Agendamento Aulas");
        MenuItem itemComoUsar = new MenuItem("Como Usar?");
        MenuItem itemEvento = new MenuItem("Criar / Consultar Evento");

        itemSair.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });


        menuEvento.getItems().add(itemEvento);
        menuArquivo.getItems().add(itemSair);
        menuCadastros.getItems().add(itemCadastro);
        menuCadastros.getItems().add(itemTreino);
        menuCadastros.getItems().add(itemCadastroFunc);
        menuCadastros.getItems().add(itemCadastroEquip);
        menuAgendamento.getItems().add(itemAgendamento);
        menuAjuda.getItems().add(itemCreditos);
        menuAjuda.getItems().add(itemComoUsar);

        itemCadastro.setOnAction(this);
        itemAgendamento.setOnAction(this);
        itemCreditos.setOnAction(this);
        itemCadastroFunc.setOnAction(this);
        itemCadastroEquip.setOnAction(this);
        itemTreino.setOnAction(this);
        itemEvento.setOnAction(this);


        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros, menuAgendamento, menuAjuda, menuEvento);

        panePrincipal.setTop(menuPrincipal);

        stage.setScene(scn);
        stage.setTitle("Gestão Academia Javeiros");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }

    @Override
    public void handle(ActionEvent event) {
        EventTarget target = event.getTarget();

        if (target instanceof MenuItem) {
            MenuItem menu = (MenuItem) target;
            String texto = menu.getText();
            StrategyBoundary tela = telas.get(texto);
            panePrincipal.setCenter(tela.render());
        }
    }
}
