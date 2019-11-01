/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Loan;
import java.util.List;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Audrius
 */
public class LoanDaoTest {
    private LoanDao loanDao;
    public LoanDaoTest() {
        loanDao = new LoanDao("libraryTest");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getActiveLoanForMemeber method, of class LoanDao.
     */
    @Test
    public void testGetActiveLoanForMemeber() {
        System.out.println("getActiveLoanForMemeber");
        String username = "senkutis123";
        List<Loan> result = loanDao.getActiveLoanForMemeber(username);
        assertEquals(1, result.size());
    }
    
    /**
     * Test of getActiveLoanForMemeber method, of class LoanDao (Where no 
     * match found).
     */
    @Test
    public void testGetActiveLoanForMemeber_NoMatchFound() {
        System.out.println("getActiveLoanForMemeber_NoMatchFound");
        String username = "noUser";
        List<Loan> result = loanDao.getActiveLoanForMemeber(username);
        assertEquals(0, result.size());
    }

    /**
     * Test of getAllLoanSinceJoin method, of class LoanDao.
     */
    @Test
    public void testGetAllLoanSinceJoin() {
        System.out.println("getAllLoanSinceJoin");
        String username = "testUser";
        List<Loan> result = loanDao.getAllLoanSinceJoin(username);
        assertEquals(6, result.size());
    }
    
    /**
     * Test of getAllLoanSinceJoin method, of class LoanDao (Where no 
     * match found).
     */
    @Test
    public void testGetAllLoanSinceJoin_NoMatchFound() {
        System.out.println("getAllLoanSinceJoin");
        String username = "noLoans";
        List<Loan> result = loanDao.getAllLoanSinceJoin(username);
        assertEquals(0, result.size());
    }

    /**
     * Test of returnBook method, of class LoanDao.
     */
    @Test
    public void testReturnBook() {
        System.out.println("returnBook");
        String bookTitle = "Mr. Magic";
        String username = "senkutis123";
        boolean expResult = true;
        boolean result = loanDao.returnBook(bookTitle, username);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of returnBook method, of class LoanDao (Where no 
     * match found).
     */
    @Test
    public void testReturnBook_NoMatchFound() {
        System.out.println("returnBook_NoMatchFound");
        String bookTitle = "Unknows";
        String username = "noUser";
        boolean expResult = false;
        boolean result = loanDao.returnBook(bookTitle, username);
        assertEquals(expResult, result);
    }

    /**
     * Test of addNewLoan method, of class LoanDao.
     */
    @Test
    public void testAddNewLoan() {
        System.out.println("addNewLoan");
        String bookTitle = "Raft";
        String uName = "senkutis123";
        Loan l = new Loan(bookTitle, uName);
        boolean expResult = true;
        boolean result = loanDao.addNewLoan(bookTitle, uName);
        assertTrue(result);
        
        if(result){
            System.out.println("Method returned appropriately, confirming "
                    + "database changed by trying to remove what was added");
            boolean rowDeleted = loanDao.removeLoan(bookTitle, uName);
            assertEquals(rowDeleted, true);
        }
    }

    /**
     * Test of changeLoanStatus method, of class LoanDao.
     */
    @Test
    public void testChangeLoanStatus() {
        System.out.println("changeLoanStatus");
        String bookTitle = "";
        String uName = "";
        LoanDao instance = null;
        boolean expResult = false;
        boolean result = instance.changeLoanStatus(bookTitle, uName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLoanExist method, of class LoanDao.
     */
    @Test
    public void testIsLoanExist() {
        System.out.println("isLoanExist");
        String bookTitle = "It: A Novel";
        String username = "testUser";
        boolean expResult = true;
        boolean result = loanDao.isLoanExist(bookTitle, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findLoanByUsernameAndIsbn method, of class LoanDao.
     */
    @Test
    public void testFindLoanByUsernameAndIsbn() {
        System.out.println("findLoanByUsernameAndIsbn");
        String bookTitle = "Mr. Magic";
        String username = "senkutis123";
        LoanDao instance = null;
        Loan expResult = null;
        Loan result = instance.findLoanByUsernameAndIsbn(bookTitle, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of doesUserHadBook method, of class LoanDao.
     */
    @Test
    public void testDoesUserHadBook() {
        System.out.println("doesUserHadBook");
        String bookTitle = "";
        String username = "";
        LoanDao instance = null;
        boolean expResult = false;
        boolean result = instance.doesUserHadBook(bookTitle, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeLoan method, of class LoanDao.
     */
    @Test
    public void testRemoveLoan() {
        System.out.println("removeLoan");
        String title = "";
        String username = "";
        LoanDao instance = null;
        boolean expResult = false;
        boolean result = instance.removeLoan(title, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
