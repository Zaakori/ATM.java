package sample;

import java.io.Serializable;
import java.util.ArrayList;


  // a standard User class that holds all needed info
public class User implements Serializable{

    private String firstName;
    private String lastName;
    private String password;
    private double currentMoney = 500;
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


    // sets TransactionList if it has already been made (already saved to .dat file)
    public void setTransactionList(ArrayList<Transaction> transactionList){

        if(transactionList == null){
            return;
        }

        this.transactionList = transactionList;
    }

    // increments by one after every Transaction
    public int getAmountOfTransactionsMade() {

        int amountOfTransactions = amountOfTransactionsMade;

        amountOfTransactionsMade++;

        return amountOfTransactions;
    }

    // needed if some Transactions were made in previous session so the number of transactions is correct
    // no matter how many sessions have been made
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


    // this equals returns true if Users first name, last name and password are equal
    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof User))  {
            return false;
        }

        User otherUser = (User) obj;

        if((otherUser.getFirstName().equals(firstName)) && (otherUser.getLastName().equals(lastName)) && (otherUser.getPassword().equals(password))){
            return true;
        }

        return false;
    }
}
