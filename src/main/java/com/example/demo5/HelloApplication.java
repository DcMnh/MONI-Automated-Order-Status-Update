package com.example.demo5;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.automation;

import java.util.ArrayList;

public class HelloApplication extends Application {
    private ArrayList<Integer> orderNumbersList = new ArrayList<>();
    private String selectedStatus = "";
    private boolean notifyCustomerCheckBox = false;
    private String customerMessage = "";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ArrayList<Integer> orderNumbersList = new ArrayList<>();

        primaryStage.setTitle("Moni order status update");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);

        Label labelMoni = new Label("MONI");
        labelMoni.setFont(javafx.scene.text.Font.font("Calibri", 24));
        labelMoni.setStyle("-fx-font-weight: bold;");
        GridPane.setConstraints(labelMoni, 0, 0, 2, 1);

        Label labelDesignedBy = new Label("Designed by Duy Duc Minh Vo");
        GridPane.setConstraints(labelDesignedBy, 0, 8, 2, 1);

        Label labelOrderStatus = new Label("Order Status Update");
        labelOrderStatus.setFont(javafx.scene.text.Font.font("Calibri", 20));
        labelOrderStatus.setStyle("-fx-font-weight: bold;");
        GridPane.setConstraints(labelOrderStatus, 0, 1, 2, 1);

        Label labelOrderNumber = new Label("Order(s) number");
        labelOrderNumber.setFont(javafx.scene.text.Font.font("Calibri", 16));
        GridPane.setConstraints(labelOrderNumber, 0, 2);

        Label labelStatus = new Label("Status");
        labelStatus.setFont(javafx.scene.text.Font.font("Calibri", 16));
        GridPane.setConstraints(labelStatus, 0, 3);

        Label labelNotifyCustomer = new Label("Notify Customer");
        labelNotifyCustomer.setFont(javafx.scene.text.Font.font("Calibri", 16));
        GridPane.setConstraints(labelNotifyCustomer, 0, 4);

        Label labelMessage = new Label("Message");
        labelMessage.setFont(javafx.scene.text.Font.font("Calibri", 16));
        GridPane.setConstraints(labelMessage, 0, 5);

        TextField orderNumbers = new TextField();
        GridPane.setConstraints(orderNumbers, 1, 2, 2, 1);

        ComboBox<String> statusOptions = new ComboBox<>();
        statusOptions.getItems().addAll("BRING TO SCHOOL - PAID", "BACK ORDER CANCELLED", "BACK ORDER PAID", "BACK ORDER READY",
                "BACK ORDER READY at MONI", "BACK ORDER READY TO EMBROIDER", "BACK TO STOCK", "BEACONSFIELD BRING TO SCHOOL",
                "beaconsfield HOME", "beaconsfield MONI", "Beaconsfield SCHOOL", "BRING BACK BY ALEX", "BRING back to MONI from SCHOOL",
                "BRING TO SCHOOL - EIA", "BRING TO SCHOOL - CAVELIER", "BRING TO SCHOOL - FELIX", "BRING TO SCHOOL - HSB - CHARGED",
                "BRING TO SCHOOL - LCCHS", "BRING TO SCHOOL - St.Georges", "BRING TO SCHOOL - WESTWOOD", "BRING TO SCHOOL - CWA",
                "BRING TO SCHOOL - DORVAL", "BRING TO SCHOOL - HSB - NOT CHARGED", "BRING TO SCHOOL - NOT CHARGE",
                "BRING TO SCHOOL - PAUL JARRY", "Canceled", "Canceled BACK ORDER", "Complete", "Credit card charged",
                "Credit card declined", "CREDIT CARD INFO REQUESTED", "DELIVERY READY - NOT COMPLETE", "DROPPED OFF at SCHOOL",
                "EXCHANGE", "GRADHSB", "GRADNAME", "Home Delivery", "INCOMPLETE", "LEFT AT SCSHOOL", "LORNA", "MODIFIED",
                "MONI", "New Credi Card", "Nicole to deliver", "No credit card", "ON HOLD AT PLANET COURRIER", "Order Incomplete",
                "ORDER LEFT at SCHOOL", "Order no pick up - back to stock", "ORDER NOT READY", "PAID INTERAC", "PENDING ETRANSFER",
                "PICKED UP AT SCHOOL", "Picked up + Back Order", "PLANETE COURRIER", "PLEASE CONTACT US", "PREPARED - INCOMPLETE",
                "PREPARED NOT CHARGED", "Processing", "READY EXCEPT HOODIE", "Ready for delivery", "READY for pick up - INCOMPLETE",
                "Ready for pick up at School", "Ready for pick up at Moni", "RECEVIED ORDER - NOT COMPLETE", "Refunded", "Reversed",
                "RUSH ORDER", "SCHOOL", "school pick up", "Shipped", "SHIPPED BY ALEX", "SHIPPED BY BRENDA", "SHIPPED BY LORNA",
                "SHIPPED BY NICOLE", "SHIPPED BY PHILLIPE", "SHIPPED BY PLANETE COURRIER", "SHIPPED TO HSB", "SHIPPED TO SCHOOL",
                "Shipping Cost Determined", "Transaction cancelled", "Westmount-school pick up", "WESTWOOD CANCELED", "westwood HOME",
                "westwood MONI", "westwood SCHOOL", "WSW bring to SCHOOL PAID", "WSW CANCEL POLICY CHANGE", "WSW HOME DELIVERY NOT PAID",
                "WSW HOME PAID", "WSW SCHOOL NOT PAID"); // Add your status options
        statusOptions.setPrefWidth(250);
        GridPane.setConstraints(statusOptions, 1, 3, 2, 1);

        CheckBox notifyCustomer = new CheckBox("Notify Customer");
        GridPane.setConstraints(notifyCustomer, 1, 4);

        TextArea message = new TextArea();
        GridPane.setConstraints(message, 0, 6, 3, 1);

        Button submitButton = new Button("Update");
        submitButton.setMinWidth(80);
        GridPane.setConstraints(submitButton, 2, 7);

        grid.getChildren().addAll(
                labelMoni, labelDesignedBy, labelOrderStatus,
                labelOrderNumber, labelStatus, labelNotifyCustomer, labelMessage,
                orderNumbers, statusOptions, notifyCustomer, message, submitButton
        );

        Scene scene = new Scene(grid, 808, 472);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Event handler for the "Update" button
        submitButton.setOnAction(e -> {
            // Extract and store user input
            String orderNumbersText = orderNumbers.getText();
            String[] orderNumbersArray = orderNumbersText.split(",");
            orderNumbersList.clear();
            for (String orderNumberStr : orderNumbersArray) {
                try {
                    int orderNumber = Integer.parseInt(orderNumberStr.trim());
                    orderNumbersList.add(orderNumber);
                } catch (NumberFormatException ex) {
                    // Handle invalid input (non-integer order number)
                    ex.printStackTrace();
                }
            }
            selectedStatus = statusOptions.getValue();
            notifyCustomerCheckBox = notifyCustomer.isSelected();
            customerMessage = message.getText();

            try {
                automation Automation = new automation(orderNumbersList, selectedStatus, notifyCustomerCheckBox, customerMessage);
                Automation.automationProcess();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
    }


}
