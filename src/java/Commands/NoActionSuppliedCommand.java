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
     * this command sends the user to an error page , when no action has been supplied
     *
     * 
     * 
     * @return String telling the user the error
     */
public class NoActionSuppliedCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // Handle where NO action was supplied
        String forwardToJsp = null;
        String errorMessage = "No valid action was supplied in this request";
        HttpSession session = request.getSession();
        session.setAttribute("errorMessage", errorMessage);
        forwardToJsp = "error.jsp";
        return forwardToJsp;
    }
    
}
