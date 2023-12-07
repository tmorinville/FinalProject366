

/**
 *
 * @author tmmor
 */

import java.util.ArrayList;
import java.sql.*;
public class Test {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
//        ArrayList<Book> result = Book.getBooksByGenre(1, "dystopian");
//        
//        for(Book b : result){
//            System.out.println(b.toString());
//            System.out.println("----------------------------------");
//        }
        ArrayList<Library> b = Book.getAllLibraryWithBook(13);
        
        for(Library books : b ){
            System.out.println(books.getName());
            System.out.println(books.getLibrary_id());
            System.out.println("--------");
        }
    }
    
}
