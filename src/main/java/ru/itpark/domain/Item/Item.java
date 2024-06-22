package ru.itpark.domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public abstract class Item implements Serializable {
    private static final long serialVersionUID = 312312123132L;
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
