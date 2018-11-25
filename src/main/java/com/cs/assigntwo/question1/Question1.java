package com.cs.assigntwo.question1;

import com.cs.assigntwo.dependencies.csScanner;
import com.cs.assigntwo.dependencies.csUtility;

public class Question1 {

    private csScanner scn;
    private Student[] students;

    /**
     * Constructor
     */
    public Question1() {
        this.scn = new csScanner();
    }

    /**
     * Create students
     */
    public void createStudents(){
        System.out.println("creating new students...");
    }

    /**
     * Update a student
     */
    public void updateStudent(){
        System.out.println("updating new students...");

    }

    /**
     * deletes a student
     */
    public void deleteStudent(){
        System.out.println("deleting new students...");

    }

    /**
     * get table of results
     */
    public void getResult() {
        System.out.println("getting results...");

    }


    /**
     * get table of results by Top score
     */
    public void getResultByTopScore() {
        System.out.println("getting result top scores...");

    }

    /**
     * main function
     * @param args
     */
    public void main(String[] args){

        char option;

        while(true) {

            csUtility.clearScreen();

            System.out.println("=======================================");
            System.out.println("OPTIONS");
            System.out.println("=======================================");
            System.out.println("1 - Create New Record");
            System.out.println("2 - Update Record");
            System.out.println("3 - Delete Record");
            System.out.println("4 - Get Top Scores");
            System.out.println("5 - Get Students arranged by Name");
            System.out.println("=======================================");
            System.out.println("0 - Exit the Application");
            System.out.println("=======================================");
            System.out.println("");

            option = scn.inputChar("Please enter option:");
            switch (option){
                case '1':
                    createStudents();
                    break;
                case '2':
                    updateStudent();
                    break;
                case '3':
                    deleteStudent();
                    break;
                case '4':
                    getResultByTopScore();
                    break;
                case '5':
                    getResult();
                    break;
                case '0':
                    return;
                default:
                    System.out.println("Invalid Entry please try again");
            }
        }
    }
}
