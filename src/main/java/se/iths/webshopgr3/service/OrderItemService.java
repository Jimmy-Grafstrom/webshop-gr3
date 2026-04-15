package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.model.OrderItem;
import se.iths.webshopgr3.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    /**
     * Creates an OrderItem based on a CartItem and links it to an Order.
     * Does not need to be saved separately as Order uses CascadeType.ALL.
     */
    public OrderItem createOrderItem(CartItem cartItem, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductName(cartItem.getProduct().getName());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(cartItem.getProduct().getPrice());
        orderItem.setOrder(order);
        return orderItem;
    }
}
