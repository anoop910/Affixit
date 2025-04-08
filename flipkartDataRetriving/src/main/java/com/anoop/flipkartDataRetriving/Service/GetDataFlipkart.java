package com.anoop.flipkartDataRetriving.Service;

import java.nio.charset.StandardCharsets;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class GetDataFlipkart {

    WebClient webClient = WebClient.builder().baseUrl("https://www.flipkart.com").build();

    public Mono<String> getDAtaFromApi(String uri) {
    

        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToFlux(DataBuffer.class)
                .map(dataBuffer -> {
                    // Convert dataBuffer to string (you can accumulate and process it as required)
                    return dataBuffer.toString(StandardCharsets.UTF_8);
                })
                .collectList()
                .map(chunks -> String.join("", chunks));
    }


 


    


  

}