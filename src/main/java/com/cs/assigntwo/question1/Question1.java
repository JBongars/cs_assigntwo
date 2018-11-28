package com.cs.assigntwo.question1;

import com.cs.assigntwo.dependencies.csScanner;
import com.cs.assigntwo.dependencies.csUtility;
import com.sun.org.apache.xerces.internal.util.ShadowedSymbolTable;
import com.sun.org.apache.xerces.internal.util.SymbolTable;

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
     * Prints student's marks breakdown
     */
    public void showMarksBreakdown(Student student){
        Assignment[] assignments = student.getAssignments();
        PracticalWork[] practicalWorks = student.getPracticalWorks();
        FinalExam finalExam = student.getFinalExam();
        int i;

        System.out.println("=======================================================");
        System.out.println("Marks Breakdown");
        System.out.println("=======================================================");
        System.out.println("1. Assignments");
        for(i = 0; i < assignments.length; i++){
            if(assignments[i] != null){
                System.out.println("\t-\t" + Integer.toString(assignments[i].getScore()));
            }
        }
        System.out.println("2. Practical Works");
        for(i = 0; i < practicalWorks.length; i++){
            if(practicalWorks[i] != null) {
                System.out.println("\t-\t" + Integer.toString(practicalWorks[i].getScore()));
            }
        }
        System.out.println("3. Final Exam");

        if(finalExam != null) {
            System.out.println("\t-\t" + Integer.toString(finalExam.getScore()));
        } else {
            System.out.println("\t-\tNO TEST");
        }
        System.out.println("=======================================================");
        System.out.println("TOTAL SCORE: " + student.getOverallMark());
        System.out.println("FINAL GRADE: " + student.getFinalGrade());
        System.out.println("=======================================================");
    }

    /**
     * Create students
     */
    public void createStudents(){
        Student temp = generateStudent();

        if(checkGeneratedStudent(temp)){
            students[students.length - 1] = temp;
        } else {
            System.out.println("Operation Cancelled");
        }
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
     * Updates a student's marks
     */
    public void updateStudentMarks(){

        boolean FLAG;
        int score;

        int index = searchForStudentsIndex();
        if(index == -1){
            if(scn.inputBool("Invalid Response. Would you like to try again?", 'y')){
                updateStudentMarks();
            }
            return;
        } else {
            do {
                csUtility.clearScreen();
                System.out.println("=========================================================");
                System.out.println("Mark Options");
                System.out.println("=========================================================");
                System.out.println("1. List marks breakdown");
                System.out.println("2. Add new Assignment");
                System.out.println("3. Add new Practical Work");
                System.out.println("4. Update exam score");
                System.out.println("5. Reset marks");
                System.out.println("=========================================================");
                System.out.println("0. Exit without saving changes");
                System.out.println("=========================================================");

                switch (scn.inputChar("Please enter an option...")){
                    case '1':
                        showMarksBreakdown(students[index]);
                        break;
                    case '2':
                        score = scn.inputInt("Please enter assignment score:");
                        try {
                            students[index].createAssignemnt(score);
                        } catch (Exception err) {
                            System.out.println("Number of Assignments already exceed the limit");
                        }
                        break;
                    case '3':
                        score = scn.inputInt("Please enter practical works score:");
                        try {
                            students[index].createPracticalWork(score);
                        } catch (Exception err) {
                            System.out.println("Number of Practical Works already exceed the limit");
                        }
                        break;
                    case '4':
                        score = scn.inputInt("Please enter final exam score:");
                        students[index].createExam(score);
                        break;
                    case '5':
                        students[index].resetMarks();
                        break;
                    case '0':
                        return;
                    default:
                }
            } while(scn.inputBool("Changes saved! Would you like to make another transaction?", 'y'));
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
            System.out.println("5 - Score Further Options");
            System.out.println("6 - Update Record Details");
            System.out.println("7 - Delete Record");
            System.out.println("=======================================");
            System.out.println("0 - Exit the Application");
            System.out.println("=======================================");

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
                    updateStudentMarks();
                    break;
                case '6':
                    updateStudent();
                    break;
                case '7':
                    deleteStudent();
                    break;
                default:
                    System.out.println("Invalid Entry please try again");
            }
        }
    }
}
