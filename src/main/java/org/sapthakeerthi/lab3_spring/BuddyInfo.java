package org.sapthakeerthi.lab3_spring;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String buddyName;
    private String phoneNumber;


    public BuddyInfo() {}

    public BuddyInfo(String buddyName, String phoneNumber) {
        this.buddyName = buddyName;
        this.phoneNumber = phoneNumber;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setBuddyName(String buddyName) {
        this.buddyName = buddyName;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public Long getId() {
        return id;
    }
    public String getBuddyName() {
        return buddyName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void printBuddyInfo() {
        System.out.println("Buddy Name: " + buddyName);
        System.out.println("Phone Number: " + phoneNumber);
    }
}