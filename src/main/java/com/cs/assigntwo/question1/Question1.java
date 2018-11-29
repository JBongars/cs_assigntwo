package com.cs.assigntwo.question1;

import com.cs.assigntwo.dependencies.csScanner;
import com.cs.assigntwo.dependencies.csUtility;

/**
 * Interface Application
 * Attempt to decouple pure Student logic from interface
 */
public class Question1 {


    /*
    1. Quit (exit the program)

    2. Add (to the array) all information about a student (except the overall mark and the grade) by
    reading it from the keyboard and determine the student’s grade

    3. Output from the array the details (all information including the overall mark and the grade) of
    all students currently held in the array

    4. Compute and output the average overall mark for students currently held in the array

    5. Determine and display how many students obtained an overall mark equal to or above the
    average overall mark and how many obtained an overall mark below the average overall mark

    6. Display the distribution of grades (i.e., the number of HDs, Ds etc) awarded

    7. Given a student number (ID), view all details of the student with that number. If the student is
    not found in the array, an appropriate error message is to be displayed

    8. Given a student’s name (both surname and given name – ignoring case), view all details of that
    student. If the student is not found in the array, an appropriate error message is to be displayed
     */


    private csScanner scn;
    private Student[] students;

    /**
     * @title Constructor
     */
    public Question1() {
        this.scn = new csScanner();
        students = new Student[0]; // declare array size of zero
    }

    /**
     * Prints a table of the students, used internally
     * @param students
     */
    private void printTable(Student[] students){
        int i;
        System.out.println("\n\n\n\n");
        System.out.println("Results are:");
        System.out.println("===========================================================");
        for(i = 0; i < students.length; i++){
            System.out.println((i + 1) + ".\t\t" + students[i].getLastName() + " " + students[i].getFirstName() + "\t\t| ID = " + students[i].getStudentID() + "\t\t| Marks = " + (short) students[i].getOverallMark() + "\t\t| Grade = " + students[i].getFinalGrade());
        }
        System.out.println("===========================================================");
    }

    /**
     * Searches students via name or id, translates presentational index to actual index of students
     * Provides Interface for user to select specific student
     * @return index of student relative to this.students
     */
    private int searchForStudentsIndex(){
        int i;
        int option;

        String search = scn.inputStr("Search students by Last Name or Id:");
        Student[] results = Student.searchArray(students, search);

        if(results.length == 0){
            System.out.println("No results found!\n\n");
            return -1;
        }

        printTable(results);

        option = scn.inputInt("Select the index you would like to update:") - 1;

        if(option > results.length){
            System.out.println("\nInvalid character entered");
            return -1;
        }

        for(i = 0; i < students.length; i++){
            if(students[i].getStudentID() == results[option].getStudentID()){
                return i;
            }
        }

        System.out.println("\nSomething went wrong...");
        return -1;
    }


    /**
     * Interface to create a new student and pass student object on
     * Used in various applications of student creation and student update
     * @return student
     */
    public Student generateStudent(){
        System.out.println("\n\n\n\n");
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

    /**
     * Provides user interface to check whether a student's information is correct
     * @param student Student object
     * @return boolean
     */
    public boolean checkGeneratedStudent(Student student){
        System.out.println("\n\n\n\n");
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
     * @param student selected student
     */
    public void showMarksBreakdown(Student student){
        Assignment[] assignments = student.getAssignments();
        PracticalWork[] practicalWorks = student.getPracticalWorks();
        FinalExam finalExam = student.getFinalExam();
        int i;

        csUtility.clearScreen();
        System.out.println("=======================================================");
        System.out.println("Marks Breakdown for " + student.getFullName() + " - ID" + student.getStudentID());
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
     * Create a new student
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

    /**
     * get table of results by ID
     */
    public void getResultByID(){
        printTable(Student.sortById(students));
        scn.inputStr("\n\nPress any key to continue");
    }

    /**
     * get table of total marks and average distribution
     */
    public void getAverageMarks(){
        String[] marks = {"HD", "D", "C", "P", "N"};
        int[] tally = {0, 0, 0, 0, 0};
        int i, j;
        String currentMarks;

        int average = 0;

        for(i = 0; i < students.length; i++) {
            average += students[i].getOverallMark();
            currentMarks = students[i].getFinalGrade();
            for (j = 0; j < marks.length; j++) {
                if (currentMarks == marks[j]) {
                    tally[j]++;
                    break;
                }
            }
        }
        average = average / students.length;

        System.out.println("=======================================================");
        System.out.println("");
        System.out.println("=======================================================");
        for(i = 0; i < marks.length; i++){
            System.out.println(i + ". " + marks[i] + " => " + tally[i]);
        }
        System.out.println("=======================================================");
        System.out.println("AVERAGE SCORE: " + average);
        System.out.println("=======================================================");

        scn.inputStr("\n\nPress any key to continue...");
    }

    /**
     * mock a random score from 0 to 100
     * @return
     */
    public int mockRandomScore(){
        return (int) (Math.random() * 100);
    }

    /**
     * Mock students
     */
    public void mockStudents() {
        int i;
        students = new Student[15];

        students[0] = new Student(1, "Mr.", "Jack", "Flan", 12, 10, 1989);
        students[1] = new Student(2, "Ms.", "Sarah", "Foster", 1, 12, 1998);
        students[2] = new Student(3, "Mr.", "John", "White", 5, 10, 1997);
        students[3] = new Student(4, "Ms.", "Juliette", "Brown", 12, 12, 1982);
        students[4] = new Student(5, "Mr.", "Mark", "Cake", 12, 12, 1999);
        students[5] = new Student(6, "Mr.", "Matthew", "Black", 19, 3, 1989);
        students[6] = new Student(7, "Ms.", "Emily", "Burger", 7, 12, 1998);
        students[7] = new Student(8, "Mr.", "Guy", "Mew", 26, 4, 1997);
        students[8] = new Student(9, "Ms.", "Sophia", "Tan", 31, 5, 1982);
        students[9] = new Student(11, "Mr.", "Pierre", "Can", 14, 12, 1999);
        students[10] = new Student(12, "Mr.", "Billy", "Frawn", 15, 1, 1989);
        students[11] = new Student(13, "Ms.", "Amanda", "Periperi", 16, 9, 1998);
        students[12] = new Student(14, "Mr.", "Carl", "Candy", 12, 10, 1997);
        students[13] = new Student(15, "Ms.", "Bowsette", "Savory", 21, 12, 1982);
        students[14] = new Student(16, "Mr.", "William", "Spicy", 23, 5, 1999);

        for(i = 0; i < students.length; i++){
            //some of these method have the potential of generating an Exception error
            try {
                students[i].createAssignemnt(mockRandomScore());
                students[i].createAssignemnt(mockRandomScore());
                students[i].createAssignemnt(mockRandomScore());
                students[i].createPracticalWork(mockRandomScore());
                students[i].createPracticalWork(mockRandomScore());
                students[i].createPracticalWork(mockRandomScore());
                students[i].createExam(mockRandomScore());
            } catch (Exception err){
                System.out.println("Unknown error, please check Student class...");
            }
        }
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
            System.out.println("4 - List Records By ID");
            System.out.println("5 - Get Marks Averages");
            System.out.println("6 - Create New Record");
            System.out.println("7 - Score Further Options");
            System.out.println("8 - Update Record Details");
            System.out.println("9 - Delete Record");
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
                    getResultByID();
                    break;
                case '5':
                    getAverageMarks();
                    break;
                case '6':
                    createStudents();
                    break;
                case '7':
                    updateStudentMarks();
                    break;
                case '8':
                    updateStudent();
                    break;
                case '9':
                    deleteStudent();
                    break;
                case '0':
                    return;
                default:
                    System.out.println("Invalid Entry please try again");
            }
        }
    }
}
