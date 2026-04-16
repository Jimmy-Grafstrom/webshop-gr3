package se.iths.webshopgr3.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import se.iths.webshopgr3.model.AppUser;
import se.iths.webshopgr3.model.Cart;
import se.iths.webshopgr3.model.CartItem;
import se.iths.webshopgr3.model.Order;
import se.iths.webshopgr3.service.AppUserService;
import se.iths.webshopgr3.service.OrderService;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Controller
@AllArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final OrderService orderService;
    private final AppUserService appUserService;


    @GetMapping
    public String checkout(HttpSession httpSession, Model model,
                           RedirectAttributes redirectAttributes) {
    Cart cart= (Cart)httpSession.getAttribute("cart");

    if(cart == null || cart.getAllProductsInCart().isEmpty()){
        redirectAttributes.addFlashAttribute("errorMessage",
                "Your shopping cart is empty, please add products to the cart first.");
        return "redirect:/products";
        }

    double total = 0.0;
    for(CartItem cartItem : cart.getAllProductsInCart()){
        total += cartItem.getProduct().getPrice()*cartItem.getQuantity();
    }
    BigDecimal bigDecimal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
    total= bigDecimal.doubleValue();

    model.addAttribute("cart", cart);
    model.addAttribute("totalPrice", total);
    return "checkout";
    }


    @PostMapping
    public String processCheckout(@AuthenticationPrincipal UserDetails userDetails,
                                  HttpSession httpSession, Model model){

    Cart cart = (Cart)httpSession.getAttribute("cart");

    if(cart == null || cart.getAllProductsInCart().isEmpty()){
        return "redirect:/products";
    }
    String username =userDetails.getUsername();
    AppUser appUser = appUserService.findByUsername(username);
    if(appUser == null){
        return "redirect:/login";
    }

        Order newOrder = orderService.createOrder(cart, appUser);
        httpSession.removeAttribute("cart");
        model.addAttribute("order", newOrder);
        return "order-confirmation";


    }
}
