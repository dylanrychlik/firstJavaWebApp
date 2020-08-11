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

public class Client extends Enity {
    //Instance variables

    private String Client_name;
//empty contructor

    public Client() {
        this.Client_name = "";
    }
    //constructor that super's the client_ID and intionalizes the other vairables. 

    public Client(String Client_ID, String Client_name) {
        super(Client_ID);
    }

    public void Add(String ClientID, String Clientname) throws ClassNotFoundException, SQLException {
        Connection con;
        //Creates a connection object
        PreparedStatement pst;
        //Creates a prepared statement. 
        //Connects to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase";
        con = DriverManager.getConnection(connectionUrl, "uzaqleuw_root", "3Hotdogs!");
        //SQL for add
        pst = con.prepareStatement("INSERT INTO Clients(ClientID,Clientname)values(?,?)");
        //set the variables for add
        pst.setString(1, ClientID);
        pst.setString(2, Clientname);
        //Executes the SQL
        pst.executeUpdate();
    }
//Update method that uses the resquest.getparamters string as the emthod paramters. 

    public void Update(String ClientID, String Clientname) throws ClassNotFoundException, SQLException {
        //Creates a connection object
        Connection con;
        //Connect to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase", "uzaqleuw_root", "3Hotdogs!");
        //Creates a staement
        Statement st = con.createStatement();
        //SQL for update
        String sql = "UPDATE `Clients` SET `Clientname`='" + Clientname + "' WHERE `ClientID`='" + ClientID + "';";
        //Executes the SQL
        st.executeUpdate(sql);

    }

    @Override
    public ResultSet Select() {
        //Creates the connection object
        Connection con;
        //creates the resulset object to be returned. 
        ResultSet rs = null;
        //try
        try {
            //connects to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase", "uzaqleuw_root", "3Hotdogs!");
            //Prepares the sql statement
            String query = "SELECT * FROM Clients";
            //Creates a statement
            Statement st = con.createStatement();
            //Executes the SQL
            rs = st.executeQuery(query);

        }  //catches
        catch (ClassNotFoundException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Transaction.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }

    @Override
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
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://cloud19.hostgator.com/uzaqleuw_Simpledatabase", "uzaqleuw_root", "3Hotdogs!");
            //Prepares the sql statement
            pst = con.prepareStatement("SELECT * FROM Clients where ClientID = ?");
             //sets the variables for sql statement
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
}
