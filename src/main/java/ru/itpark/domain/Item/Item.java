package ru.itpark.domain.Item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public abstract class Item implements Serializable {
    private static final long serialVersionUID = 312312123132L;
    protected long id;

    protected String name;

    protected int price;
    private LocalDateTime createdAt;

    protected String description;

    public Item(String name, int price, LocalDateTime createdAt, String description) {
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.description = description;
    }
}
