package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ATM;
import sample.Transaction;
import sample.User;

import java.io.IOException;
import java.time.LocalDateTime;


public class TransferMoneyController {

    @FXML
    private GridPane transferMoneyGridPane;

    @FXML
    private TextField whoToSendToTextField;

    @FXML
    private TextField sendMoneyTextField;

    private User signedInUser = MainController.getSignedInUser();
    private ATM atm = MainController.getAtm();


    // actually sends money from one User to the other
    @FXML
    public void transferMoney(){

        double transferAmount = Double.parseDouble(sendMoneyTextField.getText());

        if(transferAmount > signedInUser.getCurrentMoney()){
            System.out.println("can´t send more money that what you have!");
            return;
        }

        String[] inputUser = whoToSendToTextField.getText().split(" ");

        User foundUser = MainController.findUser(inputUser[0], inputUser[1]);

        if(foundUser == null){
            return;
        }

        signedInUser.setCurrentMoney(signedInUser.getCurrentMoney() - transferAmount);
        foundUser.setCurrentMoney(foundUser.getCurrentMoney() + transferAmount);

        System.out.println("found user now has " + MainController.findUser(inputUser[0], inputUser[1]).getCurrentMoney());
        System.out.println("signed in user now has " + MainController.getSignedInUser().getCurrentMoney());


        makeAndSaveFullTransaction(foundUser, transferAmount);

    }

    // makes a Transaction, so just writes down all the info about sending the money
    private void makeAndSaveFullTransaction(User moneyReceiver, double transferAmount){

        LocalDateTime dateTimeNow = LocalDateTime.now();

        Transaction senderTransaction = new Transaction(signedInUser.getAmountOfTransactionsMade(),
                signedInUser.getFirstName() + " " + signedInUser.getLastName(),
                moneyReceiver.getFirstName() + " " + moneyReceiver.getLastName(),
                -transferAmount, dateTimeNow, signedInUser.getCurrentMoney());

        Transaction receiverTransaction = new Transaction(moneyReceiver.getAmountOfTransactionsMade(),
                signedInUser.getFirstName() + " " + signedInUser.getLastName(),
                moneyReceiver.getFirstName() + " " + moneyReceiver.getLastName(),
                transferAmount, dateTimeNow, moneyReceiver.getCurrentMoney());

        atm.addNewFullTransaction(senderTransaction, receiverTransaction, signedInUser, moneyReceiver);

    }



    // changes Scene to Profile Page (goes back)
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")));
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
