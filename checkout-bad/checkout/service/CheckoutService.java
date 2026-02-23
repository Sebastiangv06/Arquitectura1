package com.checkout.service;

import com.checkout.domain.Money;
import com.checkout.domain.Order;
import com.checkout.persistence.OrderDatabase;

import java.util.UUID;

public class CheckoutService {

    
    private final OrderDatabase database;

    
    public static final String PAYMENT_CARD = "CARD";
    public static final String PAYMENT_PSE  = "PSE";
    public static final String PAYMENT_CASH = "CASH";
   
    

    public CheckoutService(OrderDatabase database) {
        this.database = database;
    }

    
    public CheckoutResult checkout(Order order, String paymentMethod) {

       
        if (paymentMethod == null || paymentMethod.isBlank()) {
            
            throw new RuntimeException("Método de pago requerido");
        }

        String reference;
        boolean approved;
        String message;

        if (paymentMethod.equals(PAYMENT_CARD)) {
            
            double maxCard = 3_000_000;
            if (order.total.amount().doubleValue() > maxCard) {
                approved = false;
                reference = null;
                message = "Cupo insuficiente para tarjeta";
            } else {
               
                approved = true;
                reference = "CARD-" + UUID.randomUUID();
                message = "Aprobado";
            }

        } else if (paymentMethod.equals(PAYMENT_PSE)) {
           
            approved = true;
            reference = "PSE-" + UUID.randomUUID();
            message = "Aprobado por PSE";

            
            if (order.total.amount().doubleValue() > 10_000_000) {
               
                message = "Aprobado por PSE con verificación adicional";
            }

        } else if (paymentMethod.equals(PAYMENT_CASH)) {
           
            approved = true;
            reference = "CASH-DELIVERY";
            message = "Pago contraentrega registrado";

        } else {
            
            throw new RuntimeException("Método de pago no soportado: " + paymentMethod);
        }

      
        if (!approved) {
            order.status = "REJECTED";
            database.save(order);
            throw new RuntimeException("Pago rechazado: " + message);
        }

        order.status = "PAID";
        order.paymentReference = reference;
        order.paymentMethodUsed = paymentMethod; 
        database.save(order);

        return new CheckoutResult(order.id, order.status, reference);
    }

   
    

    
    public String receiptMessage(String paymentMethod, String reference) {
        if (paymentMethod.equals(PAYMENT_CARD)) {
            return "Pago con tarjeta procesado. Auth: " + reference;
        } else if (paymentMethod.equals(PAYMENT_PSE)) {
            return "Transferencia PSE completada. CUS: " + reference;
        } else if (paymentMethod.equals(PAYMENT_CASH)) {
            return "Orden registrada para pago en efectivo.";
        } else {
            return "Pago procesado."; 
        }
    }
}
