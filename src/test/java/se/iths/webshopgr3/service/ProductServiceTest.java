package se.iths.webshopgr3.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.repository.ProductRepository;

import java.util.List;

@SpringBootTest
class ProductServiceTest {


    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = new Product();
        testProduct.setName("testproduct");
        testProduct.setCategory("category");
        testProduct.setPrice(10);
        testProduct.setImageUrl("image-url");

    }

    @Test
    @DisplayName("getProducts returns list of products")
    void getAllProductsWithDataStored() {

        productService.saveProduct(testProduct);
        productService.saveProduct(testProduct);
        productService.saveProduct(testProduct);


        List<Product> productList = productService.getAllProducts();
        Assertions.assertNotNull(productList);

    }

    @Test
    @DisplayName("Get products returns void list since there is no data in the database")
    void getAllProductsReturnsNull() {
        List<Product> productList = productService.getAllProducts();
        Assertions.assertEquals(0, (long) productList.size());
    }

    @Test
    @DisplayName("Product exists in database and is retrieved")
    void getProductById() {
        productService.saveProduct(testProduct);

        Product productReturned = productService.getProductById(testProduct.getId());

        Assertions.assertEquals(testProduct.getId(), productReturned.getId());


    }

    @Test
    @DisplayName("Products does not exists in database and is not retrieved")
    void getProductByIdFails() {
        Assertions.assertThrows(Exception.class, () -> {
            productService.getProductById(9L);
        });

    }

    @AfterEach
    void after() {
        for (Product p : productService.getAllProducts()) {
            productService.deleteProduct(p.getId());
        }
    }

}