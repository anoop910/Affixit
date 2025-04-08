package com.anoop.flipkartDataRetriving.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.springframework.stereotype.Service;

@Service
public class ImageDownloadService {

    public void downloadImage(String imageUrl, String savePath) throws IOException, InterruptedException {
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Build the HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(imageUrl))
                .GET() // HTTP GET method
                .build();

        // Send the request and get the response as a byte array
        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        // Check if the response is successful
        if (response.statusCode() == 200) {
            // Save the image to the specified path
            try (FileOutputStream out = new FileOutputStream(savePath)) {
                out.write(response.body());
            }
            System.out.println("Image downloaded successfully to: " + savePath);
        } else {
            System.err.println("Failed to download image. HTTP Status: " + response.statusCode());
        }
    }

    
}
