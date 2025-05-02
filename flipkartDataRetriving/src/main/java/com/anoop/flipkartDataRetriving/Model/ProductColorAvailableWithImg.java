package com.anoop.flipkartDataRetriving.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorAvailableWithImg {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @NotNull
    String color;
    @NotNull
    String productImg;
    @Override
    public String toString() {
        return "ProductColorAvailableWithImg [id=" + id + ", color=" + color + ", productImg=" + productImg + "]";
    }

    
}
