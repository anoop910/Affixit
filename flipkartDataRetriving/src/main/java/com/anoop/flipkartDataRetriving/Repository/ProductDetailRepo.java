package com.anoop.flipkartDataRetriving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoop.flipkartDataRetriving.Model.ProductDetails;


@Repository
public interface ProductDetailRepo extends JpaRepository<ProductDetails, Long> {


    
}
