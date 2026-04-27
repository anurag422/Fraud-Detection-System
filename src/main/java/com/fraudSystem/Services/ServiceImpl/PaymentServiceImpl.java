package com.fraudSystem.Services.ServiceImpl;

import com.fraudSystem.DTO.CardDto;
import com.fraudSystem.DTO.TransactionDto;
import com.fraudSystem.DTO.TransactionResponse;
import com.fraudSystem.Entity.Card;
import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Entity.User;
import com.fraudSystem.Exception.ResourceNotFoundException;
import com.fraudSystem.Fraud.FraudDetectionSystem;
import com.fraudSystem.Repository.CardRepository;
import com.fraudSystem.Repository.TransactionRepository;
import com.fraudSystem.Repository.UserRepository;
import com.fraudSystem.Security.EncryptionUtil;
import com.fraudSystem.Services.PaymentService;
import com.fraudSystem.Util.CardUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class PaymentServiceImpl implements PaymentService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

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

        User user = userRepository.findById(cardDto.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not Found"));
        Card card = new Card();

        card.setCardNumber(EncryptionUtil.encrypt(cardDto.getCardNumber()));
        card.setExpiry(cardDto.getExpiry());
        card.setBalance(cardDto.getBalance());
        card.setUser(user);

        Card saved = cardRepository.save(card);

        logger.info("Card Added Successfully");

        return saved;

    }

    @Override
    public TransactionResponse makePayment(TransactionDto transactionDto) {

        logger.info("makePayment is Start");

        Card card = this.cardRepository.findById(transactionDto.getCardId()).orElseThrow(() -> new ResourceNotFoundException("Card is not found"));

        logger.info("Payment is start for cardId: {}",transactionDto.getCardId());

        Transaction transaction = new Transaction();

        logger.info("Transaction Amount: {}",transactionDto.getAmount());
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
                logger.info("Payment failed due to insufficient balance");
                transaction.setStatus("FAILED");
            } else {
                card.setBalance(card.getBalance() - transactionDto.getAmount());
                transaction.setStatus("SUCCESS");
                logger.info("Payment is Successful for cardId: {}",transactionDto.getCardId());
                cardRepository.save(card);
            }
        }
        transaction.setCard(card);

        Transaction saved = transactionRepository.save(transaction);

        return  response(saved,card.getCardNumber());
    }

    public TransactionResponse response(Transaction transaction, String cardNumber){

        TransactionResponse transactionResponse =  new TransactionResponse();

        transactionResponse.setAmount(transaction.getAmount());
        transactionResponse.setStatus(transaction.getStatus());

        String decrypt = EncryptionUtil.decrypt(cardNumber);

        transactionResponse.setMaskedCardNumber(CardUtil.maskCard(decrypt));

        return transactionResponse;

    }
}
