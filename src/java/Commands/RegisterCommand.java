/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.User;
import DAO.UserDao;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tom
 */

    /**
     *this command takes the information provided by the user and add a new record to the users table
     * 
     * @param username = the username to be added 
     * @param password = the users password to be added 
     * @param first = the users first name 
     * @param last = the users last name 
     * @param adminLevel = a boolean to say if the user is an admin or not , false by default
     * @param response = if the registration was successful the user is sent to the index page and a session started with their information,
     *                    if the registration fails the user is sent to an error page and informed of the error
     * @return
     */
public class RegisterCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        String generatedPassword = null;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String first = request.getParameter("firstName");
        String last = request.getParameter("lastName");
        boolean adminLevel = false;

        if (username != null && !username.equals("") && password != null && !password.equals("") && first != null && !first.equals("")) {

            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(password.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest();
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < bytes.length; i++) {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                String errorMessage = "Server side error";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }

            UserDao uDao = new UserDao("library");
            User toAdd = new User(username, generatedPassword, first, last, adminLevel);
            boolean added = uDao.addUser(toAdd);

            if (added != false) {
                // Registration was successful, log the user in!
                User u = uDao.findUserByUsernamePassword(username, generatedPassword);

                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", u);

                forwardToJsp = "index.jsp";
            } else {
                // The user couldn't be added to the database
                // Send the user to the error page and inform them of this
                String errorMessage = "User couldn't be added to the database at this time"
                        + "Please <a href='register.jsp'>go back</a> and try again.<br/>Try a different username!";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
        } else {
            // One or more fields were missing
            // Send the user to the error page and inform them of this
            String errorMessage = "One or more fields were missing. Please <a href='register.jsp'>go back</a> and try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }

        return forwardToJsp;
    }

}
