package com.checkout.persistence;

import com.checkout.domain.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * ❌ PROBLEMA: CheckoutService depende directamente de esta clase concreta,
 * no de una interfaz. No hay contrato abstracto entre capas.
 *
 * Para tests, no puedes sustituirla fácilmente — tendrías que instanciar
 * la clase real o usar reflection/mocks pesados.
 */
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
