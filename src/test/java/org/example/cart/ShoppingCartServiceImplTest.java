package org.example.cart;

import org.example.exception.NoSuchProductException;
import org.example.model.Cart;
import org.example.model.OrderEntity;
import org.example.model.Product;
import org.example.model.SimpleProduct;
import org.example.orderconfirm.Observer;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ShoppingCartServiceImplTest {

    @Mock
    private Cart cart;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private ShoppingCartServiceImpl shoppingCartService;

    private List<Observer> observers;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mock ügyfél név
        when(cart.getCustomerName()).thenReturn("Test Customer");

        // Mock observer lista és új service példány
        observers = new ArrayList<>();
        shoppingCartService = new ShoppingCartServiceImpl(cart, productRepository, orderRepository, observers);
    }

    @Test
    void testOrder_SavesOrderAndNotifiesObserversAndClearsCart() {
        // given
        Observer observer = mock(Observer.class);
        shoppingCartService.subscribe(observer);

        List<Product> products = List.of(
                new SimpleProduct(1L, "2025-07-01", "P", "A", 1, 1000.0)
        );
        when(cart.getProducts()).thenReturn(products);

        // when
        shoppingCartService.order();

        // then
        ArgumentCaptor<OrderEntity> orderCaptor = ArgumentCaptor.forClass(OrderEntity.class);
        verify(orderRepository).save(orderCaptor.capture());

        OrderEntity savedOrder = orderCaptor.getValue();
        assertEquals("Test Customer", savedOrder.getCustomerName());
        assertEquals(products.size(), savedOrder.getProducts().size());

        verify(observer, times(1)).notify(cart);
        verify(cart, times(1)).clearCart();
    }
}