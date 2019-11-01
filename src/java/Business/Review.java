/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Audrius
 */
public class Review {
    
    private int reviewID;
    String username, review, title;

    /**
     *
     */
    public Review() {
    }

    /**
     *
     * @param reviewID
     * @param username
     * @param review
     * @param title
     */
    public Review(int reviewID, String username, String review, String title) {
        this.reviewID = reviewID;
        this.username = username;
        this.review = review;
        this.title = title;
    }

    /**
     *
     * @param username
     * @param review
     */
    public Review(String username, String review) {
        this.username = username;
        this.review = review;
    }

    /**
     *
     * @return
     */
    public int getReviewID() {
        return reviewID;
    }

    /**
     *
     * @param reviewID
     */
    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getReview() {
        return review;
    }

    /**
     *
     * @param review
     */
    public void setReview(String review) {
        this.review = review;
    }

    /**
     *
     * @return
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.reviewID;
        return hash;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Review other = (Review) obj;
        if (this.reviewID != other.reviewID) {
            return false;
        }
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Review{" + "reviewID=" + reviewID + ", username=" + username + ", review=" + review + ", title=" + title + '}';
    }
    
    
}
