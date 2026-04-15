package se.iths.webshopgr3.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import se.iths.webshopgr3.repository.OrderItemRepository;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class OrderItemService {
    private final OrderItemRepository orderItemRepository;

    public void addOrderItem() {

    }


}
