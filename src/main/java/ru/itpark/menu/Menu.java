package ru.itpark.menu;

import lombok.SneakyThrows;
import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Item.clothes.Clothes;
import ru.itpark.domain.Item.clothes.ClothesType;
import ru.itpark.domain.Item.electronic.Electronic;
import ru.itpark.domain.Item.electronic.ElectronicItemType;
import ru.itpark.domain.Item.product.Product;
import ru.itpark.domain.Item.product.ProductType;
import ru.itpark.domain.Transaction;
import ru.itpark.domain.Type;
import ru.itpark.infrastructre.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.lang.StringTemplate.STR;

public class Menu {
    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void printMenu() {
        Database.loadDB();
        boolean exit = false;
        do {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить новую транзакцию");
            System.out.println("2. Просмотреть все транзакции");
            System.out.println("3. Посмотреть финансовый отчет");
            System.out.println("4. Посмотреть финансовый отчет за определенный период");
            System.out.println("N. Чтобы выйти, напишите -1");

            try {
                int numberOfMenu = Integer.parseInt(READER.readLine());

                switch (numberOfMenu) {
                    case 1 -> handleCreatingTransaction();
                    case 2 -> handleViewTransactions();
                    case 3 -> makeFinanceReport();
                    case 4 -> makeFinanceReportInPeriod();
                    case -1 -> exit = true;
                    default -> System.out.println("Неверный ввод");

                }
            } catch (Exception exception) {
                System.out.println("Неверный ввод");
                System.out.println(exception.getMessage());
            } finally {
                Database.saveDB();
            }
        } while (!exit);
    }

    @SneakyThrows
    private static void makeFinanceReportInPeriod() {
        System.out.println("Введите дату начала периода (yyyy-MM-ddThh:MM:SS):");
        LocalDateTime startDate = LocalDateTime.parse(READER.readLine());

        System.out.println("Введите дату конца периода (yyyy-MM-ddThh:MM:SS ):");
        LocalDateTime endDate = LocalDateTime.parse(READER.readLine());

        final Map<String, Double> report = Database.makeFinanceReportInPeriod(startDate, endDate);

        System.out.println("Финансовый отчет:");
    }

    private static void makeFinanceReport() {
        final Map<String, Double> report = Database.makeFinanceReport();

        System.out.println("Финансовый отчет:");
        report.forEach((itemName, spentMoney) -> System.out.println(STR."\{itemName} - \{spentMoney}"));
    }

    private static void handleViewTransactions() {
        System.out.println("Все транзакции:");
        Database.getTransactions().forEach(System.out::println);
    }

    @SneakyThrows(IOException.class)
    private static void handleCreatingTransaction() {
        System.out.println("Количество переведенных денег:");
        double money = Double.parseDouble(READER.readLine());

        System.out.println("Выберите тип операции: SERVICE(услуга) или ITEM(товар)");
        Type type = Type.valueOf(READER.readLine());

        switch (type) {
            case ITEM -> handleItem(money);

        }
    }


    private static void handleItem(double money) throws IOException {
        List<Item> items = Database.getItems();

        if (items.isEmpty()) {
            System.out.println("Товаров еще нет, желаете ли добавить? (yes/no)");
            if (READER.readLine().equalsIgnoreCase("yes")) {
                addItem();
            }
        }
        final String selectedItemsIds = selectItem(items);

        final var ids = convertToLongList(selectedItemsIds);
        final var selectedItems = Database.searchItems(ids);
        proccessItem(money, selectedItems);
    }

    private static List<Long> convertToLongList(String input) {
        return Arrays.stream(input.split(",")).map(Long::parseLong).toList();
    }


    private static void proccessItem(double money, List<Item> selectedItems) {
        Transaction transaction = Transaction.builder().cost(money).type(Type.ITEM).items(selectedItems).build();

        Database.add(transaction);
    }

    @SneakyThrows(IOException.class)
    private static String selectItem(List<Item> items) {
        items.forEach(item -> System.out.println(String.format("%d ) %s", item.getId(), item.getName())));

        System.out.println("Выберите товары (напишите их порядковый номер через запятую");
        return READER.readLine();
    }

    @SneakyThrows(IOException.class)
    public static void addItem() {
        System.out.println("Выберите тип товара");
        printEnumValues(AllTypesOfItems.values());

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

    private static void printEnumValues(Enum<?>[] values) {
        Stream.of(values).forEach(type -> System.out.println(type.name()));
    }


    @SneakyThrows(IOException.class)
    private static void handleClothes(String name, Integer price, String description) {
        System.out.println("Введите цвет, размер, тип одежды, страну производителя, сезон, материал и пол");
        printEnumValues(ClothesType.values());

        var clothes = new Clothes(name, price, description, READER.readLine(), // color
                Integer.parseInt(READER.readLine()),  // size
                ClothesType.valueOf(READER.readLine().toUpperCase()),   // clothes type
                READER.readLine(),  // country
                READER.readLine(),   // season
                READER.readLine(),    // material
                READER.readLine(),
                LocalDateTime.now()// sex
        );

        Database.add(clothes);
    }

    @SneakyThrows
    private static void handleElectronic(String name, Integer price, String description) {
        System.out.println("Выберите тип электроники, введите необходимое напряжение, мощность");
        printEnumValues(ElectronicItemType.values());
        var electronic = new Electronic(name, price, description, ElectronicItemType.valueOf(READER.readLine().toUpperCase()),  // type
                Integer.parseInt(READER.readLine()),   // voltage
                Double.parseDouble(READER.readLine()),
                LocalDateTime.now()// power
        );

        Database.add(electronic);
    }

    @SneakyThrows(IOException.class)
    private static void handleProduct(String name, Integer price, String description) {
        System.out.println("Введите рейтинг товара, его тип");
        printEnumValues(ProductType.values());
        var product = new Product(name, price, description, Double.parseDouble(READER.readLine()),    // rating
                ProductType.valueOf(READER.readLine().toUpperCase()),    // type
                LocalDateTime.now());

        Database.add(product);
    }
}
