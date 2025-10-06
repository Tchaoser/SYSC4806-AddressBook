package org.sapthakeerthi.lab3_spring;



import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BuddyInfo> buddyList = new ArrayList<>();

    public AddressBook() {}

    public Long getId() {
        return id;
    }

    public void addBuddy(BuddyInfo buddy) {
        buddyList.add(buddy);
    }

    public void removeBuddy(BuddyInfo buddy) {
        buddyList.remove(buddy);
    }

    public List<BuddyInfo> getBuddyList() {
        return buddyList;
    }

    public void printBuddyList() {
        for (BuddyInfo buddy : buddyList) {
            buddy.printBuddyInfo();
        }
    }

}
