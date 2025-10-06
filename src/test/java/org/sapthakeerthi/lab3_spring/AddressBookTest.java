package org.sapthakeerthi.lab3_spring;

import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class AddressBookTest {

    @Test
    public void addBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Alice", "1234567890");
        book.addBuddy(buddy);

        List<BuddyInfo> list = book.getBuddyList();
        assertEquals(1, list.size());
        assertEquals("Alice", list.get(0).getBuddyName());
    }


    @Test
    public void removeBuddy() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Bob", "9876543210");
        book.addBuddy(buddy);

        book.removeBuddy(buddy);
        assertTrue(book.getBuddyList().isEmpty());
    }

    @Test
    public void getBuddyList() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy1 = new BuddyInfo("Alice", "1234567890");
        BuddyInfo buddy2 = new BuddyInfo("Bob", "9876543210");
        book.addBuddy(buddy1);
        book.addBuddy(buddy2);

        List<BuddyInfo> list = book.getBuddyList();
        assertEquals(2, list.size());
        assertEquals("Alice", list.get(0).getBuddyName());
        assertEquals("Bob", list.get(1).getBuddyName());
    }

    @Test
    public void printBuddyList() {
        AddressBook book = new AddressBook();
        BuddyInfo buddy = new BuddyInfo("Charlie", "5555555555");
        book.addBuddy(buddy);

        // Instead of testing print output, check the data directly
        assertEquals(1, book.getBuddyList().size());
        assertEquals("Charlie", book.getBuddyList().get(0).getBuddyName());
        assertEquals("5555555555", book.getBuddyList().get(0).getPhoneNumber());
    }
}
