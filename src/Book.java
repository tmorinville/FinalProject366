/**
 *
 * @author tmmor
 */

import java.util.Date;
import java.util.ArrayList;
import java.sql.*;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private Date publishDate;
    private String isbn;
    private String description;
    private String genre;
    
    // Constructor
    public Book(int bookIDNum, String bookTitle, String bookAuthor, Date bookPublishDate, 
            String bookISBN, String bookDescription, String bookGenre){
        bookID = bookIDNum;
        title = bookTitle;
        author = bookAuthor; 
        publishDate = bookPublishDate; 
        isbn = bookISBN;
        description = bookDescription;
        genre = bookGenre;
    }
    
    public int getBookID() { return bookID; }
    
    public String getTitle() { return title; }
    
    public String getAuthor() { return author; }
    
    public Date getPublishDate() { return publishDate; }
    
    public String getISBN() { return isbn; }
    
    public String getDescription() { return description; }
    
    public String getGenre() { return genre; }
    
    public String toString(){
        return "Book ID: " + bookID + "\nTitle: " + title + "\nAuthor: " + author
                + "\nPublish Date: "  + publishDate.toString() + "\nISBN: " + isbn
                + "\nDescription: " + description + "\nGenre: " + genre;
    }
    
    public static ArrayList<Book> getAllBooksFromLibrary(int libraryID) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String selectBooks = "SELECT book.book_id, book.title, book.author, "
                + "book.publish_date, book.isbn, book.description, book.genre "
                + "from book INNER JOIN available_copies ON book.book_id = "
                + "available_copies.book_id INNER JOIN library ON "
                + "library.library_id = available_copies.library_id WHERE "
                + "available_copies.library_id = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBooks);
        pstmt.setInt(1, libraryID);
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        // Store results in ArrayList
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
    
    public static ArrayList<Book> getBooksFromLibraryByAuthor(int libraryID, String searchAuthor) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String selectBooks = "SELECT book.book_id, book.title, book.author, "
                + "book.publish_date, book.isbn, book.description, book.genre "
                + "from book INNER JOIN available_copies ON book.book_id = "
                + "available_copies.book_id INNER JOIN library ON "
                + "library.library_id = available_copies.library_id WHERE "
                + "available_copies.library_id = ? and lower(author) = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBooks);
        pstmt.setInt(1, libraryID);
        pstmt.setString(2, searchAuthor.toLowerCase());
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        // Store results in ArrayList
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
    
    public static ArrayList<Book> getBooksByTitle(int libraryID, String searchTitle) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String selectBooks = "SELECT book.book_id, book.title, book.author, "
                + "book.publish_date, book.isbn, book.description, book.genre "
                + "from book INNER JOIN available_copies ON book.book_id = "
                + "available_copies.book_id INNER JOIN library ON "
                + "library.library_id = available_copies.library_id WHERE "
                + "available_copies.library_id = ? and lower(title) = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBooks);
        pstmt.setInt(1, libraryID);
        pstmt.setString(2, searchTitle.toLowerCase());
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        // Store results in ArrayList
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
    
    public static ArrayList<Book> getBooksByGenre(int libraryID, String bookGenre) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String selectBooks = "SELECT book.book_id, book.title, book.author, "
                + "book.publish_date, book.isbn, book.description, book.genre "
                + "from book INNER JOIN available_copies ON book.book_id = "
                + "available_copies.book_id INNER JOIN library ON "
                + "library.library_id = available_copies.library_id WHERE "
                + "available_copies.library_id = ? and lower(genre) = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBooks);
        pstmt.setInt(1, libraryID);
        pstmt.setString(2, bookGenre.toLowerCase());
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        // Store results in ArrayList
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
    
    // Helper method for use in the Librarian class
    public static Book getBookByID(int id) throws SQLException{
        Connection connection = DatabaseManager.getConnection();
        
        // SQL select statement
        String selectBook = "Select * from book where book_id = ?";
        
        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBook);
        pstmt.setInt(1, id);
        
        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
        
        int bookID = rs.getInt("book_id");
        String title = rs.getString("title");
        String author = rs.getString("author");
        Date publishDate = rs.getDate("publish_date");
        String isbn = rs.getString("isbn");
        String description = rs.getString("description");
        String genre = rs.getString("genre");

        // Create and return new Book object
        Book book = new Book(bookID, title, author, publishDate, isbn, description, genre);
        return book;   
    }
}
