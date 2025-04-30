package com.anoop.flipkartDataRetriving.Service;


import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.ExtractData.RetriveImageLink;
import com.anoop.flipkartDataRetriving.Model.CreateProduct;
import com.anoop.flipkartDataRetriving.Model.ProductDetails;
import com.anoop.flipkartDataRetriving.Repository.ProductDetailRepo;




@Service
public class CreateProductService {

   
   

    private GetDataFlipkart getDataFlipkart;
    private RetriveImageLink retriveImageLink;
    private ProductDetailRepo productDetailRepo;

   
    public CreateProductService(GetDataFlipkart getDataFlipkart, RetriveImageLink retriveImageLink, ProductDetailRepo productDetailRepo) {
        this.getDataFlipkart = getDataFlipkart;
        this.retriveImageLink = retriveImageLink;
        this.productDetailRepo = productDetailRepo;
      
        
    

    }

  
    public Boolean createProductUrl(CreateProduct createProduct) throws IOException {
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

            String firstWord = "</span><span class=\"VU-ZEz\">";
            String lastWord = "</span></h1>";
           
           
            List<String> productImgUrl = retriveImageLink.findImageLink(startWord, endWord, filePath, findKeyValue);
           String title = retriveImageLink.findProductTitle(firstWord, lastWord, filePath);
           String price = retriveImageLink.findProductPrice("<div class=\"Nx9bqj CxhGGd\">", "</div>", filePath);
           retriveImageLink.findProudctColorWithImage("{\"type\":\"ProductSwatchValue\",\"attributeOptions\":[", "],\"attributes\":[{\"attributeImage\":", filePath);
            
           
            ProductDetails productDetails = new ProductDetails();
            productDetails.setProductImageURL(productImgUrl);
            productDetails.setTitle(title);
            productDetails.setPrice(price);
            productDetailRepo.save(productDetails); // save product details like image link, price, title
            

          retriveImageLink.deleteFile(filePath);


        }
        return true;
    }

    

}
