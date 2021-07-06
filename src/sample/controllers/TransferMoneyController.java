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


public class TransferMoneyController {

    @FXML
    private GridPane transferMoneyGridPane;
    @FXML
    private TextField whoToSendToTextField;
    @FXML
    private TextField sendMoneyTextField;
    @FXML
    private Label label;
    private User signedInUser = MainController.getSignedInUser();
    private ATM atm = MainController.getAtm();


    // actually sends money from one User to the other
    @FXML
    public void transferMoney(){

        double transferAmount;

        try{
            BigDecimal bd = new BigDecimal(sendMoneyTextField.getText()).setScale(2, RoundingMode.CEILING);
            transferAmount = bd.doubleValue();
        } catch (Exception e){
            label.setText("Something went wrong, check the money amount.");
            e.printStackTrace();
            return;
        }

        if(transferAmount > signedInUser.getCurrentMoney()){
            label.setText("It´s not possible to transfer more money than there is in your account.");
            return;
        }

        if(transferAmount < 0){
            label.setText("Transferring negative amounts of money is not possible.");
            return;
        }

        String[] inputUser = whoToSendToTextField.getText().split(" ");
        User foundUser = MainController.findUser(inputUser[0], inputUser[1]);

        if(foundUser == null){
            label.setText("The User was not found.");
            return;
        }

        if(foundUser.equals(signedInUser)){
            label.setText("It´s not possible to transfer money to yourself.");
            return;
        }

        signedInUser.setCurrentMoney(signedInUser.getCurrentMoney() - transferAmount);
        foundUser.setCurrentMoney(foundUser.getCurrentMoney() + transferAmount);

        label.setText(transferAmount + " was transferred successfully to " + foundUser.getFirstName() + " " + foundUser.getLastName() + ".");

        System.out.println("found user now has " + MainController.findUser(inputUser[0], inputUser[1]).getCurrentMoney());
        System.out.println("signed in user now has " + MainController.getSignedInUser().getCurrentMoney());

        makeAndSaveFullTransaction(foundUser, transferAmount);
    }

    // makes a Transaction, so just writes down all the info about sending the money
    private void makeAndSaveFullTransaction(User moneyReceiver, double transferAmount){

        LocalDateTime dateTimeNow = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = dateTimeNow.format(formatter);


        Transaction senderTransaction = new Transaction(signedInUser.getAmountOfTransactionsMade(),
                signedInUser.getFirstName() + " " + signedInUser.getLastName(),
                moneyReceiver.getFirstName() + " " + moneyReceiver.getLastName(),
                -transferAmount, formattedDateTime, signedInUser.getCurrentMoney());

        Transaction receiverTransaction = new Transaction(moneyReceiver.getAmountOfTransactionsMade(),
                signedInUser.getFirstName() + " " + signedInUser.getLastName(),
                moneyReceiver.getFirstName() + " " + moneyReceiver.getLastName(),
                transferAmount, formattedDateTime, moneyReceiver.getCurrentMoney());

        atm.addNewTransaction(senderTransaction, receiverTransaction, signedInUser, moneyReceiver);

    }

    // changes Scene to Profile Page (goes back)
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")), 400, 400);
        } catch(IOException e){
            System.out.println("Could not load Window.");
            return;
        }

        Stage primaryStage = (Stage) transferMoneyGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();
    }
}
