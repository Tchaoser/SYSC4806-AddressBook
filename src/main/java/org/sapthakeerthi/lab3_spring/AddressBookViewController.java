package org.sapthakeerthi.lab3_spring;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AddressBookViewController {
    //thymeleaf controller
    private final AddressBookRepository repo;

    public AddressBookViewController(AddressBookRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/addressbook/{id}")
    public String showAddressBook(@PathVariable Long id, Model model) {
        AddressBook book = repo.findById(id).orElse(null);
        model.addAttribute("book", book);
        return "addressbook"; // -> addressbook.html in /templates
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("addressBooks", repo.findAll());
        return "index";   // renders templates/index.html
    }
}
