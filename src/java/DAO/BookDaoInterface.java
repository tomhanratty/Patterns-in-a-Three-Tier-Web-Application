/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Book;
import java.util.ArrayList;

/**
 *
 * @author Tom
 */
public interface BookDaoInterface {
           //Get all Books matching the supplied title exactly

    /**
     *
     * @return
     */
   public ArrayList<Book> getAllBooks(); 
        //Get all Books matching the supplied title exactly

    /**
     *
     * @param title
     * @return
     */
   public ArrayList<Book> selectBookByTitle(String title); 
   
   //Get all Books with a Book title containing the supplied text

    /**
     *
     * @param title
     * @return
     */
   public ArrayList<Book> selectBooksContainingTitle(String title);
   
   //Get the Book matching the specified ISDN number

    /**
     *
     * @param isbn
     * @return
     */
   public Book findBookByISBN(int isbn);
   
   //Add the supplied Book object to the database (return true if successful & false
    //otherwise). Remember that when you do an insert, you need an extra catch!

    /**
     *
     * @param b
     * @return
     */
   public boolean addBook(Book b);
   
   //Reduce the number of copies of a book by 1

    /**
     *
     * @param bookTitle
     * @return
     */
   public boolean reduceBookStock(String bookTitle);
   
   //Increase the number of copies of a book by 1

    /**
     *
     * @param bookTitle
     * @return
     */
   public boolean increaseBookStock(String bookTitle);
   
   //DELETE a book given its ISBN

    /**
     *
     * @param bookTitle
     * @return
     */
   public boolean deleteBook(String bookTitle);   
   
   //check quantity left in stock for particular book

    /**
     *
     * @param bookTitle
     * @return
     */
   public int quantityLeftInStock(String bookTitle);
   
   //find book by author

    /**
     *
     * @param author
     * @return
     */
   public ArrayList<Book> selectBooksContainingAuthor(String author);
}
