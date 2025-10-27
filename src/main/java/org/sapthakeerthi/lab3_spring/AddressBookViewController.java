package org.sapthakeerthi.lab3_spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AddressBookViewController {
    //thymeleaf controller
    private final AddressBookRepository repo;

    public AddressBookViewController(AddressBookRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("addressBooks", repo.findAll());
        return "index";   //templates/index.html
    }

    @GetMapping("/addressbook/{id}")
    public String showAddressBook(@PathVariable Long id, Model model) {
        AddressBook book = repo.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "addressbook";  //templates/addressbook.html
    }

    @PostMapping("/addBook")
    public String addBook(@RequestParam String name) {
        AddressBook book = new AddressBook();
        repo.save(book);
        return "redirect:/";
    }

    @PostMapping("/addressbook/{id}/addBuddy")
    public String addBuddy(@PathVariable Long id,
                           @RequestParam String name,
                           @RequestParam String phoneNumber) {
        AddressBook book = repo.findById(id).orElse(null);
        if (book != null) {
            BuddyInfo buddy = new BuddyInfo(name, phoneNumber);
            book.addBuddy(buddy);
            repo.save(book);
        }
        return "redirect:/addressbook/" + id;
    }
}
