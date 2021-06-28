package sample;

import javafx.collections.ObservableList;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

    private String firstName;
    private String lastName;
    private int pinCode;
    private double currentMoney = 500;                 // GET RID OF THAT later
    private ArrayList<Transaction> transactionList;
    private ObservableList<Transaction> observableTransactionList;
    private int amountOfTransactionsMade = 1;

    private long serialVersionUID = 1L;

    public User() {
    }

    public User(String firstName, String lastName, int pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pinCode = pinCode;
        this.transactionList = new ArrayList<>();
//        this.observableTransactionList = new
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    public double getCurrentMoney() {
        return currentMoney;
    }

    public void setCurrentMoney(double currentMoney) {
        this.currentMoney = currentMoney;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

//    public ObservableList<Transaction> getObservableTransactionList(){
//
//    }

    public void setTransactionList(ArrayList<Transaction> transactionList){

        if(transactionList == null){
            return;
        }

        this.transactionList = transactionList;

    }

    public int getAmountOfTransactionsMade() {

        int amountOfTransactions = amountOfTransactionsMade;

        amountOfTransactionsMade++;

        return amountOfTransactions;
    }

    public void setAmountOfTransactionsMade(int amountOfTransactions){

        amountOfTransactions++;

        amountOfTransactionsMade = amountOfTransactions;

    }

    public void addTransaction(Transaction newTransaction){

        if(transactionList == null){
            transactionList = new ArrayList<>();
        }

        transactionList.add(newTransaction);

    }

    @Override
    public boolean equals(Object obj) {

        User otherUser = (User) obj;

        if((otherUser.getFirstName().equals(firstName)) && (otherUser.getLastName().equals(lastName)) && (otherUser.getPinCode() == pinCode)){
            return true;
        }

        return false;
    }
}
