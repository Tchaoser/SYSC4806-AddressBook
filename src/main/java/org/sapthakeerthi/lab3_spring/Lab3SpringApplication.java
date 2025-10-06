package org.sapthakeerthi.lab3_spring;

import jdk.jshell.spi.ExecutionControl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Lab3SpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(Lab3SpringApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(BuddyInfoRepository repositoryB,  AddressBookRepository repositoryA) {
        return (args) -> {
            AddressBook addressBook = new AddressBook();
            BuddyInfo samIam = new BuddyInfo("Sam","9289782372");
            BuddyInfo steveIsee = new BuddyInfo("Steve Rogers", "99999999");
            addressBook.addBuddy(samIam);
            addressBook.addBuddy(steveIsee);

            repositoryA.save(addressBook); //cascade all saves the buddies in the buddylist as well
            repositoryA.findAll().forEach(AddressBook::printBuddyList);

        };
    }

}
//microphone not working
//dunno why
//will beback
//yes onw aec
//6135202600 ext;1832
//Sapthakeerthi Udayakumar
//101289957