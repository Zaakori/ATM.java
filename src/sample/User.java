package sample;

import java.util.ArrayList;

public class User {

    private String firstName;
    private String lastName;
    private int pinCode;
    private double currentFunds;
    private ArrayList<Transaction> transactionList;
    private int amountOfTransactionsMade = 1;

    public User() {
    }

    public User(String firstName, String lastName, int pinCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pinCode = pinCode;
        this.transactionList = new ArrayList<Transaction>();
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

    public double getCurrentFunds() {
        return currentFunds;
    }

    public void setCurrentFunds(double currentFunds) {
        this.currentFunds = currentFunds;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(ArrayList<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public int getAmountOfTransactionsMade() {
        return amountOfTransactionsMade;
    }

    public void setAmountOfTransactionsMade(int amountOfTransactionsMade) {
        this.amountOfTransactionsMade = amountOfTransactionsMade;
    }
}
