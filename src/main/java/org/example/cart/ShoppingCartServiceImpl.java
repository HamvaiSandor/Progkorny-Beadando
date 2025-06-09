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

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final Cart cart = new SimpleCart();
    private final ProductRepository productRepository = new StubProductRepository();
    private final OrderRepository orderRepository = new StubOrderRepository();
    private final List<Observer> observers = new ArrayList<>(List.of(
            new WareHouse()
    ));

    @Override
    public void order() {
        orderRepository.saveOrder(cart);
        for (Observer observer : observers) {
            observer.notify(cart);
        }
        cart.clearCart();
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
    }

    @Override
    public List<Product> getProductsFromCart() {
        return cart.getProducts();
    }

    @Override
    public void removeProduct(Product productToRemove) {
        cart.removeProduct(productToRemove);
    }

    @Override
    public void subscribe(Observer observer) {
        observers.add(observer);
    }
}