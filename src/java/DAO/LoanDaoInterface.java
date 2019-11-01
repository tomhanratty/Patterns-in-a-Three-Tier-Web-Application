/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Loan;
import java.util.List;

/**
 *
 * @author Audrius
 */
public interface LoanDaoInterface {

    /**
     *
     * @param username
     * @return
     */
    public List<Loan> getActiveLoanForMemeber(String username);

    /**
     *
     * @param username
     * @return
     */
    public List<Loan> getAllLoanSinceJoin(String username);

    /**
     *
     * @param bookTitle
     * @param username
     * @return
     */
    public boolean returnBook(String bookTitle, String username);

    /**
     *
     * @param bookTitle
     * @param username
     * @return
     */
    public boolean addNewLoan(String bookTitle, String username);

    /**
     *
     * @param bookTitle
     * @param username
     * @return
     */
    public boolean changeLoanStatus(String bookTitle, String username);
    
    /**
     *
     * @param bookTitle
     * @param username
     * @return
     */
    public boolean isLoanExist(String bookTitle, String username);
    
    /**
     *
     * @param bookTitle
     * @param username
     * @return
     */
    public Loan findLoanByUsernameAndIsbn(String bookTitle, String username);
    
    /**
     * 
     * @param bookTitle
     * @param username
     * @return 
     */
    public boolean doesUserHadBook(String bookTitle, String username);
}
