package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class CheckBalanceController {

    @FXML
    private GridPane checkBalanceGridPane;
    @FXML
    private Label balanceLabel;

    private double balance;

    public void initialize(){
        balance = MainController.getSignedInUser().getCurrentMoney();
        balanceLabel.setText("You have: " + Double.toString(balance) + " EUR");
    }

    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")));
        } catch(IOException e){
            System.out.println("Could not load Window.");
            return;
        }

        Stage primaryStage = (Stage) checkBalanceGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();
    }




}
