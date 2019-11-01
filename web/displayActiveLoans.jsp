<%-- 
    Document   : displayActiveLoans
    Created on : 02-Dec-2018, 18:01:47
    Author     : Audrius
--%>

<%@page import="java.util.List"%>
<%@page import="DAO.LoanDao"%>
<%@page import="Business.Loan"%>
<%@page import="java.util.ArrayList"%>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "header.jsp" %>

    <body>
        <div class="container">
            <%            if (loggedInUser != null) {
            %>
            <h1 id="allbookstitle"><%=dataBundle.getString("loanTableHead_activeLoan")%></h1>

            <%                LoanDao loanDao = new LoanDao("library");
                User loggedUser = (User) session.getAttribute("loggedInUser");
                String username = loggedUser.getUsername();
                List<Loan> activeLoans = loanDao.getActiveLoanForMemeber(username);
                if (activeLoans != null) {
                    //display loans    
%> 

            <table id="allloans">
                <thead>
                    <tr>
                        <th>ISBN</th>
                        <th><%=dataBundle.getString("loanTableHead_username")%></th>
                        <th><%=dataBundle.getString("loanTableHead_issueDate")%></th>
                    </tr>
                </thead>
                <tbody>
                    <% //loop and print out all active loans
                        for (Loan l : activeLoans) {
                    %>
                    <!-- Create a row for this loan -->

                    <tr>
                        <td><a href="viewLoan.jsp?bookTitle=<%=l.getBookTitle()%>&loanUsername=<%=l.getUsername()%>"><%=l.getBookTitle()%></a></td>
                        <td><%=l.getUsername()%></td>
                        <td><%=l.getIssueDate()%></td>
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
            <% } else { %>
            <p id="minimessage"><%=dataBundle.getString("loanTableHead_notSignedInMsg")%></p>

            <% }%>
        </div>
            <%@ include file = "footer.jsp" %>

    </body>
</html>