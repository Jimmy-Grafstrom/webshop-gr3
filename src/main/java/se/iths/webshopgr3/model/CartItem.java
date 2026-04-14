package se.iths.webshopgr3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.product = product;
        this.quantity = 1;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public double getTotalPrice() {
        double total = product. getPris() * quantity;
        return total;
    }
    }

