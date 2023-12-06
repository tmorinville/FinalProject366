
/**
 *
 * @author Alyssa
 */
public class UserAccount {
    private int user_id;
    private String firstname;
    private String lastname;
    private String phone_number;
    private String email;
    
    // Constructor
    public UserAccount(int userid, String firstname, String lname, String phone_number, String email){
        user_id = userid;
        firstname = firstname;
        lastname = lname;
        phone_number = phone_number;
        email = email;
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
    //view books checked out
    //check out book
    //return book
}