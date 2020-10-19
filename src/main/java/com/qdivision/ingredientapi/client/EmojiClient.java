package com.qdivision.ingredientapi.client;

import com.qdivision.ingredientapi.model.Emoji;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class EmojiClient {

  public static void createEmoji(String name, String emoji) {
    RestTemplate restTemplate = new RestTemplate();

    HttpEntity request = new HttpEntity(new Emoji(emoji));

    ResponseEntity<Void> response = restTemplate.exchange(
      "http://localhost:4002/emojis/" + name, HttpMethod.POST, request, Void.class
    );

    if (response.getStatusCode() != HttpStatus.OK) {
      throw new RuntimeException("Failed to POST emoji");
    }
  }

}
