package sample.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.User;

import java.io.IOException;

public class TransferHistoryController {

    @FXML
    private GridPane transferHistoryGridPane;
    @FXML
    private TableView tableView;

    public void initialize(){

        tableView.setItems(MainController.getSignedInUser().getObservableTransactionList());

    }

    // changes Scene to Profile Page (so, goes back)
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")));
        } catch(IOException e){
            System.out.println("Could not load Window.");
            return;
        }

        Stage primaryStage = (Stage) transferHistoryGridPane.getScene().getWindow();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Profile Page");
        primaryStage.show();
    }
}
