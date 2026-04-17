package se.iths.webshopgr3.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CartItem {

    private Long id;
    private Product product;
    private int quantity;

    public CartItem(Product product) {
        this.id = product.getId();
        this.product = product;
        this.quantity = 1;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "id=" + id +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}

