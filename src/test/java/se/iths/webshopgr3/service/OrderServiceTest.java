package se.iths.webshopgr3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.repository.OrderRepository;

@SpringBootTest
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    public void testCheckout() {
        Cart cart = new Cart();
        Product product = new Product(1L, "TestProduct", 3.55, "category", "img_url");
        AppUser user = new AppUser(1L, "name", "password", true, "user");
        CartItem cartItem = new CartItem(product);

//        cart.addProductToCart(cart);
//        Order order= orderService.createOrder(cart, );

    }

    @Test
    void createOrder() {
    }

    @Test
    void getOrdersByUsername() {
    }

    @Test
    void getAllOrders() {
    }

    @Test
    void getOrderById() {
    }
}