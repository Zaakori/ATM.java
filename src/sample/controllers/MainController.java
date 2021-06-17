package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
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
    private Label testLabel;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField pinTextField;
    @FXML
    private Button signInButton;
    @FXML
    private Button signUpButton;
    private ATM atm;
    private static User signedInUser;


    public void initialize(){
        this.atm = new ATM();
        this.signedInUser = new User();
    }

    public ATM getAtm() {
        return atm;
    }

    public static User getSignedInUser(){
        return signedInUser;
    }

    @FXML

    public void signIn() throws IOException{

        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        int pin = Integer.parseInt(pinTextField.getText().trim());
        User inputUser = new User(firstName, lastName, pin);
        boolean exitWhileLoop = true;


        while(exitWhileLoop){

            for(User u : atm.getUserList()){

                if(u.equals(inputUser)){
                    testLabel.setText("Sign In Test: YOU ARE SIGNED IN!");
                    signedInUser = u;
                    changeSceneToProfileWindow();
                    return;
                }
            }
            exitWhileLoop = false;
        }

        testLabel.setText("Sign In Test: NO SUCH USER FOUND");

    }


    @FXML
    public void changeSceneToProfileWindow() throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/profileWindow.fxml"));

        Scene profileScene = null;
        try{
            profileScene = new Scene(fxmlLoader.load());
        } catch(IOException e){
            System.out.println("Could not load Profile Window.");
            return;
        }

        Stage primaryStage = (Stage) mainGridPane.getScene().getWindow();
        primaryStage.setScene(profileScene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();

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
        primaryStage.setTitle("SIGN UP");
        primaryStage.show();

    }






}
