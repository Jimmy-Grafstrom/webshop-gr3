package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "customer")
    private String username;

    @Column(nullable = false, name = "total_price")
    private double totalPrice;

    @Column(nullable = false, name = "order_date")
    private LocalDateTime orderDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public Order() {
    }

    public Order(Long id, String username, double totalPrice, LocalDateTime orderDate) {
        this.id = id;
//        this.orderItems = orderItems;
        this.username = username;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
