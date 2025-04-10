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

public class LoanCalculator extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Loan Calculator");


        // Create UI elements
        TextField loanAmountField = new TextField();
        TextField interestRateField = new TextField();
        TextField loanTermField = new TextField();
        Button calculateButton = new Button("Calculate");
        Label resultLabel = new Label();

        // Event Handling
        calculateButton.setOnAction(e -> {

            // Calculation Logic
            double loanAmount =   Double.parseDouble(loanAmountField.getText());
            double interestRate = Double.parseDouble(interestRateField.getText()) / 100 / 12;
            int loanTerm = Integer.parseInt(loanTermField.getText()) * 12;
            double monthlyPayment = (loanAmount * interestRate) / (1 - Math.pow(1 + interestRate, -loanTerm));
            resultLabel.setText(String.format("Monthly Payment: %.2f", monthlyPayment));
        });


        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(8);
        vbox.getChildren().add(new Label("Loan Amount:"));
        vbox.getChildren().add(loanAmountField);
        vbox.getChildren().add(new Label("Interest Rate:"));
        vbox.getChildren().add(interestRateField);
        vbox.getChildren().add(new Label("Loan Term:"));
        vbox.getChildren().add(loanTermField);
        vbox.getChildren().add(calculateButton);
        vbox.getChildren().add(resultLabel);

        startBouncingBall(vbox);

        Scene scene = new Scene(vbox, 600, 500);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

    private void startBouncingBall(VBox vbox){
        Circle circle = new Circle(15, Color.RED);
        circle.setCenterX(20);
        circle.setCenterY(20);
        Pane pane = new Pane(circle);
        circle.setOnMouseClicked(e -> {
                    if (circle.getFill() == Color.RED) {
                        circle.setFill(Color.BLUE);
                    } else {
                        circle.setFill(Color.RED);
                    }
                }

        );
        //scene.setRoot(pane);
        vbox.getChildren().add(pane);
        // Animation logic
        TranslateTransition transition = new TranslateTransition(Duration.seconds(2), circle);
        transition.setFromY(0);
        transition.setToY(200);
        transition.setCycleCount(Timeline.INDEFINITE);
        transition.setAutoReverse(true);
        transition.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
