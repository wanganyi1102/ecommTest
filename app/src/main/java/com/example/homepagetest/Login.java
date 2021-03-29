package com.example.homepagetest;
import java.io.*;

public class Login {
    final static String ACCOUNTCSV = "C:\\Users\\Anyi\\Desktop\\software eng\\accountDatabase.csv";

    static enum columns{
        PhoneEmail, Password;
    }

    public static boolean checkExist(String phoneEmail){ //check if phoneEmail exists in database
        try{
            System.out.println("hello");
            BufferedReader fileReader = new BufferedReader(new FileReader(ACCOUNTCSV));
            System.out.println("hi");
            fileReader.readLine();
            String line = "";
            while ((line = fileReader.readLine()) != null){
                String[] tokens = line.split(",",-1);
                if(tokens[columns.PhoneEmail.ordinal()].compareTo(phoneEmail) == 0){
                    return true;
                }
            }
            fileReader.close();
        }
        catch(Exception e){
            System.out.println("Error in csv reader");
            return false;
        }
        return false;
    }

    public static boolean verifyLogin(String phoneEmail, String password){ //verify that phoneEmail and password match
        if(checkExist(phoneEmail)){
            try{
                BufferedReader fileReader = new BufferedReader(new FileReader(ACCOUNTCSV));
                fileReader.readLine();
                String line = "";
                while ((line = fileReader.readLine()) != null){
                    String[] tokens = line.split(",",-1);
                    if(tokens[columns.PhoneEmail.ordinal()].compareTo(phoneEmail) == 0
                            && tokens[columns.Password.ordinal()].compareTo(password) == 0){ //if both phoneEmail and password match
                        return true;
                    }
                }
                fileReader.close();
            }
            catch (Exception e){
                System.out.println("Error in csv reader");
                return false;
            }
        }
        return false;
    }
}
