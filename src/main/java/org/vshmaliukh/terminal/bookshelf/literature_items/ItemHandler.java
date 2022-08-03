package org.vshmaliukh.terminal.bookshelf.literature_items;

import org.vshmaliukh.terminal.menus.menu_items.MenuItemForSorting;
import org.vshmaliukh.terminal.services.input_services.InputHandlerForLiterature;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;
import java.util.Random;

public interface ItemHandler<T extends Item> {

    String CHOOSE_TYPE_OF_SORTING = "Choose type of sorting:";
    String ENTER_ANOTHER_VALUE_TO_RETURN = "Enter another value to return";
    String NO_AVAILABLE_LITERATURE_ITEM_IN_SHELF_FOR_SORTING = "No available literature item IN shelf for sorting";

    List<T> getSortedItems(int typeOfSorting, List<T> inputList);

    List<MenuItemForSorting> getSortingMenuList();

    default void printSortingMenu(PrintWriter printWriter){
        myCustomPrintln(printWriter, CHOOSE_TYPE_OF_SORTING);
        for (MenuItemForSorting menuItemForSorting : getSortingMenuList()) {
            printWriter.println(menuItemForSorting );
        }
        printWriter.println(ENTER_ANOTHER_VALUE_TO_RETURN );
    }

     static void myCustomPrintln(PrintWriter printWriter, String str) {
        printWriter.println(str);
    }

    default List<T> clarificationForSortingItems(List<T> items, int userChoice, PrintWriter printWriter){
        if (items.isEmpty()) {
            printWriter.println(NO_AVAILABLE_LITERATURE_ITEM_IN_SHELF_FOR_SORTING );
        } else {
            printSortingMenu(printWriter);
            return getSortedItems(userChoice, items);
        }
        return items;
    }

    T getItemByUserInput(InputHandlerForLiterature inputHandlerForLiterature, PrintWriter printWriter);

    T getRandomItem(Random random);

    Map<String, String> convertItemToListOfString(T item);

    String generateHTMLFormBodyToCreateItem(HttpServletRequest request);

    boolean isValidHTMLFormData(HttpServletRequest request);

    T generateItemByHTMLFormData(HttpServletRequest request, PrintWriter printWriter);
}
