package com.fraudSystem.DTO;

import lombok.Data;

@Data
public class CardDto {
    private String cardNumber;
    private String expiry;
    private String cvv;
    private Double balance;
    private Long userId;
}
