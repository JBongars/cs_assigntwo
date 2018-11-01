package com.cs.assigntwo.dependencies;

public class csUtility {

    public void csUtility() { }

    /**
     * @title Hillbilly clear screen
     * @description just print 100 blank lines, that will clear the screen
     */
    public static void clearScreen(){
        for(int i = 0; i < 100; i++){
            System.out.println();
        }
    }

    /**
     * @title Print student info
     * @description prints the student info at the beginning of a program
     */
    public static void printStudentInfo(String title){
        clearScreen();
        System.out.println("\n=============================================");
        System.out.println("Title: " + title );
        System.out.println("Author: Julien Bongars");
        System.out.println("Student No: ");
        System.out.println("Date: 10 Oct 2018");
        System.out.println("=============================================\n\n");
        System.out.println();
        System.out.println();
    }

//    /**
//     * @title 1950s nerd clear screen
//     * @description
//     * moves the cursor to the top left side of the screen,
//     * copies escape character to the bottom right side of the screen
//     * does not work...
//     */
//    public static void clearScreen1() {
//        System.out.print("\033[H\033[2J");
//        System.out.flush();
//    }
//
//    /**
//     * @title original clear screen
//     * @description
//     * Send clear screen command to terminal. Does not work
//     */
//    public static void clearScreen2() {
//        try {
//            String clearScreenCommand = null;
//            if( System.getProperty( "os.name" ).startsWith( "Window" ) )
//                clearScreenCommand = "cls";
//            else
//                clearScreenCommand = "clear";
//
//            Runtime.getRuntime().exec( clearScreenCommand );
//
//        } catch (IOException err){
//            System.out.println("Unable to clear screen. Please ensure JAVA has access to IO");
//        }
//    }

}
