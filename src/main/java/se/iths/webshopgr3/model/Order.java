package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(nullable = false, name = "customer")
    private String username;

    @PositiveOrZero(message = "Total price cannot be negative")
    @Column(nullable = false, name = "total_price")
    private double totalPrice;

    @NotNull(message = "Order date is required")
    @Column(nullable = false, name = "order_date")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
