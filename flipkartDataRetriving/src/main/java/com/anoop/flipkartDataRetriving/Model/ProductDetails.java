package com.anoop.flipkartDataRetriving.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    List<String> productImageURL = new ArrayList<>();

    @Override
    public String toString() {
        return "ProductDetails [id=" + id + ", productImageURL=" + productImageURL + "]";
    }

    
}
