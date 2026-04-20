package se.iths.webshopgr3.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.lw.mailfunctionlibrary.service.MessageService;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.repository.AppUserRepository;
import se.iths.webshopgr3.repository.OrderRepository;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceTestMock {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private MessageService messageService;

    @Mock
    private AppUserRepository appUserRepository;

    private Cart testCart;

    private AppUser testUser;

    private Order order;

    private Product product;

    @BeforeEach
    void setUp() {
        //ORDERITEM
        //CARTITEM
        //ORDER

        testUser = new AppUser(1L, "name", "password", true, "user1");
        testCart = new Cart();
        product = new Product(1L, "TestProduct", 10, "category", "img_url");
    }

    @Test
    @DisplayName("Assert that cart is cleared when order is saved")
    void createOrderItem() {

    }


}