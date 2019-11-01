<%-- 
    Document   : footer
    Created on : 30-Nov-2018, 15:33:51
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<br><br>
<footer>
    <center>
        <div id="popper">

            <div id="footerlogo">
                <img id="logoLib" src="img/footerLogo.png" alt="Lamborghini Logo" >
                <p id="footertext"><%=dataBundle.getString("footer_copyright")%> &copy; <%=dataBundle.getString("footer_libapp")%>.</p>
            </div>

            <p class="author"> <%=dataBundle.getString("footer_created")%>: Audrius Senkus, Tom Hanratty & Ahmed Khan.<br><br>
                <%=dataBundle.getString("footer_changeLink")%></p>


            <select id="StyledSelect" name="language" onchange="this.form.submit()">

                <option value="en" selected>English</option>
                <option value="fr">Français</option>
                <option value="de">Deutsche</option>
                <option value="es">Español</option>
            </select>

        </div>
    </center>
</footer>
