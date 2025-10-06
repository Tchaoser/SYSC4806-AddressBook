package org.sapthakeerthi.lab3_spring;

import static org.junit.Assert.*;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class BuddyInfoTest {

    @Test
    public void getBuddyName() {
        BuddyInfo buddy = new BuddyInfo("Alice", "1234567890");
        assertEquals("Alice", buddy.getBuddyName());
    }

    @Test
    public void getPhoneNumber() {
        BuddyInfo buddy = new BuddyInfo("Alice", "1234567890");
        assertEquals("1234567890", buddy.getPhoneNumber());
    }

    @Test
    public void printBuddyInfo() {
        BuddyInfo buddy = new BuddyInfo("Alice", "1234567890");

        assertEquals("Alice", buddy.getBuddyName());
        assertEquals("1234567890", buddy.getPhoneNumber());
    }
}
