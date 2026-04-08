package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false, name = "order_items")
//    private List<OrderItem> orderItems;

    @Column(nullable = false, name = "customer")
    private String username;

    @Column(nullable = false, name = "total_price")
    private double totalPrice;

    @Column(nullable = false, name = "order_date")
    private Date orderDate;

    public Order() {
    }

    public Order(Long id, List<OrderItem> orderItems, String username, double totalPrice, Date orderDate) {
        this.id = id;
        this.orderItems = orderItems;
        this.username = username;
        this.totalPrice = totalPrice;
        this.orderDate = orderDate;
    }
}
