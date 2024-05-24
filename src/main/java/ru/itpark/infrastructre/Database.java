package ru.itpark.infrastructre;

import ru.itpark.domain.Transaction;

import java.util.List;

public class Database {
    private static List<Transaction> transactions;

    public static Transaction add(Transaction transaction) {
        transaction.setId(transactions.size() + 1);

        transactions.add(transaction);

        return transaction;
    }
}
