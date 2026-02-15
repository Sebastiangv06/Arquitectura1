package com.checkout.domain.model;


public record Payment(String methodCode, Money amount, String reference) {}

