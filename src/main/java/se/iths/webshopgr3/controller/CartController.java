package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.service.OrderItemService;
import se.iths.webshopgr3.service.OrderService;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final Cart cart;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @GetMapping
    public String getCartPage() {
        Cart thisCart = (Cart) cart.getAllProductsInCart();
        return "cart";
    }

    @PostMapping
    public String printProducts(Model model) {
        model.addAttribute("cart", cart.getAllProductsInCart());
        return "cart";
    }

    @GetMapping
    private String redirectToCheckout(@ModelAttribute AppUser user, @ModelAttribute Cart cart, Model model) {
        Order order = new Order();

        //Create order
        //Add orderItems to order
        //save orderItems
        //save order
        //clear cart
        return "redirect://checkout";
    }
}