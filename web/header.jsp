<%-- 
    Document   : header
    Created on : 30-Nov-2018, 14:23:34
    Author     : Tom
--%>

<%@page import="Business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <div class="container">
        <div class="page-header">
            <%
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                if (loggedInUser != null) {
            %>

            <div>
                <h2 id="greeting"><%=dataBundle.getString("logInForm_greeting")%>  <%=loggedInUser.getUsername()%></h2>
            </div>

            <%
            } else {
            %>     
            <center>
                    <div class="con">
                        <h2 id="sign"><%=dataBundle.getString("logInForm_title")%></h2><hr style="border: 2px groove white;"/>
                        <form action="TheServlet" method="post" autocomplete="off">

                            <input name="username" required size=20 type="text" placeholder="<%=dataBundle.getString("logInForm_username")%>"/> 

                            <input name="password" required size=50 type="password" placeholder="<%=dataBundle.getString("logInForm_password")%>"/>

                            <hr style="border: 2px groove white;"/>
                            <button id="button" type="submit">&nbsp;<%=dataBundle.getString("logInForm_button")%></button><br><br>

                            <!-- Include a hidden field to identify what the user wants to do -->

                            <input type="hidden" name ="action" value="login" />
                        </form>
                        <label id="signup"><%=dataBundle.getString("logInForm_notRegMsg")%> &nbsp;<a id="account" href="register.jsp"><%=dataBundle.getString("logInForm_notRegLink")%></a></label>
                    </div>
                    <% }%>
                </center>


        </div>

    </div>
</html>
