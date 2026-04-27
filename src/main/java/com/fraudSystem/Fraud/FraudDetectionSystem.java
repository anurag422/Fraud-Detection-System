package com.fraudSystem.Fraud;

import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FraudDetectionSystem {

    private static final Logger logger = LoggerFactory.getLogger(FraudDetectionSystem.class);

    @Autowired
    private TransactionRepository transactionRepository;

    public String checkFraud(Long cardId,Double amount,String location){

        logger.info("Running Fraud detection for cardId: {}",cardId);

        int risk = 0;

        if(amount >= 50000){
            logger.info("Heigh Amount detected: {}",amount);
            risk += 50;
        }

        List<Transaction> transactions = transactionRepository.findTop5ByCardIdOrderByTimeStampDesc(cardId);

        logger.info("get all the transaction for checking");

        for (Transaction tx: transactions){

            if (tx.getCard().getId().equals(cardId)){

                Duration duration = Duration.between(tx.getTimeStamp(), LocalDateTime.now());

                if(duration.toMinutes() < 2){
                    risk += 30;
                }

            }

        }

        logger.info("Check location of transactions");

        for (Transaction tx: transactions){
            if (tx.getCard().getId().equals(cardId)){
                if (!tx.getLocation().equals(location)){
                    risk += 20;
                    break;
                }
            }
        }

        logger.info("Final result finding");

        if (risk >= 70){
            return "BLOCKED";
        } else if (risk >= 40) {
            return "SUSPICIOUS";
        }else {
            return "SUCCESS";
        }

    }

}
