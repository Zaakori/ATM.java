package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;

  // controller for the Window where you can see your current balance, that´s it
public class CheckBalanceController {

    @FXML
    private GridPane checkBalanceGridPane;
    @FXML
    private Label balanceLabel;


    public void initialize(){

        double balance;

        balance = MainController.getSignedInUser().getCurrentMoney();
        balanceLabel.setText("You have: " + balance + " EUR");
    }

    // changes Scene to Profile Page Scene
      @FXML
    public void changeScene(){

        Scene scene;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")), 400, 400);
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
