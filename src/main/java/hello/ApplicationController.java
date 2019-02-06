package hello;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ApplicationController {

    @Autowired
    private AddressBookRepository addressBookRepository;

    @GetMapping(value = "/")
    public String home(Model model) {
        // Store all address books into a list
        List<AddressBook> addressBooks = new ArrayList<>();
        addressBookRepository.findAll().forEach(addressBooks::add);

        // Add address books to model
        model.addAttribute("addressBooks", addressBooks);
        return "home";
    }
}
