package com.anoop.flipkartDataRetriving.Service;


import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.ExtractData.RetriveImageLink;
import com.anoop.flipkartDataRetriving.Model.CreateProduct;
import com.anoop.flipkartDataRetriving.Model.ProductDetails;

@Service
public class CreateProductService {

    private GetDataFlipkart getDataFlipkart;
    private RetriveImageLink retriveImageLink;
    private ImageDownloadService imageDownloadService;
    private LinkEdit linkEdit;

    public CreateProductService(GetDataFlipkart getDataFlipkart, RetriveImageLink retriveImageLink, ImageDownloadService imageDownloadService, LinkEdit linkEdit) {
        this.getDataFlipkart = getDataFlipkart;
        this.retriveImageLink = retriveImageLink;
        this.imageDownloadService = imageDownloadService;
        this.linkEdit = linkEdit;

    }

    public Boolean createProductUrl(CreateProduct createProduct) {
        String url = createProduct.getProductURL();
        linkEdit.reviewLinkEdit(url);
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
           
            List<String> productImgUrl = retriveImageLink.findData(startWord, endWord, filePath, findKeyValue);
            
           
            ProductDetails productDetails = new ProductDetails();
            productDetails.setProductImageURL(productImgUrl);

           retriveImageLink.deleteFile(filePath);


        for (String productImgUrl2 : productImgUrl) {
           
           if (productImgUrl2.contains("{@width}")) {
            
           String width = productImgUrl2.replace("{@width}", "900");
           String height = width.replace("{@height}","700");
           String quality = height.replace("{@quality}", "100");
            System.out.println(quality);
            try {
                imageDownloadService.downloadImage(quality, "D:\\E-Anuj\\flipkart\\image" + (int)(Math.random() * 10000) + ".jpg" );
                
            } catch (IOException e) {
                
                e.printStackTrace();
            } catch (InterruptedException e) {
                
                e.printStackTrace();
            }
            
           }
        }
            // System.out.println(productDetails);

        }
        return true;
    }

    

}
