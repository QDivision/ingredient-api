package com.qdivision.ingredientapi.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

  private final RabbitSender rabbitSender;

  public RabbitReceiver(RabbitSender rabbitSender) {
    this.rabbitSender = rabbitSender;
  }

  @SneakyThrows
  public void receiveMessage(String rawMessage) {
    IngredientRequest request = new ObjectMapper().readValue(rawMessage, IngredientRequest.class);
    System.out.println("REQUEST: " + request);

    IngredientResponse response = new IngredientResponse();
    response.setId(request.getId());
    response.setExists(true);
    rabbitSender.send(response);
  }

}
