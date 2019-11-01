/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

//import DTO.Address;
import Business.Loan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Audrius
 */
public class LoanDao extends Dao implements LoanDaoInterface {

    /**
     *
     * @param databaseName
     */
    public LoanDao(String databaseName) {
        super(databaseName);
    }

    /**
     * Gets all active loans for particular member.
     *
     * @param username - String, member username
     * @return List<Address> - all active loans for that user
     */
    @Override
    public List<Loan> getActiveLoanForMemeber(String username) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> activeLoans = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * "
                    + "FROM loan "
                    + "WHERE username = ? "
                    + "AND returndate IS NULL";

            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                String bookTitle = rs.getString("bookTitle");
                String uName = rs.getString("username");
                Date issueDate = rs.getDate("issueDate");
                Date returnDate = rs.getDate("returnDate");

                Loan l = new Loan(bookTitle, uName, issueDate, returnDate);
                activeLoans.add(l);

            }
        } catch (SQLException e) {
            System.out.println("Exception occured in "
                    + "getActiveLoanForMemeber method: " + e.getMessage());
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
                        + "of getActiveLoanForMemeber method: " + e.getMessage());
            }
        }
        return activeLoans;
    }

    /**
     * Gets all since join date loans for particular member.
     *
     * @param username - String, member username
     * @return List<Address> - all loans for that user
     */
    @Override
    public List<Loan> getAllLoanSinceJoin(String username) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Loan> allLoans = new ArrayList();

        try {

            con = getConnection();
            String query = "SELECT * "
                    + "FROM loan "
                    + "WHERE username = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {
                String bookTitle = rs.getString("bookTitle");
                String uName = rs.getString("username");
                Date issueDate = rs.getDate("issueDate");
                Date returnDate = rs.getDate("returnDate");

                Loan l = new Loan(bookTitle, uName, issueDate, returnDate);
                allLoans.add(l);
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in "
                    + "getAllLoanSinceJoin method: " + e.getMessage());
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
                        + "of getAllLoanSinceJoin method: " + e.getMessage());
            }
        }
        return allLoans;

    }

    /**
     * Return book where username and isbn match.
     *
     * @param bookTitle
     * @param username - String, member username
     * @return boolean TRUE if returned or FALSE if not
     */
    @Override
    public boolean returnBook(String bookTitle, String username) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = false;

        try {
            con = getConnection();
            String query = "UPDATE loan "
                    + "SET loanStatus = 1 "
                    + "WHERE bookTitle = ? "
                    + "AND username = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            ps.setString(2, username);
            int result = ps.executeUpdate();

            if (result == 1) {
                confirmation = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in "
                    + "returnBook method: " + e.getMessage());
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
                        + "of returnBook method: " + e.getMessage());
            }
        }
        return confirmation;
    }

    /**
     * Add new loan for member.
     *
     * @param bookTitle
     * @param uName - String, member username
     * @return boolean TRUE if added or FALSE if not
     */
    @Override
    public boolean addNewLoan(String bookTitle, String uName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = false;

        try {

            con = getConnection();
            String query = "INSERT INTO loan(bookTitle, username) "
                    + "VALUE (?, ?)";

            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            ps.setString(2, uName);
            int result = ps.executeUpdate();

            if (result == 1) {
                confirmation = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in the addNewLoan"
                    + " method: " + e.getMessage());
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
                        + "of the addNewLoan method: " + e.getMessage());
            }
        }
        return confirmation;
    }

    /**
     * Change userType, where isbn match and username match.
     *
     * @param bookTitle
     * @param isbn - int, book isbn
     * @param uName - String, member username
     * @return boolean TRUE if changed or FALSE if not
     */
    @Override
    public boolean changeLoanStatus(String bookTitle, String uName) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = false;

        try {
            con = getConnection();
//            String query = "UPDATE loan "
//                    + "SET returnDate = NULL "
//                    + "WHERE isbn = ? "
//                    + "AND username = ? "
//                    + "AND returnDate = NULL";
            String query = "UPDATE loan SET loanStatus = 1 "
                    + "WHERE bookTitle = ? "
                    + "AND username = ? ";

            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            ps.setString(2, uName);
            int result = ps.executeUpdate();

            if (result == 1) {
                confirmation = true;
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in "
                    + "returnBook method: " + e.getMessage());
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
                        + "of returnBook method: " + e.getMessage());
            }
        }
        return confirmation;
    }

    /**
     * Check is loan who are not returned exist
     *
     * @param bookTitle
     * @param username - String, member username
     * @return boolean TRUE if loan exist or FALSE if not
     */
    @Override
    public boolean isLoanExist(String bookTitle, String username) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = false;
        //DEBUG HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        try {
            con = getConnection();
            String query = "SELECT bookTitle, username "
                    + "FROM loan "
                    + "WHERE bookTitle = ? "
                    + "AND username = ? "
                    + "AND returnDate IS NULL";

            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            ps.setString(2, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                confirmation = true;
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the isLoanExist method:");
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
                System.err.println("A problem occurred when closing down the isLoanExist method:\n" + e.getMessage());
            }
        }
        return confirmation;
    }

    /**
     *
     * @param bookTitle
     * @param username
     * @return
     */
    @Override
    public Loan findLoanByUsernameAndIsbn(String bookTitle, String username) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Loan singleLoan = null;

        try {

            con = getConnection();
            String query = "SELECT * "
                    + "FROM loan "
                    + "WHERE bookTitle = ? "
                    + "AND username = ? "
                    + "AND returnDate IS NULL";
            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            ps.setString(2, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                String loanName = rs.getString("username");
                String bTitle = rs.getString("bookTitle");
                Date issueDate = rs.getDate("issueDate");
                Date returnDate = rs.getDate("returnDate");
                singleLoan = new Loan(bTitle, loanName, issueDate, returnDate);

            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the findLoanByUsernameAndIsbn method:");
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
                System.err.println("A problem occurred when closing down the findLoanByUsernameAndIsbn method:\n" + e.getMessage());
            }
        }
        return singleLoan;
    }

    /**
     * Check is user ever had this book for a loan
     *
     * @param bookTitle
     * @param username - String, member username
     * @return boolean TRUE if yes or FALSE if not
     */
    @Override
    public boolean doesUserHadBook(String bookTitle, String username) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = false;
        //DEBUG HERE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
        try {
            con = getConnection();
            String query = "SELECT bookTitle, username "
                    + "FROM loan "
                    + "WHERE bookTitle = ? "
                    + "AND username = ? ";

            ps = con.prepareStatement(query);
            ps.setString(1, bookTitle);
            ps.setString(2, username);
            rs = ps.executeQuery();

            if (rs.next()) {
                confirmation = true;
            }
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the isLoanExist method:");
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
                System.err.println("A problem occurred when closing down the isLoanExist method:\n" + e.getMessage());
            }
        }
        return confirmation;
    }

    /**
     * Remove loan from loan table.
     * @param title book title  
     * @param username user username
     * @return boolean true if loan was removed, else false
     */
    public boolean removeLoan(String title, String username) {
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        boolean confirmation = false;

        try {
            con = getConnection();
            String query = "DELETE FROM loan "
                    + "WHERE username = ? "
                    + "AND booktitle = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, title);

            rowsAffected = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the "
                    + "removeLoan method:");
            System.err.println("\t" + e.getMessage());
            rowsAffected = 0;

        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    freeConnection(con);
                }
            } catch (SQLException e) {
                System.err.println("A problem occured when closing down the "
                        + "removeLoan method:\n" + e.getMessage());
            }
        }
        if (rowsAffected > 0) {
            confirmation = true;
        }
        return confirmation;
    }

}
