package com.cs.assignone.question1;

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

    private long studentID
    private String title;
    private String firstName;
    private String lastName;

    private int dateOfBirth_day;
    private int dateOfBirth_month;
    private int dateOfBirth_year;

    private Assignment[] assignments;
    private PracticalWork[] practicalWorks;
    private FinalExam finalExam;

    public Student() { }

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
     * setter method of studentID
     * @param studentID
     */
    public void setStudentID(long studentID) {
        this.studentID = studentID;
    }

    /**
     * setter method for title
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * setter method for firstName
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * setter mothod for lastName
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setter method for dateOfBirth
     * @param dateOfBirth_day
     * @param dateOfBirth_month
     * @param dateOfBirth_year
     */
    public void setDateOfBirth(int dateOfBirth_day, int dateOfBirth_month, int dateOfBirth_year) {
        this.dateOfBirth_day = dateOfBirth_day;
        this.dateOfBirth_month = dateOfBirth_month;
        this.dateOfBirth_year = dateOfBirth_year;
    }

    /**
     * setter method for assignments as array
     * @param assignments
     */
    public void setAssignments(Assignment[] assignments) {
        this.assignments = assignments;
    }

    /**
     * setter method for practicalWorks as array
     * @param practicalWorks
     */
    public void setPracticalWorks(PracticalWork[] practicalWorks) {
        this.practicalWorks = practicalWorks;
    }

    /**
     * setter method for finalExam
     * @param finalExam
     */
    public void setFinalExam(FinalExam finalExam) {
        this.finalExam = finalExam;
    }
}
