<%-- 
    Document   : displaySearchedBooks
    Created on : 02-Dec-2018, 13:43:10
    Author     : Audrius
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Business.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "header.jsp" %>
    <body>
        <div class="container">
            <h1 id="allbookstitle">Searched books</h1>
            <%                ArrayList<Book> foundBooks = (ArrayList<Book>) session.getAttribute("foundBooks");
                if (foundBooks != null) {
                    //deal with displaying

            %>

            <table id="allbooks">
                <thead>
                    <tr>
                        <th>Title</th>
                        <th>Author</th>
                    </tr>
                </thead>
                <tbody>
                    <%                // Loop to print out all of the rows
                        for (Book b : foundBooks) {
                    %>
                    <!-- Create a row for this customer -->
                    <tr>
                        <!-- Create a cell for each component of this customer's information and fill it with 
                             data in this customer's object -->
                        <td><a href="viewBook.jsp?bookNum=<%=b.getIsbn()%>"><%=b.getTitle()%></a></td>
                        <td><%=b.getAuthor()%></td>
                        <!-- Line to generate a tailored link that will go to the view Customer page and pass it the id of the customer to be selected from the database -->


                    </tr>
                    <%
                            // Close the loop
                        }
                        // We have finished with the list of found books 
                        // so now we can remove the value from the session
                        session.removeAttribute("foundBooks");
                    %>
                </tbody>
            </table>
            <% } else {%>
            No books was found.
            <%}%>
            <%@ include file = "footer.jsp" %>

    </body>
</html>
