
package Backend;

import java.sql.*;

//Makes enity abtract
public abstract class Enity {
    //intance variable 'Client_ID'
    private String Client_ID;
    
    //main constructor
    public Enity(String Client_ID){
        this.Client_ID = Client_ID;
    }
    //empty constructor
      public Enity(){
        this.Client_ID = "";
    }
 
 
    //abtract methods with a resultset return type. 
    public abstract ResultSet Select();
    
    public abstract ResultSet SelectWhere(String ID);
    
    
    
}
