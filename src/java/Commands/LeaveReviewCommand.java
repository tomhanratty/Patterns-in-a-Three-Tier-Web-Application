/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.User;
import DAO.ReviewDao;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Audrius
 */

    /**
     * The command takes the logged in user's username , the title of the book currently being viewed and a text based review the user inputs.
     * The review is then added to the review table with the book title and the username
     *
     * @param loggedInUser - the user in this session
     * @param username - the user's username
     * @param review - text review the user left for the book 
     * @param bookTitle - the title of the book
     * 
     * @return - if success
     */
public class LeaveReviewCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");
        String username = loggedInUser.getUsername();
        String review = request.getParameter("reviewText");
        String bookTitle = request.getParameter("bookTitle");
        
        if(username != null && review != null && !username.equals("") && !review.equals("")){
            ReviewDao reviewDao = new ReviewDao("library");
            if(reviewDao.addReviewForBook(review, username, bookTitle)){
                forwardToJsp = "displayAllBooks.jsp";
            }
            
        } else {
            // The book title was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your username, review or book title was missing. Please try again.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        
        return forwardToJsp;
    }
    
}
