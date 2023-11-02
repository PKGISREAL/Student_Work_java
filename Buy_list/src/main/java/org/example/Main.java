package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Map<String, Integer>> database = new TreeMap<>();

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                break;
            }

            String[] parts = line.split(" ");
            if (parts.length == 3) {
                String customer = parts[0];
                String product = parts[1];
                int quantity = Integer.parseInt(parts[2]);

                database.putIfAbsent(customer, new TreeMap<>());
                database.get(customer).put(product, database.get(customer).getOrDefault(product, 0) + quantity);
            }
        }

        for (Map.Entry<String, Map<String, Integer>> entry : database.entrySet()) {
            String customer = entry.getKey();
            Map<String, Integer> purchases = entry.getValue();

            System.out.println(customer + ":");
            for (Map.Entry<String, Integer> purchase : purchases.entrySet()) {
                String product = purchase.getKey();
                int quantity = purchase.getValue();

                System.out.println(product + " " + quantity);
            }
        }
    }
}