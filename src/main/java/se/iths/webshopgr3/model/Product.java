package se.iths.webshopgr3.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(nullable = false, name = "product_name")
    @NotBlank(message = "Product name is required")
    private String name;

    @Column(nullable = false, name = "price")
    @Positive(message = "Price must be a positive number")
    @NotNull(message = "Price is required")
    private double price;

    @Column(nullable = false, name = "category")
    @NotBlank(message = "Category is required")
    private String category;

    @Column(nullable = false, name = "image_url")
    @NotBlank(message = "Image URL is required")
    private String imageUrl;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
