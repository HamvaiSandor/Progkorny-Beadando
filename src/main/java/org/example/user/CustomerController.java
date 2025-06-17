package org.example.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/register")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/register")
    public String showRegisterPage() {
        return "register/submit";  // regisztrációs oldal megjelenítése
    }

    @PostMapping
    public String processRegistration(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        Customer newCustomer = new Customer(name, email, password);
        customerRepository.save(newCustomer);  // mentés az adatbázisba

        return "redirect:/products/list";  // sikeres regisztráció után átirányítás
    }
}
