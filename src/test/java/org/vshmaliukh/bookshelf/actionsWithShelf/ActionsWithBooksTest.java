package org.vshmaliukh.bookshelf.actionsWithShelf;

import org.vshmaliukh.bookshelf.Shelf;
import org.vshmaliukh.bookshelf.bookshelfObjects.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.vshmaliukh.services.LiteratureSorterHandler;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.vshmaliukh.constants.ConstantsLiteratureSorterHandler.*;

class ActionsWithBooksTest {
    PrintWriter printWriter = new PrintWriter(System.out, true);
    
    Book book1 = new Book("noNameBook1",1,false,"NoAuthor1", new Date(System.currentTimeMillis() - 60*60*64*1000));
    Book book2 = new Book("noNameBook2",2,false,"NoAuthor2",new Date(System.currentTimeMillis() - 60*60*64));
    Book book3 = new Book("noNameBook3",3,true,"NoAuthor3",new Date());

    Book expectedBook1 = new Book("noNameBook1",1,false,"NoAuthor1", new Date(System.currentTimeMillis() - 60*60*64*1000));
    Book expectedBook2 = new Book("noNameBook2",2,false,"NoAuthor2",new Date());

    /**
     * The book3 is not expected to be included for sorting
     */
    int expectedArraySize = 3;

    Shelf shelf1 = new Shelf(printWriter);

    /*
      Adding books in revers order
     */
    {
        shelf1.addLiteratureObject(book3);
        shelf1.addLiteratureObject(book2);
        shelf1.addLiteratureObject(book1);
    }


    @Test
    @DisplayName("test printable stings of sorted Books by Name")
    void printSortedBooksByName() {
        List<Book> sortedBooksByName =
                new LiteratureSorterHandler<Book>(shelf1.getBooks())
                        .getSortedLiterature(BOOK_COMPARATOR_BY_NAME);

        assertEquals(expectedArraySize, sortedBooksByName.size());
        assertEquals(expectedBook1.toString(), sortedBooksByName.get(0).toString());
        assertEquals(expectedBook2.toString(), sortedBooksByName.get(1).toString());
    }

    @Test
    @DisplayName("test printable stings of sorted Books by Pages")
    void printSortedBooksByPages() {
        List<Book> sortedBooksByPages =
                new LiteratureSorterHandler<Book>(shelf1.getBooks())
                        .getSortedLiterature(BOOK_COMPARATOR_BY_PAGES);

        assertEquals(expectedArraySize, sortedBooksByPages.size());
        assertEquals(expectedBook1.toString(), sortedBooksByPages.get(0).toString());
        assertEquals(expectedBook2.toString(), sortedBooksByPages.get(1).toString());
    }

    @Test
    @DisplayName("test printable stings of sorted Books by Author")
    void printSortedBooksByAuthor() {
        List<Book> sortedBooksByAuthor =
                new LiteratureSorterHandler<Book>(shelf1.getBooks())
                        .getSortedLiterature(BOOK_COMPARATOR_BY_AUTHOR);

        assertEquals(expectedArraySize, sortedBooksByAuthor.size());
        assertEquals(expectedBook1.toString(), sortedBooksByAuthor.get(0).toString());
        assertEquals(expectedBook2.toString(), sortedBooksByAuthor.get(1).toString());
    }

    @Test
    @DisplayName("test printable stings of sorted Books by Date")
    void printSortedBooksByDate() {
        List<Book> sortedBooksByDate =
                new LiteratureSorterHandler<Book>(shelf1.getBooks())
                        .getSortedLiterature(BOOK_COMPARATOR_BY_DATE);

        assertEquals(expectedArraySize, sortedBooksByDate.size());
        assertEquals(expectedBook1.toString(), sortedBooksByDate.get(0).toString());
        assertEquals(expectedBook2.toString(), sortedBooksByDate.get(1).toString());
    }
}