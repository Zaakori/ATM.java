package sample;

import java.util.ArrayList;


public class ATM {

    private ArrayList<User> userList;
    private SaveAndLoad saveAndLoad;

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ATM() {
        this.userList = new ArrayList<>();
        this.saveAndLoad = new SaveAndLoad();
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
        return true;
    }

    public boolean deleteUser(User userToDelete){

        if(userToDelete == null){
            System.out.println("No user chosen for deletion");
            return false;
        }

        if(!(userList.contains(userToDelete))){
            System.out.println("There is no such user in the system.");
            return false;
        }

        userList.remove(userToDelete);
        return true;
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
