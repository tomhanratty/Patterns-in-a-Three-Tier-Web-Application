<%-- 
    Document   : displayAllLoans
    Created on : Dec 4, 2018, 5:05:10 PM
    Author     : Audrius
--%>

<%@page import="java.util.List"%>
<%@page import="Business.Loan"%>
<%@page import="DAO.LoanDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "header.jsp" %>

    <body>
        <div class="container">
            <%            if (loggedInUser != null) {
            %>

            <h1 id="allbookstitle"><%=dataBundle.getString("loanTableHead_allloans")%></h1>

            <%  LoanDao loanDao = new LoanDao("library");
                User loggedUser = (User) session.getAttribute("loggedInUser");
                String username = loggedUser.getUsername();
                List<Loan> allLoans = loanDao.getAllLoanSinceJoin(username);
                if (allLoans != null) {
                    //display loans    
%> 

            <table id="allbooks">
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th><%=dataBundle.getString("loanTableHead_username")%></th>
                        <th><%=dataBundle.getString("loanTableHead_issueDate")%></th>
                        <th><%=dataBundle.getString("loanTableHead_returnDate")%></th>
                    </tr>
                </thead>
                <tbody>
                    <% //loop and print out all active loans
                        for (Loan l : allLoans) {
                    %>
                    <!-- Create a row for this loan -->

                    <tr>
                        <td><%=l.getBookTitle()%></td>
                        <td><%=l.getUsername()%></td>
                        <td><%=l.getIssueDate()%></td>
                        <td><%=l.getReturnDate()%></td>
                    </tr>


                    <% }//close loop
                        // We have finished with the list of found loans 
                        // so now we can remove the value from the session

                    %>
                </tbody>
            </table>

            <% } else {%>
            <p><%=dataBundle.getString("loanTableHead_noLoansMsg")%></p>
            <% }%>

            <% } else {%>
            <p id="minimessage"><%=dataBundle.getString("loanTableHead_notSignedInMsg")%></p>

            <% }%>
        </div>
        <%@ include file = "footer.jsp" %>
    </body>
</html>
