package org.example.warehouse;

import org.example.model.Cart;
import org.example.model.Product;
import org.example.orderconfirm.Observer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WareHouse implements Observer {
    private static final Logger LOG = LoggerFactory.getLogger(WareHouse.class);

    @Override
    public void notify(Cart cart) {
        registerOrderedProducts(cart.getProducts());
    }

    public void registerOrderedProducts(List<Product> products) {
        LOG.info("Products registered in warehouse: {}", products);
    }
}
