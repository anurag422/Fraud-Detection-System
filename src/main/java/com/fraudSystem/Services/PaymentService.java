package com.fraudSystem.Services;

import com.fraudSystem.DTO.CardDto;
import com.fraudSystem.DTO.TransactionDto;
import com.fraudSystem.Entity.Card;
import com.fraudSystem.Entity.Transaction;

public interface PaymentService {
    public Card addCart(CardDto cardDto);

    public Transaction makePayment(TransactionDto transactionDto);
}
