package se.iths.webshopgr3.service;

import org.springframework.stereotype.Service;
import se.iths.webshopgr3.repository.OrderRepository;

@Service
public class OrderRepositoryService {

    private OrderRepository orderRepository;

    public OrderRepositoryService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
