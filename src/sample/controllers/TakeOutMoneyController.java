package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.Transaction;
import sample.User;

import java.io.IOException;
import java.util.ArrayList;

public class TakeOutMoneyController {

    @FXML
    private GridPane takeOutMoneyGridPane;
    @FXML
    private TextField takeOutMoneyTextField;
    private User signedInUser;
    private double amountOfMoney;
    private ArrayList<Transaction> transactionList;

    public void initialize(){

        this.signedInUser = MainController.getSignedInUser();
        this.amountOfMoney = signedInUser.getCurrentMoney();
        this.transactionList = signedInUser.getTransactionList();

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

        Stage primaryStage = (Stage) takeOutMoneyGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();
    }

}
