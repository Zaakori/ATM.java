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

public class PutInMoneyController {

    @FXML
    private GridPane putInMoneyGridPane;
    @FXML
    private TextField addMoneyTextField;
    @FXML
    private Label label;
    private User signedInUser;
    private ATM atm = MainController.getAtm();


    public void initialize(){
        this.signedInUser = MainController.getSignedInUser();
    }


    // adds money to the Users account
    @FXML
    public void addMoneyToBalance(){

        double amountToAdd;

        try{
            BigDecimal bd = new BigDecimal(addMoneyTextField.getText()).setScale(2, RoundingMode.CEILING);
            amountToAdd = bd.doubleValue();
        } catch (Exception e){
            label.setText("Something went wrong, check the money amount.");
            e.printStackTrace();
            return;
        }

        // checks, that you can´t add negative amounts of money
        if(amountToAdd < 0){
            label.setText("Taking out negative amounts of money is not possible.");
            return;
        }

        // adds the money
        signedInUser.setCurrentMoney(signedInUser.getCurrentMoney() + amountToAdd);
        makeAndSaveTransaction(amountToAdd);
        label.setText(amountToAdd + " was successfully added to your account.");
    }

    // makes a Transaction, so just writes down all the info about putting the money
    private void makeAndSaveTransaction(double amountToAdd){

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTimeNow.format(formatter);


        Transaction newTransaction = new Transaction(signedInUser.getAmountOfTransactionsMade(),
                "---", signedInUser.getFirstName() + " " + signedInUser.getLastName(),
                amountToAdd, formattedDateTime, signedInUser.getCurrentMoney());

        atm.addNewTransaction(newTransaction, signedInUser);
    }

    // changes Scene to Profile Page
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")), 400, 400);
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
