package com.anoop.flipkartDataRetriving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoop.flipkartDataRetriving.Model.ReviewData;

@Repository
public interface RetriveReviewRepo extends JpaRepository<ReviewData, Long>{
    
}
