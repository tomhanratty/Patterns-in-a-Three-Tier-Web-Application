<%-- 
    Document   : displayAllBooks
    Created on : 30-Nov-2018, 16:22:52
    Author     : Tom
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Business.Book"%>
<%@page import="DAO.BookDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "header.jsp" %>


    <body>
    <center>

        <div class="container">
            <h1 id="allbookstitle"><%=dataBundle.getString("displayall_allbooks")%></h1>

            <%            BookDao bookDao = new BookDao("library");
                ArrayList<Book> books = bookDao.getAllBooks();
                // If there is a customer list to use in the session (and it's not empty)
                // Carrying out this check avoids the page breaking when the session times out

                if (books != null && !books.isEmpty()) {
                    // Get the error message variable out of the session
                    Object msg = session.getAttribute("errorMessage");
                    // Cast it to a String so we can use it
                    String error = (String) msg;
                    if (error != null) {
                        // Display the message
            %>

            <div> <%=error%> </div>
            <%
                }
                // We have finished with the results of this action
                // so now we can remove the value from the session
                session.removeAttribute("errorMessage");
                // This makes sure that old error messages 
                // don't mistakenly get printed out later
            %>
            <!-- set up table structure -->




            <table id="allbooks">
                <thead>
                    <tr>
                        <th><%=dataBundle.getString("displayAllBookTitleHeading")%></th>
                        <th><%=dataBundle.getString("displayAllBookAuthorHeading")%></th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Loop to print out all of the rows
                        for (Book b : books) {
                    %>
                    <!-- Create a row for this customer -->
                    <tr>
                        <!-- Create a cell for each component of this customer's information and fill it with 
                             data in this customer's object -->
                        <td><a href="viewBook.jsp?bookNum=<%=b.getIsbn()%>&bookTitle=<%=b.getTitle()%>"><%=b.getTitle()%></a></td>
                        <td><%=b.getAuthor()%></td>
                        <td id="minipic"><img id="bookimage2" src="img/<%=b.getImageName()%>"</td>
                        <!-- Line to generate a tailored link that will go to the view Customer page and pass it the id of the customer to be selected from the database -->


                    </tr>
                    <%
                            // Close the loop
                        }
                    %>
                </tbody>
            </table>
        </div>
        <%
            } else {
                out.println("No customers found. Please try again.");
            }
        %>

    </center>
    <%@ include file = "footer.jsp" %>

</body>
</html>
