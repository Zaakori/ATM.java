package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CheckBalanceController {

    @FXML
    private Label balanceLabel;

    private double balance = MainController.getSignedInUser().getCurrentFunds();

    public void initialize(){
        balanceLabel.setText("You have: " + Double.toString(balance) + " EUR");
    }




}
