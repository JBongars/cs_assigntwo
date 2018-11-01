package com.cs.assignone;

import com.cs.assignone.question1.Question1;
import com.cs.assignone.question2.Question2;

import com.cs.assignone.dependencies.csUtility;
import com.cs.assignone.dependencies.csScanner;

/**
 * Hello world!
 *
 */
public class App
{
    public static csScanner scn = new csScanner();

    public static Question1 question1 = new Question1();
    public static Question2 question2 = new Question2();

//    public static Object[] options = {new Question1(), new Question2()};


    public static void main( String[] args )
    {
        char option;
        boolean FLAG = false;

        do {

            System.out.println("=======================================");
            System.out.println("OPTIONS");
            System.out.println("=======================================");
            System.out.println("1 - Question 1");
            System.out.println("2 - Question 2");
            System.out.println("=======================================");

            do {
                FLAG = false;
                option = scn.inputChar("Please enter option:");
                switch (option){
                    case '1':
                        System.out.println("starting question 1");
                        question1.main(args);
                        break;
                    case '2':
                        System.out.println("starting question 2");
                        question2.main(args);
                        break;
                    default:
                        System.out.println("Invalid Entry please try again");
                        FLAG = true;
                }
            } while(FLAG);

            csUtility.clearScreen();
            System.out.println("End of Program...");

        } while(scn.inputBool("Would you like to select a different option?", 'y'));



    }
}
