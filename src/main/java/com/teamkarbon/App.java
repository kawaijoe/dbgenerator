package com.teamkarbon;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * App.java
 */
public class App {

    public static void main(String[] args) {


        // The name of the file to open.
        String fileName = "res/DBMokemon.txt";
        final String DB = "ItemTransaction";

        String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        // This will reference one line at a time
        String line = null;

        try {

            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            String string;

            while((line = bufferedReader.readLine()) != null) {
                string = "";
                line = line.replaceAll("\t", "\n");
                String[] stringArray = line.split(System.getProperty("line.separator"));

                string = "INSERT INTO " + DB + " VALUES(";
                for(int i = 0; i < stringArray.length; i++) {
                    if((containsNum(stringArray[i], number) && !stringArray[i].contains("-")) || stringArray[i].equals("NULL")) {
                        // Contains a number
                        if(i == stringArray.length -1)
                            string += stringArray[i] + ")";
                        else
                            string += stringArray[i] + ", ";

                    } else {
                        // Does not contain number
                        if(i == stringArray.length -1)
                            string += "\'" + stringArray[i] + "\')";
                        else
                            string += "\'" + stringArray[i] + "\', ";
                    }

                }

                System.out.println(string);
            }

            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch(IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }
    }

    public static boolean containsNum(String inputStr, String[] items) {
        for(int i =0; i < items.length; i++) {
            if(inputStr.contains(items[i])) {
                return true;
            }
        }
        return false;
    }
}
