package com.anoop.flipkartDataRetriving.ExtractData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.Model.ReviewData;

@Service
public class RetriveReview {

    public void createFileAndWriteReviewPageData(String content) {
        try {
            FileWriter writer = new FileWriter("reviewpage.txt");
            writer.write(content);
            writer.close();
            System.out.println("String written Reviewpage data in file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing reviewpage data file: " + e.getMessage());

        }

    }

    public List<ReviewData> findReviewData(String startWord, String endWord, String endWord2, String filepath) {

        List<ReviewData> reviewDatas = new ArrayList<>();
        
       
       
        try {
            String content = new String(Files.readAllBytes(Path.of(filepath)));
            int lastIdx;
            int startindex = content.indexOf(startWord);
            int endIndex = content.indexOf(endWord);
            int endIndex2 = content.indexOf(endWord2);
          
            if (endIndex != -1) {
                lastIdx = endIndex;
            }else{
                lastIdx = endIndex2;
            }
            

            if (startindex != -1 && lastIdx != -1) {
                String extractContent = content.substring(startindex, lastIdx);
               //  System.out.println(extractContent);

                String jsonString = extractContent.trim();

                JSONArray jsonArray = new JSONArray(jsonString);
                //System.out.println(jsonArray);


                for (int i = 0; i < jsonArray.length(); i++) {
                    ReviewData reviewData = new ReviewData();
                    List<String> reviewImgLink = new ArrayList<>();

                    Object obj = jsonArray.get(i);

                    if (obj instanceof JSONObject jsonObject) {
                        if (jsonObject.has("widget")) {
                            JSONObject widget = jsonObject.getJSONObject("widget");
                            
                            if (widget.has("data")) {
                                JSONObject data = widget.getJSONObject("data");
                                if (data.has("renderableComponents")) {
                                    JSONArray renderableComponents = data.getJSONArray("renderableComponents");
                                    if (renderableComponents.getJSONObject(0) != null) {
                                        JSONObject indexZero = renderableComponents.getJSONObject(0);
                                        if (indexZero.has("value")) {
                                            JSONObject value = indexZero.getJSONObject("value");
                                            if (value.has("images")) {
                                                JSONArray images = value.getJSONArray("images");
                                                for (int j = 0; j < images.length(); j++) {
                                                    JSONObject image = images.getJSONObject(j);
                                                    JSONObject imavalue = image.getJSONObject("value");
                                                    String imgURL = imavalue.getString("imageURL");
                                                    reviewImgLink.add(imgURL);
                                                    //System.out.println(imgURL);
                                                }
                                                reviewData.setReviewImgLink(reviewImgLink);
                                            }
                                            if (value.has("rating")) {
                                                int ratingValue = value.getInt("rating");
                                                reviewData.setRating(ratingValue);
                                                //System.out.println("rating value : "+ ratingValue);
                                            }
                                            if (value.has("author")) {
                                                String author = value.getString("author");
                                                String created = value.getString("created");
                                                String text = value.getString("text");
                                                reviewData.setAuthor(author);
                                                reviewData.setCreated(created);
                                                reviewData.setText(text);
                                                //System.out.println(author);
                                                //System.out.println(created);
                                                //System.err.println(text);
                                            }
                                            if (value.has("helpfulCount")) {
                                                int like = value.getInt("helpfulCount");
                                                reviewData.setHelpfulCount(like);
                                               // System.out.println("Like : "+ like);
                                            }
                                            if (value.has("totalCount")) {
                                                int totalCountValue = value.getInt("totalCount");
                                                reviewData.setTotalCount(totalCountValue);
                                               // System.out.println("Toltal Count : "+ totalCountValue);
                                            }
                                            try {
                                            if (value.has("location")) {
                                                JSONObject location = value.getJSONObject("location");
                                                String city = location.getString("city");
                                                String state = location.getString("state");
                                                reviewData.setCity(city);
                                                reviewData.setState(state);
                                              //  System.out.println("city is : " + city + " and state : "+ state);
                                               

                                            }}catch (Exception e) {
                                                e.printStackTrace();
                                            }
                                           
                                        }

                                    }
                                    reviewDatas.add(reviewData);
                                }
                            }
                        }
                        
                    }
                    
                }
            } else{
                System.out.println("some thing wrong in finding data check your sartWord and endWord");
            }

        } catch (IOException e) {

            e.printStackTrace();
        }
        return reviewDatas;

    }

}
