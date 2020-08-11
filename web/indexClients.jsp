<%-- 
    Document   : indexClients
    Created on : Aug 9, 2020, 6:05:15 PM
    Author     : drych
--%>

<%@page import="java.sql.*" %> 
<%@page import="Backend.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<%
    //If submit button is clicked
    if (request.getParameter("submit") != null) {
        //Uses strings to request the parameters input text name made in html. 
        String ClientID = request.getParameter("ClientID");
        String Clientname = request.getParameter("ClientName");
        //Creates a client object referenced in the backend
        Client client = new Client(ClientID, Clientname);
        //Calls the MySQL add method. 
        client.Add(ClientID, Clientname);
%> 
<script>
    alert("Record Addded!");
</script> 
<%        }
%>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
<a href="indexTransaction.jsp" class="Transaction">Go to Transaction page</a>
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
        .Transaction {
            margin-top: 5px;
            margin-left: 200px;
            font-size: 20px;
            color: #1E90FF;
            text-align: center;


        }
    </style>
    <h1>Client Create and Update using-JSP</h1>
    </br>
    <div class="row">
        <div class="col-sm-4">
            <form  method="POST" action="#" > 

                <div alight="left">
                    <label class="form-label">Client ID</label>
                    <input type="text" class="form-control" placeholder="ClientID" name="ClientID" id="ClientID" required >
                </div>

                <div alight="left">
                    <label class="form-label">Client Name</label>
                    <input type="text" class="form-control" placeholder="ClientName" name="ClientName" id="ClientName" required >
                </div>
                <div alight="right">
                    <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                    <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                </div>  

            </form>
        </div>

        <div class="col-sm-8">
            <div class="panel-body">
                <table id="tbl-student" class="table table-responsive table-bordered" cellpadding ="0" width="100%">
                    <thead>
                        <tr>
                           
                            <th>Client Name</th>
                            <th>Edit</th>
                        </tr>  

                        <%
                            //Creates a resultset object to read the MySQL query. 
                            ResultSet rs;
                            //Creates an enity object to be assigned to the client object using polymorphism. 
                            Enity client = new Client();
                            //calls the abtract select method
                            rs = client.Select();
                            //read through the result set. Mixed in the html to allow the table read. 
                            while (rs.next()) {
                                String id = rs.getString("ClientID");
                        %>

                        <tr>
                           
                            <td><%=rs.getString("Clientname")%></td>
                            <td><a href="updateClients.jsp?id=<%=id%>">Edit</a></td>

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