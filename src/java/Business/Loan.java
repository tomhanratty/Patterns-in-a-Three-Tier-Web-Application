/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author D00197085
 */
public class Loan {

    /*
    isbn Book
    username Member
    issueDate date
    returnDate date
    loanStatus boolean
     */

    private int loanID, loanStus;
    private String username, bookTitle;
    private Date issueDate, returnDate;

    /**
     *
     */
    public Loan() {
    }

    /**
     *
     * @param bookTitle
     * @param username
     * @param issueDate
     */
    public Loan(String bookTitle, String username, Date issueDate) {
        this.bookTitle = bookTitle;
        this.username = username;
        this.issueDate = issueDate;
    }

    /**
     *
     * @param bookTitle
     * @param username
     */
    public Loan(String bookTitle, String username) {
        this.bookTitle = bookTitle;
        this.username = username;
    }

    /**
     *
     * @param bookTitle
     * @param username
     * @param issueDate
     * @param returnDate
     */
    public Loan(String bookTitle, String username, Date issueDate, Date returnDate) {
        this.bookTitle = bookTitle;
        this.username = username;
        this.issueDate = issueDate;
        this.returnDate = returnDate;
    }

    /**
     *
     * @return
     */
    public String getBookTitle() {
        return bookTitle;
    }

    /**
     *
     * @param bookTitle
     */
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    /**
     *
     * @return
     */
    public int getLoanID() {
        return loanID;
    }

    /**
     *
     * @param loanID
     */
    public void setLoanID(int loanID) {
        this.loanID = loanID;
    }

    /**
     *
     * @return
     */
    public int getLoanStus() {
        return loanStus;
    }

    /**
     *
     * @param loanStus
     */
    public void setLoanStus(int loanStus) {
        this.loanStus = loanStus;
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
    public Date getIssueDate() {
        return issueDate;
    }

    /**
     *
     * @param issueDate
     */
    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    /**
     *
     * @return
     */
    public Date getReturnDate() {
        return returnDate;
    }

    /**
     *
     * @param returnDate
     */
    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.bookTitle);
        hash = 37 * hash + Objects.hashCode(this.username);
        hash = 37 * hash + Objects.hashCode(this.issueDate);
        return hash;
    }

    /**
     * Loan considered equal if ISBN, username and issue date are the same.
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
        final Loan other = (Loan) obj;
        if (!Objects.equals(this.bookTitle, other.bookTitle)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.issueDate, other.issueDate)) {
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
        return "Loan{" + "bookTitle=" + bookTitle + ", username=" + username + ", issueDate=" + issueDate + ", returnDate=" + returnDate + '}';
    }

}
