package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.lw.mailfunctionlibrary.service.MessageService;
import se.iths.webshopgr3.model.*;
import se.iths.webshopgr3.repository.AppUserRepository;
import se.iths.webshopgr3.repository.OrderItemRepository;
import se.iths.webshopgr3.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class OrderServiceMockTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private OrderItemService orderItemService;

    @Mock
    private MessageService messageService;

    @Mock
    private AppUserRepository appUserRepository;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderItemRepository orderItemRepository;

    private List<OrderItem> orderItemList;

    private Order testOrder;
    private AppUser testUser;
    private Cart testCart;
    private Product product;
    private CartItem cartItem;

    @BeforeEach
    void setUp() {
        testUser = new AppUser();
        testUser.setId(1L);
        testUser.setUsername("test@test.com");
        testUser.setPassword("password");
        testUser.setConsent(true);
        testUser.setRole("USER");

        testOrder = new Order();
        testOrder.setId(1L);
        testOrder.setOrderDate(LocalDateTime.now());
        testOrder.setUsername("test@test.com");
        testOrder.setTotalPrice(4.99);
        testOrder.setOrderItems(orderItemList);

        product = new Product();
        product.setName("TestProduct");
        product.setPrice(10);
        product.setCategory("category");
        product.setImageUrl("img_url");

        cartItem = new CartItem(product);
        cartItem.setId(1L);

        OrderItem orderItem = new OrderItem();
        orderItem.setQuantity(2);
        orderItem.setOrder(testOrder);
        orderItem.setProductName(product.getName());
        orderItem.setPrice(product.getPrice());

        testCart = new Cart();
        testCart.addProductToCart(cartItem);
    }

    @Test
    @DisplayName("saveMethod is called in repository")
    void saveMethodIsCalledInRepository() {
        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(testOrder);
        orderService.createOrder(testCart, testUser);
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        Mockito.verify(orderRepository).save(orderCaptor.capture());
    }

    @Test
    @DisplayName("The order that is saved has the correct values")
    void valueReturnedIsCorrect() {
        Mockito.when(orderItemService.createOrderItem(any(), any())).thenReturn(new OrderItem());
        Mockito.when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        //Call method
        orderService.createOrder(testCart, testUser);
        //Catch the data that is passed into the mocked method.
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        //Save the data to captor
        Mockito.verify(orderRepository).save(orderCaptor.capture());
        //Extract values from the captor
        Order capturedOrder = orderCaptor.getValue();

        Assertions.assertEquals(testUser.getUsername(), capturedOrder.getUsername());
        Assertions.assertNotNull(capturedOrder.getOrderDate());
        Assertions.assertNotNull(capturedOrder.getOrderItems());
        Assertions.assertFalse(capturedOrder.getOrderItems().isEmpty());
        Assertions.assertEquals(testCart.getTotalPrice(), capturedOrder.getTotalPrice());
    }

    @Test
    @DisplayName("findById is called in repository")
    void findByIdIsCalledInRepository() {
        orderService.getOrderById(testOrder.getId());
        Mockito.verify(orderRepository).findById(testOrder.getId());
    }

    @Test
    @DisplayName("findOrdersByUsername called in repository")
    void findOrdersByIdIsCalledInRepository() {
        orderService.getOrdersByUsername(testUser.getUsername());
        Mockito.verify(orderRepository).findByUsername(testUser.getUsername());
    }

    @Test
    @DisplayName("findAll is called in repository")
    void findAllIsCalledInRepository() {
        orderService.getAllOrders();
        Mockito.verify(orderRepository).findAll();
    }
}