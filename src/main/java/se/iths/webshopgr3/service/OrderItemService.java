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

    public OrderItem createOrderItem(CartItem cartItem, Order order) {
        OrderItem orderItem = new OrderItem();
        orderItem.setProductName(cartItem.getProduct().getName());
        orderItem.setQuantity(cartItem.getQuantity());
        orderItem.setPrice(cartItem.getProduct().getPrice());
        orderItem.setOrder(order);
        return orderItem;
    }
}
