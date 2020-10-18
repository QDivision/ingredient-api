package com.qdivision.ingredientapi.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qdivision.ingredientapi.repository.IngredientRepository;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

@Component
public class RabbitReceiver {

  private final RabbitSender rabbitSender;
  private final IngredientRepository ingredientRepository;

  public RabbitReceiver(RabbitSender rabbitSender, IngredientRepository ingredientRepository) {
    this.rabbitSender = rabbitSender;
    this.ingredientRepository = ingredientRepository;
  }

  @SneakyThrows
  public void receiveMessage(String rawMessage) {
    IngredientRequest request = new ObjectMapper().readValue(rawMessage, IngredientRequest.class);
    System.out.println("REQUEST: " + request);

    boolean exists = ingredientRepository.existsByName(request.getName());

    IngredientResponse response = new IngredientResponse();
    response.setId(request.getId());
    response.setExists(exists);

    rabbitSender.send(response);
  }

}
