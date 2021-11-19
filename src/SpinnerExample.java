import boundary.PrincipalBoundary;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class SpinnerExample extends Application {

    @Override
    public void start(Stage stage) {
        //Setting the label
        Label label = new Label("Select Date:");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        label.setFont(font);
        //Creating a spinner
        Spinner month = new Spinner(1, 12, 4);
        //Setting the spinner editable
        month.setEditable(true);
        //Setting the size
        month.setPrefSize(75, 25);
        Label label1 = new Label("Month: ");
        //Creating a spinner
        Spinner day = new Spinner(1, 31, 4);
        //Setting the spinner editable
        day.setEditable(true);
        //Setting the size
        day.setPrefSize(75, 25);
        Label label2 = new Label("Day: ");
        //Creating a spinner
        Spinner year = new Spinner(1947, 2999, 2009);
        //Setting the spinner editable
        year.setEditable(true);
        //Setting the size
        year.setPrefSize(75, 25);
        Label label3 = new Label("Year: ");
        HBox hbox = new HBox(5);
        hbox.setPadding(new Insets(10, 10, 10, 25));
        hbox.getChildren().addAll(label1, month, label2, day, label3, year);
        //Creating a vbox to hold the pagination
        VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 10, 10, 25));
        vbox.getChildren().addAll(label, hbox);
        //Setting the stage
        Group root = new Group(vbox);
        Scene scene = new Scene(root, 595, 200, Color.BEIGE);
        stage.setTitle("Spinner");
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String args[]){Application.launch(SpinnerExample.class, args);
    }
}