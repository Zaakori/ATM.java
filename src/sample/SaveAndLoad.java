package sample;

import java.io.*;
import java.util.ArrayList;

public class SaveAndLoad {

    public boolean saveUserToTextFile(User user){

        if(user == null){
            return false;
        }

        File userInfoFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\userList.dat");

        try(ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userInfoFile, true)))){
            objOut.writeObject(user);

            return true;
        } catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> loadUserList(){

        try{
            ArrayList<User> userList = new ArrayList<>();
            File userInfoFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\userList.dat");
            FileInputStream fileStream = new FileInputStream(userInfoFile);
            ObjectInputStream objIn = null;


            while(fileStream.available() > 0){

                objIn = new ObjectInputStream(fileStream);
                User user = (User) objIn.readObject();
                userList.add(user);

            }

            if(objIn == null){
                return null;
            } else {
                objIn.close();
            }

            return userList;

        } catch (FileNotFoundException e){
            System.out.println("FileNotFound in loadUserList, so there is no .dat file or can´t find it.");
        } catch (Exception e){
            System.out.println("Exception in loadUserList");
            e.printStackTrace();
        }

        return null;
    }

    // saves (adds) a new transaction to a .txt file, there is a separate file for each User.
    // if there is no file the new file gets created
    public boolean saveNewTransaction(Transaction newTransaction, User theUser){

        System.out.println(newTransaction.toString());

        if((newTransaction == null) || (theUser == null)){
            return false;
        }

        File userTransactionFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\"
            + theUser.getFirstName() + theUser.getLastName() + "TransactionList.dat");


        try(ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userTransactionFile, true)))){
            objOut.writeObject(newTransaction);

            return true;
        } catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
            return false;
        }
    }

    // loads needed Transaction list to the needed User
    public ArrayList<Transaction> loadTransactionList(User theUser){

        File userTransactionFile = null;
        boolean fileExists = false;

        // checking, if the selected User even has made any Transactions a.k.a even has a TransactionList file
            userTransactionFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\"
                    + theUser.getFirstName() + theUser.getLastName() + "TransactionList.dat");

            fileExists = userTransactionFile.exists();


        if(fileExists){

            try{

                ArrayList<Transaction> personalTransactionList = new ArrayList<>();
                FileInputStream fileStream = new FileInputStream(userTransactionFile);
                ObjectInputStream objIn = null;


                while(fileStream.available() > 0){

                    objIn = new ObjectInputStream(fileStream);
                    Transaction transaction = (Transaction) objIn.readObject();
                    personalTransactionList.add(transaction);

                }

                if(objIn == null){
                    return null;
                } else {
                    objIn.close();
                }

                return personalTransactionList;

            } catch (IOException e) {
                System.out.println("something wrong in SaveAndLoad, loadTransactionList");
                e.printStackTrace();
            } catch (ClassNotFoundException c){
                System.out.println("in SaveAndLoad, loadTransactionList - ClassNotFoundException");
                c.printStackTrace();
            }
        }

        return null;
    }
}
