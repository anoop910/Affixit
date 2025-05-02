package com.anoop.flipkartDataRetriving.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.ExtractData.RetriveImageLink;
import com.anoop.flipkartDataRetriving.ExtractData.RetriveReview;
import com.anoop.flipkartDataRetriving.Model.ReviewData;
import com.anoop.flipkartDataRetriving.Repository.RetriveReviewRepo;

@Service
public class AddReview {
    private LinkEdit linkEdit;
    private GetDataFlipkart getDataFlipkart;
    private RetriveReview retriveReview;
    private RetriveImageLink retriveImageLink;
    private String url;
    private RetriveReviewRepo retriveReviewRepo;
        
    public AddReview(LinkEdit linkEdit, GetDataFlipkart getDataFlipkart, RetriveReview retriveReview, RetriveImageLink retriveImageLink, RetriveReviewRepo retriveReviewRepo){
        this.linkEdit = linkEdit;
        this.getDataFlipkart = getDataFlipkart;
        this.retriveReview = retriveReview;
        this.retriveImageLink = retriveImageLink;
        this.retriveReviewRepo = retriveReviewRepo;


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
        String firstWord = "[{\"slotType\":\"LOGICAL\",\"id\":10003,\"parentId\":10002,\"layoutParams\":"; //[{"slotType":"LOGICAL","id":10003,"parentId":10002,"layoutParams":
        String endWord = "\"10003\":[{\"slotType\":\"WIDGET\"";
        String endWord2 = "\"10003\":[{\"slotType\":\"LOGICAL\"";
        String filepath = "reviewpage.txt";
       List<ReviewData> reviewDatas = retriveReview.findReviewData(firstWord, endWord, endWord2, filepath);
       retriveReviewRepo.saveAll(reviewDatas);
      // System.out.println(reviewDatas);
       retriveImageLink.deleteFile(filepath);
       
    }




    
    
}
