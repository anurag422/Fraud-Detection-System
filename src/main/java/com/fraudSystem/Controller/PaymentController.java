package com.fraudSystem.Controller;

import com.fraudSystem.DTO.CardDto;
import com.fraudSystem.DTO.TransactionDto;
import com.fraudSystem.DTO.TransactionResponse;
import com.fraudSystem.Entity.Card;
import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Services.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addCard")
    public Card addCard(@Valid @RequestBody CardDto cardDto){
        return this.paymentService.addCart(cardDto);
    }

    @PostMapping("/pay")
    public TransactionResponse pay(@Valid @RequestBody TransactionDto transactionDto){
        return this.paymentService.makePayment(transactionDto);
    }

}
