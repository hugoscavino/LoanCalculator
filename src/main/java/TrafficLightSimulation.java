import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class TrafficLightSimulation extends Application {

    private final Circle redLight = new Circle(20, Color.TRANSPARENT);
    private final Circle yellowLight = new Circle(20, Color.TRANSPARENT);
    private final Circle greenLight = new Circle(20, Color.TRANSPARENT);

    @Override
    public void start(Stage primaryStage) {

        // 1. Create the lights
        redLight.setStroke(Color.BLACK);
        yellowLight.setStroke(Color.BLACK);
        greenLight.setStroke(Color.BLACK);

        // 2. Add the lights to the Vertical box with a spacing of 10
        VBox lightsBox = new VBox(10, redLight, yellowLight, greenLight);
        lightsBox.setPadding(new Insets(10));
        // Set the border to black
        lightsBox.setStyle("-fx-border-color: black");
        lightsBox.setAlignment(Pos.CENTER);

        // 3. Create radio buttons
        RadioButton rbRed = new RadioButton("Red");
        RadioButton rbYellow = new RadioButton("Yellow");
        RadioButton rbGreen = new RadioButton("Green");

        // only one can be enabled at a time when Radio Buttons
        // are in a group
        ToggleGroup group = new ToggleGroup();
        rbRed.setToggleGroup(group);
        rbYellow.setToggleGroup(group);
        rbGreen.setToggleGroup(group);

        // 4. Add the radio buttons to a horizontal box
        HBox buttonsBox = new HBox(10, rbRed, rbYellow, rbGreen);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setPadding(new Insets(10));

        // Set up event handling for each button click
        rbRed.setOnAction(e -> {
            redLight.setFill(Color.RED);
            yellowLight.setFill(Color.TRANSPARENT);
            greenLight.setFill(Color.TRANSPARENT);
        });

        // When Yellow is clicked then perform this action
        rbYellow.setOnAction(e -> {
            redLight.setFill(Color.TRANSPARENT);
            yellowLight.setFill(Color.YELLOW);
            greenLight.setFill(Color.TRANSPARENT);
        });


        // WHen Green Radio Button is clicked then perform this action
        rbGreen.setOnAction(e -> {
            redLight.setFill(Color.TRANSPARENT);
            yellowLight.setFill(Color.TRANSPARENT);
            greenLight.setFill(Color.GREEN);
        });

        // 5. Add the Vertical and Horizontal boxes to pane
        BorderPane pane = new BorderPane();
        pane.setCenter(lightsBox);
        pane.setBottom(buttonsBox);

        // 6. Add the pane to the scene
        Scene scene = new Scene(pane, 200, 250);

        // Finally set the scene onto the passed in stage
        primaryStage.setTitle("Exercise16_03");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        // Launch the UI
        launch(args);
    }
}
