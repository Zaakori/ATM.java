package sample.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.ObservableTransaction;
import sample.Transaction;
import sample.User;

import java.io.IOException;

public class TransferHistoryController {

    @FXML
    private GridPane transferHistoryGridPane;
    @FXML
    private TableView tableView;
    private ObservableList<ObservableTransaction> observableList = MainController.getAtm().getSignedInUserObservableList();


    public void initialize(){

        if(MainController.getSignedInUser().getTransactionList() != null){
                tableView.setItems(observableList);
        }
    }



    // changes Scene to Profile Page (so, goes back)
    @FXML
    public void changeScene(){

        Scene scene = null;
        try{
            scene = new Scene(FXMLLoader.load(getClass().getResource("fxml/profileWindow.fxml")), 400, 400);
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
