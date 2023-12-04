/**
 *
 * @author tmmor
 */
import java.util.Date;
import java.sql.*;
public class Librarian {
    private int librarianID;
    private int libraryWorksForID;
    private String firstname;
    private String lastname;
    private Date startDate;
    
    public Librarian(int newLibrarianID, int libraryID, String fName, String lName, Date sDate){
        librarianID = newLibrarianID;
        libraryWorksForID = libraryID;
        firstname = fName;
        lastname = lName;
        startDate = sDate;
    }
    
    public void insertBook(int bookID, int numberOfCopies) throws SQLException{
        // Get Book object by ID
        Book bookToInsert = Book.getBookByID(bookID);
        
        // Connection
        Connection connection = DatabaseManager.getConnection();
        
        // SQL insert statement
        String insertNewBook = "Insert into available_copies (library_id, book_id,"
                + " num_available_copies) values (?, ?, ?)";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(insertNewBook);
        pstmt.setInt(1, this.libraryWorksForID);
        pstmt.setInt(2, bookToInsert.getBookID());
        pstmt.setInt(3, numberOfCopies);
        
        // Execute query, returnValue never read because this is a void method
        int returnValue = pstmt.executeUpdate();
    }
    
    
}
