/**
 *
 * @author Taya Morinville
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
                    int userID = Integer.parseInt(MenuHelper.getString("Enter your user id: "));
                    
                    currentUser = UserAccount.getUserInfo(userID);
                    
                    badInput = false; // set flag to false if no exception is thrown
                    
                }
                catch(InputMismatchException ex){
                    System.out.println("Invalid id entered.");
                }
                catch(SQLException ex){
                    System.out.println("Unable to find user with entered id. Please try again.");
                }
            }
            
            // Ask user what they want to do
            String prompt = "Enter the corresponding number for your choice: "
                    + "\n\t1)  Return a book"
                    + "\n\t2)  Check out a book"
                    + "\n\t3)  List books by a certain author at a particular library"
                    + "\n\t4)  Search for books by title at a particular library"
                    + "\n\t5)  Search for books by genre at a particular library"
                    + "\n\t6)  View books checked out"
                    + "\n\t7)  View all books at a particular library"
                    + "\n\t8)  Search for particular book at a particular library"
                    + "\n\t9)  Find libraries where particular book is stocked";
                    
            int choice = 0; // To store user choice
            while(choice != -1){
                choice = MenuHelper.scanChoice(prompt, 1, 9);
                
                if(choice == 1){
                    int bookID = Integer.parseInt(MenuHelper.getString("Enter the book id of the book you want to return: "));
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you wish to return the book: "));
                    int userID = currentUser.getUserID(); 
                    
                    try{
                        Book.returnBook(bookID, libraryID, userID);
                        System.out.println("Successfully return book.");
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid book id or library id, cannot return book.");
                    }
                }
                else if(choice == 2){                 
                    int bookID = Integer.parseInt(MenuHelper.getString("Enter the book id of the book you want to check out: "));
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you wish to check out the book: "));
                    int userID = currentUser.getUserID();
                    
                    try{
                        Book.checkOutBook(bookID, libraryID, userID);
                        System.out.println("Successfully checked out book.");
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid book id or library id, cannot check out book.");
                    }
                    
                    catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else if(choice == 3){
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you wish to search: "));
                    String author = MenuHelper.getString("Enter the author you wish to search for: ");
                    
                    try{
                        ArrayList<Book> books = Book.getBooksFromLibraryByAuthor(libraryID, author);
                        for(Book b : books){
                            System.out.println(b.toString());
                            System.out.println("-----------------------");
                        }
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid library id or author name, cannot search for books.");
                    }
                }
                else if(choice == 4){
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you wish to search: "));
                    String title = MenuHelper.getString("Enter the title you wish to search for: ");
                    
                    try{
                        ArrayList<Book> books = Book.getBooksByTitle(libraryID, title);
                        for(Book b : books){
                            System.out.println(b.toString());
                            System.out.println("---------------------");
                        }
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid library id or title, cannot search for books.");
                    }
                }
                else if(choice == 5){
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you wish to search: "));
                    String genre = MenuHelper.getString("Enter the genre you wish to search for: ");
                    
                    try{
                        ArrayList<Book> books = Book.getBooksByGenre(libraryID, genre);
                        for(Book b : books){
                            System.out.println(b.toString());
                            System.out.println("---------------------");
                        }
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid library id or genre, cannot search for books.");
                    }
                }
                else if(choice == 6){
                    try{
                        ArrayList<Book> books = UserAccount.viewBooksCheckedOut(currentUser.getUserID());
                        for(Book b : books){
                            System.out.println(b.toString());
                            System.out.println("-----------------------");
                        }
                    }
                    catch(SQLException ex){
                        System.out.println("You have no books checked out.");
                    }
                }
                else if(choice == 7){
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you wish to search: "));
                                        
                    try{
                        ArrayList<Book> books = Book.getAllBooksFromLibrary(libraryID);
                        for(Book b : books){
                            System.out.println(b.toString());
                            System.out.println("-----------------------");
                        }
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid library id, cannot search for books.");
                    }
                }
                else if(choice == 8){
                    int bookID = Integer.parseInt(MenuHelper.getString("Enter the book id of the book you want to search for: "));
                    int libraryID = Integer.parseInt(MenuHelper.getString("Enter the library id where you want to search for the book: "));
                    
                    try{
                        ArrayList<Book> books = Book.getSpecifiedBookAtSpecifiedLibrary(libraryID, bookID);
                        for(Book b : books){
                            System.out.println(b.toString());
                            System.out.println("-----------------------");
                        }
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid book id or library id, cannot search for books");
                    }
                }
                else if(choice == 9){
                    int bookID = Integer.parseInt(MenuHelper.getString("Enter the book id of the book you want to search for: "));
                   
                    try{
                       ArrayList<Library> libraries = Book.getAllLibraryWithBook(bookID);
                       for(Library l : libraries){
                           System.out.println(l.toString());
                           System.out.println("---------------------");
                       }
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid book id, cannot search for libraries.");
                    }
                }
            }
        }
        else if(userOrLibrarian == 2){
            Librarian currentLibrarian = null; // Librarian logged in
            boolean badInput = true; // flag for checking if id entered is valid
            
            while(badInput){
                try{
                   int librarianID = Integer.parseInt(MenuHelper.getString("Enter your librarian id: "));
                   
                   currentLibrarian = Librarian.getLibrarianByID(librarianID);
                   badInput = false;
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
                    int insertBookID = Integer.parseInt(MenuHelper.getString("Enter the book id: "));
                    int numCopies = Integer.parseInt(MenuHelper.getString("Enter the number of copies: "));
                    
                    try{
                        Librarian.insertBook(insertBookID, currentLibrarian.getLibraryWorksForID(), numCopies);
                        System.out.println("Successfully inserted book to library.");
                    }
                    catch(SQLException ex){
                        System.out.println("Invalid book id, unable to add to library.");
                    }
                }
            }
        }
        
        // Ending the program
        System.out.println("Program has ended.");
    }
}
