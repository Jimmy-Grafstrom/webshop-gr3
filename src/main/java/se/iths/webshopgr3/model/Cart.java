package se.iths.webshopgr3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@Component
public class Cart {

    private List<CartItem> cartItems = new ArrayList<>();
    private double totalPrice;

    public void addProductToCart(CartItem cartItem) {
        for (CartItem product : cartItems) {
            if (product.getId().equals(cartItem.getId())) {
                increaseQuantity(product);
            } else {
                cartItems.add(cartItem);
            }
        }
        updatePrice();
    }

    public List<CartItem> getAllProductsInCart() {
        return cartItems;
    }

    public void updatePrice() {
        totalPrice = 0;
        for (CartItem product : cartItems) {
            totalPrice += product.getQuantity() * product.getProduct().getPrice();
        }
    }

    public void increaseQuantity(CartItem itemInCart) {
        itemInCart.setQuantity(itemInCart.getQuantity() + 1);
    }

    public void clearCart() {
        cartItems.clear();
    }
}