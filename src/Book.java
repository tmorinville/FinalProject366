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
            String bookISBN, String bookDescription, String bookGenre) {
        bookID = bookIDNum;
        title = bookTitle;
        author = bookAuthor;
        publishDate = bookPublishDate;
        isbn = bookISBN;
        description = bookDescription;
        genre = bookGenre;
    }

    public int getBookID() {
        return bookID;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public String getISBN() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public String getGenre() {
        return genre;
    }

    public String toString() {
        return "Book ID: " + bookID + "\nTitle: " + title + "\nAuthor: " + author
                + "\nPublish Date: " + publishDate.toString() + "\nISBN: " + isbn
                + "\nDescription: " + description + "\nGenre: " + genre;
    }

    public static ArrayList<Book> getAllBooksFromLibrary(int libraryID) throws SQLException {
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
        while (rs.next()) {
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

    public static ArrayList<Book> getSpecifiedBookAtSpecifiedLibrary(int libraryID, int bookID) throws SQLException {
        Connection connection = DatabaseManager.getConnection();

        // SQL select statement
        String selectBooks = "SELECT book.book_id, book.title, book.author, book.publish_date, book.isbn, book.description, book.genre, library.name, num_available_copies FROM available_copies "
                + "INNER JOIN book ON book.book_id = available_copies.book_id "
                + "INNER JOIN library ON library.library_id = available_copies.library_id "
                + "WHERE book.book_id = ? and library.library_id = ?";

        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBooks);
        pstmt.setInt(1, bookID);
        pstmt.setInt(2, libraryID);

        // Execute query
        ResultSet rs = pstmt.executeQuery();

        // Store results in ArrayList
        ArrayList<Book> resultBookList = new ArrayList<>();
        while (rs.next()) {
            int book_id = rs.getInt("book_id");
            String title = rs.getString("title");
            String author = rs.getString("author");
            Date publishDate = rs.getDate("publish_date");
            String isbn = rs.getString("isbn");
            String description = rs.getString("description");
            String genre = rs.getString("genre");

            // Create object and add to ArrayList
            Book book = new Book(book_id, title, author, publishDate, isbn, description, genre);
            resultBookList.add(book);
        }
        // Return ArrayList
        return resultBookList;
    }

    public static ArrayList<Library> getAllLibraryWithBook(int bookID) throws SQLException {
        Connection connection = DatabaseManager.getConnection();

        // SQL select statement
        String selectBooks = "SELECT library.library_id, library.name, library.street, "
                + "library.city, library.state, library.zipcode, library.phone_number\n"
                + " FROM available_copies \n"
                + " INNER JOIN book ON book.book_id = available_copies.book_id \n"
                + " INNER JOIN library ON library.library_id = available_copies.library_id \n"
                + " WHERE book.book_id = ?";

        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(selectBooks);
        pstmt.setInt(1, bookID);

        // Execute query
        ResultSet rs = pstmt.executeQuery();

        // Store results in ArrayList
        ArrayList<Library> resultLibraryList = new ArrayList<>();
        while (rs.next()) {
            int lib_id = rs.getInt("library_id");
            String name = rs.getString("name");
            String street = rs.getString("street");
            String city = rs.getString("city");
            String state = rs.getString("state");
            String zip = rs.getString("zipcode");
            String phone_number = rs.getString("phone_number");

            // Create object and add to ArrayList
            Library lib = new Library(lib_id, name, street, city, state, zip, phone_number);
            resultLibraryList.add(lib);
        }
        // Return ArrayList
        return resultLibraryList;
    }

    public static ArrayList<Book> getBooksFromLibraryByAuthor(int libraryID, String searchAuthor) throws SQLException {
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
        while (rs.next()) {
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

    public static ArrayList<Book> getBooksByTitle(int libraryID, String searchTitle) throws SQLException {
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
        while (rs.next()) {
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

    public static ArrayList<Book> getBooksByGenre(int libraryID, String bookGenre) throws SQLException {
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
        while (rs.next()) {
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
    public static Book getBookByID(int id) throws SQLException {
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

    // Helper method for use only in the return book method
    private static void removeBookAndUserRow(int bookID, int userID) throws SQLException {
        // Connection
        Connection connection = DatabaseManager.getConnection();

        // SQL delete statement
        String deleteBookUserRow = "Delete from books_checked_out where user_id = ? "
                + "and book_id = ?";

        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(deleteBookUserRow);
        pstmt.setInt(1, userID);
        pstmt.setInt(2, bookID);

        // Execute query, returnValue never read 
        int returnValue = pstmt.executeUpdate();
    }

    // Helper method for use only in the check out book method
    private static void addBookAndUserRow(int bookID, int userID) throws SQLException {
        // Connection
        Connection connection = DatabaseManager.getConnection();

        // SQL delete statement
        String insertBookUserRow = "Insert into books_checked_out values (?, ?)";

        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(insertBookUserRow);
        pstmt.setInt(1, userID);
        pstmt.setInt(2, bookID);

        // Execute query, returnValue never read 
        int returnValue = pstmt.executeUpdate();
    }
    private static void updateAvailableCopies(int bookID, int libraryID, int shift) throws SQLException{
        Connection connection = DatabaseManager.getConnection();

        // SQL select statement
        String updateQuery = "update available_copies "
                + "set num_available_copies = num_available_copies + ?"
                + " where available_copies.book_id = ? and available_copies.library_id = ?";

        // PreparedStatement
        PreparedStatement pstmt = connection.prepareStatement(updateQuery);
        pstmt.setInt(1, shift);
        pstmt.setInt(2, bookID);
        pstmt.setInt(3, libraryID);

        // Execute query
        ResultSet rs = pstmt.executeQuery();
        
    }
}
