<%-- 
    Document   : viewBook
    Created on : 30-Nov-2018, 16:29:38
    Author     : Tom
--%>

<%@page import="DAO.LoanDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Business.Review"%>
<%@page import="DAO.ReviewDao"%>
<%@page import="java.util.List"%>
<%@page import="Business.Book"%>
<%@page import="DAO.BookDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "head.jsp" %>
    <%@ include file = "header.jsp" %>

    <body>


        <div class="container">


            <%                // Get the number of the customre that was clicked on
                String idVal = request.getParameter("bookNum");
                String title = request.getParameter("bookTitle");
                boolean confirmation = false;
                // Confirm that a number was supplied
                if (idVal != null) {
                    // Parse the number from text into an int (remember, all request parameters are Strings)
                    int custNum = 0;
                    try {
                        custNum = Integer.parseInt(idVal);
                    } // Provide code to handle what happens if there wasn't a number passed in
                    catch (NumberFormatException e) {
                        // Set an error message and redirect to the error page
                        String errorMessage = "Text was supplied for the customer number (instead of a number)";
                        session.setAttribute("errorMessage", errorMessage);
                        response.sendRedirect("error.jsp");
                    }

                    // If everything is appropriate, 
                    // get the specific Customer from the database and display it
                    BookDao bookDao = new BookDao("library");
                    Book b = bookDao.findBookByISBN(custNum);
                    try {
                        String username = loggedInUser.getUsername();
                        LoanDao loanDao = new LoanDao("library");

                        confirmation = loanDao.doesUserHadBook(title, username);
                    } catch(Exception e){
                        System.out.println(e);
                    }
                    //LoanDao loanDao = new LoanDao("library");

                    //boolean confirmation = loanDao.doesUserHadBook(title, username);
                    ReviewDao reviewDao = new ReviewDao("library");
                    ArrayList<Review> reviews = reviewDao.getReviewsForBook(title);
                    if (b != null) {

            %>  
            <form action="TheServlet" method="post">
                <!-- set up table structure -->
                <table id="onebook">
                    <thead>
                        <tr>
                            <th>ISBN</th>
                            <th><%=dataBundle.getString("singleBookTitleHeading")%></th>
                            <th><%=dataBundle.getString("singleBookLanguageHeading")%></th>
                            <th><%=dataBundle.getString("singleBookYearPubHeading")%></th>
                            <th><%=dataBundle.getString("singleBookAuthorHeading")%></th>   
                            <th><%=dataBundle.getString("singleBookCopiesHeading")%></th>
                            <th><%=dataBundle.getString("singleBookImageHeading")%></th>
                        </tr>
                    </thead>
                    <!-- Create a row for this customer -->
                    <tbody>
                        <tr>
                            <!-- Create a cell for each component of this customer's information and fill it with 
                                 data in this customer's object -->
                            <td><%=b.getIsbn()%></td>
                            <td><%=b.getTitle()%></td>
                            <td><%=b.getBookLanguage()%></td>
                            <td><%=b.getYearOfPublication()%></td>
                            <td><%=b.getAuthor()%></td>
                            <td><%=b.getAvailableNoOfCopies()%></td>
                            <td><img id="bookimage" src="img/<%=b.getImageName()%>"></td>
                        </tr>
                    </tbody>
                </table>
                <%if (loggedInUser != null) {%>

                <center>
                    <input class="buttonLoan" type="submit" onclick="window.location.href = 'updateAccount.jsp'" value="Loan This Book" >&nbsp;&nbsp;
                </center> 

                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="bookTitle" value="<%=b.getTitle()%>" />  
                <input type="hidden" name ="action" value="loanBook" /> 


            </form>
            <% }
                if (loggedInUser != null && confirmation == true) {%>
            <center>
                <form action="TheServlet" method="post">
                    <div class="form-group">
                        <label id="textarea" for="review">Leave Review:</label>
                        <textarea class="form-control" rows="5" id="review" name="reviewText"></textarea>
                    </div>
                    <input class="buttons3" type="submit" value="submit"/>
                    <input type="hidden" name ="bookTitle" value="<%=b.getTitle()%>" /> 
                    <input type="hidden" name ="action" value="leaveReview" />
                </form>
            </center>

            <% }

            } else {
            %>
            <div><b>No customer was found with that number.</b></div>
            <%
                }

                if (reviews != null) {
            %>
            <table id="allbooks">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Review</th>
                    </tr>
                </thead>
                <!-- Create a row for this customer -->
                <tbody>
                    <%for (Review r : reviews) {%>
                    <tr>
                        <!-- Create a cell for each component of this customer's information and fill it with 
                             data in this customer's object -->
                        <td><%=r.getUsername()%></td>
                        <td><%=r.getReview()%></td>
                    </tr> <% } %>
                </tbody>
            </table> <% } %> 


            <%

                    // Close the if statement
                } else {
                    // Set an error message and redirect to the error page
                    String errorMessage = "No Book number was supplied";
                    session.setAttribute("errorMessage", errorMessage);
                    response.sendRedirect("error.jsp");
                }


            %>
        </div>

        <%@ include file = "footer.jsp" %>



    </body>
</html>
