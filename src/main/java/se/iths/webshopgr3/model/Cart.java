package se.iths.webshopgr3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SessionScope
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Component
public class Cart {

    private List<CartItem> cartItems = new ArrayList<>();
    private double totalPrice;

    public void addProductToCart(CartItem cartItem) {
        for (CartItem product : cartItems) {
            if (product.getId().equals(cartItem.getId())) {
                increaseQuantity(product);
                updatePrice();
                return;
            }
        }
        cartItems.add(cartItem);
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

        BigDecimal bigDecimal = new BigDecimal(totalPrice).setScale(2, RoundingMode.HALF_UP);
        totalPrice = bigDecimal.doubleValue();
    }

    public void increaseQuantity(CartItem itemInCart) {
        itemInCart.setQuantity(itemInCart.getQuantity() + 1);
    }

    public void clearCart() {
        cartItems.clear();
    }
}