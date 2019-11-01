<%-- 
    Document   : updateAccount
    Created on : 02-Dec-2018, 18:50:30
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@ include file = "head.jsp" %>
    <%@ include file = "nav.jsp" %>
     <%@ include file = "header.jsp" %>
    <body>
        <div class="container">

            <form action="TheServlet" method="post" autocomplete="off">

                <center>
                    <div class="con2">
                        <h2 id="sign2"><%=dataBundle.getString("updateAcc_updatedetails")%></h2><hr style="border: 2px dotted white;"/>

                        <label><%=dataBundle.getString("updateAcc_currentusername")%></label>&nbsp;&nbsp;
                        <input name="username" required size=20 type="text" placeholder="<%=dataBundle.getString("updateAcc_enterhere")%>"/><br>

                        <label><%=dataBundle.getString("updateAcc_currentpassword")%></label>&nbsp;&nbsp;
                        <input name="password" required size=50 type="password" placeholder="<%=dataBundle.getString("updateAcc_enterhere")%>"/><br>

                        <label><%=dataBundle.getString("updateAcc_newfirstname")%></label>&nbsp;&nbsp;
                        <input name="firstName" required size=50 type="text" placeholder="<%=dataBundle.getString("updateAcc_enterhere")%>"/><br>

                        <label><%=dataBundle.getString("updateAcc_newlastname")%></label>&nbsp;&nbsp;
                        <input name="lastName" required size=40 type="text" placeholder="<%=dataBundle.getString("updateAcc_enterhere")%>"/><br>

                        <hr style="border: 2px dotted white;" />
                        <button id="button" type="submit" value="update"><%=dataBundle.getString("updateAcc_updatebutton")%></button><br><br>

                        <!-- Include a hidden field to identify what the user wants to do -->

                        <input type="hidden" name ="action" value="updateUser" />
                    </div>

                </center>
              
            </form>
        </div>
        <%@ include file = "footer.jsp" %>
    </body>
</html>
