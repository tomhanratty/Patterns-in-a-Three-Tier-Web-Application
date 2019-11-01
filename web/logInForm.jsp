<%-- 
    Document   : logInForm
    Created on : 02-Dec-2018, 18:06:53
    Author     : Tom
--%>
<%@include file="internationalisationHeader.jsp" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <form action="TheServlet" method="post">
        <table>
            <tr>
                <td> <%=dataBundle.getString("logInForm_username")%>  : </td><td> <input name="username" required size=20 type="text" /> </td> 
            </tr>
            <tr>
                <td> <%=dataBundle.getString("logInForm_password")%>  : </td><td> <input name="password" required size=50 type="password" /> </td> 
            </tr>
        </table>
        <input type="submit" value=<%=dataBundle.getString("logInForm_button")%> />
        <!-- Include a hidden field to identify what the user wants to do -->
        <input type="hidden" name ="action" value="login" />
    </form>


    <div>
        <p>
            <%=dataBundle.getString("logInForm_notRegMsg")%><br/>
            <a href="register.jsp"><%=dataBundle.getString("logInForm_notRegLink")%></a>
        </p>
    </div>
</html>
