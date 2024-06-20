package ru.itpark;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Transaction;
import ru.itpark.menu.Menu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {
    @AllArgsConstructor
    @Getter
    @Setter
    static class User {
        private Long id;
        private String name;
        private Integer age;
    }

    @Getter
    static class Transaction {
        String buyer;
        String product;
        int quantity;
        double pricePerUnit;

        public Transaction(String buyer, String product, int quantity, double pricePerUnit) {
            this.buyer = buyer;
            this.product = product;
            this.quantity = quantity;
            this.pricePerUnit = pricePerUnit;
        }

        public double getTotalPrice() {
            return quantity * pricePerUnit;
        }

        @Override
        public String toString() {
            return String.format("%s купил %d единиц %s по %.2f за единицу", buyer, quantity, product, pricePerUnit);
        }
    }

    static void first() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Иван", "Яблоки", 5, 20.0),
                new Transaction("Мария", "Бананы", 2, 30.0),
                new Transaction("Иван", "Яблоки", 3, 20.0),
                new Transaction("Олег", "Апельсины", 10, 15.0),
                new Transaction("Мария", "Яблоки", 1, 20.0)
        );

        transactions.stream()
//                .mapToDouble(transaction -> {
//                            return transaction.getTotalPrice();
//                        }
//                )
//                .mapToDouble(transaction -> transaction.getTotalPrice())
                .mapToDouble(Transaction::getTotalPrice)
                .sum();
    }

    static void second() {
        List<Transaction> transactions = Arrays.asList(
                new Transaction("Иван", "Яблоки", 5, 20.0),
                new Transaction("Мария", "Бананы", 2, 30.0),
                new Transaction("Иван", "Яблоки", 3, 20.0),
                new Transaction("Олег", "Апельсины", 10, 15.0),
                new Transaction("Мария", "Яблоки", 1, 20.0)
        );

        /*
        tovar -> count
         */

        final Map<String, Integer> collect = transactions.stream()
                .collect(
                        Collectors.groupingBy(
                                Transaction::getProduct,
                                Collectors.summingInt(Transaction::getQuantity)
                        )
                );

        collect.forEach((product, quantity) -> {
            System.out.println(product + " - " + quantity);
        });
    }

    public static void main(String[] args) {
//        Menu.printMenu();

        /*
        у нас с вами есть список пользователей.
        нам. нужно получить список людей (имена) в возрасте от 25 до 40 лет
         */

        var m = new Main();
        var users = List.of(
                new User(1L, "Иван", 15),
                new User(2L, "Петя", 25),
                new User(3L, "Олег", 30),
                new User(4L, "Андрей", 45),
                new User(5L, "Антон", 35),
                new User(6L, "Александр", 40)
        );

        // 1ый способ - на циклах

        List<String> result = new ArrayList<>();

        for (var user : users) {
            if (user.getAge() >= 25 && user.getAge() <= 40) {
                result.add(user.getName());
            }
        }

//        users.filter(lambda x: x.age >= 25 and x.age <= 40)

        final List<String> list = users.stream()
                .filter((user) -> user.getAge() >= 25 && user.getAge() <= 40)
                .map(user -> user.getName())
                .toList();

    }
}