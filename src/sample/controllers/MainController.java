package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ATM;

import java.io.IOException;


public class MainController {

    // WHERE STOPPED - in SignUpController the method addNewUser throws an Exception
    // and claims that mainCon is null... I don´t get why on Earth is it null

    @FXML
    private GridPane mainGridPane;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField pinTextField;
    @FXML
    private Button signUpButton;
    private ATM atm;

    public ATM getAtm() {
        return atm;
    }

    public void initialize(){
        atm = new ATM();
    }

    @FXML
    public void changeSceneToSignUpWindow() throws IOException{

        Scene signUpScene = null;
        try{
            signUpScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/signUpWindow.fxml")));
        } catch(IOException e){
            System.out.println("Could not load Sign Up Window.");
            return;
        }

        Stage primaryStage = (Stage) mainGridPane.getScene().getWindow();
        primaryStage.setScene(signUpScene);
        primaryStage.show();

    }

//    public void addNewUser(){
//
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        fxmlLoader.setLocation(getClass().getResource("fxml/signUpWindow.fxml"));
//
//        SignUpController signCon = fxmlLoader.getController();
//        atm.addNewUser(signCon.getNewUser());
//    }


    



}
