package sample;

import java.io.*;
import java.util.ArrayList;

  // here the Users and their Transactions are saved and loaded from .dat files
public class SaveAndLoad {


    // saves new User to .dat file
    public void saveUserToTextFile(User user){

        File userInfoFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\userList.dat");

        if(user == null){
            return;
        }

        try(ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userInfoFile, true)))){
            objOut.writeObject(user);

        } catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    // loads UserList from .dat file
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

    // saves (adds) a new Transaction to .dat file, there is a separate file for each User.
    // if there is no file then a new file is created
    public void saveNewTransaction(Transaction newTransaction, User theUser){

        if(theUser == null){
            return;
        }

        File userTransactionFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\"
            + theUser.getFirstName() + theUser.getLastName() + "TransactionList.dat");


        try(ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userTransactionFile, true)))){

            objOut.writeObject(newTransaction);
        } catch (IOException e){

            System.out.println("IO Exception");
            e.printStackTrace();
        }
    }

    // loads needed Transaction list
    public ArrayList<Transaction> loadTransactionList(User theUser){

        File userTransactionFile;
        boolean fileExists;


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
