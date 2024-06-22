package ru.itpark.menu;

import lombok.SneakyThrows;
import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Item.clothes.Clothes;
import ru.itpark.domain.Item.clothes.ClothesType;
import ru.itpark.domain.Item.electronic.Electronic;
import ru.itpark.domain.Item.electronic.ElectronicItemType;
import ru.itpark.domain.Item.product.Product;
import ru.itpark.domain.Item.product.ProductType;
import ru.itpark.domain.Type;
import ru.itpark.infrastructre.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class Menu {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить новую транзакцию");

        try {
            int numberOfMenu = Integer.parseInt(READER.readLine());

            switch (numberOfMenu) {
                case 1 -> handleTransaction();
            }
        } catch (Exception exception) {
            System.out.println("Неверный ввод");
            System.out.println(exception.getMessage());
        }
    }

    @SneakyThrows(IOException.class)
    private static void handleTransaction() {
        System.out.println("Количество переведенных денег:");
        double money = Double.parseDouble(READER.readLine());

        System.out.println("Выберите тип операции: SERVICE(услуга) или ITEM(товар)");
        Type type = Type.valueOf(READER.readLine());

        List<Item> items = Database.getItems();

        if (items.isEmpty()) {
            System.out.println("Товаров еще нет, желаете ли добавить? (yes/no)");
            if (READER.readLine().equalsIgnoreCase("yes")) {
                addItem();
            }
        }
        selectAndProccesItem(items);
    }

    private static void selectAndProccesItem(List<Item> items) {
        items.forEach(item -> System.out.println(
                String.format(
                        "%d ) %s",
                        item.getId(),
                        item.getName()
                )
        ));
    }

    @SneakyThrows(IOException.class)
    public static void addItem() {
        System.out.println("Выберите тип товара");
        Stream.of(AllTypesOfItems.values()).forEach(type -> System.out.println(type.name()));

        String className = READER.readLine();

        System.out.println("Введите название товара:  ");
        String name = READER.readLine();

        System.out.println("Введите цену:  ");
        Integer price = Integer.parseInt(READER.readLine());

        System.out.println("Введите описание товара:");
        String description = READER.readLine();

        switch (className) {
            case "Clothes" -> handleClothes(name, price, description);
            case "Electronic" -> handleElectronic(name, price, description);
            case "Product" -> handleProduct(name, price, description);
        }
    }


    @SneakyThrows(IOException.class)
    private static void handleClothes(String name, Integer price, String description) {
        System.out.println("Введите цвет, размер, тип одежды, страну производителя, сезон, материал и пол");

        var clothes = new Clothes(name, price, description,
                READER.readLine(), // color
                Integer.parseInt(READER.readLine()),  // size
                ClothesType.valueOf(READER.readLine().toUpperCase()),   // clothes type
                READER.readLine(),  // country
                READER.readLine(),   // season
                READER.readLine(),    // material
                READER.readLine()    // sex
        );

        Database.addItem(clothes);
    }

    @SneakyThrows
    private static void handleElectronic(String name, Integer price, String description) {
        System.out.println("Выберите тип электроники, введите необходимое напряжение, мощность");
        var electronic = new Electronic(name, price, description,
                ElectronicItemType.valueOf(READER.readLine().toUpperCase()),  // type
                Integer.parseInt(READER.readLine()),   // voltage
                Double.parseDouble(READER.readLine())   // power
        );

        Database.addItem(electronic);
    }

    @SneakyThrows(IOException.class)
    private static void handleProduct(String name, Integer price, String description) {
        System.out.println("Введите рейтинг товара, его тип");
        var product = new Product(name, price, description,
                Double.parseDouble(READER.readLine()),    // rating
                ProductType.valueOf(READER.readLine().toUpperCase()),    // type
                LocalDateTime.now()
        );

        Database.addItem(product);
    }
}
