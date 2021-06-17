package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {

    @FXML
    private GridPane profileGridPane;


    @FXML
    public void changeSceneToPutInMoneyWindow(){

        changeScene("putInMoneyWindow.fxml", "Profile - Put In Money");
    }

    @FXML
    public void changeSceneToTakeOutMoneyWindow(){

        changeScene("takeOutMoneyWindow.fxml", "Profile - Take Out Money");
    }

    @FXML
    public void changeSceneToCheckBalanceWindow(){

        changeScene("checkBalanceWindow.fxml", "Profile - Check Balance");

    }

    @FXML
    public void changeSceneToMainWindow(){

        changeScene("mainWindow.fxml", "ATM interface");

    }

    @FXML
    public void changeScene(String fxmlFile, String windowTitle){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/" + fxmlFile)));
        } catch(IOException e){
            System.out.println("Could not load Window.");
            return;
        }

        Stage primaryStage = (Stage) profileGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle(windowTitle);
        primaryStage.show();
    }
}
