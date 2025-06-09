package org.example.cart;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.NoSuchProductException;
import org.example.model.Cart;
import org.example.model.Product;
import org.example.orderconfirm.Observer;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public abstract class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Cart cart;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final List<Observer> observers = new ArrayList<>();

    @Autowired
    public ShoppingCartServiceImpl(Cart cart, ProductRepository productRepository, OrderRepository orderRepository, List<Observer> observers) {
        this.cart = cart;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.observers.addAll(observers);
    }

    List<Observer> observersTemp2;

    @Autowired
    public void setObserversTemp2(List<Observer> observersTemp2) {
        this.observersTemp2 = observersTemp2;
    }

    @Autowired
    List<Observer> observersTemp;

    @PostConstruct
    public void populate() {
        log.info("Shopping cart initialized successfully.");
    }

    @PreDestroy
    public void cleanUp() {
        log.info("Shopping cart cleanup process started.");
    }

    @Override
    public void order() {
        orderRepository.saveOrder(cart);
        log.info("Order placed. Total price: {}", getTotalPrice());
        observers.forEach(observer -> observer.notify(cart));
        cart.clearCart();
    }

    @Override
    public void addProduct(String productName) throws NoSuchProductException {
        Product productToAdd = productRepository.findAllProducts()
                .stream()
                .filter(product -> product.name().equals(productName))
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
        cart.addProduct(productToAdd);
        log.info("Added product to cart: {}", productName);
    }

    @Override
    public List<Product> getProductsFromCart() {
        return cart.getProducts();
    }

    @Override
    public void removeProduct(final Product productToRemove) {
        cart.removeProduct(productToRemove);
    }

    @Override
    public double getTotalPrice() {
        return cart.getProducts().stream().mapToDouble(Product::price).sum();
    }

    @Override
    public void subscribe(final Observer observer) {
        observers.add(observer);
        log.info("Observer subscribed: {}", observer.getClass().getSimpleName());
    }
}















   /* @Autowired
    public ShoppingCartServiceImpl(Cart cart, ProductRepository productRepository, OrderRepository orderRepository, List<Observer> observers) {
        this.cart = cart;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.observers.addAll(observers);
    }

    @PreDestroy
    public void cleanUp() {
        log.info("CleanUp shopping cart.");
    }

    @Override
    public void order() {
        double totalPrice = getTotalPrice();  // Összár kiszámítása
        orderRepository.saveOrder(cart);
        for (Observer observer : observers) {
            observer.notify(cart);
        }
        cart.clearCart();
        log.info("Order placed and cart cleared. Total price: {}", totalPrice);
        System.out.println("Order placed successfully. Total price: " + totalPrice);
    }

    @Override
    public double getTotalPrice() {
        return cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void addProduct(String productName, int quantity) throws NoSuchProductException {
        Product productToAdd = productRepository.getAllProducts()
                .stream()
                .filter(product -> product.name().equals(productName))
                .findFirst()
                .orElseThrow(NoSuchProductException::new);

        productToAdd.purchaseTickets(quantity);
        cart.addProduct(productToAdd);
        log.info("Added product to cart: {} (quantity: {})", productName, quantity);
    }

    @Override
    public List<Product> getProductsFromCart() {
        return cart.getProducts();
    }

    @Override
    public void removeProduct(Product productToRemove) {
        cart.removeProduct(productToRemove);
        log.info("Removed product from cart: {}", productToRemove.name());
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
        log.info("Observer subscribed: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void listProducts() {
        List<Product> products = productRepository.findAllProducts();
        log.info("Available products:");
        for (Product product : products) {
            log.info("{} | Id: {} | Section: {} | Date: {} | Available: {} | Price: {}",
                    product.id(),
                    product.name(),
                    product.sector(),
                    product.date(),
                    product.AvailableTickets(),
                    product.price());
        }
    }
}*/