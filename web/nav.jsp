<%-- 
    Document   : nav
    Created on : 30-Nov-2018, 15:02:07
    Author     : Tom
--%>
<%@include file="internationalisationHeader.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>


    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="index.jsp"><%=dataBundle.getString("nav_title")%></a>
            </div>
            <ul class="nav navbar-nav">
                <li class="nav"><a href="index.jsp"><%=dataBundle.getString("nav_indexLink")%></a></li>
                <li class="nav"><a href="displayAllBooks.jsp"><%=dataBundle.getString("nav_AllBooksLink")%></a></li>
                <li class="nav"><a href="displayAllLoans.jsp"><%=dataBundle.getString("nav_AllLoansLink")%></a></li>
                <li class="nav"><a href="displayActiveLoans.jsp"><%=dataBundle.getString("nav_ActiveLoans")%></a></li>
            </ul>
            <form class="navbar-form navbar-left" action="TheServlet" method="post"> 
                <select name="searchOption">
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                </select>
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="<%=dataBundle.getString("nav_search")%>" name="book">
                </div>
                <input type="hidden" name ="action" value="searchBook" />
                <button type="submit" class="btn btn-default"><%=dataBundle.getString("nav_submit")%></button>
            </form>
        </div>
    </nav>



</html>
