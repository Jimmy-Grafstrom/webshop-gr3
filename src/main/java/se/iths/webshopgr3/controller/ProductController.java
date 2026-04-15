package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.service.ProductService;

@Controller
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
//    private final Cart cart;

    @GetMapping()
    public String showProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";
    }

    @PostMapping
    public String AddProductToList(@ModelAttribute Product product, Model model) {
        CartItem cartItem = new CartItem(product);
        Cart cart = new Cart();
        cart.addProductToCart(cartItem);
        return "redirect://products";
    }
}