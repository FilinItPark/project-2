package ru.itpark.domain.Item.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.domain.Item.Item;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product extends Item {
    private int rating;
    private ProductType type;
    private LocalDateTime createdAt;
}
