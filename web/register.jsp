<%-- 
    Document   : register
    Created on : 30-Nov-2018, 15:32:51
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
    
    <body>
        <div class="con"><center>
            <h2 id="sign"><%=dataBundle.getString("register_title")%></h2><hr style="border: 2px groove white;"/>
            <form action="TheServlet" method="post" autocomplete="off">

                <input name="firstName" required size=20 type="text" placeholder="<%=dataBundle.getString("register_fname")%>"/> 
                <input name="lastName" required size=40 type="text" placeholder="<%=dataBundle.getString("register_lname")%>"/> 
                <input name="username" required size=20 type="text" placeholder="<%=dataBundle.getString("register_username")%>"/> 
                <input name="password" required size=50 type="password" placeholder="<%=dataBundle.getString("register_password")%>"/> 


                <hr style="border: 2px groove white;"/>
                <button id="button" type="submit">&nbsp; <%=dataBundle.getString("register_button")%></button><br><br>

                <!-- Include a hidden field to identify what the user wants to do -->
                <input type="hidden" name ="action" value="register" />
            </form>
            <label id="signup"><%=dataBundle.getString("register_alreadyregistered")%> &nbsp;<a id="account" href="index.jsp"><%=dataBundle.getString("register_loginhere")%></a></label>
            </center>
        </div>
        
        <!-- Include a footer so that there is always a link back to the home page! -->
        <%@ include file = "footer.jsp" %>
        
    </body>
</html>
