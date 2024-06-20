package ru.itpark.infrastructre;

import ru.itpark.domain.Item.Item;
import ru.itpark.domain.Transaction;
import ru.itpark.exceptions.TransactionNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Database {
    private static List<Transaction> transactions = new ArrayList<>();
    private static List<Item> items = new ArrayList<>();

    public static Transaction add(Transaction transaction) {
        transaction.setId(transactions.size() + 1);

        transactions.add(transaction);

        return transaction;
    }

    public static Transaction find(int id) {
        for (var currentTransaction : transactions) {
            if (currentTransaction.getId() == id) {
                return currentTransaction;
            }
        }

        throw new TransactionNotFoundException("Transaction not found");
    }

    public static Item addItem(Item item) {
        item.setId(items.size() + 1);

        items.add(item);

        return item;
    }

    public static List<Item> getItems() {
        return items;
    }
}
