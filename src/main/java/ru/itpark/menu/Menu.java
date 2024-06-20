package ru.itpark.menu;

import lombok.SneakyThrows;
import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Item.clothes.Clothes;
import ru.itpark.domain.Item.clothes.ClothesType;
import ru.itpark.domain.Item.electronic.Electronic;
import ru.itpark.domain.Item.electronic.ElectronicItemType;
import ru.itpark.domain.Type;
import ru.itpark.infrastructre.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import static jdk.internal.jrtfs.JrtFileAttributeView.AttrID.size;

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


                        System.out.println("Введите название товара:  ");
                        String name = bufferedReader.readLine();
                        System.out.println("Введите цену:  ");
                        Integer price = Integer.parseInt(bufferedReader.readLine());
                        System.out.println("Введите описание товара:");
                        String description = bufferedReader.readLine();

                        switch (className) {
                            case "Clothes":
                                handleClothes(name, price, description, bufferedReader);
                                break;
                            case "Electronic":
                                handleElectronic(name, price, description, bufferedReader);
                                break;
                            case "Product":
                                handleProduct(name, price, description, bufferedReader);
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

    @SneakyThrows(IOException.class)
    private static Item handleClothes(String name, Integer price, String description, BufferedReader bufferedReader) {

        System.out.println("Введите цвет: ");
        String color = bufferedReader.readLine();

        System.out.println("Введите размер: ");
        Integer size = Integer.parseInt(bufferedReader.readLine());

        var types = ClothesType.values();
        for (var type1 : types) {
            System.out.println(type1);
        }
        System.out.println("Выберите тип одежды: ");
        String typeOfCloth = bufferedReader.readLine().toUpperCase();
//                                try {
//                                    type2 = ClothesType.valueOf(typeOfCloth);
//                                }
//                                catch (Exception ex) {
//                                    System.out.println(ex.getMessage());
//                                }
        System.out.println("Введите страну производителя: ");
        String country = bufferedReader.readLine();

        System.out.println("Введите сезон: ");
        String season = bufferedReader.readLine();

        System.out.println("Введите материал: ");
        String material = bufferedReader.readLine();

        System.out.println("Введите пол: ");
        String sex = bufferedReader.readLine();

        Clothes cloth = new Clothes(name, price, description, color, size, ClothesType.valueOf(typeOfCloth), country, season, material, sex);

        Database.addItem(cloth);

        return cloth;
    }

    @SneakyThrows
    private static Item handleElectronic(String name, Integer price, String description, BufferedReader bufferedReader) {

        var types = ElectronicItemType.values();
        System.out.println("Выберите тип электроники: ");
        for (var type1 : types) {
            System.out.println(type1);
        }

        String typeOfElectronic = bufferedReader.readLine().toUpperCase();
        ElectronicItemType typeOfElectronicEnumValue = null;
        try {
            typeOfElectronicEnumValue = ElectronicItemType.valueOf(typeOfElectronic);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println("Введите необходимое напряжение: ");
        Integer requiredPowerVoltage = Integer.parseInt(bufferedReader.readLine());

        System.out.println("Введите мощность: ");
        Double power = Double.parseDouble(bufferedReader.readLine());


        var electronic = new Electronic(name, price, description, typeOfElectronicEnumValue, requiredPowerVoltage, power);

        Database.addItem(electronic);

        return electronic;
    }

    private static Item handleProduct(String name, Integer price, String description, BufferedReader bufferedReader) {

    }
}
