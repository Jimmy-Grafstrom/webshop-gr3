package se.iths.webshopgr3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

//    private final Cart cart;
//    private final OrderService orderService;
//    private final OrderItemService orderItemService;
//
//    @GetMapping
//    public String getCartPage() {
//        Cart thisCart = (Cart) cart.getAllProductsInCart();
//        return "cart";
//    }
//
//    @PostMapping
//    public String printProducts(Model model) {
//        model.addAttribute("cart", cart.getAllProductsInCart());
//        return "cart";
//    }
//
//    @GetMapping
//    private String redirectToCheckout() {
//        Order order = new Order();
//
//        //Create order
//        //Add orderItems to order
//        //save orderItems
//        //save order
//        //clear cart
//        return "redirect://checkout";
//    }
}