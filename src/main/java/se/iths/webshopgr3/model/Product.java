package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false, name = "product_name")
    private String name;

    @Column(nullable = false, name = "price")
    private double price;

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = false, name = "image_url")
    private String imageUrl;

    public Product() {
    }

    public Product(Long id, String name, double price, String category, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.imageUrl = imageUrl;
    }

    public double getPris() {
        return 0;
    }
}

