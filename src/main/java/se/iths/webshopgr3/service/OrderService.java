package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.model.OrderItem;
import se.iths.webshopgr3.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;

    /**
     * Creates a permanent Order in the database based on the content of a Cart.
     * Mapping and persistence is handled here. Email confirmation and workflow
     * orchestration should be handled by the CheckoutService.
     */
    public Order createOrder(Cart cart, AppUser user) {
        Order order = new Order();
        order.setUsername(user.getUsername());
        order.setTotalPrice(cart.getTotalPrice());
        order.setOrderDate(LocalDateTime.now());

        List<OrderItem> orderItems = new ArrayList<>();
        cart.getCartItems().forEach(cartItem -> {
            OrderItem orderItem = orderItemService.createOrderItem(cartItem, order);
            orderItems.add(orderItem);
        });
        order.setOrderItems(orderItems);

        Order savedOrder = orderRepository.save(order);
        cart.clearCart();

        return savedOrder;
    }

    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
