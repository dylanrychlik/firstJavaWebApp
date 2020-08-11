<%-- 
    Document   : updateClients
    Created on : Aug 9, 2020, 6:23:42 PM
    Author     : drych
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 
<%@page import="Backend.*" %> 

<%
     //If submit button is clicked
    if (request.getParameter("submit") != null) {
         //Uses strings to request the parameters input text name made in html. 
        String ClientID = request.getParameter("id");
        String ClientName = request.getParameter("Clientname");
         //Create a client object referenced in the backend
        Client client = new Client(ClientID, ClientName);
   //Call the MySQL update method. 
        client.Update(ClientID, ClientName);


%>

<script>
    alert("Record Updated!");
</script>
<%        }

%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title> 
        <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body>
        <h1>Client Update</h1>


        <div class="row">
            <div class="col-sm-4">
                <form  method="POST" action="#" >

                    <%  
                        //Creates a resultset object to read the MySQL query.  
                         ResultSet rs;
                         //Calls the id parameter which referenced the id of the record we want to update.
                        String id = request.getParameter("id");
                        //Creates an enity object to be assigned to the client object using polymorphism. 
                        Enity client = new Client();
                       
                        rs = client.SelectWhere(id);
                       //reads through the result set. Mixed in the html to allow the record to be selected based upon a specified ID. 
                        while (rs.next()) {

                    %>


                    <div alight="left">
                        <label class="form-label">Client Name</label>
                        <input type="text" class="form-control" placeholder="Clientname" name="Clientname" value="<%= rs.getString("Clientname")%>" id="course" required >
                    </div>



                    <% }%>



                    </br>

                    <div alight="right">
                        <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                        <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                    </div>  

                    <div align="right">

                        <p><a href="indexClients.jsp">Click Back</a></p>


                    </div>

                </form>
            </div>          
        </div>

    </body>
</html>