package ru.itpark.domain;

import lombok.*;
import ru.itpark.domain.Item.Item;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Transaction {
    private long id;
    private double cost;
    private List<Item> items;
    private Type type;
}
