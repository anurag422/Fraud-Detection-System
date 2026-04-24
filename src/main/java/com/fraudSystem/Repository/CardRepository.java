package com.fraudSystem.Repository;

import com.fraudSystem.Entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card,Long> {
}
