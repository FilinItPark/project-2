package ru.itpark.domain.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Item {
    protected long id;

    protected String name;

    protected int price;

    protected String description;
}
