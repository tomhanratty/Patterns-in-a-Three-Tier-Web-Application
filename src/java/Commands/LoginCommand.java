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
     *This command takes the information provided on the the login screen , checks it against the user table and either logs in the user or 
     * informs them there was a problem with the information they provided
     * 
     * @param username - the username provided on the login screen
     * @param password - the password provided on the login screen
     * @param response - if the log in is successful the user is informed and a session is stated using their details.
     *                    if the log in fails the user is sent to an error page and informed of the error.
     * @return
     */
public class LoginCommand implements Command {

    //@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        String generatedPassword = null;
        // Get the username and password information that the user
        // entered into the form on the previous page
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if(username != null && password != null && !username.equals("") && !password.equals(""))
        {
            
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
                forwardToJsp = "index.jsp";
            }
            
            UserDao uDao = new UserDao("library");  //change name of DB
            User u = uDao.findUserByUsernamePassword(username, generatedPassword);
            // If a user was found, then there is a user with this username and password
            // so we can log them in
            if(u!= null){
                // Put the user in the session 
                // (we can use this to track if the user has logged in -
                // if it's there, they they have completed this process
                // if it's not, then they haven't)
                HttpSession session = request.getSession();
                session.setAttribute("loggedInUser", u);
                forwardToJsp = "index.jsp";
            }
            else{
                // The username and/or password didn't match someone in the database
                // Send the user to the error page and inform them of this
                String errorMessage = "No user found matching those details.";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "index.jsp";
            }
        }
        else{
            // The username and/or password was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your username and/or password was missing. Please <a href='login.jsp'>go back</a> and try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "logInForm.jsp";
        }
        return forwardToJsp;
    }
}
