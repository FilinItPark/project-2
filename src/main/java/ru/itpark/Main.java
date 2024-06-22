package ru.itpark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Transaction;
import ru.itpark.menu.Menu;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Menu.printMenu();
    }
}