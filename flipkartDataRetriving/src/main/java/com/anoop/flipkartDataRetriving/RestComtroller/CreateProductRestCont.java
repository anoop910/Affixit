package com.anoop.flipkartDataRetriving.RestComtroller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anoop.flipkartDataRetriving.Model.CreateProduct;
import com.anoop.flipkartDataRetriving.Model.ProductDetails;
import com.anoop.flipkartDataRetriving.Service.AddReview;
import com.anoop.flipkartDataRetriving.Service.CreateProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class CreateProductRestCont {

    private CreateProductService createProductService;
    private AddReview addReview;

    public CreateProductRestCont(CreateProductService createProductService, AddReview addReview) {
        this.createProductService = createProductService;
        this.addReview = addReview;
    }

    @PostMapping
    public Boolean createProductByUrl(@Valid @RequestBody CreateProduct createProduct) {
        return createProductService.createProductUrl(createProduct);
    }

    @PostMapping("review")
    public Boolean saveReviewBoolean(@Valid @RequestBody CreateProduct createProduct) {
        String url = createProduct.getProductURL();
        return addReview.saveReviewData(url);
    }

    @GetMapping
    public List<String> getProductDetails() {
        ProductDetails productDetails = new ProductDetails();
        List<String> url = productDetails.getProductImageURL();
        return url;
    }

}
