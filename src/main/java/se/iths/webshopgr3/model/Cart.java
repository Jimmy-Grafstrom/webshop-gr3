package se.iths.webshopgr3.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@SessionScope
@NoArgsConstructor
@AllArgsConstructor
public class Cart {

    private List<CartItem> items = new ArrayList<>();
    private double totalPrice;

    public void addProductToCart(CartItem cartItem) {

        for (CartItem product : items) {
            if (product.getId() == cartItem.getId()) {
                increaseQuantity(product);
            } else {
                items.add(cartItem);
            }
        }
    }

    public List<CartItem> getAllProductsInCart() {
        return items;
    }

    public void updatePrice() {
        totalPrice = 0;
        for (CartItem product : items) {
            totalPrice += product.getTotalPrice() * product.getQuantity();
        }
    }

    public void increaseQuantity(CartItem itemInCart) {
        itemInCart.setQuantity(itemInCart.getQuantity() + 1);
    }

    public void clearCart() {
        items.clear();
    }
}