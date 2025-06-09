package org.example.presentation;

import org.example.cart.ShoppingCartService;
import org.example.cart.ShoppingCartServiceImpl;
import org.example.exception.NoSuchProductException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CommandProcessor {
    private static final Logger log = LoggerFactory.getLogger(CommandProcessor.class);
    private final ShoppingCartService shoppingCartService;

    public CommandProcessor(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }


    public void process() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        log.info("Application started.");
        System.out.println("Welcome to the product booking system! Enter a command:");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            log.info("User input received: {}", input);

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                log.info("Exiting application...");
                running = false;
            } else if (input.matches("add product .+ \\d+")) {
                handleAddProduct(input);
            } else if (input.equalsIgnoreCase("order products")) {
                shoppingCartService.order();
                System.out.println("Products ordered successfully.");
                log.info("Products ordered.");
            } else if (input.equalsIgnoreCase("list products")) {
                shoppingCartService.listProducts();
                log.info("Product list requested.");
            } else {
                System.out.println("Unknown command. Try again.");
                log.warn("Unknown command: {}", input);
            }
        }
        scanner.close();
        log.info("Scanner closed, application terminated.");
    }

    private void handleAddProduct(String input) {
        Pattern pattern = Pattern.compile("add product (.+) (\\d+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            String productName = matcher.group(1);
            int quantity = Integer.parseInt(matcher.group(2));
            try {
                shoppingCartService.addProduct(productName, quantity);
                System.out.println("Product added: " + productName);
                log.info("Product added via command: {} (quantity: {})", productName, quantity);
            } catch (NoSuchProductException e) {
                System.out.println("Product not found: " + productName);
                log.warn("Attempted to add unknown product: {}", productName);
            }
        } else {
            log.warn("Invalid product add command format: {}", input);
        }
    }
}