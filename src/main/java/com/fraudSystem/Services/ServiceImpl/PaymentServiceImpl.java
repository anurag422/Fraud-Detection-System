package com.fraudSystem.Services.ServiceImpl;

import com.fraudSystem.DTO.CardDto;
import com.fraudSystem.DTO.TransactionDto;
import com.fraudSystem.Entity.Card;
import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Entity.User;
import com.fraudSystem.Fraud.FraudDetectionSystem;
import com.fraudSystem.Repository.CardRepository;
import com.fraudSystem.Repository.TransactionRepository;
import com.fraudSystem.Repository.UserRepository;
import com.fraudSystem.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private FraudDetectionSystem fraudDetectionSystem;

    @Override
    public Card addCart(CardDto cardDto) {

        User user = userRepository.findById(cardDto.getUserId()).orElseThrow(() -> new RuntimeException("User not Found"));
        Card card = new Card();

        card.setCardNumber(cardDto.getCardNumber());
        card.setCvv(cardDto.getCvv());
        card.setExpiry(cardDto.getExpiry());
        card.setBalance(cardDto.getBalance());
        card.setUser(user);

        return cardRepository.save(card);
    }

    @Override
    public Transaction makePayment(TransactionDto transactionDto) {

        Card card = this.cardRepository.findById(transactionDto.getCardId()).orElseThrow(() -> new RuntimeException("Card is not found"));

        Transaction transaction = new Transaction();

        transaction.setAmount(transactionDto.getAmount());
        transaction.setLocation(transactionDto.getLocation());
        transaction.setTimeStamp(LocalDateTime.now());

        String checkedFraud = fraudDetectionSystem.checkFraud(card.getId(), transactionDto.getAmount(), transactionDto.getLocation());

        if (checkedFraud.equals("BLOCKED")){
            transaction.setStatus("BLOCKED");
        } else if (checkedFraud.equals("SUSPICIOUS")) {
            transaction.setStatus("SUSPICIOUS");
        }else {

            if (card.getBalance() < transactionDto.getAmount()) {
                transaction.setStatus("FAILED");
            } else {
                card.setBalance(card.getBalance() - transactionDto.getAmount());
                transaction.setStatus("SUCCESS");
                cardRepository.save(card);
            }
        }
        transaction.setCard(card);
        return transactionRepository.save(transaction);
    }
}
