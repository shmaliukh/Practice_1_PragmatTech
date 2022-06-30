package org.ShmaliukhVlad.bookshelf.actionsWithShelf;

import org.ShmaliukhVlad.bookshelf.Shelf;
import org.ShmaliukhVlad.bookshelf.bookshelfObjects.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ActionsWithBooksTest {
    
    Book book1 = new Book("noNameBook1",1,false,"NoAuthor1", new Date(System.currentTimeMillis() - 60*60*64*1000));
    Book book2 = new Book("noNameBook2",2,false,"NoAuthor2",new Date());
    Book book3 = new Book("noNameBook3",3,true,"NoAuthor3",new Date());

    Book expectedBook1 = new Book("noNameBook1",1,false,"NoAuthor1", new Date(System.currentTimeMillis() - 60*60*64*1000));
    Book expectedBook2 = new Book("noNameBook2",2,false,"NoAuthor2",new Date());

    /**
     * The book3 is not expected to be included for sorting
     */
    int expectedArraySize = 2;

    Shelf shelf1 = new Shelf();

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
        ArrayList<Book> sortedBooksByName = shelf1.getSortedBooksByName();

        assertEquals(expectedArraySize, sortedBooksByName.size());
        assertEquals(expectedBook1.getPrintableLineOfLiteratureObject(), sortedBooksByName.get(0).getPrintableLineOfLiteratureObject());
        assertEquals(expectedBook2.getPrintableLineOfLiteratureObject(), sortedBooksByName.get(1).getPrintableLineOfLiteratureObject());
    }

    @Test
    @DisplayName("test printable stings of sorted Books by Pages")
    void printSortedBooksByPages() {
        ArrayList<Book> sortedBooksByPages = shelf1.getSortedBooksByPages();

        assertEquals(expectedArraySize, sortedBooksByPages.size());
        assertEquals(expectedBook1.getPrintableLineOfLiteratureObject(), sortedBooksByPages.get(0).getPrintableLineOfLiteratureObject());
        assertEquals(expectedBook2.getPrintableLineOfLiteratureObject(), sortedBooksByPages.get(1).getPrintableLineOfLiteratureObject());
    }

    @Test
    @DisplayName("test printable stings of sorted Books by Author")
    void printSortedBooksByAuthor() {
        ArrayList<Book> sortedBooksByAuthor = shelf1.getSortedBooksByAuthor();

        assertEquals(expectedArraySize, sortedBooksByAuthor.size());
        assertEquals(expectedBook1.getPrintableLineOfLiteratureObject(), sortedBooksByAuthor.get(0).getPrintableLineOfLiteratureObject());
        assertEquals(expectedBook2.getPrintableLineOfLiteratureObject(), sortedBooksByAuthor.get(1).getPrintableLineOfLiteratureObject());
    }

    @Test
    @DisplayName("test printable stings of sorted Books by Date")
    void printSortedBooksByDate() {
        ArrayList<Book> sortedBooksByDate = shelf1.getSortedBooksByDate();

        assertEquals(expectedArraySize, sortedBooksByDate.size());
        assertEquals(expectedBook1.getPrintableLineOfLiteratureObject(), sortedBooksByDate.get(0).getPrintableLineOfLiteratureObject());
        assertEquals(expectedBook2.getPrintableLineOfLiteratureObject(), sortedBooksByDate.get(1).getPrintableLineOfLiteratureObject());
    }
}