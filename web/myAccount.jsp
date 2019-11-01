<%-- 
    Document   : myAccount
    Created on : 02-Dec-2018, 15:53:16
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
     <%@ include file = "header.jsp" %>
    
     
    <body>
        <center>

        <div class="container">
            <%            if (loggedInUser != null) {
            %>
            <p class="greeting3"><%=dataBundle.getString("myAcc_username")%> : <%=loggedInUser.getUsername()%></p>
            <p class="greeting3"><%=dataBundle.getString("myAcc_firstName")%> : <%=loggedInUser.getFirstName()%></p>
            <p class="greeting3"><%=dataBundle.getString("myAcc_secondName")%> : <%=loggedInUser.getLastName()%></p>
            <p class="greeting3"><%=dataBundle.getString("myAcc_password")%> : <%=loggedInUser.getPassword()%></p>

            <input class="buttons2" type="button" onclick="window.location.href = 'updateAccount.jsp'" value="<%=dataBundle.getString("myAcc_updateLink")%>" />&nbsp;&nbsp;

            <%
                //end of what happens if theyre logged in
            } else {
            %>

         

        <h1>Log in to see user profile</h1>
        

        <%
            }
        %>
     </div>
    </center>
    <%@ include file = "footer.jsp" %>
</body>
</html>
