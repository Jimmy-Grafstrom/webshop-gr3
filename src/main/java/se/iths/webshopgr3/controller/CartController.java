package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.Cart;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private Cart cart;

    @GetMapping
    public String getCartPage(Model model) {
        model.addAttribute("cart", cart.getAllProductsInCart());
        return "cart";
    }

}