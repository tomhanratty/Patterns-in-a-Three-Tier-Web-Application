/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import Business.Book;
import DAO.BookDao;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Audrius
 */

    /**
     * This command takes the title of the book the user wants to search for , checks it against the book table (title) 
     * if found - directs teh user to page to view results of search (the returned book)
     * if not found directs the user to error page with error.
     *
     * @param book - the title of the book the user wants to search for 
     * 
     * @return - if successful , directs user to page with search results (the returned book)
     */
public class SearchBookCommand implements Command {

    public SearchBookCommand() {
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String forwardToJsp = null;

        // Get the string information that the user
        // entered into the form on the previous page
        String book = request.getParameter("book");
        String searchOption = request.getParameter("searchOption");

        if (book != null && !book.equals("") && searchOption != null && !searchOption.equals("")) {
            BookDao bDao = new BookDao("Library");
            ArrayList<Book> b = new ArrayList();
            
            //check user choice and make aproptiate search
            if (searchOption.equalsIgnoreCase("title")) {
                b = bDao.selectBooksContainingTitle(book);

            } else if (searchOption.equalsIgnoreCase("author")) {
                b = bDao.selectBooksContainingAuthor(book);
            }
            //if book  or books was found, then we can storit and display to user
            if (b != null) {
                HttpSession session = request.getSession();
                session.setAttribute("foundBooks", b);
                forwardToJsp = "displaySearchedBooks.jsp";
            } else {
                //user input did not match any book title
                //send the user to error page and inform of this
                String errorMessage = "No books was found matching details provided."
                        + " Please try again.";
                HttpSession session = request.getSession();
                session.setAttribute("errorMessage", errorMessage);
                forwardToJsp = "error.jsp";
            }
//        } else if (searchOption.equalsIgnoreCase("author")) {
//            BookDao bDao = new BookDao("Library");
//            ArrayList<Book> b = bDao.selectBooksContainingAuthor(book);
//            if (b != null) {
//                HttpSession session = request.getSession();
//                session.setAttribute("foundBooks", b);
//                forwardToJsp = "displaySearchedBooks.jsp";
//            } else {
//                //user input did not match any book title
//                //send the user to error page and inform of this
//                String errorMessage = "No books was found matching author details."
//                        + " Please try again.";
//                HttpSession session = request.getSession();
//                session.setAttribute("errorMessage", errorMessage);
//                forwardToJsp = "error.jsp";
//            }
//        }

        } else {
            // The book title was missing
            // Send the user to the error page and inform them of this
            String errorMessage = "Your book title was missing. Please try again.";
            HttpSession session = request.getSession();
            session.setAttribute("errorMessage", errorMessage);
            forwardToJsp = "error.jsp";
        }
        return forwardToJsp;
    }

}
