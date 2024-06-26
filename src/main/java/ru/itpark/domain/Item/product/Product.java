package ru.itpark.domain.Item.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.domain.Item.Item;

import java.time.LocalDateTime;

@Getter
@Setter
public class Product extends Item {
    private double rating;
    private ProductType type;

    public Product(String name, int price, String description, double rating, ProductType type, LocalDateTime createdAt) {
        super(name, price, createdAt, description);
        this.rating = rating;
        this.type = type;
    }
}
