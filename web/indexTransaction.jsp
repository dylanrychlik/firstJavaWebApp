<%-- 
    Document   : index
    Created on : Aug 6, 2020, 8:38:22 PM
    Author     : Dylan Rychlik
--%>
<%@page import="java.sql.*" %> 
<%@page import="Backend.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%
//If submit button is clicked
    if (request.getParameter("submit") != null) {
        //Uses strings to request the parameters of the input textfields. 
        String TransactionID = request.getParameter("TransactionID");
        String Amount = request.getParameter("Amount");
        String Description = request.getParameter("Description");
        String ClientID = request.getParameter("ClientID");
        String Date = request.getParameter("Date");
 //Creates a transaction object referenced in the backend
        Transaction transaction = new Transaction(TransactionID, Amount, Description, ClientID, Date);
//Calls the MySQL add method. 
        transaction.Add(TransactionID, Amount, Description, ClientID, Date);
%> 
<script>
    alert("Record Addded!");
</script> 
<%        }
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
<a href="indexClients.jsp" class="Client">Go to Clients page</a>
<a href="index.html" class="Home">Go to home page</a>
<link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css"/>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <style>


         .Home {
                 margin-top: 5px;
               margin-left:  220px;
                text-align: center;
                font-size: 20px;
                color: #1E90FF;
               margin-right: 250px;
              

            }
             .Client {
               margin-top: 5px;
               margin-left: 200px;
                font-size: 20px;
                color: #1E90FF;
                 text-align: center;
               

            }

        
    </style>
    <h1>Transaction Create and Update using-JSP</h1>
    </br>
    <div class="row">
        <div class="col-sm-4">
            <form  method="POST" action="#" > 

                <div alight="left">
                    <label class="form-label">Transaction ID</label>
                    <input type="text" class="form-control" placeholder="TransactionID" name="TransactionID" id="TransactionID" required >
                </div>

                <div alight="left">
                    <label class="form-label">Amount</label>
                    <input type="text" class="form-control" placeholder="Amount" name="Amount" id="amount" required >
                </div>

                <div alight="left">
                    <label class="form-label">Description</label>
                    <input type="text" class="form-control" placeholder="Description" name="Description" id="description" required >
                </div>

                <div alight="left">
                    <label class="form-label">Client ID</label>
                    <input type="text" class="form-control" placeholder="ClientID" name="ClientID" id="ClientID" required >
                </div>

                <div alight="left">
                    <label class="form-label">Date</label>
                    <input type="text" class="form-control" placeholder="Date" name="Date" id="date" required >
                </div>
                </br>

                <div alight="right">
                    <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                    <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                </div>  

            </form>
        </div>

        <div class="col-sm-8">
            <div class="panel-body">
                <table id="tbl-transaction" class="table table-responsive table-bordered" cellpadding ="0" width="100%">
                    <thead>
                        <tr>
                          
                            <th>Amount</th>
                            <th>Description</th>
                            <th>Client ID</th>
                            <th>Date</th>
                            <th>Edit</th>
                        </tr>  

                        <%
                             //Creates a resultset object to read the MySQL query. 
                            ResultSet rs;
                             //Create an enity object to be assigned to the client object using polymorphism. 
                            Enity transaction = new Transaction();
                             //calls the abtract select method with the 'rs' variable as the return type.
                            rs = transaction.Select();
                             //reads through the result set. Mixed in the html to allow the table read.
                            while (rs.next()) {
                                String id = rs.getString("TransactionID");
                        %>

                        <tr>
                            
                            <td><%=rs.getString("Amount")%></td>
                            <td><%=rs.getString("Description")%></td>
                            <td><%=rs.getString("ClientID")%></td>
                            <td><%=rs.getString("Date")%></td>
                            <td><a href="updateTransaction.jsp?id=<%=id%>">Edit</a></td>

                        </tr> 


                        <%

                            }
                        %>

                </table>    
            </div>

        </div>  
    </div>

</body>
</html>
