package org.vshmaliukh.services.print_table_service.convertors;

import org.vshmaliukh.bookshelf.bookshelfObjects.Magazine;
import org.vshmaliukh.services.print_table_service.ConvertorToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConvertorToStringForMagazine implements ConvertorToString<Magazine> {

    @Override
    public List<String> convertObjectToListOfString(Magazine magazine) {
        List<String> list = new ArrayList<>();
        list.add(magazine.getClass().getSimpleName());
        list.add(magazine.getName());
        list.add(strValueOf(magazine.getPagesNumber()));
        list.add(strValueOf(magazine.isBorrowed()));
        return list;
    }
}