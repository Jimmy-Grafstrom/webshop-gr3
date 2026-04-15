package se.iths.webshopgr3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(Order order) {


        orderRepository.save(order);
    }


}
