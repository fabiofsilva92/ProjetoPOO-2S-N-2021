package boundary;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CreditoBoundary implements StrategyBoundary{

    public Pane render() {
        VBox pane = new VBox();

        pane.getChildren().addAll(
                new Label("Desenvolvedores: Alex Henrique Pineda, Elisio Ricardo, Fábio Fernandes Silva"),
                new Label("Faculdade: Faculdade de Tecnologia da Zona Leste"),
                new Label("Coordenador Luciano Oliveira"),
                new Label("Professor Antonio Rodrigues"),
                new Label("Aos alunos da turma da noite de POO")
        );
        return pane;
    }
}

