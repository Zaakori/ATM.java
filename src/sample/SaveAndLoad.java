package sample;

import java.io.*;
import java.util.ArrayList;

public class SaveAndLoad {

    public boolean saveUserToTextFile(User user){

        if(user == null){
            return false;
        }

        File userInfoFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\textfiles\\userList.txt");

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(userInfoFile))){

            bw.write(user.getFirstName() + " " + user.getLastName() + " " + user.getPinCode());

            return true;
        } catch (IOException e){
            return false;
        }
    }

    public ArrayList<User> loadUserList(){

        ArrayList<User> userList = new ArrayList<>();
        File userInfoFile = new File("C:\\Users\\User\\IdeaProjects\\ATMproject\\src\\sample\\textfiles\\userList.txt");


        try(BufferedReader br = new BufferedReader(new FileReader(userInfoFile))){

           String userLine = br.readLine();
           String[] userInfoArray = userLine.split(" ");

           User loadUser = new User();
           loadUser.setFirstName(userInfoArray[0]);
           loadUser.setLastName(userInfoArray[1]);
           loadUser.setPinCode(Integer.parseInt(userInfoArray[2]));

           userList.add(loadUser);

        } catch (IOException e){
            return null;
        }

        return null;
    }



}
