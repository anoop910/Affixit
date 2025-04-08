package com.anoop.flipkartDataRetriving.ExtractData;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

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

    public void findReviewData(String startWord, String endWord, String filepath) {

        try {
            String content = new String(Files.readAllBytes(Path.of(filepath)));
            int startindex = content.indexOf(startWord);

            int endIndex = content.indexOf(endWord);

            if (startindex != -1 && endIndex != -1) {
                String extractContent = content.substring(startindex, endIndex);
                // System.out.println(extractContent);

                String jsonString = extractContent.trim();

                JSONArray jsonArray = new JSONArray(jsonString);

                for (int i = 0; i < jsonArray.length(); i++) {
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
                                                    String imaURL = imavalue.getString("imageURL");
                                                    System.out.println(imaURL);
                                                }
                                            }
                                            if (value.has("rating")) {
                                                int ratingValue = value.getInt("rating");
                                                System.out.println("rating value : "+ ratingValue);
                                            }
                                            if (value.has("author")) {
                                                String author = value.getString("author");
                                                String created = value.getString("created");
                                                String text = value.getString("text");
                                                System.out.println(author);
                                                System.out.println(created);
                                                System.err.println(text);
                                            }
                                            if (value.has("helpfulCount")) {
                                                int like = value.getInt("helpfulCount");
                                                System.out.println("Like : "+ like);
                                            }
                                            if (value.has("totalCount")) {
                                                int totalCountValue = value.getInt("totalCount");
                                                System.out.println("Toltal Count : "+ totalCountValue);
                                            }
                                            if (value.has("location")) {
                                                JSONObject location = value.getJSONObject("location");
                                                String city = location.getString("city");
                                                String state = location.getString("state");
                                                System.out.println("city is : " + city + " and state : "+ state);
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }

        } catch (IOException e) {

            e.printStackTrace();
        }

    }

}
