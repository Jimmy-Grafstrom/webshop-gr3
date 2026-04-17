package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.webshopgr3.model.*;
import se.iths.webshopgr3.repository.OrderRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        Order order = orderService.createOrder(testCart, testUser);

        List<Order> expectedOrder = List.of(order);
        List<Order> orderReturned = orderService.getOrdersByUsername("test2");

        Assertions.assertNotEquals(expectedOrder, orderReturned);

    }


    @Test
    @DisplayName("All orders can be retrieved")
    void getAllOrders() {
        List<Order> ordersReceived = orderService.getAllOrders();
        Assertions.assertNotNull(ordersReceived);
    }

    @Test
    @DisplayName("An order can be found using its id")
    void getOrderById() {
        Order order = orderService.createOrder(testCart, testUser);

        List<Order> orderReceived = Collections.singletonList(orderService.getOrderById(order.getId()).orElseThrow());

        Assertions.assertNotNull(orderReceived);
    }

    @Test
    @DisplayName("An order can be found using its id")
    void getOrderByIdReturnsCorrectData() {
        Order order = orderService.createOrder(testCart, testUser);

        List<Order> expectedOrder = List.of(order);

        List<Order> orderReceived = Collections.singletonList(orderService.getOrderById(order.getId()).orElseThrow());
        Assertions.assertEquals(expectedOrder, orderReceived);
    }


    @Test
    @DisplayName("Orders with invalid id is not returned")
    void orderNotReturnedDueToIncorrectId() {

        Order order = orderService.createOrder(testCart, testUser);

        Optional<Order> orderReceived = orderService.getOrderById(8L);

        Assertions.assertEquals(Optional.empty(), orderReceived);


    }
}