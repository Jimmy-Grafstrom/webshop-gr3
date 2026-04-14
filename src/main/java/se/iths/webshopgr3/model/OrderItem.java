package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "order_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false, name = "product_name")
    private String productName;

    @Positive(message = "Quantity must be a positive number")
    @Column(nullable = false, name = "quantity")
    private int quantity;

    @PositiveOrZero
    @Column(nullable = false, name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    @NotNull(message = "Order is required")
    private Order order;

}
