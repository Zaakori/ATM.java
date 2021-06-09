package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveAndLoad {

    public boolean saveUserToTextFile(User user){

        if(user == null){
            return false;
        }

        try(BufferedWriter br = new BufferedWriter(new FileWriter("textfiles/userList.txt"))){

            br.write(user.getFirstName() + " " + user.getLastName() + " " + user.getPinCode());

            return true;
        } catch (IOException e){
            return false;
        }
    }



}
