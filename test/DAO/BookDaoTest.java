/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Book;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Audrius
 */
public class BookDaoTest {
    
    private BookDao bookDao;
    
    public BookDaoTest() {
        bookDao = new BookDao("libraryTest");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of selectBookByTitle method, of class BookDao.
     */
    @Test
    public void testSelectBookByTitle() {
        System.out.println("selectBookByTitle");
        String title = "Consider Phlebas";

        int numBooks = 1;
        ArrayList<Book> result = bookDao.selectBookByTitle(title);
        assertEquals(numBooks, result.size());
    }

    /**
     * Test of selectBooksContainingTitle method, of class BookDao.
     */
    @Test
    public void testSelectBooksContainingTitle() {
        String title = "Consider";

        int NumBooksinTable = 1;

        ArrayList<Book> result = bookDao.selectBooksContainingTitle(title);
        assertEquals(NumBooksinTable, result.size());
    }

    /**
     * Test of findBookByISBN method, of class BookDao.
     */
    @Test
    public void testFindBookByISBN() {
        int isbn = 978031600;

        Book expResult = new Book(978031600, "Consider Phlebas", "english", 1986, "Iain m.Banks", 20, "ConsiderPhlebas.jpg");
        Book result = bookDao.findBookByISBN(isbn);
        assertEquals(expResult, result);
    }

    /**
     * Test of addBook method, of class BookDao.
     */
    @Test
    public void testAddBook() {
        System.out.println("addBook");
        Book b = new Book(1, "test", "test", 1986, "test", 20, "test.jpg");
        boolean expResult = true;
        boolean result = bookDao.addBook(b);

        assertTrue((result));
        if (result) {
            System.out.println("Method Returned appropriately, confirming database changed by trying to remove what was added");
            boolean deleted = bookDao.deleteBook("test");
            assertEquals(deleted, true);
        }
    }

    /**
     * Test of getAllBooks method, of class BookDao.
     */
    @Test
    public void testGetAllBooks() {
        System.out.println("getAllBooks");
        int NumBooksinTable = 9;

        ArrayList<Book> expResult = null;
        ArrayList<Book> result = bookDao.getAllBooks();
        assertEquals(NumBooksinTable, result.size());
    }

    /**
     * Test of doesBookExist method, of class BookDao.
     */
    @Test
    public void testDoesBookExist() {
        System.out.println("doesBookExist");
        String bookTitle = "Consider Phlebas";
        boolean expResult = true;
        boolean result = bookDao.doesBookExist(bookTitle);
        assertEquals(expResult, result);

    }

    /**
     * Test of reduceBookStock method, of class BookDao.
     */
    @Test
    public void testReduceBookStock() {
        System.out.println("reduceBookStock");
        String bookTitle = "Consider Phlebas";
        boolean expResult = true;
        boolean result = bookDao.reduceBookStock(bookTitle);
        assertEquals(expResult, result);

    }



    /**
     * Test of increaseBookStock method, of class BookDao.
     */
    @Test
    public void testIncreaseBookStock() {
        System.out.println("increaseBookStock");
        String bookTitle = "Consider Phlebas";
        boolean expResult = true;
        boolean result = bookDao.increaseBookStock(bookTitle);
        assertEquals(expResult, result);
       
    }

    /**
     * Test of quantityLeftInStock method, of class BookDao.
     */
    @Test
    public void testQuantityLeftInStock() {
        System.out.println("quantityLeftInStock");
        String bookTitle = "Consider Phlebas";
        int expResult = 16;
        int result = bookDao.quantityLeftInStock(bookTitle);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of selectBooksContainingAuthor method, of class BookDao.
     */
    @Test
    public void testSelectBooksContainingAuthor() {
        System.out.println("selectBooksContainingAuthor");
        String author = "Iain m.Banks";
        int expResult = 1;
        ArrayList<Book> result = bookDao.selectBooksContainingAuthor(author);
        assertEquals(expResult, result.size());
        
    }
    
        /**
     * Test of deleteBook method, of class BookDao.
     */
    @Test
    public void testDeleteBook() {
        System.out.println("deleteBook");
        String bookTitle = "Consider Phlebas";
        boolean expResult = true;
        boolean result = bookDao.deleteBook(bookTitle);
        assertEquals(expResult, result);

    }

    
}
