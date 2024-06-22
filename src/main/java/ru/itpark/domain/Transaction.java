package ru.itpark.domain;

import lombok.*;
import ru.itpark.domain.Item.Item;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction implements Serializable {
    private static final long serialVersionUID = 123132123123665436L;

    private long id;
    private double cost;
    private List<Item> items;
    private Type type;
}
