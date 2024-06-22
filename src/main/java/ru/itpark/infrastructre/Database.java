package ru.itpark.infrastructre;

import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Transaction;
import ru.itpark.exceptions.TransactionNotFoundException;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Database {
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();

    public static Transaction add(Transaction transaction) {
        transaction.setId(transactions.size() + 1);

        transactions.add(transaction);

        return transaction;
    }

    public static List<Transaction> getTransactions() {
        return transactions;
    }

    public static void loadDB() {
        try (var objectInputStream = new ObjectInputStream(new FileInputStream("db.serialization"))) {
            Database.transactions = (List<Transaction>) objectInputStream.readObject();
            Database.items = (List<Item>) objectInputStream.readObject();
        } catch (Exception e) {
            System.out.println("Не удалось загрузить базу данных: " + e.getMessage());
        }
    }


    public static void saveDB() {
        try (
                ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream("db.serialization")
                )
        ) {
            oos.writeObject(transactions);
            oos.writeObject(items);
        } catch (IOException exception) {
            System.out.println("Не удалось сохранить данные " + exception.getMessage());
        }
    }

    public static Transaction find(int id) {
        for (var currentTransaction : transactions) {
            if (currentTransaction.getId() == id) {
                return currentTransaction;
            }
        }

        throw new TransactionNotFoundException("Transaction not found");
    }

    public static List<Item> searchItems(List<Integer> ids) {
        return items.stream()
                .filter(currentItem -> ids.contains(currentItem.getId()))
                .toList();
    }

    public static Item add(Item item) {
        item.setId(items.size() + 1);

        items.add(item);

        return item;
    }

    public static List<Item> getItems() {
        return items;
    }

}
