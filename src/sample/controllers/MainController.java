package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ATM;
import sample.User;
import java.io.IOException;

  // controller for the Window that you get when opening the program, the Sign In window
public class MainController {

    @FXML
    private GridPane mainGridPane;
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private PasswordField passwordField;
    private static ATM atm;
    private static User signedInUser;


    public void initialize(){
        atm = new ATM();
        signedInUser = new User();
    }

    public static ATM getAtm() {
        return atm;
    }

    public static User getSignedInUser(){
        return signedInUser;
    }

    // used to find the needed User from the ATM classes UserList and return it if the correct User was found
    public static User findUser(String inputFirstName, String inputLastName){
    return atm.findAndReturnUser(inputFirstName, inputLastName);
    }

    // method to 'get into ATM' using full name and password combination
    @FXML
    public void signIn(){

        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String password = passwordField.getText().trim();
        User inputUser = new User(firstName, lastName, password);
        boolean exitWhileLoop = true;


        while(exitWhileLoop){

            for(User u : atm.getUserList()){

                if(u.equals(inputUser)){
                    signedInUser = u;

                    if(signedInUser.getTransactionList() != null){
                        atm.setObservableList();
                    }

                    changeSceneToProfileWindow();
                    return;
                }
            }
            exitWhileLoop = false;
        }
    }

    // changes Scene to Profile Window Scene
    @FXML
    public void changeSceneToProfileWindow(){

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/profileWindow.fxml"));
        Scene profileScene;


        try{
            profileScene = new Scene(fxmlLoader.load(), 400, 400);
        } catch(IOException e){
            System.out.println("Could not load Profile Window.");
            return;
        }

        Stage primaryStage = (Stage) mainGridPane.getScene().getWindow();
        primaryStage.setScene(profileScene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();

    }

    // changes Scene to Sign Up Scene, where you can sign up new Users
    @FXML
    public void changeSceneToSignUpWindow(){

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/signUpWindow.fxml"));
        Scene signUpScene;


        try{
            signUpScene = new Scene(fxmlLoader.load(), 400, 400);
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
