package com.anoop.flipkartDataRetriving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoop.flipkartDataRetriving.Model.ProductColorAvailableWithImg;

@Repository
public interface ProductColorAvailableWithImgRepo extends JpaRepository<ProductColorAvailableWithImg, Long>{
    
}
