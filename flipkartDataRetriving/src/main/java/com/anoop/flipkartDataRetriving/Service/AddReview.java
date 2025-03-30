package com.anoop.flipkartDataRetriving.Service;

import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.ExtractData.RetriveImageLink;
import com.anoop.flipkartDataRetriving.ExtractData.RetriveReview;

@Service
public class AddReview {
    private LinkEdit linkEdit;
    private GetDataFlipkart getDataFlipkart;
    private RetriveReview retriveReview;
    private RetriveImageLink retriveImageLink;
    private String url;
        
    public AddReview(LinkEdit linkEdit, GetDataFlipkart getDataFlipkart, RetriveReview retriveReview, RetriveImageLink retriveImageLink){
        this.linkEdit = linkEdit;
        this.getDataFlipkart = getDataFlipkart;
        this.retriveReview = retriveReview;
        this.retriveImageLink = retriveImageLink;


    }
    // get url from LinkEdit for get Review data form flipkart
    public Boolean saveReviewData(String uri){
       url = linkEdit.reviewLinkEdit(uri);
       getReviewDataFromFlipkart(url);
       return true;

    }

    public void getReviewDataFromFlipkart(String uri){
        String reviewData = getDataFlipkart.getDAtaFromApi(uri).block();
        retriveReview.createFileAndWriteReviewPageData(reviewData);
        String firstWord = "[{\"slotType\":\"LOGICAL\",\"id\":10003,\"parentId\":10002,\"layoutParams\":";
        String endWord = "\"10003\":[{\"slotType\":\"WIDGET\"";
        String filepath = "reviewpage.txt";
        retriveReview.findReviewData(firstWord, endWord, filepath);
        retriveImageLink.deleteFile(filepath);
       
    }




    
    
}
