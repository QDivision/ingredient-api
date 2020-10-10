package com.qdivision.ingredientapi.rabbit;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitSender {

  private final RabbitTemplate rabbitTemplate;

  public RabbitSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  @SneakyThrows
  public void send(IngredientResponse rawMessage) {
    String exchangeName = RabbitConnection.TOPIC_EXCHANGE_NAME;
    String routingKey = RabbitConnection.RESPONSE_ROUTING_KEY;
    String message = new ObjectMapper().writeValueAsString(rawMessage);
    rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
  }

}
