
/**
 *
 * @author Alyssa
 */

import java.sql.*;
import java.util.ArrayList;

public class UserAccount {
    private int user_id;
    private String firstname;
    private String lastname;
    private String phone_number;
    private String email;
    
    // Constructor
    public UserAccount(int userID, String fname, String lname, String phoneNumber, String userEmail){
        this.user_id = userID;
        this.firstname = fname;
        this.lastname = lname;
        this.phone_number = phoneNumber;
        this.email = userEmail;
    }
    
    public int getUserID() { return user_id; }
    
    public String getfname() { return firstname; }
    
    public String getlname() { return lastname; }
    
    public String getPhoneNumber() { return phone_number; }
    
    public String getEmail() { return email; }
    
    public String toString(){
        return "User Info " + user_id + "\nName: " + firstname + " " + lastname
                + "\nPhone Number: " + phone_number + "\nEmail" + email;
    }
    
    public static UserAccount getUserObjByID(int id) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL query
        String getUser = "Select * from user_account where user_id = ?";
        
        PreparedStatement pstmt = connection.prepareStatement(getUser);
        pstmt.setInt(1, id);
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        int userid = rs.getInt("user_id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String phone_number = rs.getString("phone_number");
        String email = rs.getString("email");
        
        UserAccount toReturn = new UserAccount(userid, firstname, lastname, phone_number, email);
        
        return toReturn;
    }
    
    //User information
    public static UserAccount getUserInfo(int userId) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String accountInfo = "Select * from user_account WHERE user_id = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(accountInfo);
        pstmt.setInt(1, userId);
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        int userid = rs.getInt("user_id");
        String firstname = rs.getString("firstname");
        String lastname = rs.getString("lastname");
        String phone_number = rs.getString("phone_number");
        String email = rs.getString("email");
        
        // Create and return User Account object
        UserAccount account = new UserAccount(userid, firstname, lastname, phone_number, email);
        return account;
    }
    
    //view books checked out
    public static ArrayList<Book> viewBooksCheckedOut(int user_id) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        
        // SQL select statement
        String outBooks = "SELECT * FROM books_checked_out WHERE user_id = ?"; //this will be changed today(12/7)
                
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(outBooks);
        pstmt.setInt(1, user_id);        
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        ArrayList<Book> resultBookList = new ArrayList<>();
        while(rs.next()){
            int bookID = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            Date publishDate = rs.getDate("publish_date");
            String isbn = rs.getString("isbn");
            String description = rs.getString("description");
            String genre = rs.getString("genre");
            
            // Create object and add to ArrayList
            Book book = new Book(bookID, title, author, publishDate, isbn, description, genre);
            resultBookList.add(book);
        }
        // Return ArrayList
        return resultBookList;
    }
    
}