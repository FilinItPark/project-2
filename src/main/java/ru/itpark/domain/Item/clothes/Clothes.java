package ru.itpark.domain.Item.clothes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.domain.Item.Item;

import java.time.LocalDateTime;

@Getter
@Setter
public class Clothes extends Item {
    private String color;
    private Integer size;
    private ClothesType type;
    private String country;
    private String season;
    private String material;
    private String sex;

    public Clothes(String name, int price, String description, String color, Integer size, ClothesType type, String country, String season, String material, String sex, LocalDateTime now) {
        super(name, price, now, description);
        this.color = color;
        this.size = size;
        this.type = type;
        this.country = country;
        this.season = season;
        this.material = material;
        this.sex = sex;
    }
}
