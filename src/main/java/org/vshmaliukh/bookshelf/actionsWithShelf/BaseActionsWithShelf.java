package org.vshmaliukh.bookshelf.actionsWithShelf;

import org.vshmaliukh.bookshelf.bookshelfObjects.Item;

/**
 * @author ShmaliukhVlad
 * @version 1.0.0
 * This is interface which describes all base methods Shelf have to do
 */
public interface BaseActionsWithShelf {

    /**
     * This method for adding Literature object (Book or Magazine) to the shelf
     * add element to the end of shelf objects
     * @param item Book or Magazine object
     */
    void addLiteratureObject(Item item);

    /**
     * This method for deleting Literature object (Book or Magazine) from the shelf by index
     * @param index index of Literature object in shelf need to delete
     */
    void deleteLiteratureObjectByIndex(int index);

    /**
     * This method for borrowing Literature object (Book or Magazine) from the shelf by index
     * @param index index of Literature object in shelf need to borrow
     */
    void borrowLiteratureObjectFromShelfByIndex(int index);

    /**
     * This method for arriving borrowed Literature object (Book or Magazine) back to the shelf by index
     * @param index index of Literature object out shelf need to arrive
     */
    void arriveLiteratureObjectFromShelfByIndex(int index);
}
