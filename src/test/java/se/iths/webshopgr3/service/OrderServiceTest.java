package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.webshopgr3.model.*;
import se.iths.webshopgr3.repository.OrderRepository;

import java.util.List;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    private AppUser testUser;
    private Cart testCart;
    private Product product;

    @BeforeEach
    void setUp() {
        testUser = new AppUser(1L, "name", "password", true, "user1");
        testCart = new Cart();
        product = new Product(1L, "TestProduct", 10, "category", "img_url");

    }

    @Test
    @DisplayName("Assert that the creation of order (checkout) works")
    public void testCheckout() {
        CartItem cartItem = new CartItem(product);
        testCart.addProductToCart(cartItem);
        Order order = orderService.createOrder(testCart, testUser);

        Assertions.assertEquals(10L, order.getTotalPrice());

    }


    @Test
    @DisplayName("Orders can be retrieved using the username of the customer")
    void getOrdersByUsername() {
        testUser.setUsername("test2");
        Order order = orderService.createOrder(testCart, testUser);

        List<Order> expectedOrder = List.of(order);
        List<Order> orderReturned = orderService.getOrdersByUsername(testUser.getUsername());

        String expectedUser = expectedOrder.getFirst().getUsername();
        String recievedUser = orderReturned.getFirst().getUsername();

        Assertions.assertEquals(expectedUser, recievedUser);
    }

    @Test
    @DisplayName("Order is not retrieved due to username being incorrect")
    void orderIsNotRetrievedDueToInvalidUsername() {

    }


    @Test
    @DisplayName("All orders can be retrieved")
    void getAllOrders() {

        List<Order> ordersRecieved = orderService.getAllOrders();
        Assertions.assertNotNull(ordersRecieved);


    }

    @Test
    @DisplayName("An order can be found using its id")
    void getOrderById() {


    }

    @Test
    @DisplayName("Orders with invalid id is not returned")
    void orderNotReturnedDueToIncorrectId() {

    }
}