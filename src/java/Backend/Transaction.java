package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

//extends the abtract enity class
public class Transaction extends Enity {
    //Instance variables

    private String TransactionID;
    private String Amount;
    private String Description;
    private String Date;
    //constructor that calls the constructor of the super class. The client_ID get called into the super class while the other variables are intionalized. 

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
    //Update method that uses the resquest.getparamters string as the method paramters. 

    public void Update(String TransactionID, String Amount, String Description, String ClientID, String Date) throws ClassNotFoundException, SQLException {
        //Create a connection object
        Connection con;
       
      con = this.getConnection();
        //Creates a staement
        Statement st = con.createStatement();
        //SQL for update
        String sql = "UPDATE `Transactions` SET `Amount`='" + Amount + "',`Description` ='" + Description + "', `ClientID`= '" + ClientID + "', `Date`= '" + Date + "' WHERE `TransactionID`='" + TransactionID + "';";
        //Executes the SQL
        st.executeUpdate(sql);

    }

    public void Add(String TransactionID, String Amount, String Description, String ClientID, String Date) throws ClassNotFoundException, SQLException {
        //Creates a connection object
        Connection con;
        //Creates a prepared statement. 
        PreparedStatement pst;
        //Connects to the remote Database
         con = this.getConnection();
        //SQL for add
        pst = con.prepareStatement("INSERT INTO Transactions(TransactionID,Amount,Description,ClientID,Date)values(?,?,?,?,?)");
        //sets the variables for add
        pst.setString(1, TransactionID);
        pst.setString(2, Amount);
        pst.setString(3, Description);
        pst.setString(4, ClientID);
        pst.setString(5, Date);
        //Executes the SQL
        pst.executeUpdate();
    }

    public ResultSet SelectWhere(String id) {
        //Creates the connection object
        Connection con;
        //creates the resulset object to be returned. 
        ResultSet rs = null;
        //try statement
        try {
            //creates a preparedstatement
            PreparedStatement pst;
            //connects to the database
           con = this.getConnection();
            //Prepare the sql statement
            pst = con.prepareStatement("SELECT * FROM Transactions where TransactionID = ?");
            //set the variables for sql statement
            pst.setString(1, id);
            //executes the query
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
        //Creates the connection object
        Connection con;
        //creates the resulset object to be returned. 
        ResultSet rs = null;
        //try
        try {
            con = this.getConnection();
            //Prepare the sql statement
            String query = "SELECT * FROM Transactions";
            //Creates a statement
            Statement st = con.createStatement();
            //Execute the SQL
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
