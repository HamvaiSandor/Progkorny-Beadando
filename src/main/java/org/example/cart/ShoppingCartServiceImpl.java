package org.example.cart;

import lombok.extern.slf4j.Slf4j;
import org.example.exception.NoSuchProductException;
import org.example.model.Cart;
import org.example.model.OrderEntity;
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
import java.util.UUID;

@Slf4j
@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final Cart cart;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final List<Observer> observers;

    @Autowired
    public ShoppingCartServiceImpl(Cart cart, ProductRepository productRepository, OrderRepository orderRepository, List<Observer> observers) {
        this.cart = cart;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.observers = (observers != null) ? observers : new ArrayList<>(); // ✔ Biztosítjuk, hogy ne legyen null
    }

    @PostConstruct
    public void populate() {
        log.info("Shopping cart initialized successfully with {} observers.", observers.size());
    }

    @PreDestroy
    public void cleanUp() {
        log.info("Shopping cart cleanup process started. Remaining products in cart: {}", cart.getProducts().size());
    }

    @Override
    public void order() {
        OrderEntity order = new OrderEntity(cart.getCustomerName(), cart.getProducts());
        orderRepository.save(order);

        log.info("Order placed. Total price: {} $, Number of items: {}", getTotalPrice(), cart.getProducts().size());
        observers.forEach(observer -> observer.notify(cart));
        cart.clearCart();
    }

    @Override
    public void addProduct(String productName, int quantity) throws NoSuchProductException {
        Product productToAdd = productRepository.findAll()
                .stream()
                .filter(product -> product.getName().equals(productName))
                .findFirst()
                .orElseThrow(NoSuchProductException::new);
        cart.addProduct(productToAdd);
        log.info("Added product to cart: {} (Quantity: {})", productName, quantity);
    }

    @Override
    public List<Product> getProductsFromCart() {
        return cart.getProducts();
    }

    @Override
    public void removeProduct(final Product productToRemove) {
        cart.removeProduct(productToRemove);
        log.info("Removed product from cart: {}", productToRemove.getName());
    }

    @Override
    public double getTotalPrice() {
        return cart.getProducts().stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public void subscribe(final Observer observer) {
        observers.add(observer);
        log.info("Observer subscribed: {}", observer.getClass().getSimpleName());
    }

    @Override
    public void listProducts() {
        cart.getProducts().forEach(product ->
                log.info("Product in cart: {} ({} tickets) - {} Ft", product.getName(), product.getAvailableTickets(), product.getPrice())
        );
    }
}