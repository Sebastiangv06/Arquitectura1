package com.checkout.app;

import com.checkout.domain.Money;
import com.checkout.domain.Order;
import com.checkout.persistence.OrderDatabase;
import com.checkout.service.CheckoutResult;
import com.checkout.service.CheckoutService;

public class App {
    public static void main(String[] args) {

       
        OrderDatabase db = new OrderDatabase();
        CheckoutService service = new CheckoutService(db);

        // --- Caso 1: pago con tarjeta ---
        Order order1 = new Order(Money.of(500_000, "COP"));
        CheckoutResult result1 = service.checkout(order1, "CARD");
        System.out.println("Resultado: " + result1.status() + " | Ref: " + result1.reference());
        System.out.println("Recibo: " + service.receiptMessage("CARD", result1.reference()));

        System.out.println("---");

        // --- Caso 2: pago PSE monto alto ---
        Order order2 = new Order(Money.of(12_000_000, "COP"));
        CheckoutResult result2 = service.checkout(order2, "PSE");
        System.out.println("Resultado: " + result2.status() + " | Ref: " + result2.reference());
        
        System.out.println("---");

        // --- Caso 3: efectivo ---
        Order order3 = new Order(Money.of(80_000, "COP"));
        CheckoutResult result3 = service.checkout(order3, "CASH");
        System.out.println("Resultado: " + result3.status());

        System.out.println("---");

        // --- Caso 4: tarjeta rechazada por cupo ---
        try {
            Order order4 = new Order(Money.of(5_000_000, "COP"));
            service.checkout(order4, "CARD");
        } catch (RuntimeException e) {
            System.out.println("Esperado — " + e.getMessage());
        }

        // --- Caso 5: método desconocido ---
        try {
            Order order5 = new Order(Money.of(10_000, "COP"));
            
            service.checkout(order5, "NEQUI");
        } catch (RuntimeException e) {
            System.out.println("Esperado — " + e.getMessage());
        }
    }
}
