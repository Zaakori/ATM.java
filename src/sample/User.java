package sample;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

    private String firstName;
    private String lastName;
    private String password;
    private double currentMoney = 500;                 // GET RID OF THAT later
    private ArrayList<Transaction> transactionList;
    private int amountOfTransactionsMade = 1;

    private static final long serialVersionUID = 1L;

    public User() {
    }

    public User(String firstName, String lastName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.transactionList = new ArrayList<>();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

        if((otherUser.getFirstName().equals(firstName)) && (otherUser.getLastName().equals(lastName)) && (otherUser.getPassword().equals(password))){
            return true;
        }

        return false;
    }
}
