package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.controllers.MainController;
import java.util.ArrayList;


  // class that does some User management
public class ATM {

    private final ArrayList<User> userList;
    private final SaveAndLoad saveAndLoad;
    private final ObservableList<ObservableTransaction> signedInUserObservableList;


    // this ugly looking constructor loads UserList from .dat file and loads each
    // separate Users TransactionList from another .dat file
    public ATM() {
        this.saveAndLoad = new SaveAndLoad();
        this.signedInUserObservableList = FXCollections.observableArrayList();


        if(saveAndLoad.loadUserList() != null){

            this.userList = saveAndLoad.loadUserList();

            // for easier debugging
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

    // makes an ObservableList that can be viewed in TableView, it
    // just copies the signed in Users TransactionList
    public void setObservableList(){

        if(!signedInUserObservableList.isEmpty()){
            signedInUserObservableList.clear();
        }

        for(Transaction t :  MainController.getSignedInUser().getTransactionList()){

            signedInUserObservableList.add(new ObservableTransaction(t.getTransactionNumber(), t.getSenderFullName(),
                    t.getReceiverFullName(), t.getMoneyAmountTransfered(), t.getDateAndTime(), t.getMoneyLeft()));

        }
    }

    public ObservableList<ObservableTransaction> getSignedInUserObservableList(){
        return signedInUserObservableList;
    }

    // finds the specified User in UserList, if it´s there then returns it
    public User findAndReturnUser(String inputFirstName, String inputLastName){

        if(inputFirstName.trim().equals("") || inputLastName.trim().equals("")){
            return null;
        }

        for(User u : userList){

            if(u.getFirstName().equals(inputFirstName) && u.getLastName().equals(inputLastName)){
                return u;
            }
        }

        return null;
    }

    // adds new User to ATM's UserList and saves it to .dat file
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

    // adds a new Transaction to User object and saves it to Users .dat file too
    public void addNewTransaction(Transaction newTransaction, User user){

        user.addTransaction(newTransaction);
        saveAndLoad.saveNewTransaction(newTransaction, user);
    }

    // overloaded method that makes two Transactions at the same time (one for sender and one for receiver)
    public void addNewTransaction(Transaction newTransactionForSender, Transaction newTransactionForReceiver, User moneySender, User moneyReceiver){

        addNewTransaction(newTransactionForSender, moneySender);
        addNewTransaction(newTransactionForReceiver, moneyReceiver);
    }

    // validates that the Users name contains only letters
    private boolean stringValidation(String userInput){

       String lowerInput = userInput.toLowerCase();
       char[] charArray = lowerInput.toCharArray();


        for (char ch : charArray) {

            if (!(ch >= 'a' && ch <= 'z')) {
                return false;
            }
        }

       return true;
    }
}
