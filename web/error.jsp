<%-- 
    Document   : error
    Created on : 30-Nov-2018, 15:57:09
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "nav.jsp" %>
    <%@ include file = "head.jsp" %>
    <body>
        <div id="errorpage">
            <center>
                <h1>Something went wrong</h1>

                <%
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
            </center>
        </div>
        <%@ include file = "footer.jsp" %>
    </body>
</html>

