package com.fraudSystem.DTO;

import lombok.Data;

@Data
public class TransactionResponse {

    private Double amount;

    private String status;

    private String maskedCardNumber;

}
