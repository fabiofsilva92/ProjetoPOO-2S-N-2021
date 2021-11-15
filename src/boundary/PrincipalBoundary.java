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
//    private ClienteBoundary cb = new ClienteBoundary();

    public PrincipalBoundary() {
        telas.put("Clientes", new ClienteBoundary());
//        telas.put("Creditos", new PetCreditosBoundary());
    }

    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 800, 600);

        MenuBar menuPrincipal = new MenuBar();

        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cadastros");
        Menu menuAjuda = new Menu("Ajuda");
        Menu menuAgendamento = new Menu("Agendamento Aula");

        MenuItem itemSair = new MenuItem("Sair");
        MenuItem itemClientes = new MenuItem("Clientes");
        MenuItem itemAgendamento = new MenuItem("Agendamento");
        MenuItem itemCreditos = new MenuItem("Créditos");

        menuArquivo.getItems().add(itemSair);
        menuCadastros.getItems().add(itemClientes);
        menuAgendamento.getItems().add(itemAgendamento);
        menuAjuda.getItems().add(itemCreditos);

        itemSair.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

        itemClientes.setOnAction(this);
        itemCreditos.setOnAction(this);


        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros, menuAgendamento, menuAjuda);

        panePrincipal.setTop(menuPrincipal);
//        panePrincipal.setCenter(cb.render());

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
