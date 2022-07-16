package org.vshmaliukh.services;

import org.junit.jupiter.api.Test;
import org.vshmaliukh.bookshelf.bookshelfObjects.Book;
import org.vshmaliukh.bookshelf.bookshelfObjects.Literature;
import org.vshmaliukh.bookshelf.bookshelfObjects.Magazine;
import org.vshmaliukh.services.print_table_service.ConvertorToStringForBook;
import org.vshmaliukh.services.print_table_service.ConvertorToStringForMagazine;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.vshmaliukh.constants.ConstantsForTerminal.DATE_FORMAT;

class ConvertorToStringForLiteratureTest {

    ConvertorToStringForMagazine convertorMagazine = new ConvertorToStringForMagazine();
    ConvertorToStringForBook convertorBook = new ConvertorToStringForBook();

    Book book1 = new Book("noNameBook1",1,false,"NoAuthor1", DATE_FORMAT.parse("10-07-2022"));
    Book book2 = new Book("noNameBook2___",22,true,"NoAuthor2___",DATE_FORMAT.parse("13-07-2022"));

    Magazine magazine1 = new Magazine("noNameMagazine1",1,false);
    Magazine magazine2 = new Magazine("noNameMagazine2___",222222222,true);

    ConvertorToStringForLiteratureTest() throws ParseException {
    }

    @Test
    void testConvertLiteratureObjectToListOfString_magazine1() {
        String expectedStr = "[Magazine, noNameMagazine1, 1, false]";
        assertEquals(expectedStr, convertorMagazine.convertObjectToListOfString(magazine1).toString());
    }
    @Test
    void testConvertLiteratureObjectToListOfString_magazine2() {
        String expectedStr = "[Magazine, noNameMagazine2___, 222222222, true]";
        assertEquals(expectedStr, convertorMagazine.convertObjectToListOfString(magazine2).toString());
    }

    @Test
    void testConvertLiteratureObjectToListOfString_book1() {
        String expectedStr = "[Book, noNameBook1, 1, false, NoAuthor1, 10-07-2022]";
        assertEquals(expectedStr, convertorBook.convertObjectToListOfString(book1).toString());
    }

    @Test
    void testConvertLiteratureObjectToListOfString_book2() {
        String expectedStr = "[Book, noNameBook2___, 22, true, NoAuthor2___, 13-07-2022]";
        assertEquals(expectedStr, convertorBook.convertObjectToListOfString(book2).toString());
    }

    @Test
    void testForClass_methods(){
        System.out.println("Literature.class.getClass() = " + Literature.class.getPackage().getName());
        System.out.println("Literature.class.getClass() = " + Literature.class.getClass());
        System.out.println("Literature.class.getName()  = " + Literature.class.getName());
        System.out.println("Literature.class.getCanonicalName()  = " + Literature.class.getCanonicalName());
        System.out.println("Literature.class.getSimpleName()  = " + Literature.class.getSimpleName());
        System.out.println("Literature.class.getTypeName()  = " + Literature.class.getTypeName());
        System.out.println("Literature.class.getName()  = " + Literature.class.getName());
        System.out.println("Literature.class.getName()  = " + Literature.class.getName());
        Literature.class.getClass();
    }

    //@Test
    //void testGetConvertedToStringList() {
    //    String expectedStr = "[[Book, noNameBook1, 1, false, NoAuthor1, 10-07-2022], " +
    //            "[Book, noNameBook2___, 22, true, NoAuthor2___, 13-07-2022], " +
    //            "[Magazine, noNameMagazine1, 1, false], " +
    //            "[Magazine, noNameMagazine2___, 222222222, true]]";
    //    List<Literature> stringList = new ArrayList<>();
    //    stringList.add(book1);
    //    stringList.add(book2);
    //    stringList.add(magazine1);
    //    stringList.add(magazine2);
//
    //    assertEquals(expectedStr, convertor.convertObjectToListOfStringt(stringList).toString());
    //}
}