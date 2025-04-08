package com.anoop.flipkartDataRetriving.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.anoop.flipkartDataRetriving.Model.CreateProduct;


@Repository
public interface CreateProductRepo extends JpaRepository<CreateProduct, Long>{
    
}
