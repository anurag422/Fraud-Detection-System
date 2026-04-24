package com.fraudSystem.DTO;

import lombok.Data;

@Data
public class TransactionDto {
    private Long cardId;
    private Double amount;
    private String location;
}
