package Backend;

import java.sql.*;

//Makes enity abstract 
public abstract class Enity {

    //instance variable 'Client_ID'
    private String Client_ID;

    //main constructor
    public Enity(String Client_ID) {
        this.Client_ID = Client_ID;
    }

    //empty constructor
    public Enity() {
        this.Client_ID = "";
    }

    public Connection getConnection() throws ClassNotFoundException, SQLException {
        //Creates a connection object
        Connection con;
        //Connects to the remote Database
        Class.forName("com.mysql.jdbc.Driver");
        String connectionUrl = "jdbc:mysql://**********/***********";
        con = DriverManager.getConnection(connectionUrl, "**********", "********");
        //Returns the connection object
        return con;
    }

    //abstract methods with a resultset return type. 
    public abstract ResultSet Select();

    public abstract ResultSet SelectWhere(String ID);

}
