package org.vshmaliukh.handlers.ItemHandlers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.vshmaliukh.bookshelf.bookshelfObjects.Magazine;
import org.vshmaliukh.services.input_services.InputHandlerForLiterature;
import org.vshmaliukh.services.print_table_service.ConvertorToString;

import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.vshmaliukh.services.input_services.ConstantsForUserInputHandler.*;

class MagazineHandlerTest {

    PrintWriter printWriter = new PrintWriter(System.out, true);

    MagazineHandler magazineHandler = new MagazineHandler();

    Magazine magazine1 = new Magazine("noNameMagazine1", 1, false);
    Magazine magazine2 = new Magazine("noNameMagazine2", 2, false);

    List<Magazine> magazines = Arrays.asList(magazine2, magazine1);

    @Test
    void testGetConvertorToString() {
        ConvertorToString<Magazine> convertorToString = magazineHandler.getConvertorToString();
        //List<String> convertObjectToListOfString = convertorToString.convertObjectToListOfString(magazine1);

        List<String> stringList = new ArrayList<>();
        stringList.add(magazine1.getClass().getSimpleName());
        stringList.add(magazine1.getName());
        stringList.add(String.valueOf(magazine1.getPagesNumber()));
        stringList.add(String.valueOf(magazine1.isBorrowed()));

        //assertEquals(stringList.size(), convertObjectToListOfString.size());
        //assertTrue(stringList.containsAll(convertObjectToListOfString));
    }

    @ParameterizedTest(name = "{index} ==> input string ''{0}''")
    @ValueSource(ints = {1, 2})
    void getSortedItems(int typeOfSorting) {
        List<Magazine> sortedItems = magazineHandler.getSortedItems(typeOfSorting, magazines);

        assertTrue(magazines.containsAll(sortedItems));
        assertFalse(magazine2.toString().equals(sortedItems.get(0).toString()));
    }

    private static Stream<Arguments> providedArgsForSorting() {
        Magazine magazine1 = new Magazine("noNameMagazine1", 1, false);
        Magazine magazine2 = new Magazine("noNameMagazine2", 2, false);
        return Stream.of(
                Arguments.of(1, new ArrayList<>(Arrays.asList(magazine1, magazine2))),
                Arguments.of(2, new ArrayList<>(Arrays.asList(magazine1, magazine2))),
                Arguments.of(1, new ArrayList<>(Arrays.asList(magazine2, magazine1))),
                Arguments.of(2, new ArrayList<>(Arrays.asList(magazine2, magazine1)))
        );
    }

    @ParameterizedTest
    @MethodSource("providedArgsForSorting")
    void clarificationForSortingItems(int typeOfSorting, ArrayList arrayList) {
        List<Magazine> sortedItems = magazineHandler.clarificationForSortingItems(arrayList, typeOfSorting, printWriter);

        List<String> stringList = sortedItems.stream()
                .map(Magazine::toString)
                .collect(Collectors.toList());

        List<String> stringList1 = magazines.stream()
                .map(Magazine::toString)
                .collect(Collectors.toList());

        assertTrue(stringList.containsAll(stringList1));
        assertFalse(magazine2.toString().equals(sortedItems.get(0).toString()));
    }

    @Test
    void getItemByUserInput() {
        Scanner scanner = new Scanner(System.lineSeparator());
        InputHandlerForLiterature inputHandlerForLiterature = new InputHandlerForLiterature(scanner, printWriter);
        Magazine itemByUserInput = magazineHandler.getItemByUserInput(inputHandlerForLiterature, printWriter);
        System.out.println(itemByUserInput);

        assertNotNull(itemByUserInput);
        assertNotNull(itemByUserInput.toString());
        assertEquals(DEFAULT_STRING, itemByUserInput.getName());
        assertEquals(DEFAULT_INTEGER, itemByUserInput.getPagesNumber());
        assertEquals(DEFAULT_BOOLEAN, itemByUserInput.isBorrowed());
    }

    @Test
    void getRandomItem() {
        Magazine randomItem = magazineHandler.getRandomItem(new Random());
        assertNotNull(randomItem);
        assertNotNull(randomItem.getName());
        assertNotNull(randomItem.toString());
    }
}