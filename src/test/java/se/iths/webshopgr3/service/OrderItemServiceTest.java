package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.model.OrderItem;
import se.iths.webshopgr3.model.Product;

@SpringBootTest
class OrderItemServiceTest {

    @Autowired
    private OrderItemService orderItemService;


    private CartItem cartItem;
    private Order order;

    @BeforeEach
    void setUp() {
        order = new Order();
        cartItem = new CartItem(new Product());
    }

    @Test
    @DisplayName("OrderItem is saved and can be retrieved from database")
    void createOrderItem() {
        OrderItem orderItem = orderItemService.createOrderItem(cartItem, order);
        Assertions.assertNotNull(orderItem);

    }
}