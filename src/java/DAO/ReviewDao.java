/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Review;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Audrius
 */
public class ReviewDao extends Dao implements ReviewDaoInterface {

    /**
     *
     * @param databaseName
     */
    public ReviewDao(String databaseName) {
        super(databaseName);
    }

    /**
     *
     * @param title
     * @return
     */
    @Override
    public ArrayList<Review> getReviewsForBook(String title) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Review> reviews = new ArrayList();

        try {
            con = getConnection();
            String query = "SELECT * "
                    + "FROM review "
                    + "WHERE title = ?";
//            String query = "SELECT "
//                    + "u.username, "
//                    + "R1.userReview AS textReview "
//                    + "FROM userbookreview u JOIN review R1 ON u.reviewID = R1.reviewID "
//                    + "WHERE title = ?";

            ps = con.prepareStatement(query);
            ps.setString(1, title);
            rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String review = rs.getString("review");

                reviews.add(new Review(username, review));
            }

        } catch (SQLException e) {
            System.out.println("Exception occured in "
                    + "getReviewsForBook method: " + e.getMessage());
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
                        + "of getReviewsForBook method: " + e.getMessage());
            }
        }
        return reviews;
    }

    /**
     *
     * @param review
     * @param username
     * @param title
     * @return
     */
    @Override
    public boolean addReviewForBook(String review, String username, String title) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean confirmation = false;

        try {

            con = getConnection();
            String query = "INSERT INTO review (review, username, title) "
                    + "VALUE (?, ?, ?)";

            ps = con.prepareStatement(query);
            ps.setString(1, review);
            ps.setString(2, username);
            ps.setString(3, title);
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
    
    public boolean removeReview(String title, String username){
        Connection con = null;
        PreparedStatement ps = null;
        int rowsAffected = 0;
        boolean confirmation = false;
        
        try {
            con = getConnection();
            String query = "DELETE FROM review "
                    + "WHERE title = ? "
                    + "AND username = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, username);
            
            rowsAffected = ps.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("\tA problem occurred during the "
                    + "removeReview method:");
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
                        + "removeReview method:\n" + e.getMessage());
            }
        }
        
        if (rowsAffected > 0) {
            confirmation = true;
        }
        return confirmation;
    }

}
