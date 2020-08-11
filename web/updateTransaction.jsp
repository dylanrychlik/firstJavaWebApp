<%-- 
    Document   : update
    Created on : Aug 8, 2020, 12:56:20 PM
    Author     : drych
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %> 
<%@page import="Backend.*" %> 

<%
     //If submit button is clicked
    if (request.getParameter("submit") != null) {
         //Uses strings to request the parameters of the input textfields. 
        String TransactionID = request.getParameter("id");
        String Amount = request.getParameter("amount");
        String Description = request.getParameter("description");
        String ClientID = request.getParameter("clientID");
        String Date = request.getParameter("date");
         //Creates a Transaction object referenced in the backend
        Transaction transaction = new Transaction(TransactionID, Amount, Description, ClientID, Date);
         //Calls the MySQL update method. 
        transaction.Update(TransactionID, Amount, Description, ClientID, Date);


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
        <h1>Transaction Update</h1>


        <div class="row">
            <div class="col-sm-4">
                <form  method="POST" action="#" >

                    <%  
                         //Creates a resultset object to read the MySQL query. 
                        ResultSet rs;
                        //Calls the id parameter which referenced the id of the record we want to update.
                        String id = request.getParameter("id");
                         //Creates an enity object to be assigned to the Transaction object using polymorphism. 
                        Enity transaction = new Transaction();
                         
                        rs = transaction.SelectWhere(id);
                        //reads through the result set. Mixed in the html to allow the record to be selected based upon a specified ID. 
                        while (rs.next()) {

                    %>


                    <div alight="left">
                        <label class="form-label">Amount</label>
                        <input type="text" class="form-control" placeholder="Amount" name="amount" value="<%= rs.getInt("Amount")%>" id="course" required >
                    </div>

                    <div alight="left">
                        <label class="form-label">Description</label>
                        <input type="text" class="form-control" placeholder="Description" name="description" id="description" value="<%= rs.getString("Description")%>" required >
                    </div>

                    <div alight="left">
                        <label class="form-label">Client ID</label>
                        <input type="text" class="form-control" placeholder="ClientID" name="clientID" id="clientID" value="<%= rs.getInt("ClientID")%>" required >
                    </div>

                    <div alight="Date">
                        <label class="form-label">Date</label>
                        <input type="text" class="form-control" placeholder="Date" name="date" value="<%= rs.getDate("Date")%>" id="Date" required >
                    </div>


                    <% }%>



                    </br>

                    <div alight="right">
                        <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                        <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                    </div>  

                    <div align="right">

                        <p><a href="indexTransaction.jsp">Click Back</a></p>


                    </div>

                </form>
            </div>          
        </div>

    </body>
</html>