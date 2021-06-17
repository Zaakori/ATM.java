package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;

public class SignUpController {

    @FXML
    private GridPane signUpGridPane;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField pinTextField;
    @FXML
    private Button signUpButton;
    @FXML
    private Button goBackButton;

    public Button getSignUpButton() {
        return signUpButton;
    }

    // when you click a "SIGN UP" button is adds a new User to ATM instance and it is saved into
    // the .txt file using a SaveAndLoad class. After that it changes the Scene just like "go back" button
    @FXML
    public void addNewUserAndExitSignUpWindow(){

        User newUser = new User();

        newUser.setFirstName(firstNameTextField.getText());
        newUser.setLastName(lastNameTextField.getText());
        newUser.setPinCode(Integer.parseInt(pinTextField.getText()));

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/mainWindow.fxml"));

        Scene mainScene = null;
        try{
            mainScene = new Scene(fxmlLoader.load());
        } catch(IOException e){
            System.out.println("Could not load Main Window.");
            return;
        }

        MainController mainCon = fxmlLoader.getController();
        mainCon.getAtm().addNewUser(newUser);

        Stage primaryStage = (Stage) signUpGridPane.getScene().getWindow();
        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    @FXML
    public void changeSceneToMainWindow(){

        Scene mainScene = null;
        try{
            mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/mainWindow.fxml")));
        } catch(IOException e){
            System.out.println("Could not load Main Window.");
            return;
        }

        Stage primaryStage = (Stage) signUpGridPane.getScene().getWindow();
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("ATM interface");
        primaryStage.show();
    }



}
