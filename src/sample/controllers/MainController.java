package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ATM;
import sample.User;

import java.io.IOException;

public class MainController {

    // WHERE STOPPED - in TableView each time you go there another copy of Transactions is added, gotta clear that up

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
        this.atm = new ATM();
        this.signedInUser = new User();
    }

    public static ATM getAtm() {
        return atm;
    }

    public static User getSignedInUser(){
        return signedInUser;
    }

    public static User findUser(String inputFirstName, String inputLastName){

    return atm.findAndReturnUser(inputFirstName, inputLastName);

    }

    @FXML
    public void signIn() throws IOException{

        String firstName = firstNameTextField.getText().trim();
        String lastName = lastNameTextField.getText().trim();
        String password = passwordField.getText().trim();

        User inputUser = new User(firstName, lastName, password);
        boolean exitWhileLoop = true;


        while(exitWhileLoop){

            for(User u : atm.getUserList()){

                if(u.equals(inputUser)){
                    signedInUser = u;
                    atm.setObservableList();
                    changeSceneToProfileWindow();
                    return;
                }
            }
            exitWhileLoop = false;
        }
    }


    @FXML
    public void changeSceneToProfileWindow() throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/profileWindow.fxml"));

        Scene profileScene = null;
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

    @FXML
    public void changeSceneToSignUpWindow() throws IOException{

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("fxml/signUpWindow.fxml"));

        Scene signUpScene = null;
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
