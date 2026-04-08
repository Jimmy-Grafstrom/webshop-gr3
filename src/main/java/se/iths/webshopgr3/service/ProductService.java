package se.iths.webshopgr3.service;

import org.springframework.stereotype.Service;
import se.iths.webshopgr3.repository.ProductRepository;

@Service
public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
