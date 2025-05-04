package com.anoop.flipkartDataRetriving.ExtractData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.anoop.flipkartDataRetriving.Model.ProductColorAvailableWithImg;

@Service
public class RetriveImageLink {
    
    public void createFileAndWrite(String content) {
        try (FileWriter writer = new FileWriter("output.txt")) {
            writer.write(content);
            System.out.println("String written to file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

    // find the image link
    public List<String> findImageLink(String startWord, String endWord, String filePath, String findKeyValue) {

        List<String> imgUrl = new ArrayList<>();

        try {
            // Read entire file into a single String
            String content = new String(Files.readAllBytes(Path.of(filePath)));

            // Find the start and end index of the target words
            int startIndex = content.indexOf(startWord);
            int endIndex = content.indexOf(endWord, startIndex);

            // Check if both words exist
            if (startIndex != -1 && endIndex != -1) {
                // Extract content between the two words
                String extractedContent = content.substring(
                        startIndex + startWord.length(), endIndex);
                String jsonString = extractedContent.trim();
                // System.out.println("Extracted Content:\n" + jsonString + "\n\n\n\n");
                try {
                    JSONArray jsonArray = new JSONArray(jsonString);

                    // Loop through the JSON array to retrieve all URLs dynamically
                    for (int i = 0; i < jsonArray.length(); i++) {
                        Object obj = jsonArray.get(i); // Get element from JSONArray

                        if (obj instanceof JSONObject jsonObject) {
                            if (jsonObject.has("value")) {
                                JSONObject value = jsonObject.getJSONObject("value");
                                if (value.has("url")) {
                                    String url = value.getString("url"); // Get the URL dynamically
                                    // System.out.println("Found URL at Index " + i + ": " + url);
                                    imgUrl.add(url);
                                }
                            }
                        }
                    }
                    return imgUrl;

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }

            } else {
                System.out.println("One or both words not found in the file.");
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return null;
    }

    // find the product title
    public String findProductTitle(String startWord, String endWord, String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Path.of(filePath)));
        int startIdx = content.indexOf(startWord);
        int endIdx = content.indexOf(endWord, startIdx);
        int lenOfStartWord = startWord.length();
        if (startIdx != -1 && endIdx != -1) {
            String title = content.substring(startIdx + lenOfStartWord, endIdx);
            System.out.println("Title is : " + title);
            return title;
        } else {
            System.out.println("Con't find title");
        }

        return null;
    }

    // find the price of product
    public String findProductPrice(String startWord, String endWord, String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Path.of(filePath)));
        int startIdx = content.indexOf(startWord);
        int endIdx = content.indexOf(endWord, startIdx);
        int lenOfStartWord = startWord.length();
        if (startIdx != -1 && endIdx != -1) {
            String price = content.substring(startIdx + lenOfStartWord, endIdx);
            System.out.println(" this price of product : " + price);
            return price;
        } else {
            System.out.println("Con't find price");
        }

        return null;
    }

    // find the product color available with image
    public List<ProductColorAvailableWithImg> findProudctColorWithImage(String startWord, String endWord, String filePath) {

        List<ProductColorAvailableWithImg> productColorAvailableWithImgs = new ArrayList<>();
        
        try {
            String content = new String(Files.readAllBytes(Path.of(filePath)));
            int startIdx = content.indexOf(startWord);
            int endIdx = content.indexOf(endWord, startIdx);
            int lenOfStartWord = startWord.length();
            if (startIdx != -1 && endIdx != -1) {
                String extractedContent = content.substring(
                        startIdx + lenOfStartWord, endIdx);
                String jsonString = extractedContent.trim();
               // System.out.println("Extracted Content:\n" + jsonString + "\n\n\n\n");

                JSONArray jsonArray = new JSONArray(jsonString);

                for(int i = 0; i < jsonArray.length(); i++){
                    Object object = jsonArray.get(i);
                    
                    if (object instanceof JSONObject jsonObject) {
                      
                        if (jsonObject.has("imageUrl")) {
                            String imageUrl = jsonObject.getString("imageUrl");
                            String productColor = jsonObject.getString("value");
                            String productColorImg = imageUrl.replace("rukmini1", "rukminim2");
                           ProductColorAvailableWithImg productColorAvailableWithImg = new ProductColorAvailableWithImg();
                           productColorAvailableWithImg.setColor(productColor);
                           productColorAvailableWithImg.setProductImg(productColorImg);
                           System.out.println("product color : "+productColorAvailableWithImg);
                            productColorAvailableWithImgs.add(productColorAvailableWithImg);
                           
                            
                        }else{
                            System.out.println("some wrong in product color img");
                        }
                    }
                }


            }
        } catch (IOException e) {

            e.printStackTrace();
        }
        return productColorAvailableWithImgs;
    }

    public void findTargetWordInFile(String targetWord, String filePath) {

        boolean found = false;
        int lineNumber = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lineNumber++;
                if (line.contains(targetWord)) {
                    found = true;
                    System.out.println("Word '" + targetWord + "' found at line " + lineNumber);
                    break;
                }
            }

            if (!found) {
                System.out.println("Word '" + targetWord + "' not found in the file.");
            }

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }

    }

    public void deleteFile(String filePath) {
        File file = new File(filePath);
        // Check if the file exists
        if (file.exists()) {
            // Attempt to delete the file
            if (file.delete()) {
                System.out.println("File deleted successfully: " + filePath);
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
    }
}
