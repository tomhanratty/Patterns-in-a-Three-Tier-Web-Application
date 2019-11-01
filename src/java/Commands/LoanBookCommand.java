/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.User;
import DAO.BookDao;
import DAO.LoanDao;
import Business.Loan;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Audrius
 */
public class LoanBookCommand implements Command {

        /**
     * this command takes the logged in user's username and the title of the book to be loaned, 
     * the users active loans are checked to ensure there is only 1 copy loaned at a time to the user
     * the title is then checked against the book table to make sure there is enough quantity to allow the loan. 
     * 
     * finally, the loan is inserted into the loan table.
     *
     * @param loggedInUser - the current session's user
     * @param username -  the user's username
     * @param bookTitle -  the title of the book to be loaned
     * 
     * @return if successful the user is taken to a page with a table of their active loans, if 
     */
    public LoanBookCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("loggedInUser");

        String username = loggedInUser.getUsername();
        String bookTitle = request.getParameter("bookTitle");

        if (username != null && bookTitle != null && !username.equals("") && !bookTitle.equals("")) {
            LoanDao loanDao = new LoanDao("library");
            BookDao bookDao = new BookDao("library");

            if (!loanDao.isLoanExist(bookTitle, username)) {

                int quantity = bookDao.quantityLeftInStock(bookTitle);
                if (quantity > 0) {
                    if (bookDao.reduceBookStock(bookTitle)) {
                        if (loanDao.addNewLoan(bookTitle, username)) {
                            List<Loan> l = loanDao.getActiveLoanForMemeber(username);
                            session.setAttribute("activeLoans", l);
                            forwardToJsp = "displayActiveLoans.jsp";
                        }
                    }
                } else {
                    //user input did not match any book title
                    //send the user to error page and inform of this
                    String errorMessage = "Sorry, not enough books in a stock."
                            + " Please try again later.";
                    session.setAttribute("errorMessage", errorMessage);
                    forwardToJsp = "displayAllBooks.jsp";
                }

            } else {
                String errorMessage = "Sorry, you can have only one copy at the same time";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "displayAllBooks.jsp";
            }
        } else {
            // The book title was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your username or ISBN was missing. Please try again.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

}
