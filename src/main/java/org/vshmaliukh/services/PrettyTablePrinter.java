package org.vshmaliukh.services;

import org.vshmaliukh.bookshelf.Shelf;
import org.vshmaliukh.bookshelf.bookshelfObjects.Book;
import org.vshmaliukh.bookshelf.bookshelfObjects.Literature;
import org.vshmaliukh.bookshelf.bookshelfObjects.Magazine;
import org.vshmaliukh.constants.enums_for_menu.MenuForAddingLiterature;
import org.vshmaliukh.constants.enums_for_menu.MenuForSortingBooks;
import org.vshmaliukh.constants.enums_for_menu.MenuForSortingMagazines;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static org.vshmaliukh.constants.ConstantsForPrettyPrinter.*;
import static org.vshmaliukh.constants.ConstantsForTerminal.*;

public class PrettyTablePrinter {

    private final PrintWriter printWriter;

    private String format;

    private int spaceForIndex;
    private int spaceForType;
    private int spaceForName;
    private int spaceForPages;
    private int spaceForIsBorrowed;
    private int spaceForAuthor;
    private int spaceForDate;

    private int gotPagesLength;
    private int gotNameLength;

    public PrettyTablePrinter(PrintWriter printWriter){
        this.printWriter = printWriter;
        setDefaultSpaces();
    }

    public void printTable(List<Literature> literatureList){
        setFormat(literatureList);
        printTitle(literatureList);
        for (int i = 1 ; literatureList.size() +1 > i; i++) {
            printLiteratureObject(i, literatureList.get(i-1));
        }
    }

    private void printTitle(List<Literature> literatureList) {
        if(literatureList.isEmpty()){
            printWriter.println("No literature to print");
        }
        else {
            printWriter.println(String.format(format,
                    TITLE_INDEX, TITLE_TYPE, TITLE_NAME, TITLE_PAGES, TITLE_BORROW, TITLE_AUTHOR, TITLE_DATE));
        }
    }

    public void printLiteratureObject(int i, Literature literature){
        printWriter.println(getPrettyStringOfLiterature(i, literature));
    }

    private void setDefaultSpaces() {
        spaceForIndex = TITLE_INDEX.length();
        spaceForType = TITLE_TYPE.length();
        spaceForName = TITLE_NAME.length();
        spaceForPages = TITLE_PAGES.length();
        spaceForIsBorrowed = TITLE_BORROW.length();
        spaceForAuthor = TITLE_AUTHOR.length();
        spaceForDate = TITLE_DATE.length();
    }

    public String getPrettyStringOfLiterature (int i ,Literature literature){
        if (literature instanceof Book){
            return getPrettyString(i, (Book) literature);
        }
        else if (literature instanceof Magazine){
            return getPrettyString(i, (Magazine) literature);
        }
        return "";
    }

    public String getPrettyString(int i, Magazine magazine){
        return String.format(format, i, MAGAZINE_TYPE, magazine.getName(), magazine.getPagesNumber(), magazine.isBorrowed(), "", "");
    }

    public String getPrettyString(int i, Book book){
        return String.format(format, i, BOOK_TYPE, book.getName(), book.getPagesNumber(), book.isBorrowed(), book.getAuthor(), DATE_FORMAT.format(book.getIssuanceDate()));
    }

    public void setFormat(List<Literature> literatureList){
        getSpacesValue(literatureList);
        format = "| %-"
                + spaceForIndex+"s | %-"
                + spaceForType+"s | %-"
                + spaceForName+"s | %-"
                + spaceForPages +"s | %-"
                + spaceForIsBorrowed+"s | %-"
                + spaceForAuthor +"s | %-"
                + spaceForDate +"s |";
    }

    private void getSpacesValue(List<Literature> literatureList) {
        spaceForIndex = String.valueOf(literatureList.size()).length();
        for (Literature literature : literatureList) {
            getSpacesForMagazines(literature);
            getSpacesForBooks(literature);
        }
    }

    private void getSpacesForBooks(Literature literature) {
        if(spaceForType < BOOK_TYPE.length()){
            spaceForType = BOOK_TYPE.length();
        }
        if (literature instanceof Book) {
            Book book = (Book) literature;
            gotNameLength = book.getName().length();
            gotPagesLength = String.valueOf(book.getPagesNumber()).length();
            int gotAuthorLength = book.getAuthor().length();
            int gotForDateLength = DATE_FORMAT.format(book.getIssuanceDate()).length();
            if (gotNameLength > spaceForName) {
                spaceForName = gotNameLength;
            }
            if (gotPagesLength > spaceForPages) {
                spaceForPages = gotPagesLength;
            }
            if (gotAuthorLength > spaceForAuthor) {
                spaceForAuthor = gotAuthorLength;
            }
            if (gotForDateLength > spaceForPages) {
                spaceForDate = gotForDateLength;
            }
        }
    }

    private void getSpacesForMagazines(Literature literature) {
        if(spaceForType < MAGAZINE_TYPE.length()){
            spaceForType = MAGAZINE_TYPE.length();
        }
        if (literature instanceof Magazine) {
            gotNameLength = literature.getName().length();
            gotPagesLength = String.valueOf(literature.getPagesNumber()).length();
            if (gotNameLength > spaceForName) {
                spaceForName = gotNameLength;
            }
            if (gotPagesLength > spaceForPages) {
                spaceForPages = gotPagesLength;
            }
        }
    }


}
