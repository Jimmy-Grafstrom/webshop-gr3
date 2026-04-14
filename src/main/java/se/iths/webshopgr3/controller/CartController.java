package se.iths.webshopgr3.controller;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cart")
public class CartController {

    private final ProductService productService;

    public CartController(ProductService productService) {
        this.productService = productService;
    }

    private Cart getCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }

        return cart;
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.getProductById(id).orElseThrow();

        Cart cart = getCart(session);
        cart.addProduct(product);

        return "redirect:/products";
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        Cart cart = getCart(session);

        model.addAttribute("cart", cart);
        return "cart";
    }
}