package com.anoop.flipkartDataRetriving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoop.flipkartDataRetriving.Model.Marketer;

@Repository
public interface MarketerRepo extends JpaRepository<Marketer, Long>{

    
    
}
