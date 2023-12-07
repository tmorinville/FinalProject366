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
    
    public int getLibraryWorksForID() { return libraryWorksForID; }
    
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
    
    public static int getTotalNumberOfBooks(int libraryID) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String selectNumCopies = "SELECT sum(num_available_copies) from available_copies where library_id = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectNumCopies);
        pstmt.setInt(1, libraryID);
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        int count = 0;
        while(rs.next()){
            count = rs.getInt("sum");
        }
        
        return count;
    }
    
    public static Librarian getLibrarianByID(int id) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        String query = "Select * from librarian where librarian_id = ?";
        
        PreparedStatement pstmt = connection.prepareStatement(query);
        pstmt.setInt(1, id);
        
        ResultSet rs = pstmt.executeQuery();
        
        int librarianID = rs.getInt("librarian_id");
        int libraryID = rs.getInt("library_id");
        String fName = rs.getString("firstname");
        String lName = rs.getString("lastname");
        Date startDate = rs.getDate("start_date");
        
        Librarian objLibrarian = new Librarian(librarianID, libraryID, fName, lName, startDate);
        
        return objLibrarian;
    }
}
