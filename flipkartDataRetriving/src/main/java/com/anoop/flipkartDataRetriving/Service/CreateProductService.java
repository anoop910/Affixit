package com.anoop.flipkartDataRetriving.Service;

import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.DTO.CreateProductDTO;
import com.anoop.flipkartDataRetriving.ExtractData.RetriveImageLink;
import com.anoop.flipkartDataRetriving.Model.CreateProduct;
import com.anoop.flipkartDataRetriving.Model.ProductColorAvailableWithImg;
import com.anoop.flipkartDataRetriving.Model.ProductDetails;
import com.anoop.flipkartDataRetriving.Repository.ProductColorAvailableWithImgRepo;
import com.anoop.flipkartDataRetriving.Repository.ProductDetailRepo;

@Service
public class CreateProductService {

    private GetDataFlipkart getDataFlipkart;
    private RetriveImageLink retriveImageLink;
    private ProductDetailRepo productDetailRepo;
    private ProductColorAvailableWithImgRepo productColorAvailableWithImgRepo;

    public CreateProductService(GetDataFlipkart getDataFlipkart, RetriveImageLink retriveImageLink,
            ProductDetailRepo productDetailRepo, ProductColorAvailableWithImgRepo productColorAvailableWithImgRepo) {
        this.getDataFlipkart = getDataFlipkart;
        this.retriveImageLink = retriveImageLink;
        this.productDetailRepo = productDetailRepo;
        this.productColorAvailableWithImgRepo = productColorAvailableWithImgRepo;

    }

    public ProductDetails createProductDetails(CreateProductDTO createProduct) throws IOException {
        String url = createProduct.getProductURL();
        int originalUrlIndx = url.indexOf("FLIPKART");
        String originalUrl = url.substring(24, originalUrlIndx + 8);
        if (createProduct != null) {
            CreateProduct createProductNew = new CreateProduct();
            createProductNew.setProductURL(originalUrl);
            String firstPageData = getDataFlipkart.getDAtaFromApi(originalUrl).block();
            retriveImageLink.createFileAndWrite(firstPageData);

            String filePath = "output.txt";
            String startWord = "multimediaComponents\":";
            String endWord = ",\"offerButton\":null,\"productDetailsAnnouncement";
            String findKeyValue = "url";

            String firstWord = "<span class=\"VU-ZEz\">";  
            String lastWord = "</span></h1>";

            List<String> productImgUrl = retriveImageLink.findImageLink(startWord, endWord, filePath, findKeyValue);
            String title = retriveImageLink.findProductTitle(firstWord, lastWord, filePath);
            String price = retriveImageLink.findProductPrice("<div class=\"Nx9bqj CxhGGd\">", "</div>", filePath);
            List<ProductColorAvailableWithImg> productColorAvailableWithImgs = retriveImageLink
                    .findProudctColorWithImage("{\"type\":\"ProductSwatchValue\",\"attributeOptions\":[",
                            "],\"attributes\":[{\"attributeImage\":", filePath);

            productColorAvailableWithImgRepo.saveAll(productColorAvailableWithImgs);

            ProductDetails productDetails = new ProductDetails();
            productDetails.setProductImageURL(productImgUrl);
            productDetails.setTitle(title);
            productDetails.setPrice(price);
            ProductDetails productDetails2 = productDetailRepo.save(productDetails); // save product details like image link, price, title

           retriveImageLink.deleteFile(filePath);
           return productDetails2;
        }
      return null;
    }

}
