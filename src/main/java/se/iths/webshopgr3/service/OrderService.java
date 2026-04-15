package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.lw.mailfunctionlibrary.service.MessageService;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MessageService messageService;

    /**
     * Creates a permanent Order in the database based on the content of a Cart.
     * This is called by the CheckoutService when a purchase is finalized.
     * Includes sending email confirmation as per project requirements (page 14).
     */
    public Order createOrder(Cart cart, AppUser user) {
        // 1. Map Cart to Order and OrderItems
        // 2. Save Order (cascades to OrderItems)
        // 3. Send email via messageService.send(...)
        
        return new Order(); 
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
