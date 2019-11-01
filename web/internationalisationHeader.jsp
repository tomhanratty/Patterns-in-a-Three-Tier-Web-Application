<%-- 
    Document   : internationalisationHeader
    Created on : 02-Dec-2018, 17:01:58
    Author     : Tom
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.util.ResourceBundle"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    // Retrieve the appropriate Locale - check if it's already been set within the site
    Locale clientLocale = (Locale) session.getAttribute("currentLocale");

    // If there was no locale already set -- it's their first time here or their session timed out
    if(clientLocale == null){
        // Get the locale for the client's browser (that is what's stored in the request)
        clientLocale = request.getLocale();
        // Save it as the currently selected locale
        session.setAttribute("currentLocale", clientLocale);
    }
%>
<!-- create a form to change the language based on changing the drop down selection -->
<form action="TheServlet" method="post">
    <!-- When the value of the drop down changes, 
    submit the form and send the value to the controller -->
    <select id="StyledSelect" name ="language" onchange="this.form.submit()">
        <%
            String language = (String) session.getAttribute("language");
            if(language == null || language.equals("en")){
                
        %>
            <option value="en" selected>English</option>
            <option value="fr">Français</option>
            <option value="de">Deutsche</option>
            <option value="es">Español</option>
        <%
            }else{
        %>
            <option value="en">English</option>
            <option value="fr" selected>Français</option>
            <option value="de">Deutsche</option>
            <option value="es">Español</option>
        <%        
            }
        %>
    </select>
    <input type="hidden" name="action" value="changeLanguage"/>
</form>
<!-- Create the resource bundle we're going to be using in all pages.
    Putting it in here means we don't need to repeat it in every page
--> 
<%
    // Retrieve the resource bundle from the session
    ResourceBundle dataBundle = (ResourceBundle) session.getAttribute("dataBundle");
    // If there is no bundle stored (i.e. if this is the first time you're coming to the site)
    if(dataBundle == null){
        // Create a resource bundle based on the client's current locale settings
        dataBundle = ResourceBundle.getBundle("properties.library", clientLocale);

        // Store this resource bundle for future use
        session.setAttribute("dataBundle", dataBundle);
    }

%>
