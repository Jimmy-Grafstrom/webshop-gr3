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
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class ProductServiceMockTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    private List<Product> testProducts;

    @BeforeEach
    void setUp() {

        testProduct = new Product();
        testProduct.setName("name");
        testProduct.setPrice(2.6);
        testProduct.setCategory("Category");
        testProduct.setImageUrl("img_url");

        testProducts = new ArrayList<>();

        testProducts.add(testProduct);
        testProducts.add(testProduct);
        testProducts.add(testProduct);
    }

    @Test
    @DisplayName("Assert that all products can be retrieved")
    void getAllProducts() {
        Mockito.when(productRepository.findAll()).thenReturn(testProducts);
        List<Product> productsReturned = productService.getAllProducts();
        Assertions.assertEquals(testProducts.getFirst().getId(), productsReturned.getFirst().getId());
    }

    @Test
    @DisplayName("Assert that findAll is called in productRepository")
    void getAllProductsCallsFindAllInRepository() {
        productService.getAllProducts();
        Mockito.verify(productRepository).findAll();
    }

    @Test
    @DisplayName("The method returns the correct product")
    void getProductById() {
        Mockito.when(productRepository.findById(testProduct.getId()))
                .thenReturn(Optional.of((testProduct)));
        Product productReturned = productService.getProductById(testProduct.getId());
        Assertions.assertEquals(testProduct.getId(), productReturned.getId());
    }

    @Test
    @DisplayName("getProductById thows exception due to invalid id")
    void getProductByIdThrowsException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    productService.getProductById(100L);
                }
        );
    }

    @Test
    @DisplayName("Assert that save-method is called")
    void saveProduct() {
        productService.saveProduct(testProduct);
        Mockito.verify(productRepository).save(testProduct);
    }

    @Test
    @DisplayName("DeleteById is called in repository")
    void deleteProduct() {
        productService.deleteProduct(testProduct.getId());
        Mockito.verify(productRepository).deleteById(testProduct.getId());
    }
}