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

    @GetMapping
    public String showRegistrationForm() {
        return "register"; // /register URL-re jön a sablon
    }

    @PostMapping
    public String processRegistration(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {

        Customer newCustomer = new Customer(name, email, password);
        customerRepository.save(newCustomer);

        return "redirect:/register?success=true";
    }
}
