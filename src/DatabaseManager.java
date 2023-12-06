/**
 *
 * @author tmmor
 */
import java.sql.*;
public class DatabaseManager {
    // Taya's connection information
   // static String jdbcURL = "jdbc:postgresql://localhost:5432/Assignment9FinalRun";
   // static String username = "postgres";
   // static String password = "Taya101603!";

    // Alyssa's connection information
    static String jdbcURL = "jdbc:postgresql://localhost:5432/Assignment9Final";
    static String username = "postgres";
    static String password = "Acall1";

    // Peyton's connection information
//    static String jdbcURL = 
//    static String username = 
//    static String password = 
    
    // Connection object
    private static Connection connection;
    
    public static Connection getConnection(){
        if(connection == null){
            setConnection();
        }
        return connection;
    }
    
    private static void setConnection(){
        // Create connection object
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, username, password);
        }
        catch(ClassNotFoundException ex){
            System.out.println("Cannot load driver.");
        }
        catch(SQLException ex){
            System.out.println("SQLException thrown.");
        }
    }
    
    public static void closeConnection(){
        try{
            connection.close();
        }
        catch (SQLException ex){
            
        }
    }
}
