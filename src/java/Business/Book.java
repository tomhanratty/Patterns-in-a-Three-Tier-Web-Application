/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business;

/**
 *
 * @author Tom
 */
public class Book {

    private int isbn;
    private String title;
    private String bookLanguage;

    private int yearOfPublication;
    private String author;
    private int availableNoOfCopies;
    private String imageName;

    /**
     *
     */
    public Book() {

    }

    /**
     *
     * @param isbn
     * @param title
     * @param bookLanguage
     * @param yearOfPublication
     * @param author
     * @param availableNoOfCopies
     * @param imageName
     */
    public Book(int isbn, String title, String bookLanguage, int yearOfPublication, String author, int availableNoOfCopies, String imageName) {
        this.isbn = isbn;
        this.title = title;
        this.bookLanguage = bookLanguage;

        this.yearOfPublication = yearOfPublication;
        this.author = author;
        this.availableNoOfCopies = availableNoOfCopies;
        this.imageName = imageName;
    }

    //getters and setters

    /**
     *
     * @return
     */
    public String getImageName() {
        return imageName;
    }

    /**
     *
     * @param imageName
     */
    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    /**
     *
     * @return
     */
    public int getIsbn() {
        return isbn;
    }

    /**
     *
     * @param isdn
     */
    public void setIsbn(int isdn) {
        this.isbn = isdn;
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
    public String getBookLanguage() {
        return bookLanguage;
    }

    /**
     *
     * @param bookLanguage
     */
    public void setBookLanguage(String bookLanguage) {
        this.bookLanguage = bookLanguage;
    }

    /**
     *
     * @return
     */
    public int getYearOfPublication() {
        return yearOfPublication;
    }

    /**
     *
     * @param yearOfPublication
     */
    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    /**
     *
     * @return
     */
    public String getAuthor() {
        return author;
    }

    /**
     *
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     *
     * @return
     */
    public int getAvailableNoOfCopies() {
        return availableNoOfCopies;
    }

    /**
     *
     * @param availableNoOfCopies
     */
    public void setAvailableNoOfCopies(int availableNoOfCopies) {
        this.availableNoOfCopies = availableNoOfCopies;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.isbn;
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
        final Book other = (Book) obj;
        if (this.isbn != other.isbn) {
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
        return "Book{" + "isdn=" + isbn + ", title=" + title + ", bookLanguage=" + bookLanguage + ", yearOfPublication=" + yearOfPublication + ", author=" + author + ", availableNoOfCopies=" + availableNoOfCopies + '}';
    }

}
