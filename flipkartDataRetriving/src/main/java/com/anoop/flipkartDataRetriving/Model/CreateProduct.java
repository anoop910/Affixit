package com.anoop.flipkartDataRetriving.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product URL can't be blank")
    @Pattern(regexp = "https://www\\.flipkart\\.com.*", message = "URL must contain 'https://www.flipkart.com'")
    private String productURL;

    // private String productCategories;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Marketer_id")
    @JsonBackReference
    private Marketer marketer;

    @OneToOne
    @JoinColumn(name = "ProductDetails_id")
    private ProductDetails productDetails;

}
