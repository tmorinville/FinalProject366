
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author peyton schultz
 * 
 * 
 */
public class MenuHelper {
    
    
    /** 
     * This Method will display a user-defined message that should prompt a user to enter an integer.  
     * 
     * @param prompt message for the user
     * @param min lower numerical bound on user's choice
     * @param max upper numerical bound on user's choice
     * @return user's choice as an integer
     */
    public static int scanChoice(String prompt, int min, int max) {
        Scanner scan = new Scanner(System.in);
        boolean proper = false;
        int result = -10;

        while (proper == false) {
            System.out.println(prompt + "\nPress q to quit at any time.");
            if (scan.hasNextInt()) {
                result = scan.nextInt();
                if (result <= max && result >= min) {
                    proper = true;
                } else {
                    System.out.println("\n\t invalid integer :: min: " + min + " max: " + max);
                    scan.nextLine();
                }
            } else if (scan.nextLine().equalsIgnoreCase("q")) {
                return -1;
            } else {
                System.out.println("you did not enter a proper int");
                //scan.nextLine();
                scan.reset();
            }
        }
        return result;
    }
    
    /**
     * This method displays a user-defined message to prompt a user to enter a string
     * The string is returned after this method is completed.
     * @param prompt message for the user
     * @return string that represents their response
     */
    public static String getString(String prompt){
        Scanner scan = new Scanner(System.in);
        String result = "";
        while(result == null || result.isBlank()){
            System.out.println(prompt);
            result = scan.nextLine();
            
            if(result == null || result.isBlank()){
                System.out.println("String should not be null or empty");
            }
            
        }
        
        return result.trim();
    }
    
}
