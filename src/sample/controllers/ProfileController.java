package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.IOException;


// controller for the main Profile Window
public class ProfileController {

    @FXML
    private GridPane profileGridPane;


    @FXML
    public void changeSceneToPutInMoneyWindow(){

        changeScene("putInMoneyWindow.fxml", "Profile - Put In Money", 400, 400);
    }

    @FXML
    public void changeSceneToTakeOutMoneyWindow(){

        changeScene("takeOutMoneyWindow.fxml", "Profile - Take Out Money", 400, 400);
    }

    @FXML
    public void changeSceneToTransferMoneyWindow(){

        changeScene("transferMoneyWindow.fxml", "Profile - Transfer Money", 400, 400);
    }

    @FXML
    public void changeSceneToCheckBalanceWindow(){

        changeScene("checkBalanceWindow.fxml", "Profile - Check Balance", 400, 400);
    }

    @FXML
    public void changeSceneToTransferHistoryWindow(){

        changeScene("transferHistoryWindow.fxml", "Profile - Transfer History", 700, 400);
    }

    @FXML
    public void changeSceneToMainWindow(){

        changeScene("mainWindow.fxml", "ATM interface", 400, 400);
    }

    // changes the Scene to the Scene that is specified in parameters
    @FXML
    public void changeScene(String fxmlFile, String windowTitle, int horizontal, int vertical){

        Scene scene;


        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/" + fxmlFile)), horizontal, vertical);
        } catch(IOException e){
            System.out.println("Could not load Window.");
            e.printStackTrace();
            return;
        }

        Stage primaryStage = (Stage) profileGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle(windowTitle);
        primaryStage.show();
    }
}
