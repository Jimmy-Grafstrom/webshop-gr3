package se.iths.webshopgr3.service;

import org.springframework.stereotype.Service;
import se.iths.webshopgr3.repository.ProductRepository;

@Service
public class OrderItemService {

    private ProductRepository productRepository;

    public OrderItemService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
