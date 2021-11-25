package boundary;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import static javafx.application.Application.launch;

public class ComoUsarBoundary implements StrategyBoundary{


    public Pane render() {

        WebView webView = new WebView();

        webView.getEngine().load("https://www.youtube.com/embed/LK5v1OGhUI");

        VBox pane = new VBox(webView);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }


}

