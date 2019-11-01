/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Tom
 */

    /**
     * This command logs the user out my ending their session. 
     * 
     * 
     * @return the session is invalidated and ended
     */
public class LogoutCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = "index.jsp";
        HttpSession session = request.getSession();
        // To log a user out, wipe their session clear
        session.invalidate();
        return forwardToJsp;
    }
    
} 
