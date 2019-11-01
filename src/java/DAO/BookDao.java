/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tom
 */
public class BookDao extends Dao implements BookDaoInterface {

    /**
     *
     * @param databaseName
     */
    public BookDao(String databaseName) {
        super(databaseName);
    }

    /**
     * gets information on book based on title
     *
     * @param title - String title
     * @return Book - single object of found book
     */
    @Override
    public ArrayList<Book> selectBookByTitle(String title) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList();

        try {
            con = getConnection();

            String query = "Select * from book where title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();

            if (rs.next()) {
                Book b = new Book(rs.getInt("ISBN"), rs.getString("title"), rs.getString("bookLanguage"), rs.getInt("yearOfPublication"), rs.getString("author"), rs.getInt("availableNoOfCopies"), rs.getString("imageName"));
                books.add(b);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectBookByTitle() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the selectBookByTitle() method: " + e.getMessage());
            }
        }
        return books;
    }

    /**
     * gets all books matching the passed title or partial title
     *
     * @param title
     * @return List of Books list<Book>
     */
    @Override
    public ArrayList<Book> selectBooksContainingTitle(String title) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT * FROM book WHERE title LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + title + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Book b = new Book(rs.getInt("ISBN"), rs.getString("title"), rs.getString("bookLanguage"), rs.getInt("yearOfPublication"), rs.getString("author"), rs.getInt("availableNoOfCopies"), rs.getString("imageName"));
                books.add(b);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectBooksContainingTitle() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the selectBooksContainingTitle() method: " + e.getMessage());
            }
        }
        return books;
    }

    /**
     * get single book based on the ISBN
     *
     * @param isdn - int
     * @return - Book object
     */
    @Override
    public Book findBookByISBN(int isdn) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Book p = null;

        try {
            con = getConnection();

            String query = "Select * from book where ISBN = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, isdn);
            rs = ps.executeQuery();

            if (rs.next()) {
                p = new Book(rs.getInt("ISBN"), rs.getString("title"), rs.getString("bookLanguage"), rs.getInt("yearOfPublication"), rs.getString("author"), rs.getInt("availableNoOfCopies"), rs.getString("imageName"));
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the findBookByISDN() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the findBookByISDN() method: " + e.getMessage());
            }
        }
        return p;
    }

    /**
     * adds a book to the book table
     *
     * @param b - the book to be added
     * @return - boolean , true if added , false otherwise
     */
    @Override
    public boolean addBook(Book b) {
        Connection con = null;
        PreparedStatement ps = null;
        int rs = 0;

        try {
            con = getConnection();
            String query = "INSERT INTO book VALUES(?,?,?,?,?,?)";
            ps = con.prepareStatement(query);
            ps.setInt(1, b.getIsbn());
            ps.setString(2, b.getTitle());
            ps.setString(3, b.getBookLanguage());

            ps.setInt(4, b.getYearOfPublication());
            ps.setString(5, b.getAuthor());
            ps.setInt(6, b.getAvailableNoOfCopies());
            rs = ps.executeUpdate();
            if (rs > 0) {
                System.out.println("Book has been added");
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in  the addBook() method: " + e.getMessage());
        } finally {
            try {

                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the addBook() method: " + e.getMessage());
            }
        }
        return false;
    }

    /**
     * gets list of all books
     *
     * @return arraylist of all books
     */
    @Override
    public ArrayList<Book> getAllBooks() {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> bookMatches = new ArrayList();
        Book b = null;

        try {
            //Get connection object using the methods in the super class (Dao.java)...
            con = this.getConnection();

            String query = "SELECT * FROM book";
            ps = con.prepareStatement(query);

            //Using a PreparedStatement to execute SQL...
            rs = ps.executeQuery();
            while (rs.next()) {
                b = new Book(rs.getInt("ISBN"), rs.getString("title"), rs.getString("bookLanguage"), rs.getInt("yearOfPublication"), rs.getString("author"), rs.getInt("availableNoOfCopies"), rs.getString("imageName"));

                bookMatches.add(b);
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the getAllStock method:");
            System.err.println("\t" + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occurred when closing down the getAllStock() method:\n" + e.getMessage());
            }
        }
        return bookMatches;     // may be empty
    }

    /**
     * Reduce the stock value for a given book by 1.
     *
     * @param bookTitle
     * @return true if done , false otherwise
     */
    @Override
    public boolean reduceBookStock(String bookTitle) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        boolean changed = false;

        try {
            con = getConnection();

            String query = "UPDATE book SET availableNoOfCopies = availableNoOfCopies - 1 "
                    + "WHERE title = ?";

            ps = con.prepareStatement(query);;
            ps.setString(1, bookTitle);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the reduceBookStock() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the reduceBookStock() method");
                e.getMessage();
            }
        }
        if (rowsAffected > 0) {
            changed = true;
        }
        return changed;
    }

    /**
     * delete a book based on the isbn
     *
     * @param bookTitle
     * @return true if deleted , false otherwise
     */
    @Override
    public boolean deleteBook(String bookTitle) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        boolean changed = false;

        try {
            con = getConnection();

            String query = "DELETE FROM book WHERE title = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the deleteBook() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the deleteBook() method");
                e.getMessage();
            }
        }
        if (rowsAffected > 0) {
            changed = true;
        }
        return changed;
    }

    /**
     * checks if a book is on the table , ISBN is the search parameter
     *
     * @param bookTitle
     * @return true if it does exist , false if not.
     */
    public boolean doesBookExist(String bookTitle) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = true;

        try {
            con = getConnection();
            String query = "SELECT isbn "
                    + "FROM book "
                    + "WHERE title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            rs = ps.executeQuery();

            if (!(rs.next())) {
                confirmation = false;
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in "
                    + "isBookExist method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section "
                        + "of isBookExist method: " + e.getMessage());
            }
        }
        return confirmation;
    }

    /**
     * Increase the stock value for a given book by 1.
     *
     * @param bookTitle
     * @return true if done , false otherwise
     */
    @Override
    public boolean increaseBookStock(String bookTitle) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        boolean changed = false;

        try {
            con = getConnection();

            String query = "UPDATE book SET availableNoOfCopies = availableNoOfCopies + 1 "
                    + "WHERE title = ?";

            ps = con.prepareStatement(query);;
            ps.setString(1, bookTitle);

            rowsAffected = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Exception occured in the increaseBookStock() method: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the increaseBookStock() method");
                e.getMessage();
            }
        }
        if (rowsAffected > 0) {
            changed = true;
        }
        return changed;
    }

    /**
     * Checks amount of books left in stock.
     * @param bookTitle
     * @return int - number of books in stock
     */
    @Override
    public int quantityLeftInStock(String bookTitle) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int quantity = 0;

        try {
            con = getConnection();
            String query = "SELECT availableNoOfCopies "
                    + "FROM book "
                    + "WHERE title = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            rs = ps.executeQuery();

            if (rs.next()) {
                quantity = rs.getInt("availableNoOfCopies");
            }
        } catch (SQLException ex) {
            System.out.println("Exception occured in the quantityLeftInStock() method: " + ex.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the selectBookByTitle() method: " + e.getMessage());
            }
        }
        return quantity;
    }

    /**
     * gets all books matching the passed author name or partial name
     * @param author
     * @return List of Books list<Book>
     */
    @Override
    public ArrayList<Book> selectBooksContainingAuthor(String author) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Book> books = new ArrayList();

        try {
            con = getConnection();

            String query = "SELECT * FROM book WHERE author LIKE ?";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + author + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
                Book b = new Book(rs.getInt("ISBN"), rs.getString("title"), rs.getString("bookLanguage"), rs.getInt("yearOfPublication"), rs.getString("author"), rs.getInt("availableNoOfCopies"), rs.getString("imageName"));
                books.add(b);
            }
        } catch (SQLException e) {
            System.out.println("Exception occured in the selectBooksContainingAuthor() method: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.out.println("Exception occured in the finally section of the selectBooksContainingAuthor() method: " + e.getMessage());
            }
        }
        return books;
    }
}
