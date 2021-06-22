package sample;

import java.util.ArrayList;


public class ATM {

    private ArrayList<User> userList;
    private SaveAndLoad saveAndLoad;

    public ATM() {
        this.saveAndLoad = new SaveAndLoad();

        if(saveAndLoad.loadUserList() != null){

            this.userList = saveAndLoad.loadUserList();

            // for debugging
            for(User u : userList){
                System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getPinCode());
            }
        } else {
            this.userList = new ArrayList<>();
        }

    }
    public ArrayList<User> getUserList() {
        return userList;
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

        int pinCode = newUser.getPinCode();
        String firstName = newUser.getFirstName();
        String lastName = newUser.getLastName();

        if((pinCode < 1000) || (pinCode > 9999)){
            System.out.println("PIN must consist of 4 numbers, try again.");
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
                System.out.println(u.getFirstName() + " " + u.getLastName() + " " + u.getPinCode());
            }
        }



        return true;
    }

    public void addNewFullTransaction(Transaction newTransactionForSender, Transaction newTransactionForReceiver, User moneySender, User moneyReceiver){

    // save new Full Transaction Object to User
    moneySender.addTransaction(newTransactionForSender);
    moneyReceiver.addTransaction(newTransactionForReceiver);

    // save new Full Transaction to .dat file
    saveAndLoad.saveNewTransaction(newTransactionForSender, moneySender);
    saveAndLoad.saveNewTransaction(newTransactionForReceiver, moneyReceiver);

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
