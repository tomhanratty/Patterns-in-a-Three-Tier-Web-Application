/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.Loan;
import Business.User;
import DAO.BookDao;
import DAO.LoanDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Audrius
 */

    /**
     * This command takes the logged in user's username , and the title of the book they loaned. 
     * This book's quantity is updated by 1 and the 
     * loan table is updated with the return date.
     *
     * @param username - the username of the logged in user
     * @param bookTitle =  the title of the book the user wants to return 
     * @return-  transfers the user to page to see their current loans.
     */
public class ReturnBookCommand implements Command {

    public ReturnBookCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String bookTitle = request.getParameter("bookTitle");
        //get user and book details

        if (username != null && bookTitle != null && !username.equals("") && !bookTitle.equals("")) {

            int bookIsbn = 0;
            boolean numberSupplied = true;

//            try {
//                //parse number information
//                bookIsbn = Integer.parseInt(request.getParameter("isbn"));
//            //deal if there was not a number supplied
//            } catch (NumberFormatException e) {
//                numberSupplied = false;
//                forwardToJsp = "error.jsp";
//                // Add an error message to the session to be displayed on the error page
//                // This lets us inform the user about what went wrong
//                session.setAttribute("errorMessage", "Text was supplied for the amount to be updated by.");
//            }
            if(numberSupplied) {
                LoanDao loanDao = new LoanDao("library");
                BookDao bookDao = new BookDao("library");
                
                if(loanDao.isLoanExist(bookTitle, username)){
                    if(loanDao.changeLoanStatus(bookTitle, username)){
                        bookDao.increaseBookStock(bookTitle);
                        List<Loan> l = loanDao.getAllLoanSinceJoin(username);
                        session.setAttribute("allLoans", l);
                        forwardToJsp = "displayAllLoans.jsp";
                    }
                } else {
                    //loan does not exist or book bin return already
                    String errorMessage = "Loan does not exist, or book bin "
                            + "returned already";
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
                }
            }
        } else {
            // The book title was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your username or bookTitle was missing. Please try again.";
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

}
