/**
 *
 * @author tmmor
 */
import java.sql.*;
import java.util.ArrayList;
public class Client {
    public static void main(String[] args) {
        // Ask if librarian or user, store as a 1 if user and store as a 2 if libarian
        int userOrLibrarian = MenuHelper.scanChoice("Enter 1 if a user or 2 if a librarian: ", 1, 2);
        
        if(userOrLibrarian == 1){ 
            UserAccount currentUser = null; // UserAccount logged in
            boolean badInput = true; // flag for checking if user id is valid
            while(badInput){
                try{ 
                    // Ask for user id
                    int userID = MenuHelper.scanChoice("Enter your user id: ", 0, Integer.MAX_VALUE);
                    
                    currentUser = UserAccount.getUserInfo(userID);
                    
                    badInput = false; // set flag to false if no exception is thrown
                    
                }
                catch(SQLException ex){
                    System.out.println("Unable to find user with entered id. Please try again.");
                }
            }
            
            // Ask user what they want to 
        }
        else if(userOrLibrarian == 2){
            Librarian currentLibrarian = null; // Librarian logged in
            boolean badInput = true; // flag for checking if id entered is valid
            
            while(badInput){
                try{
                   int librarianID = MenuHelper.scanChoice("Enter your librarian id: ", 0, Integer.MAX_VALUE);
                   
                   currentLibrarian = Librarian.getLibrarianByID(librarianID);
                   badInput = false;
                   System.out.println("Success!!!!!");
                }
                catch(SQLException ex){
                    System.out.println("Invalid librarian id, try again.");
                }
            }
            
            int choice = 0;
            String prompt = "Enter 1 to list count of available books or 2 to add a book to the library: ";
            while(choice != -1){
                choice = MenuHelper.scanChoice(prompt, 1, 2);
                
                if(choice == 1){ 
                    try{
                        // View count of all books
                        int countOfBooks = Librarian.getTotalNumberOfBooks(currentLibrarian.getLibraryWorksForID());
                        System.out.println("The count of books in the library is " + countOfBooks);
                    }
                    catch(SQLException ex){
                        ex.printStackTrace();
                    }
                }
                else if(choice == 2){
                    
                }
            }
        }
        
        
       
        
        // USER
        // Ask which library to login to
        // Login using user_id
        // If successful login, list all functions and let them choose
        
        // LIBRARIAN
        // If successful login, list count of all available books or add a book to the library
        
        // Ending the program
        System.out.println("Ending program.");
    }
}
