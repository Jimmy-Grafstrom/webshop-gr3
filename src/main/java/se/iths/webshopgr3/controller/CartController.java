package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.service.ProductService;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final Cart cart;
    private final ProductService productService;

    @GetMapping
    public String getCartPage() {
        return "cart";
    }

    @GetMapping
    public String printProducts(Model model) {
        model.addAttribute("cart", cart);
        return "cart";
    }


}