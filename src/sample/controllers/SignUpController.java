package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.User;
import java.io.IOException;


  // controller for Window where it´s possible to sign up new Users
public class SignUpController {

    @FXML
    private GridPane signUpGridPane;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label label;


    // when you click a "SIGN UP" button is adds a new User to ATM instance and it is saved into
    // the .dat file using a SaveAndLoad class
    @FXML
    public void addNewUserAndExitSignUpWindow(){

        User newUser = new User();


        try{
            newUser.setFirstName(firstNameTextField.getText().trim());
            newUser.setLastName(lastNameTextField.getText().trim());
            newUser.setPassword(passwordTextField.getText().trim());
        } catch (Exception e){
            label.setText("Something went wrong, check if you entered all info correctly.");
        }

        boolean signUpButtonClick = MainController.getAtm().addNewUser(newUser);

        if(signUpButtonClick){
            label.setText("User " + firstNameTextField.getText() + " " + lastNameTextField.getText() + " was added successfully.");
        } else {
            label.setText("Something went wrong, check if you entered all info correctly.");
        }
    }

    // changes Scene to Main Window Scene
    @FXML
    public void changeSceneToMainWindow(){

        Scene mainScene;


        try{
            mainScene = new Scene(FXMLLoader.load(getClass().getResource("fxml/mainWindow.fxml")), 400, 400);
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