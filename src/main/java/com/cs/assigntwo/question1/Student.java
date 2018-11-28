package com.cs.assigntwo.question1;

import com.cs.assigntwo.dependencies.csArray;


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
     * Deep Clone constructor
     * @param student object we wish to clone
     */
    public Student(Student student){
        this.studentID = student.getStudentID();
        this.title = student.getTitle();
        this.firstName = student.getFirstName();
        this.lastName = student.getLastName();
        this.dateOfBirth_day = student.getDateOfBirth_day();
        this.dateOfBirth_month = student.getDateOfBirth_month();
        this.dateOfBirth_year = student.getDateOfBirth_year();
        this.assignments = new Assignment[3];
        this.practicalWorks = new PracticalWork[3];
        this.finalExam = null;
    }

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

    static private Student clone(Student student){
        return new Student(student);
    }

    static private Student[] swap(Student[] students, int from, int to){
        Student studentTemp = Student.clone(students[from]);
        students[from] = students[to];
        students[to] = studentTemp;

        return students;
    }

    static private void printArray(Student[] students){
        int i;
        for(i = 0; i < students.length; i++){
            System.out.println(students[i].toString());
        }
        System.out.println("\n");
    }

    /**
     * Filters the top two students with the highest marks
     * @param students array of students
     * @return the top two students with the highest marks in the class
     */
    static public Student[] top2HighestMark(Student[] students){

        int i, j;
        double si, sj;

        // selection sort
        for(i = 0; i < students.length; i++){
            for(j = i; j < students.length; j++){
                si = students[i].getOverallMark();
                sj = students[j].getOverallMark();

                if(!Double.isNaN(sj) && (si < sj || Double.isNaN(sj))){
                    students = Student.swap(students, i , j);
                }
            }
        }
        return students;
    }

    /**
     * Sorts an array of students by their ID
     * @param students array of students
     * @return an array of students sorted by order of their ID
     */
    static public Student[] sortById(Student[] students){
        int i, j;
        Student [] result = students;

        for(i = 0; i < result.length; i++){
            for(j = i + 1; j < result.length; j++){
                if(result[j].getStudentID() < result[i].getStudentID()){
                    result = swap(result, i, j);
                }
            }
        }
        return result;
    }

    /**
     * Sorts an array of students by their last name
     * @param students array of students
     * @return an array of students sorted by order of their last name
     */
    static public Student[] sortByLastName(Student[] students){
        int i, j;
        Student [] result = students;

        for(i = 0; i < result.length ; i++){
            for(j = i + 1; j < result.length; j++){
                if(result[i].getLastName().compareTo(result[j].getLastName()) > 0){
                    result = swap(result, i, j);
                }
            }
        }
        return result;
    }

    static public Student[] searchArray(Student[] students, String search){
        Student[] result = new Student[0];
        Student[] temp;
        String name;
        int i, j;

        try {
            int search_id = Integer.parseInt(search);

            System.out.println("search is id!");
            for(i = 0; i < students.length; i++){
                if(students[i].getStudentID() == search_id){
                    result = new Student[1];
                    result[0] = students[i];
                }
            }

        } catch (Exception err){
            System.out.println("search is name!");
            for(i = 0; i < students.length; i++) {
                name = students[i].getLastName();
                if (name.matches(search)) {
                    temp = result;
                    result = new Student[result.length + 1];
                    for (j = 0; j < temp.length; j++) {
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
        int i;
        int count = 0;
        double total = 0.00;
        double section_total = 0.00;

        for(i = 0; i < assignments.length; i++){
            if(assignments[i] != null){
                count++;
                section_total += (double) assignments[i].getScore();
            }
        }

        total += (section_total / count) * 0.450;
        section_total = 0.00;
        count = 0;


        for(i = 0; i < practicalWorks.length; i++){
            if(practicalWorks[i] != null){
                count++;
                section_total += (double) assignments[i].getScore();
            }
        }

        total += (section_total / count) * 0.100;
        section_total = 0.00;

        if(finalExam != null) {
            section_total += (double) finalExam.getScore();
        }

        total += section_total * 0.450;
        return total;
    }

    /**
     * Calculates the final Letter Grade
     * @return Letter Grade
     */
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
     * getter method for full name
     * @return fullname
     */
    public String getFullName() {
        return firstName + ' ' + lastName;
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
     * getter for dateOfBirth_day
     * @return dateOfBirth_day
     */
    public int getDateOfBirth_day() {
        return dateOfBirth_day;
    }

    /**
     * getter for dateOfBirth_month
     * @return dateOfBirth_month
     */
    public int getDateOfBirth_month() {
        return dateOfBirth_month;
    }

    /**
     * getter for dateOfBirth_year
     * @return dateOfBirth_year
     */
    public int getDateOfBirth_year() {
        return dateOfBirth_year;
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
     * Create New Assignment
     * @param score the assignment score
     * @throws Exception if there too many assignment saved
     */
    public void createAssignemnt(int score) throws Exception {
        Assignment work = new Assignment(score);
        int i;
        for(i = 0; i < this.assignments.length; i++){
            if(this.assignments[i] == null){
                this.assignments[i] = work;
                return;
            }
        }
        throw new Exception("new works exceed max limit");
    }

    /**
     * Create New Practical Work
     * @param score the practical work score
     * @throws Exception if there too many assignment saved
     */
    public void createPracticalWork(int score) throws Exception {
        PracticalWork work = new PracticalWork(score);
        int i;
        for(i = 0; i < this.practicalWorks.length; i++){
            if(this.practicalWorks[i] == null){
                this.practicalWorks[i] = work;
                return;
            }
        }
        throw new Exception("new works exceed max limit");
    }

    /**
     * Create new Exam
     * @param score exam score
     */
    public void createExam(int score){
        FinalExam exam = new FinalExam(score);
        this.finalExam = exam;
    }


    public void resetMarks(){
        this.assignments = new Assignment[3];
        this.practicalWorks = new PracticalWork[3];
        this.finalExam = null;
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
        return "< Student id=" + this.studentID + "Last Name=" + this.getFullName() + " >";
    }
}
