package se.iths.webshopgr3.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Product;
import se.iths.webshopgr3.service.ProductService;

@Controller
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    private final ProductService productService;

    @GetMapping
    public String showCart(HttpSession session, Model model) {
        Cart cart = getOrCreateCart(session);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/add/{id}")
    public String addToCart(@PathVariable Long id, HttpSession session) {
        Product product = productService.getProductById(id);
        Cart cart = getOrCreateCart(session);
        CartItem cartItem = new CartItem(product);
        cart.addProductToCart(cartItem);
        return "redirect:/products";
    }

    private Cart getOrCreateCart(HttpSession session) {
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            session.setAttribute("cart", cart);
        }
        return cart;
    }
}