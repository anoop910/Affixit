package com.anoop.flipkartDataRetriving.Service;

import org.springframework.stereotype.Service;

@Service
public class LinkEdit {
    


    public String reviewLinkEdit(String url){
        String link = url;
        String reviewLink = link.replace("/p/", "/product-reviews/").concat("&page=1");
        return reviewLink;
        
    }
}
