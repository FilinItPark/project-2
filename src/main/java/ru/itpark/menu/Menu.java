package ru.itpark.menu;

import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Type;
import ru.itpark.infrastructre.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Menu {
    public static void printMenu() {
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить новую транзакцию");

        try (
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ) {

            Integer numberOfMenu = Integer.parseInt(bufferedReader.readLine());

            switch (numberOfMenu) {
                case 1:
                    System.out.println("Количество переведенных денег:");
                    Double money = Double.parseDouble(bufferedReader.readLine());

                    System.out.println("Выберите тип операции: SERVICE(услуга) или ITEM(товар)");
                    String operation = bufferedReader.readLine();

                    Type type = Type.valueOf(operation);

                    List<Item> items = Database.getItems();

                    if (items.isEmpty()) {
                        System.out.println("Товаров еще нет, желаете ли добавить?");
                        //TODO: реализовать логику

                        Boolean wantToAdd = bufferedReader.readLine().equalsIgnoreCase("yes");


                        if (wantToAdd) {
                            final AllTypesOfItems[] values = AllTypesOfItems.values();
                            System.out.println("Выберите тип товара");

                            for (var value : values) {
                                System.out.println(value.name());
                            }
                        }

                        String className = bufferedReader.readLine();

                        Item item = null;
                        
                        switch (className) {
                            case "Clothes":
                                handleClothes(item);
                                break;
                            case "Electronic":
                                handleElectronic(item);
                                break;
                            case "Product":
                                handleProduct(item);
                                break;
                        }
                    }

                    System.out.println("Выберите товары из списка:");
                    for (var item : Database.getItems()) {
                        System.out.println(item.getId() + ") " + item.getName());
                    }
            }

        } catch (IOException exception) {

        }
    }

    private static void handleClothes(Item item) {
    }

    private static void handleElectronic(Item item) {
    }

    private static void handleProduct(Item item) {
        
    }
}
