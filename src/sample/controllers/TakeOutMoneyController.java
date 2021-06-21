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

    // is called every time this Scene is loaded
    public void initialize(){
        this.signedInUser = MainController.getSignedInUser();
    }

    // allows to take out money from the account
    @FXML
    public void takeOutMoney(){

        // check, so that can´t take out negative amounts of money
        if(Double.parseDouble(takeOutMoneyTextField.getText()) < 0){
            return;
        }

        // check, that can´t take out more money than how much user has
        if(Double.parseDouble(takeOutMoneyTextField.getText()) > signedInUser.getCurrentMoney()){
            return;
        }

        signedInUser.setCurrentMoney(signedInUser.getCurrentMoney() - Double.parseDouble(takeOutMoneyTextField.getText()));
    }

    // changes Scene to Profile Page (so, goes back)
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
