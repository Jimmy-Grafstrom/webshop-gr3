package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.model.OrderItem;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.repository.OrderItemRepository;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OrderItemServiceMockTest {

    @InjectMocks
    private OrderItemService orderItemService;

    @Mock
    private OrderItemRepository orderItemRepository;

    private Order testOrder;

    private Product product;

    private CartItem cartItem;

    private List<OrderItem> orderItemList;

    @BeforeEach
    void setUp() {

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
    }

    @Test
    @DisplayName("Assert that the item retrieved matches the values sent into the method")
    void createOrderItem() {
        OrderItem ordertItemReturned = orderItemService.createOrderItem(cartItem, testOrder);

        Assertions.assertEquals(cartItem.getProduct().getName(), ordertItemReturned.getProductName());
        Assertions.assertEquals(cartItem.getQuantity(), ordertItemReturned.getQuantity());
        Assertions.assertEquals(cartItem.getProduct().getPrice(), ordertItemReturned.getPrice());
        Assertions.assertEquals(testOrder.getId(), ordertItemReturned.getOrder().getId());
    }
}