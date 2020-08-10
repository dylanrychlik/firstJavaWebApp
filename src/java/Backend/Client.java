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
        //Create a connection object
        PreparedStatement pst;
        //Create a prepared statement. 
        //Connect to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://************/************";
        con = DriverManager.getConnection(connectionUrl, "************", "************");
        //SQL for add
        pst = con.prepareStatement("INSERT INTO Clients(ClientID,Clientname)values(?,?)");
        //set the variables for add
        pst.setString(1, ClientID);
        pst.setString(2, Clientname);
        //Execute the SQL
        pst.executeUpdate();
    }
//Update method that uses the resquest.getparamters string as the emthod paramters. 

    public void Update(String ClientID, String Clientname) throws ClassNotFoundException, SQLException {
        //Create a connection object
        Connection con;
        //Connect to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://************/************", "************", "************");
        //Create a staement
        Statement st = con.createStatement();
        //SQL for update
        String sql = "UPDATE `Clients` SET `Clientname`='" + Clientname + "' WHERE `ClientID`='" + ClientID + "';";
        //Execute the SQL
        st.executeUpdate(sql);

    }

    @Override
    public ResultSet Select() {
        //Create the connection object
        Connection con;
        //create the resulset object to be returned. 
        ResultSet rs = null;
        //try
        try {
            //connect to the database
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://************/************", "************", "************");
            //Prepare the sql statement
            String query = "SELECT * FROM Clients";
            //Create a statement
            Statement st = con.createStatement();
            //Executet the SQL
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
            con = DriverManager.getConnection("jdbc:mysql://************/************", "************", "************");
            //Prepare the sql statement
            pst = con.prepareStatement("SELECT * FROM Clients where ClientID = ?");
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
}
