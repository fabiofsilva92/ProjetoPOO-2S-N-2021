package boundary;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application {

    private BorderPane panePrincipal = new BorderPane();

    private ClienteBoundary cb = new ClienteBoundary();

    @Override
    public void start(Stage stage) throws Exception {

        Scene scn = new Scene(panePrincipal, 800, 600);

        MenuBar menuPrincipal = new MenuBar();
        Menu menuArquivo = new Menu("Arquivos");
        Menu menuCadastros = new Menu("Cadastros");
        Menu menuAjuda = new Menu("Ajuda");

        MenuItem itemSair = new MenuItem("Sair");
        MenuItem itemPets = new MenuItem("Clientes");
        MenuItem itemCreditos = new MenuItem("Créditos");

        itemSair.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

//        itemPets.setOnAction(this);

//        itemCreditos.setOnAction(this);

        menuArquivo.getItems().add(itemSair);
        menuCadastros.getItems().add(itemPets);
        menuAjuda.getItems().add(itemCreditos);


        menuPrincipal.getMenus().addAll(menuArquivo, menuCadastros, menuAjuda);

        panePrincipal.setTop(menuPrincipal);
        panePrincipal.setCenter(cb.render());

        stage.setScene(scn);
        stage.setTitle("Gestão Academia Javeiros");
        stage.show();

    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }

}
