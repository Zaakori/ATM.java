package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ATM;
import sample.User;

import java.io.IOException;
import java.util.Optional;


public class MainController {

    // WHERE STOPPED - managed to make work so that multiple users
    // are saved to .dat and are loaded successfully

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

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/signUpWindow.fxml"));

        Scene signUpScene = null;
        try{
            signUpScene = new Scene(fxmlLoader.load());
        } catch(IOException e){
            System.out.println("Could not load Sign Up Window.");
            return;
        }

        Stage primaryStage = (Stage) mainGridPane.getScene().getWindow();
        primaryStage.setScene(signUpScene);
        primaryStage.show();

    }


}
