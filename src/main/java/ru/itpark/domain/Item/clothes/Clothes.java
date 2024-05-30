package ru.itpark.domain.Item.clothes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.itpark.domain.Item.Item;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Clothes extends Item {
    private String color;
    private Integer size;
    private ClothesType type;
    private String country;
    private String season;
    private String material;
    private String sex;
}
