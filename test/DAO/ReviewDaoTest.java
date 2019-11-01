/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Review;
import java.util.ArrayList;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Audrius
 */
public class ReviewDaoTest {
    ReviewDao reviewDao;
    
    public ReviewDaoTest() {
        reviewDao = new ReviewDao("libraryTest");
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }

    /**
     * Test of getReviewsForBook method, of class ReviewDao.
     */
    @Test
    public void testGetReviewsForBook() {
        System.out.println("getReviewsForBook");
        String title = "Mr. Magic";
        ArrayList<Review> result = reviewDao.getReviewsForBook(title);
        assertEquals(1, result.size());
    }
    
    /**
     * Test of getReviewsForBook method, of class ReviewDao.
     */
    @Test
    public void testGetReviewsForBook_NoMatchFound() {
        System.out.println("getReviewsForBook_NoMatchFound");
        String title = "NoTitle";
        ArrayList<Review> result = reviewDao.getReviewsForBook(title);
        assertEquals(0, result.size());
    }

    /**
     * Test of addReviewForBook method, of class ReviewDao.
     */
    @Test
    public void testAddReviewForBook() {
        System.out.println("addReviewForBook");
        String review = "Some test text";
        String username = "testUser";
        String title = "Raft";
        Review r = new Review(username, review);
        boolean expResult = true;
        boolean result = reviewDao.addReviewForBook(review, username, title);
        assertTrue(result);
        
        if(result){
            System.out.println("Method returned appropriately, confirming "
                    + "database changed by trying to remove what was added");
            boolean rowDeleted = reviewDao.removeReview(title, username);
            assertEquals(rowDeleted, true);
        }
    }
    
    /**
     * Test of addReviewForBook method, of class ReviewDao where review are not added.
     */
    @Test
    public void testAddReviewForBook_NotAdded() {
        System.out.println("addReviewForBook");
        String review = "Some test text";
        String username = "newUser";
        String title = "Raft";
        Review r = new Review(username, review);
        boolean expResult = false;
        boolean result = reviewDao.addReviewForBook(review, username, title);
        assertEquals(expResult, result);
    }
    
}
