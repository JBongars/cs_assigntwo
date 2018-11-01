package com.cs.assigntwo.dependencies;


import java.util.Scanner;

/**
 *
 */
public class csScanner {
    public Scanner scn = new Scanner(System.in);

    public String inputStr(String message) {
        System.out.println(message + ' ');
        return scn.nextLine();
    }

    public Integer inputInt(String message) {
        System.out.println(message + ' ');
        int result = scn.nextInt();
        scn.nextLine();
        return result;
    }

    public Float inputFloat(String message) {
        System.out.println(message + ' ');
        float result =  scn.nextFloat();
        scn.nextLine();
        return result;
    }

    public char inputChar(String message){
        String input;
        while(true) {
            input = inputStr(message);
            if(input.length() == 1) return input.toLowerCase().charAt(0);
            else {
                System.out.println("Invalid entry, please try again...");
            }
        }
    }

    public boolean inputBool(String message){
        return inputChar(message + " [y/n]") == 'y';
    }

    public boolean inputBool(String message, char defaultResponse){

        char input = ' ';

        if(defaultResponse != 'y' && defaultResponse != 'n'){
            throw new Error("defaultResponse must equal either 'y' or 'n'...");
        }
        message += defaultResponse == 'y' ? " [Y]/n" : " y/[N]";

        try {
            input = inputStr(message).toLowerCase().charAt(0);
        } catch (java.lang.StringIndexOutOfBoundsException err) {
            input = defaultResponse;
        } finally {
            return input == 'y';
        }
    }

}
