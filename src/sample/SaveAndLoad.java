package sample;

import java.io.*;
import java.util.ArrayList;

public class SaveAndLoad {

    public boolean saveUserToTextFile(User user){

        if(user == null){
            return false;
        }

        File userInfoFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\userList.dat");

        try(ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(userInfoFile)))){
            objOut.writeObject(user);

            return true;
        } catch (IOException e){
            System.out.println("IO Exception");
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<User> loadUserList() {

        ArrayList<User> userList = new ArrayList<>();

        try (ObjectInputStream objIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream
                ("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\datafiles\\userList.dat")))) {

            boolean endOfFile = false;

            while (!endOfFile) {
                try {
                    User user = (User) objIn.readObject();

                    userList.add(user);
                } catch (EOFException e) {
                    endOfFile = true;
                }
            }

            return userList;
        } catch (IOException e) {
            System.out.println("IO Ex in loadUserList");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found Ex in loadUserList");
        }

        return null;
    }
}
