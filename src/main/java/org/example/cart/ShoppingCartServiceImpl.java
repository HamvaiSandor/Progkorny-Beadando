package org.example.cart;

import org.example.exception.NoSuchProductException;
import org.example.model.Cart;
import org.example.model.Product;
import org.example.model.SimpleCart;
import org.example.orderconfirm.Observer;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.example.repository.StubOrderRepository;
import org.example.repository.StubProductRepository;
import org.example.warehouse.WareHouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private static final Logger log = LoggerFactory.getLogger(ShoppingCartServiceImpl.class);

    private final Cart cart;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final List<Observer> observers;

    @Autowired
    public ShoppingCartServiceImpl(Cart cart, ProductRepository productRepository, OrderRepository orderRepository, List<Observer> observers) {
        this.cart = cart;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.observers = observers;
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
                .filter(product -> product.getName().equals(productName))
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
        log.info("Removed product from cart: {}", productToRemove.getName());
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
        log.info("Observer subscribed: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void listProducts() {
        List<Product> products = productRepository.getAllProducts();
        log.info("Available products:");
        for (Product product : products) {
            log.info("{} | Section: {} | Date: {} | Available: {} | Price: {}",
                    product.getName(),
                    product.getSector(),
                    product.getDate(),
                    product.getAvailableTickets(),
                    product.getPrice());
        }
    }
}