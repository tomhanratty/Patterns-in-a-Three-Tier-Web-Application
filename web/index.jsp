<%-- 
    Document   : index
    Created on : 30-Nov-2018, 14:20:53
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <%@ include file = "nav.jsp" %>
    <%@ include file = "head.jsp" %>
    <%@ include file = "header.jsp" %>



    <body>
        <div class="container">
            <%// Get the error message variable out of the session
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
            <%            if (loggedInUser != null) {
            %>


            <h1 id="greeting2"><%=dataBundle.getString("index_loggedInMsg")%></h1>
            <center>
                <input class="buttons" type="button" onclick="window.location.href = 'myAccount.jsp'" value="<%=dataBundle.getString("index_accountMsg")%>" />&nbsp;&nbsp;
                <input class="buttons" type="button" onclick="window.location.href = 'TheServlet?action=logout'" value="<%=dataBundle.getString("index_LogOutMsg")%>" />&nbsp;&nbsp;
            </center>
            <%
                //end of what happens if theyre logged in
            } else {
            %>


            <h1 id="notlogged"><%=dataBundle.getString("index_LogInCheckMsg")%></h1>



            <%
                }
            %>
        </div>

        <%@ include file = "footer.jsp" %>

    </body>




</html>
