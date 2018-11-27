package com.cs.assigntwo.question1;

import com.cs.assigntwo.dependencies.csArray;
import sun.misc.Regexp;


public class Student {
    /*
    (a) Title of the student (eg, Mr, Miss, Ms, Mrs etc)
    (b) A first name (given name)
    (c) A last name (family name/surname)
    (d) Student number (ID) – an integer number (of type long)
    (e) A date of birth (in day/month/year format – three ints) - (Do NOT use the Date class from JAVA)
    (f) There are three assignments, each marked out of a maximum of 100 marks and equally weighted.
    The marks for each assignment are recorded separately.
    (g) There is weekly practical work. The marks for this component are recorded as a total mark
    obtained (out of a maximum of 10 marks) for all practical work demonstrated during the semester.
    (h) There is one final examination that is marked out of a maximum of 100 marks and recorded
    separately.
    (i) An overall mark (to be calculated within the program)
    (j) A final grade, which is a string (to be calculated within the program)
     */

    private long studentID;
    private String title;
    private String firstName;
    private String lastName;

    private int dateOfBirth_day;
    private int dateOfBirth_month;
    private int dateOfBirth_year;

    private Assignment[] assignments;
    private PracticalWork[] practicalWorks;
    private FinalExam finalExam;

    /**
     * Default constructor
     */
    public Student(){}

    /**
     * Constructor for Student
     * @param studentID studentID
     * @param title title
     * @param firstName firstName
     * @param lastName lastName
     * @param dateOfBirth_day dateOfBirth_day
     * @param dateOfBirth_month dateOfBirth_month
     * @param dateOfBirth_year dateOfBirth_year
     */
    public Student(long studentID, String title, String firstName, String lastName, int dateOfBirth_day, int dateOfBirth_month, int dateOfBirth_year) {
        this.studentID = studentID;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth_day = dateOfBirth_day;
        this.dateOfBirth_month = dateOfBirth_month;
        this.dateOfBirth_year = dateOfBirth_year;
        this.assignments = new Assignment[3];
        this.practicalWorks = new PracticalWork[3];
        this.finalExam = null;
    }

    /*
     * Static methods
     */

    static private Student[] swap(Student[] students, int from, int to){
        Student studentTemp = students[from];
        students[to] = students[from];
        students[from] = studentTemp;

        return students;
    }

    static public Student[] sort(Student[] students, String method){

        int i, j;
        double[] studentScores = new double[students.length];

        for (i = 0; i < students.length; i++) {
            studentScores[i] = students[i].getOverallMark();
        }

        double temp = studentScores[0];

        // selection sort
        for(i = 0; i < students.length; i++){
            for(j = i; j < students.length; j++){
                if(studentScores[j] < temp){
                    csArray.swap(studentScores, i, j);
                    Student.swap(students, i , j);
                }
            }
        }
        return students;
    }

    /**
     * Filters the top two students with the highest marks
     * @param students array of students
     * @return the top two students with the highest marks in the class
     */
    static public Student[] top2HighestMark(Student[] students){
     return students;
    }

    /**
     * Sorts an array of students by their ID
     * @param students array of students
     * @return an array of students sorted by order of their ID
     */
    static public Student[] sortById(Student[] students){
        return students;
    }

    /**
     * Sorts an array of students by their last name
     * @param students array of students
     * @return an array of students sorted by order of their last name
     */
    static public Student[] sortByLastname(Student[] students){
        return students;
    }

    static public Student[] searchByNameId(Student[] students, String search){
        Student[] result = new Student[0];
        Student[] temp;
        String name;
        int i, j;

        if(search.matches("^[\\d]+$]")){
            for(i = 0; i < students.length; i++){
                if(students[i].getStudentID() == Integer.parseInt(search)){
                    result = new Student[1];
                    result[0] = students[i];
                    return result;
                }
            }
        } else {
            for(i = 0; i < students.length; i++){
                name = students[i].getFirstName() + ' ' + students[i].getLastName();
                if(name.matches(search)){
                    temp = result;
                    result = new Student[result.length + 1];
                    for(j = 0; j < temp.length; j++){
                        result[j] = temp[j];
                    }
                    result[result.length - 1] = students[i];
                }
            }
        }
        return result;
    }

    /**
     * Get the overall mark of the student
     * @return the overall mark
     */
    public double getOverallMark(){
        return 100.00; //replace with getOverallMark method
    }

    public String getFinalGrade(){
        double marks = getOverallMark();
        if(marks >= 80) return "HD";
        else if(marks >= 70) return "D";
        else if(marks >= 60) return "C";
        else if(marks >= 50) return "P";
        else return "N";
    }

    public boolean equals(Student student) {
        return (
            student.getStudentID() == this.getStudentID() &&
            student.getFirstName() == this.getFirstName() &&
            student.getLastName() == this.getLastName() &&
            student.getDateOfBirth() == this.getDateOfBirth()
        );
    }

    /**
     * getter method for student ID
     * @return studentID
     */
    public long getStudentID() {
        return studentID;
    }

    /**
     * getter method for title
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * getter method for firstName
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter method for lastName
     * @return lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter method for dateOfBirth
     * @return String containing the day, month and year of dateOfBirth in String d/mm/yyyy format
     */
    public String getDateOfBirth() {
        return dateOfBirth_day + "/" + dateOfBirth_month + "/" + dateOfBirth_year;
    }

    /**
     * getter method for assignments
     * @return Array of Assignment Class for assignments
     */
    public Assignment[] getAssignments() {
        return assignments;
    }

    /**
     * getter method for practicalWorks
     * @return Array of PracticalWork Class for practicalWorks
     */
    public PracticalWork[] getPracticalWorks() {
        return practicalWorks;
    }

    /**
     * getter method for finalExam
     * @return finalExam
     */
    public FinalExam getFinalExam() {
        return finalExam;
    }

    /**
     * setter method for title
     * @param title new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setter method for firstName
     * @param firstName new firstname
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * setter mothod for lastName
     * @param lastName new lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setter method for dateOfBirth
     * @param dateOfBirth_day new dateOfBirth_day
     * @param dateOfBirth_month new dateOfBirth_month
     * @param dateOfBirth_year new dateOfBirth_year
     */
    public void setDateOfBirth(int dateOfBirth_day, int dateOfBirth_month, int dateOfBirth_year) {
        this.dateOfBirth_day = dateOfBirth_day;
        this.dateOfBirth_month = dateOfBirth_month;
        this.dateOfBirth_year = dateOfBirth_year;
    }

    /**
     * setter method for assignments as array
     * @param assignments new array of assignments
     */
    public void setAssignments(Assignment[] assignments) {
        this.assignments = assignments;
    }

    /**
     * setter method for practicalWorks as array
     * @param practicalWorks new array of practicalWorks
     */
    public void setPracticalWorks(PracticalWork[] practicalWorks) {
        this.practicalWorks = practicalWorks;
    }

    /**
     * setter method for finalExam
     * @param finalExam new array of finalExam
     */
    public void setFinalExam(FinalExam finalExam) {
        this.finalExam = finalExam;
    }

    /**
     * Override the toString method
     * @return Class representation String
     */
    @Override
    public String toString(){
        return "< Student id=" + this.studentID + " >";
    }
}
