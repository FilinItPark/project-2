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
    private int rating;
    private ProductType type;
    private LocalDateTime createdAt;

    public Product(String name, int price, String description, int rating, ProductType type, LocalDateTime createdAt) {
        super(name, price, description);
        this.rating = rating;
        this.type = type;
        this.createdAt = createdAt;
    }
}
