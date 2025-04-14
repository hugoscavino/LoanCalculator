package com.ep;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Application is the base class from the javafx.application
 * --
 * The entry point for JavaFX applications is the Application class. The JavaFX runtime does the following, in order, whenever an application is launched:
 * --
 * Constructs an instance of the specified Application class
 * Calls the init() method
 * Calls the start(javafx.stage.Stage) method
 * Waits for the application to finish, which happens when either of the following occur:
 * the application calls Platform.exit()
 * the last window has been closed and the implicitExit attribute on Platform is true
 * Calls the stop() method
 */
public class LoanCalculator extends Application {

    /**
     * The main entry point for all JavaFX applications. The start method is called
     * after the init method has returned, and after the system is ready for
     * the application to begin running.
     * @param primaryStage  the primary stage for this application, onto which the application
     *                      scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Loan Calculator");

        // Create UI elements for the Loan Calculator
        // Loan Amount Text Field
        TextField loanAmountField = new TextField();

        // interest rate text field
        TextField interestRateField = new TextField();

        // Term amount
        TextField loanTermField = new TextField();

        // Button for the user to press and calculate the monthly
        // loan amount
        Button calculateButton = new Button("Calculate");

        // Label for the result amount to display
        Label resultLabel = new Label();

        // Event Handling
        // Sets the value of the property onAction.
        calculateButton.setOnAction(e -> {

            // Calculation Logic
            // The total amount of the loan
            double loanAmount =   Double.parseDouble(loanAmountField.getText());

            // interest rate
            // 1) Parsed into a double
            // 2) Divided by 100 to get the double in a percentage
            // 3) Then divided by 12 to get the monthly interest rate
            double interestRate = Double.parseDouble(interestRateField.getText()) / 100 / 12;

            // The loan term (years) converted into months
            int loanTerm = Integer.parseInt(loanTermField.getText()) * 12;

            double monthlyPayment = (loanAmount * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));
            resultLabel.setText(String.format("Monthly Payment: %.2f", monthlyPayment));
        });

        // Create a layout map
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);

        // Add the elements
        vbox.getChildren().add(new Label("Loan Amount:"));

        vbox.getChildren().add(loanAmountField);
        vbox.getChildren().add(new Label("Interest Rate:"));

        vbox.getChildren().add(interestRateField);
        vbox.getChildren().add(new Label("Loan Term (Years) :"));
        vbox.getChildren().add(loanTermField);

        vbox.getChildren().add(calculateButton);
        vbox.getChildren().add(resultLabel);

        // Start the animation passing in the layout
        startBouncingBall(vbox);

        // Create the scene for the layout, with the elements
        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);

        // Show it all
        primaryStage.show();


    }

    private void startBouncingBall(VBox vbox){
        // Create a red circle
        Circle circle = new Circle(15, Color.RED);
        circle.setCenterX(20);
        circle.setCenterY(20);

        // Add the circle to a pane
        Pane pane = new Pane(circle);

        // When the circle is clicked change the color back and forth
        circle.setOnMouseClicked(e -> {
                    if (circle.getFill() == Color.RED) {
                        circle.setFill(Color.BLUE);
                    } else {
                        circle.setFill(Color.RED);
                    }
                }

        );
        // This code did not work and diplayed the animation and hid the
        // text fields and buttons
        //scene.setRoot(pane);

        // add the pane to the bottom of the layout
        vbox.getChildren().add(pane);

        // Animation logic
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), circle);
        transition.setFromY(0);
        transition.setToY(200);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }

    /**
     * Start the application
     * @param args
     */
    public static void main(String[] args) {
        // this method is inherited from the parent
        launch(args);
    }
}
