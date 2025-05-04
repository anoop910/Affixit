package com.anoop.flipkartDataRetriving.DTO;



import com.anoop.flipkartDataRetriving.Model.Marketer;
import com.anoop.flipkartDataRetriving.Model.ProductDetails;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateProductDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Product URL can't be blank")
    @Pattern(regexp = "https://www\\.flipkart\\.com.*", message = "URL must contain 'https://www.flipkart.com'")
    private String productURL;

    
   // private String productCategories;
   private Marketer marketer;
   private ProductDetails productDetails;
}
