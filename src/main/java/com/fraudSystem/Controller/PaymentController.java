package com.fraudSystem.Controller;

import com.fraudSystem.DTO.CardDto;
import com.fraudSystem.DTO.TransactionDto;
import com.fraudSystem.Entity.Card;
import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addCard")
    public Card addCard(@RequestBody CardDto cardDto){
        return this.paymentService.addCart(cardDto);
    }

    @PostMapping("/pay")
    public Transaction pay(@RequestBody TransactionDto transactionDto){
        return this.paymentService.makePayment(transactionDto);
    }

}
