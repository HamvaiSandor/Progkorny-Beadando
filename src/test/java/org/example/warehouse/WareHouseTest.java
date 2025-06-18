package org.example.warehouse;

import org.example.model.Cart;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

class WareHouseTest {

    private WareHouse warehouse;

    @BeforeEach
    void setUp() {
        warehouse = Mockito.spy(new WareHouse()); // spy, hogy a metódushívásokat figyelhessük
    }

    @Test
    void testNotify_CallsRegisterOrderedProducts() {
        // Arrange
        Product product1 = mock(Product.class);
        Product product2 = mock(Product.class);
        List<Product> productList = Arrays.asList(product1, product2);

        Cart mockCart = mock(Cart.class);
        when(mockCart.getProducts()).thenReturn(productList);

        // Act
        warehouse.notify(mockCart);

        // Assert
        verify(warehouse, times(1)).registerOrderedProducts(productList);
    }
}