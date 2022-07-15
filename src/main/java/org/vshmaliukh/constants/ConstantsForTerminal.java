package org.vshmaliukh.constants;

import java.text.SimpleDateFormat;

public final class ConstantsForTerminal {

    private ConstantsForTerminal(){}

    public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public static final int FILE_MODE_NO_WORK_WITH_FILES = 0;
    public static final int FILE_MODE_WORK_WITH_ONE_FILE = 1;
    public static final int FILE_MODE_WORK_WITH_TWO_FILES = 2;
    public static final int DEFAULT_MODE_WORK_WITH_FILES = FILE_MODE_NO_WORK_WITH_FILES;

    public static final int ADD_NEW_LITERATURE = 1;
    public static final int DELETE_LITERATURE = 2;
    public static final int BORROW_LITERATURE = 3;
    public static final int ARRIVE_LITERATURE = 4;
    public static final int PRINT_SORTED_BOOKS = 5;
    public static final int PRINT_SORTED_MAGAZINES = 6;
    public static final int PRINT_PRETTY_SHELF = 8;
    public static final int PRINT_SHELF = 9;
    public static final int EXIT = 0;

    public static final int ADD_CUSTOM_MAGAZINE = 1;
    public static final int ADD_CUSTOM_BOOK = 2;
    public static final int ADD_RANDOM_MAGAZINE = 3;
    public static final int ADD_RANDOM_BOOK = 4;

    public static final int SORT_BOOKS_BY_NAME = 1;
    public static final int SORT_BOOKS_BY_AUTHOR = 2;
    public static final int SORT_BOOKS_BY_PAGES_NUMBER = 3;
    public static final int SORT_BOOKS_BY_DATE_OF_ISSUE = 4;

    public static final int SORT_MAGAZINES_BY_NAME = 1;
    public static final int SORT_MAGAZINES_BY_PAGES_NUMBER = 2;

    public static final int WRONG_INPUT = -1;
}
