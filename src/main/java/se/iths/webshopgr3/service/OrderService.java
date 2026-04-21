package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.model.OrderItem;
import se.iths.webshopgr3.repository.AppUserRepository;
import se.iths.webshopgr3.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import se.iths.lw.mailfunctionlibrary.model.Email;
import se.iths.lw.mailfunctionlibrary.service.MessageService;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemService orderItemService;
    private final MessageService messageService;

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
        
        // Send confirmation email after saving the order
        sendOrderConfirmationEmail(user, savedOrder);
        
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

    private void sendOrderConfirmationEmail(AppUser user, Order order){
        Email email = new Email();
        email.setRecipient(user.getUsername());
        email.setSubject("Order Confirmation - Order #" + order.getId());
        email.setMessage("Hello " + user.getUsername() + ",\n\n" +
                "Thank you for your order! Here is your order confirmation:\n" +
                "Order ID: " + order.getId() + "\n" +
                "Total Price: " + order.getTotalPrice() + " SEK\n\n" +
                "We will process and ship your items as soon as possible.\n\n" +
                "Kind regards,\nWebshop-Group 3");
        
        try {
            messageService.send(email);
        } catch (Exception e) {
            // Log the error but allow the application to proceed
            System.err.println("Failed to send confirmation email: " + e.getMessage());
        }
    }
}
