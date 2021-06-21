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

public class PutInMoneyController {

    @FXML
    private GridPane putInMoneyGridPane;
    @FXML
    private TextField addMoneyTextField;
    private User signedInUser;


    public void initialize(){
        this.signedInUser = MainController.getSignedInUser();
    }


    // adds money to the Users account
    @FXML
    public void addMoneyToBalance(){

        // checks, that you can´t add negative amounts of money
        if(Double.parseDouble(addMoneyTextField.getText()) < 0){
            return;
        }

        // adds the money
        signedInUser.setCurrentMoney(signedInUser.getCurrentMoney() + Double.parseDouble(addMoneyTextField.getText()));
    }

    // changes Scene to Profile Page
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")));
        } catch(IOException e){
            System.out.println("Could not load Window.");
            return;
        }

        Stage primaryStage = (Stage) putInMoneyGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();
    }
}
