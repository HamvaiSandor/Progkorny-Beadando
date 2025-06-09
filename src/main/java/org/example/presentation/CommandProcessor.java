package org.example.presentation;

import org.example.cart.ShoppingCartService;
import org.example.cart.ShoppingCartServiceImpl;
import org.example.exception.NoSuchProductException;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandProcessor {
    private final ShoppingCartService shoppingCartService = new ShoppingCartServiceImpl();

    public void process() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("Welcome to the product booking system! Enter a command:");

        while (running) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program...");
                running = false;
            } else if (input.matches("add product .+ \\d+")) {
                handleAddProduct(input);
            } else if (input.equalsIgnoreCase("order products")) {
                shoppingCartService.order();
                System.out.println("Products ordered successfully.");
            } else {
                System.out.println("Unknown command. Try again.");
            }
        }
        scanner.close();
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
            } catch (NoSuchProductException e) {
                System.out.println("Product not found: " + productName);
            }
        }
    }
}