package org.ShmaliukhVlad.bookshelf.bookshelfObjects;

import java.time.LocalDate;

import static org.ShmaliukhVlad.ConstantValues.SORT_BOOKS_BY_NAME;
import static org.ShmaliukhVlad.ConstantValues.SORT_BOOKS_BY_AUTHOR;
import static org.ShmaliukhVlad.ConstantValues.SORT_BOOKS_BY_PAGES_NUMBER;
import static org.ShmaliukhVlad.ConstantValues.SORT_BOOKS_BY_DATE_OF_ISSUE;


/**
 * @author ShmaliukhVlad
 * @version 1.0.0
 * This is Book class which gives ability to create objects
 */
public class Book extends Literature{

    private String author;
    private LocalDate issuanceDate;

    /**
     * Constructor for creating Book object
     */
    public Book(String name, int pagesNumber, boolean isBorrowed, String author, LocalDate issuanceDate) {
        super(name, pagesNumber, isBorrowed);
        this.author = author;
        this.issuanceDate = issuanceDate;
    }

    /**
     * Simple forming String about Book object
     * @return String about Book object
     */
    @Override
    public String toString() {
        return "Book {" +
               " name='" + name + '\'' +
               " pagesNumber=" + pagesNumber +
               " author='" + author + '\'' +
               " issuanceDate=" + issuanceDate +
               " isBorrowed=" + isBorrowed +
               " }\n";
    }

    /**
     * Simple forming String about Book object in one line with necessary configuration
     * @param typeOfLineConfig integer value of configuration we need
     * @return String about Book object in one line.
     * sortBooksByName -> first value of Literature object is 'Name'
     * sortBooksByPagesNumber -> first value of Literature object is 'pagesNumber'
     * sortBooksByAuthor -> first value of Literature object is 'Author'
     * sortBooksByDateOfIssue -> first value of Literature object is 'issuanceDate'
     * sortBooksByDateOfIssue -> first value of Literature object is 'issuanceDate'
     * default -> return toString() method
     */
    @Override
    public String getPrintableLineOfLiteratureObject(int typeOfLineConfig){
        return switch (typeOfLineConfig) {
            case SORT_BOOKS_BY_NAME ->
                    "Book {" +
                    " name='" + name + '\'' +
                    " pagesNumber=" + pagesNumber +
                    " author='" + author + '\'' +
                    " issuanceDate=" + issuanceDate +
                    " isBorrowed=" + isBorrowed +
                    " }\n";
            case SORT_BOOKS_BY_PAGES_NUMBER ->
                    "Book {" +
                    " pagesNumber=" + pagesNumber +
                    " name='" + name + '\'' +
                    " author='" + author + '\'' +
                    " issuanceDate=" + issuanceDate +
                    " isBorrowed=" + isBorrowed +
                    " }\n";
            case SORT_BOOKS_BY_AUTHOR ->
                    "Book {" +
                    " author='" + author + '\'' +
                    " name='" + name + '\'' +
                    " pagesNumber=" + pagesNumber +
                    " issuanceDate=" + issuanceDate +
                    " isBorrowed=" + isBorrowed +
                    " }\n";
            case SORT_BOOKS_BY_DATE_OF_ISSUE ->
                    "Book {" +
                    " issuanceDate=" + issuanceDate +
                    " name='" + name + '\'' +
                    " author='" + author + '\'' +
                    " pagesNumber=" + pagesNumber +
                    " isBorrowed=" + isBorrowed +
                    " }\n";
            default -> toString();
        };
    }

    //getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDate getIssuanceDate() {
        return issuanceDate;
    }

    public void setIssuanceDate(LocalDate issuanceDate) {
        this.issuanceDate = issuanceDate;
    }
}