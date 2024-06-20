package ru.itpark.domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public abstract class Item {
    protected long id;

    protected String name;

    protected int price;

    protected String description;

    public Item(String name, int price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
