package com.anoop.flipkartDataRetriving.RestController;

import java.io.IOException;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anoop.flipkartDataRetriving.DTO.CreateProductDTO;
import com.anoop.flipkartDataRetriving.JPAService.CreateProductMapper;
import com.anoop.flipkartDataRetriving.Model.CreateProduct;
import com.anoop.flipkartDataRetriving.Service.AddReview;
import com.anoop.flipkartDataRetriving.Service.CreateProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/product")
public class CreateProductRestCont {

    private CreateProductService createProductService;
    private AddReview addReview;
    private CreateProductMapper createProductMapper;

    public CreateProductRestCont(CreateProductService createProductService, AddReview addReview,
            CreateProductMapper createProductMapper) {
        this.createProductService = createProductService;
        this.addReview = addReview;
        this.createProductMapper = createProductMapper;
    }

    @PostMapping("review")
    public Boolean saveReviewBoolean(@Valid @RequestBody CreateProduct createProduct) {
        String url = createProduct.getProductURL();
        return addReview.saveReviewData(url);
    }

    @GetMapping
    public List<CreateProductDTO> getAllCreatePoducts() {
        return createProductMapper.findALLCreateProduct();
    }

    @PostMapping("/create/{marketer_id}")
    public CreateProductDTO createProduct(@RequestBody CreateProductDTO createProductDTO,
            @PathVariable Long marketer_id) throws IOException {
        return createProductMapper.saveCreateProductDTO(createProductDTO, marketer_id);
    }

}
