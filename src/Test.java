/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author tmmor
 */
import java.sql.*;
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        
        Connection conn = DatabaseManager.getConnection();
        
        String query = "Select * from librarian where librarian_id = ?";
        
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, 2);
        
        ResultSet rs = ps.executeQuery();
        
        System.out.println(rs.toString());
        
        while(rs.next()){
            System.out.println(rs.getInt("librarian_id"));
            System.out.println(rs.getInt("library_id"));
            System.out.println(rs.getString("firstname"));
            System.out.println(rs.getString("lastname"));
            System.out.println(rs.getDate("start_date"));

        }
        
    }
    
}
