package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ATM;
import sample.Transaction;
import sample.User;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TakeOutMoneyController {

    @FXML
    private GridPane takeOutMoneyGridPane;
    @FXML
    private TextField takeOutMoneyTextField;
    @FXML
    private Label label;
    private User signedInUser;
    private ATM atm = MainController.getAtm();

    // is called every time this Scene is loaded
    public void initialize(){
        this.signedInUser = MainController.getSignedInUser();
    }

    // allows to take out money from the account
    @FXML
    public void takeOutMoney(){

        double amountToTakeOut;

        try{
            BigDecimal bd = new BigDecimal(takeOutMoneyTextField.getText()).setScale(2, RoundingMode.CEILING);
            amountToTakeOut = bd.doubleValue();
        } catch (Exception e){
            label.setText("Something went wrong, check the money amount.");
            e.printStackTrace();
            return;
        }

        // check, so that can´t take out negative amounts of money
        if(amountToTakeOut < 0){
            label.setText("Taking out negative amounts of money is not possible.");
            return;
        }

        // check, that can´t take out more money than how much user has
        if(amountToTakeOut > signedInUser.getCurrentMoney()){
            label.setText("There is not enough money in the account.");
            return;
        }

        signedInUser.setCurrentMoney(signedInUser.getCurrentMoney() - amountToTakeOut);
        makeAndSaveTransaction(amountToTakeOut);
        label.setText(amountToTakeOut + " was successfully taken from your account.");
    }

    // makes a Transaction, so just writes down all the info about putting the money
    private void makeAndSaveTransaction(double amountToTakeOut){

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTimeNow.format(formatter);


        Transaction newTransaction = new Transaction(signedInUser.getAmountOfTransactionsMade(),
                signedInUser.getFirstName() + " " + signedInUser.getLastName(), "---",
                -amountToTakeOut, formattedDateTime, signedInUser.getCurrentMoney());

        atm.addNewTransaction(newTransaction, signedInUser);
    }

    // changes Scene to Profile Page (so, goes back)
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")), 400, 400);
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
