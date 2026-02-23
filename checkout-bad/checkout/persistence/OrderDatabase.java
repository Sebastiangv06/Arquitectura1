package com.checkout.persistence;

import com.checkout.domain.Order;

import java.util.HashMap;
import java.util.Map;


public class OrderDatabase {

    private final Map<String, Order> store = new HashMap<>();

    public void save(Order order) {
        store.put(order.id, order);
        System.out.println("[DB] Orden guardada: " + order.id + " | Estado: " + order.status);
    }

    public Order findById(String id) {
        return store.get(id);
    }
}
