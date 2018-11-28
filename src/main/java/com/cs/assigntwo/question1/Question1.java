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
        students = new Student[0]; // declare array size of zero
    }

    public void printTable(Student[] students){
        int i;
        System.out.println("\n");
        System.out.println("Results are:");
        System.out.println("===========================================================");
        for(i = 0; i < students.length; i++){
            System.out.println((i + 1) + ".\t\t" + students[i].getLastName() + " " + students[i].getFirstName() + "\t\t| ID = " + students[i].getStudentID() + "\t\t| Marks = " + students[i].getOverallMark());
        }
        System.out.println("===========================================================");
    }


    public int searchForStudentsIndex(){
        int i;
        int option;

        String search = scn.inputStr("Search students by Last Name or Id:");
        Student[] results = Student.searchArray(students, search);

        printTable(results);

        option = scn.inputInt("Select the index you would like to update:") - 1;

        if(option > results.length){
            return -1;
        }

        return option;
    }



    public Student generateStudent(){
        System.out.println("\n\n");
        System.out.println("Please enter the following information: ");
        System.out.println("============================================");

        long studentID = students.length;
        String title = scn.inputStr("1. Title:");
        String firstName = scn.inputStr("2. First Name:");
        String lastName = scn.inputStr("3. Last Name");
        int dateOfBirth_day = scn.inputInt("4. Date of Birth (Day)");
        int dateOfBirth_month = scn.inputInt("5. Date of Birth (Month)");
        int dateOfBirth_year = scn.inputInt("6. Date of Birth (Year)");

        return new Student(studentID, title, firstName, lastName, dateOfBirth_day, dateOfBirth_month, dateOfBirth_year);
    }

    public boolean checkGeneratedStudent(Student student){
        System.out.println("\n\n");
        System.out.println("Please confirm that the following information is correct: ");
        System.out.println("============================================");

        System.out.println("1. Title: " + student.getTitle());
        System.out.println("2. First Name: " + student.getFirstName());
        System.out.println("3. Last Name: " + student.getLastName());
        System.out.println("4. Date of Birth: " + student.getDateOfBirth());
        System.out.println("\n");
        System.out.println("============================================");

        return scn.inputBool("Is this correct?", 'y');
    }

    /**
     * Create students
     */
    public void createStudents(){
        Student temp = generateStudent();

        if(checkGeneratedStudent(temp)){
            Student[] tempArray = new Student[students.length + 1];

            for(int i = 0; i < this.students.length; i++){
                tempArray[i] = this.students[i];
            }
            tempArray[tempArray.length - 1] = temp;
            this.students = tempArray;
        } else {
            System.out.println("Operation Cancelled");
        }
        return;
    }

    /**
     * Update a student
     */
    public void updateStudent(){
        Student temp;

        int index = searchForStudentsIndex();
        if(index == -1){
            if(scn.inputBool("Invalid Response. Would you like to try again?", 'y')){
                updateStudent();
            }
            return;
        } else {
            temp = generateStudent();
            if(checkGeneratedStudent(temp)){
                this.students[index] = temp;
            }
        }
    }

    /**
     * deletes a student
     */
    public void deleteStudent(){
        Student[] temp;
        int i, j;

        int index = searchForStudentsIndex();
        if(index == -1){
            if(scn.inputBool("Invalid Response. Would you like to try again?", 'y')){
                deleteStudent();
            }
            return;
        } else {
            temp = this.students;
            this.students = new Student[students.length - 1];

            j = 0;
            for(i = 0; i < temp.length; i++){
               if(i != index){
                   this.students[j] = temp[i];
                   j++;
               }
            }
        }

    }

    /**
     * get table of results by Id
     */
    public void getStudents() {
        printTable(Student.sortById(students));
        scn.inputStr("\n\nPress any key to continue...");
    }

    /**
     * get table of results
     */
    public void getStudentsByName() {
        printTable(Student.sortByLastName(students));
        scn.inputStr("\n\nPress any key to continue...");
    }

    /**
     * get table of results by Top score
     */
    public void getResultByTopScore() {
        printTable(Student.top2HighestMark(students));
        scn.inputStr("\n\nPress any key to continue...");
    }

    public void mockStudents() {
        students = new Student[5];

        students[0] = new Student(1, "Mr.", "Jack", "Flan", 12, 10, 1989);
        students[1] = new Student(2, "Ms.", "Sarah", "Foster", 12, 12, 1998);
        students[2] = new Student(3, "Mr.", "John", "White", 12, 10, 1997);
        students[3] = new Student(4, "Ms.", "Juliette", "Brown", 12, 12, 1982);
        students[4] = new Student(5, "Mr.", "Mark", "Tan", 12, 12, 1999);

        return;
    }

    /**
     * main function
     * @param args
     */
    public void main(String[] args){

        char option;

        mockStudents();
        System.out.println("Students are: " + students.length);

        while(true) {

            csUtility.clearScreen();

            System.out.println("=======================================");
            System.out.println("OPTIONS");
            System.out.println("=======================================");
            System.out.println("1 - List Records");
            System.out.println("2 - List Records By Name");
            System.out.println("3 - List Records By Top Score");
            System.out.println("4 - Create New Record");
            System.out.println("5 - Update Record");
            System.out.println("6 - Delete Record");
            System.out.println("=======================================");
            System.out.println("0 - Exit the Application");
            System.out.println("=======================================");
            System.out.println("");

            option = scn.inputChar("Please enter option:");
            switch (option){
                case '1':
                    getStudents();
                    break;
                case '2':
                    getStudentsByName();
                    break;
                case '3':
                    getResultByTopScore();
                    break;
                case '4':
                    createStudents();
                    break;
                case '5':
                    updateStudent();
                    break;
                case '6':
                    deleteStudent();
                    break;
                default:
                    System.out.println("Invalid Entry please try again");
            }
        }
    }
}
