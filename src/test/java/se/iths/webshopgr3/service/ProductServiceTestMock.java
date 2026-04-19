package se.iths.webshopgr3.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTestMock {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    private List<Product> testProducts;

    @BeforeEach
    void setUp() {

        product = new Product();
        product.setName("name");
        product.setPrice(2.6);
        product.setCategory("Category");
        product.setImageUrl("img_url");

        testProducts = new ArrayList<>();

    }

    @Test
    void getAllProducts() {
        productService.saveProduct(product);
        productService.saveProduct(product);
        productService.saveProduct(product);

        Mockito.when(productRepository.findAll());

    }

    @Test
    @DisplayName("The method returns the correct product")
    void getProductById() {
        Mockito.when(productRepository.getReferenceById(Mockito.anyLong()))
                .thenReturn(Mockito.any(Product.class));

        productService.saveProduct(product);

        Product productReturned = productService.getProductById(product.getId());

        Assertions.assertEquals(product.getId(), productReturned.getId());

    }

    @Test
    @DisplayName("Assert that save-method is called")
    void saveProduct() {
        productService.saveProduct(product);
        Mockito.verify(productRepository).save(product);
    }

    @Test
    void deleteProduct() {
        productService.saveProduct(product);
        productService.deleteProduct(product.getId());
        Mockito.verify(productRepository).delete(product);

    }
}