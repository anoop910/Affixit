package com.anoop.flipkartDataRetriving.JPAService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.anoop.flipkartDataRetriving.DTO.CreateProductDTO;
import com.anoop.flipkartDataRetriving.Model.CreateProduct;
import com.anoop.flipkartDataRetriving.Model.Marketer;
import com.anoop.flipkartDataRetriving.Model.ProductDetails;
import com.anoop.flipkartDataRetriving.Repository.CreateProductRepo;
import com.anoop.flipkartDataRetriving.Repository.MarketerRepo;
import com.anoop.flipkartDataRetriving.Service.CreateProductService;



@Service
public class CreateProductMapper {
    private CreateProductRepo createProductRepo;
    private MarketerRepo marketerRepo;
    private CreateProductService createProductService;

    public CreateProductMapper(CreateProductRepo createProductRepo, MarketerRepo marketerRepo, CreateProductService createProductService) {
        this.createProductRepo = createProductRepo;
        this.marketerRepo = marketerRepo;
        this.createProductService = createProductService;

    }

    public List<CreateProductDTO> findALLCreateProduct() {
        List<CreateProduct> createProduct = createProductRepo.findAll();
        List<CreateProductDTO> createProductDTOs = new ArrayList<>();

        createProduct.stream().forEach(createProducts -> {
            CreateProductDTO createProductDTO = new CreateProductDTO();
            createProductDTO.setId(createProducts.getId());
            createProductDTO.setProductURL(createProducts.getProductURL());
            createProductDTO.setMarketer(createProducts.getMarketer());
            createProductDTO.setProductDetails(createProducts.getProductDetails());
            createProductDTOs.add(createProductDTO);
        });
        return createProductDTOs;
    }
    @Transactional
    public CreateProductDTO saveCreateProductDTO(CreateProductDTO createProductDTO, Long marketer_id) throws IOException {
        Marketer marketer = marketerRepo.findById(marketer_id)
                .orElseThrow(
                        () -> new RuntimeException("Marketer not found with ID: " + marketer_id));

        ProductDetails productDetails = createProductService.createProductDetails(createProductDTO);
        CreateProduct createProduct = new CreateProduct();
        createProduct.setProductURL(createProductDTO.getProductURL());
        createProduct.setMarketer(marketer);
        createProduct.setProductDetails(productDetails);
        createProductRepo.save(createProduct);
        

        return createProductDTO;
    }

}
