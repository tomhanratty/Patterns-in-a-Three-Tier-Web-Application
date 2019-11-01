/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Business.Review;
import java.util.ArrayList;

/**
 *
 * @author Audrius
 */
public interface ReviewDaoInterface {
    
    /**
     *
     * @param title
     * @return
     */
    public ArrayList<Review> getReviewsForBook(String title);
    
    /**
     *
     * @param review
     * @param username
     * @param title
     * @return
     */
    public boolean addReviewForBook(String review, String username, String title);
}
