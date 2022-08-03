package org.vshmaliukh.terminal.bookshelf.literature_items.book_item;

import org.vshmaliukh.terminal.bookshelf.literature_items.ItemHandler;
import org.vshmaliukh.terminal.bookshelf.literature_items.ItemTitles;
import org.vshmaliukh.terminal.bookshelf.literature_items.comics_item.Comics;
import org.vshmaliukh.terminal.menus.menu_items.MenuItemForSorting;
import org.vshmaliukh.terminal.services.Utils;
import org.vshmaliukh.terminal.services.input_services.InputHandlerForLiterature;

import javax.servlet.http.HttpServletRequest;
import javax.xml.crypto.Data;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

import static org.vshmaliukh.terminal.Terminal.DATE_FORMAT_STR;
import static org.vshmaliukh.terminal.bookshelf.literature_items.ItemTitles.*;
import static org.vshmaliukh.terminal.bookshelf.literature_items.ItemTitles.PUBLISHER;
import static org.vshmaliukh.terminal.services.Utils.getRandomString;
import static org.vshmaliukh.terminal.services.input_services.ConstantsForUserInputHandler.*;
import static org.vshmaliukh.terminal.services.input_services.ConstantsForUserInputHandler.PATTERN_FOR_PUBLISHER;

public class BookHandler implements ItemHandler<Book> {

    public static final Comparator<Book> BOOK_COMPARATOR_BY_NAME = Comparator.comparing(Book::getName, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Book> BOOK_COMPARATOR_BY_AUTHOR = Comparator.comparing(Book::getAuthor, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Book> BOOK_COMPARATOR_BY_PAGES = Comparator.comparing(Book::getPagesNumber);
    public static final Comparator<Book> BOOK_COMPARATOR_BY_DATE = Comparator.comparing(Book::getIssuanceDate);

    @Override
    public List<Book> getSortedItems(int typeOfSorting, List<Book> inputList) {
        for (MenuItemForSorting menuItem : getSortingMenuList()) {
            if (typeOfSorting == menuItem.getIndex()) {
                return new ArrayList<>(Utils.getSortedLiterature(inputList, menuItem.getComparator()));
            }
        }
        return Collections.emptyList();
    }

    @Override
    public List<MenuItemForSorting> getSortingMenuList() {
        return Collections.unmodifiableList(Arrays.asList(
                new MenuItemForSorting(1, "Sort by 'name' value", BOOK_COMPARATOR_BY_NAME),
                new MenuItemForSorting(2, "Sort by 'author' value", BOOK_COMPARATOR_BY_AUTHOR),
                new MenuItemForSorting(3, "Sort by 'page number' value", BOOK_COMPARATOR_BY_PAGES),
                new MenuItemForSorting(4, "Sort by 'date' value", BOOK_COMPARATOR_BY_DATE)
        ));
    }

    @Override
    public Book getItemByUserInput(InputHandlerForLiterature inputHandlerForLiterature, PrintWriter printWriter) {
        String name = inputHandlerForLiterature.getUserLiteratureName();
        int pages = inputHandlerForLiterature.getUserLiteraturePages();
        boolean isBorrowed = inputHandlerForLiterature.getUserLiteratureIsBorrowed();
        String author = inputHandlerForLiterature.getUserLiteratureAuthor();
        Date dateOfIssue = inputHandlerForLiterature.getUserLiteratureDateOfIssue();
        return new Book(name, pages, isBorrowed, author, dateOfIssue);
    }

    @Override
    public Book getRandomItem(Random random) {
        return new Book(
                getRandomString(random.nextInt(20), random),
                random.nextInt(1000),
                false,
                getRandomString(random.nextInt(20), random),
                new Date(random.nextLong()));
    }

    @Override
    public Map<String, String> convertItemToListOfString(Book book) {
        Map<String, String> map = new HashMap<>();
        map.put(ItemTitles.TYPE, book.getClass().getSimpleName());
        map.put(ItemTitles.NAME, book.getName());
        map.put(ItemTitles.PAGES, String.valueOf(book.getPagesNumber()));
        map.put(ItemTitles.BORROWED, String.valueOf(book.isBorrowed()));
        map.put(ItemTitles.AUTHOR, book.getAuthor());
        map.put(ItemTitles.DATE, new SimpleDateFormat(DATE_FORMAT_STR).format(book.getIssuanceDate()));
        return new HashMap<>(map);
    }

    @Override
    public String generateHTMLFormBodyToCreateItem(HttpServletRequest request) {
        return "" +
                Utils.generateHTMLFormItem(NAME) +
                Utils.generateHTMLFormItem(PAGES) +
                Utils.generateHTMLFormItem(BORROWED) +
                Utils.generateHTMLFormItem(AUTHOR) +
                Utils.generateHTMLFormItem(DATE) +
                "   <input type = \"submit\" value = \"Submit\" />\n" +
                "</form>";
    }

    @Override
    public boolean isValidHTMLFormData(HttpServletRequest request) {
        String nameParameter = request.getParameter(NAME);
        String pagesParameter = request.getParameter(PAGES);
        String borrowedParameter = request.getParameter(BORROWED);
        String authorParameter = request.getParameter(AUTHOR);
        String dateParameter = request.getParameter(DATE);
        if (InputHandlerForLiterature.isValidInputString(nameParameter, PATTERN_FOR_NAME) &&
                InputHandlerForLiterature.isValidInputInteger(pagesParameter, PATTERN_FOR_PAGES) &&
                InputHandlerForLiterature.isValidInputString(borrowedParameter, PATTERN_FOR_IS_BORROWED) &&
                InputHandlerForLiterature.isValidInputDate(dateParameter, new SimpleDateFormat(DATE_FORMAT_FOR_INPUT_HANDLER)) &&
                InputHandlerForLiterature.isValidInputString(authorParameter, PATTERN_FOR_AUTHOR)) {
            return true;
        }
        return false;
    }

    @Override
    public Book generateItemByHTMLFormData(HttpServletRequest request, PrintWriter printWriter) {
        InputHandlerForLiterature inputHandlerForLiterature = new InputHandlerForLiterature(null, printWriter);

        String nameParameter = request.getParameter(NAME);
        String pagesParameter = request.getParameter(PAGES);
        String borrowedParameter = request.getParameter(BORROWED);
        String authorParameter = request.getParameter(AUTHOR);
        String dateParameter = request.getParameter(DATE);

        inputHandlerForLiterature.scanner = new Scanner(nameParameter);
        String name = inputHandlerForLiterature.getUserLiteratureName();
        inputHandlerForLiterature.scanner = new Scanner(pagesParameter);
        int pages = inputHandlerForLiterature.getUserLiteraturePages();
        inputHandlerForLiterature.scanner = new Scanner(borrowedParameter);
        boolean isBorrowed = inputHandlerForLiterature.getUserLiteratureIsBorrowed();
        inputHandlerForLiterature.scanner = new Scanner(authorParameter);
        String author = inputHandlerForLiterature.getUserLiteratureAuthor();
        inputHandlerForLiterature.scanner = new Scanner(dateParameter);
        Date date = inputHandlerForLiterature.getUserLiteratureDateOfIssue();

        return new Book(name, pages, isBorrowed, author, date);
    }
}
