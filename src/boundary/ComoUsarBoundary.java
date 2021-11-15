package boundary;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ComoUsarBoundary {

    public Pane render() {
        VBox pane = new VBox();

        pane.getChildren().addAll(
                new Label("Essa página vai ensinar a usar o sistema")

        );
        return pane;
    }
}
