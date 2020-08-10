package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//extends of abtract enity class
public class Transaction extends Enity {
    //Instance variables

    private String TransactionID;
    private String Amount;
    private String Description;
    private String Date;
    //constructor that super's the client_ID and intionalizes the other vairables. 

    public Transaction(String TransactionID, String Amount, String Description, String Client_ID, String Date) {
        super(Client_ID);
        this.TransactionID = TransactionID;
        this.Amount = Amount;
        this.Description = Description;
        this.Date = Date;

    }
//empty contructor

    public Transaction() {

        this.TransactionID = "";
        this.Amount = "";
        this.Description = "";
        this.Date = "";

    }
    //Update method that uses the resquest.getparamters string as the emthod paramters. 

    public void Update(String TransactionID, String Amount, String Description, String ClientID, String Date) throws ClassNotFoundException, SQLException {
        //Create a connection object
        Connection con;
        //Connect to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase", "uzaqleuw_root", "3Hotdogs!");
        //Create a staement
        Statement st = con.createStatement();
        //SQL for update
        String sql = "UPDATE `Transactions` SET `Amount`='" + Amount + "',`Description` ='" + Description + "', `ClientID`= '" + ClientID + "', `Date`= '" + Date + "' WHERE `TransactionID`='" + TransactionID + "';";
        //Execute the SQL
        st.executeUpdate(sql);

    }

    public void Add(String TransactionID, String Amount, String Description, String ClientID, String Date) throws ClassNotFoundException, SQLException {
        //Create a connection object
        Connection con;
        //Create a prepared statement. 
        PreparedStatement pst;
        //Connect to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase";
        con = DriverManager.getConnection(connectionUrl, "uzaqleuw_root", "3Hotdogs!");
        //SQL for add
        pst = con.prepareStatement("INSERT INTO Transactions(TransactionID,Amount,Description,ClientID,Date)values(?,?,?,?,?)");
        //set the variables for add
        pst.setString(1, TransactionID);
        pst.setString(2, Amount);
        pst.setString(3, Description);
        pst.setString(4, ClientID);
        pst.setString(5, Date);
        //Execute the SQL
        pst.executeUpdate();
    }

    public ResultSet SelectWhere(String id) {
        //Create the connection object
        Connection con;
        //create the resulset object to be returned. 
        ResultSet rs = null;
        //try statement
        try {
            //create a preparedstatement
            PreparedStatement pst;
            //connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase", "uzaqleuw_root", "3Hotdogs!");
            //Prepare the sql statement
            pst = con.prepareStatement("SELECT * FROM Transactions where TransactionID = ?");
            //set the variables for sql statement
            pst.setString(1, id);
            //execute the query
            rs = pst.executeQuery();
        } //catches  
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    public ResultSet Select() {
        //Create the connection object
        Connection con;
        //create the resulset object to be returned. 
        ResultSet rs = null;
        //try
        try {
            //connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase", "uzaqleuw_root", "3Hotdogs!");
            //Prepare the sql statement
            String query = "SELECT * FROM Transactions";
            //Create a statement
            Statement st = con.createStatement();
            //Executet the SQL
            rs = st.executeQuery(query);

        } //catches
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

}
