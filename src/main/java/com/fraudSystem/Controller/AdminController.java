package com.fraudSystem.Controller;

import com.fraudSystem.Entity.Transaction;
import com.fraudSystem.Entity.User;
import com.fraudSystem.Repository.TransactionRepository;
import com.fraudSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/transaction")
    public Page<Transaction> allTransaction(@RequestParam int page, @RequestParam int size ){
        return transactionRepository.findAll(PageRequest.of(page,size));
    }

    @GetMapping("/fraudTransaction")
    public List<Transaction> fraudTransaction(){
        return transactionRepository.findAll()
                .stream()
                .filter(tx->tx.getStatus().equals("BLOCKED") || tx.getStatus().equals("SUSPICIOUS"))
                .toList();
    }

    @GetMapping("/AllUsers")
    public List<User> AllUsers(){
        return userRepository.findAll();
    }

}
