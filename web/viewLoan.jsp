<%-- 
    Document   : viewLoan
    Created on : Dec 5, 2018, 4:10:19 PM
    Author     : Audrius
--%>

<%@page import="Business.Loan"%>
<%@page import="DAO.LoanDao"%>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "header.jsp" %>

    <body>
        <div class="container">
            <h1 id="allbookstitle">View Single Loan</h1>
            <%                String bookTitle = request.getParameter("bookTitle");
                String loanUsername = request.getParameter("loanUsername");

                //confirm that isbn and username was provided
                if (bookTitle != null && loanUsername != null && !bookTitle.equals("") && !loanUsername.equals("")) {
                    //try to cast isbn string to a number
                    int loanIsbn = 0;

                    //try {
                    //    loanIsbn = Integer.parseInt(loanIsbnText);
                    // } catch (NumberFormatException e) {
                    //set an error message to inform user
                    //and send it back to error.jsp page
                    //    String errorMessage = "Text was supplied for the customer number (instead of a number)";
                    //    session.setAttribute("errorMessage", errorMessage);
                    //  response.sendRedirect("error.jsp");
                    // }
                    //if everything is appropriate
                    //get the specific loan from database
                    LoanDao loanDao = new LoanDao("library");
                    Loan l = loanDao.findLoanByUsernameAndIsbn(bookTitle, loanUsername);
                    if (l != null) {


            %>
            <form action="TheServlet" method="post">
                <!-- set up table structure -->
                <table id="allloans">
                    <thead>
                        <tr>
                            <th>ISBN</th>
                            <th><%=dataBundle.getString("loanTableHead_username")%></th>
                            <th><%=dataBundle.getString("loanTableHead_issueDate")%></th>
                            <th><%=dataBundle.getString("loanTableHead_returnDate")%></th>
                        </tr>
                    </thead>
                    <!-- Create a row for this customer -->
                    <tbody>
                        <tr>
                            <!-- Create a cell for each component of this customer's information and fill it with 
                                 data in this customer's object -->
                            <td><%=l.getBookTitle()%></td>
                            <td><%=l.getUsername()%></td>
                            <td><%=l.getIssueDate()%></td>
                            <td><%=l.getReturnDate()%></td>
                        </tr>
                    </tbody>
                </table>
                <%if (loggedInUser != null) {%>


                <center>
                    <input class="buttonLoan" type="submit"  value="Return This Book" >&nbsp;&nbsp;
                </center>                


                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="bookTitle" value="<%=l.getBookTitle()%>" /> 
                <input type="hidden" name ="username" value="<%=l.getUsername()%>" /> 
                <input type="hidden" name ="action" value="returnBook" />  
                <% }%>
            </form>
            <%
            } else {
            %>
            <div id="minimessage2"><b>No customer was found with that number.</b></div>
            <%
                    }
                    // Close the if statement
                } else {
                    // Set an error message and redirect to the error page
                    String errorMessage = "No Book number or username was supplied";
                    session.setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("error.jsp");
                }
            %>
        </div>
                <%@ include file = "footer.jsp" %>

    </body>
</html>