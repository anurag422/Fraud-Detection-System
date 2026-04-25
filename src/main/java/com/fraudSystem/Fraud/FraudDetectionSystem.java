package com.fraudSystem.Fraud;

import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FraudDetectionSystem {

    @Autowired
    private TransactionRepository transactionRepository;

    public String checkFraud(Long cardId,Double amount,String location){

        int risk = 0;

        if(amount >= 50000){
            risk += 50;
        }

        List<Transaction> transactions = transactionRepository.findAll();

        for (Transaction tx: transactions){

            if (tx.getCard().getId().equals(cardId)){

                Duration duration = Duration.between(tx.getTimeStamp(), LocalDateTime.now());

                if(duration.toMinutes() > 2){
                    risk += 30;
                }

            }

        }

        for (Transaction tx: transactions){
            if (tx.getCard().getId().equals(cardId)){
                if (!tx.getLocation().equals(location)){
                    risk += 20;
                    break;
                }
            }
        }

        if (risk >= 70){
            return "BLOCKED";
        } else if (risk >= 40) {
            return "SUSPICIOUS";
        }else {
            return "SUCCESS";
        }

    }

}
