package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.controllers.MainController;

import java.util.ArrayList;


public class ATM {

    private ArrayList<User> userList;
    private SaveAndLoad saveAndLoad;
    private ObservableList<ObservableTransaction> signedInUserObservableList;

    public ATM() {
        this.saveAndLoad = new SaveAndLoad();
        this.signedInUserObservableList = FXCollections.observableArrayList();

        if(saveAndLoad.loadUserList() != null){

            this.userList = saveAndLoad.loadUserList();

            // for debugging
            for(User u : userList){
                System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getPassword());
            }
        } else {
            this.userList = new ArrayList<>();
        }

        // loads every Users personal Transaction list from a file (if such exists)
        for(User u : userList){

            if(saveAndLoad.loadTransactionList(u) != null){
                u.setTransactionList(saveAndLoad.loadTransactionList(u));

                Transaction lastTransaction = u.getTransactionList().get(u.getTransactionList().size() - 1);

                u.setAmountOfTransactionsMade(lastTransaction.getTransactionNumber());
                u.setCurrentMoney(lastTransaction.getMoneyLeft());
            }
        }
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setObservableList(){
        for(Transaction t :  MainController.getSignedInUser().getTransactionList()){

            signedInUserObservableList.add(new ObservableTransaction(t.getTransactionNumber(), t.getSenderFullName(),
                    t.getReceiverFullName(), t.getMoneyAmountTransfered(), t.getDateAndTime(), t.getMoneyLeft()));

        }
    }

    public ObservableList<ObservableTransaction> getSignedInUserObservableList(){
        return signedInUserObservableList;
    }

    public User findAndReturnUser(String inputFirstName, String inputLastName){

        if(inputFirstName.trim().equals("") || inputLastName.trim().equals("")){
            return null;
        }

        for(User u : userList){

            if(u.getFirstName().equals(inputFirstName) && u.getLastName().equals(inputLastName)){
                System.out.println("found the user! its: " + u.getFirstName() + " " + u.getLastName());
                return u;
            }
        }

        System.out.println("did not find such user");
        return null;
    }

    public boolean addNewUser(User newUser){

        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();
        String password = newUser.getPassword();

        for(User u : userList){

            if((u.getFirstName().equals(firstName)) && (u.getLastName().equals(lastName))){
                System.out.println("User with such a name already exists.");
                return false;
            }
        }

        if(password.length() < 5){
            System.out.println("the password is too short");
            return false;
        }

        if((firstName.equals("")) || (lastName.equals(""))){
            System.out.println("First name or Last name is missing.");
            return false;
        }

        if(!(stringValidation(firstName)) || !(stringValidation(lastName))){
            System.out.println("You can have only letters in your name.");
            return false;
        }

        userList.add(newUser);
        saveAndLoad.saveUserToTextFile(newUser);

        if(!userList.isEmpty()){
            System.out.println();
            for(User u : userList){
                System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getPassword());
            }
        }

        return true;
    }

    public void addNewTransaction(Transaction newTransaction, User user){

        // save Transaction Object to User
        user.addTransaction(newTransaction);

        // save Transaction to .dat file
        saveAndLoad.saveNewTransaction(newTransaction, user);

    }

    public void addNewTransaction(Transaction newTransactionForSender, Transaction newTransactionForReceiver, User moneySender, User moneyReceiver){

        addNewTransaction(newTransactionForSender, moneySender);
        addNewTransaction(newTransactionForReceiver, moneyReceiver);
    }

    private boolean stringValidation(String userInput){

       String lowerInput = userInput.toLowerCase();

       char[] charArray = lowerInput.toCharArray();

       for(int i = 0; i < charArray.length; i++){

          char ch = charArray[i];

           if(!(ch >= 'a' && ch <= 'z')){
               return false;
           }
       }

       return true;

    }





}
