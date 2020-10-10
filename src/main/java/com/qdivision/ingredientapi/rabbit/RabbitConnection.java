package com.qdivision.ingredientapi.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitConnection {

  public static final String TOPIC_EXCHANGE_NAME = "food-exchange";
  public static final String REQUEST_QUEUE_NAME = "request-food-queue";
  public static final String RESPONSE_QUEUE_NAME = "response-food-queue";
  public static final String REQUEST_ROUTING_KEY = "food.ingredient.exists.request";
  public static final String RESPONSE_ROUTING_KEY = "food.ingredient.exists.response";

  @Bean
  public Queue requestQueue() {
    return new Queue(REQUEST_QUEUE_NAME, false);
  }

  @Bean
  public Queue responseQueue() {
    return new Queue(RESPONSE_QUEUE_NAME, false);
  }

  @Bean
  public TopicExchange exchange() {
    return new TopicExchange(TOPIC_EXCHANGE_NAME);
  }

  @Bean
  public Binding requestQueueBinding(TopicExchange exchange) {
    return BindingBuilder.bind(requestQueue()).to(exchange).with(REQUEST_ROUTING_KEY);
  }

  @Bean
  public Binding responseQueueBinding(TopicExchange exchange) {
    return BindingBuilder.bind(responseQueue()).to(exchange).with(RESPONSE_ROUTING_KEY);
  }

  @Bean
  public SimpleMessageListenerContainer requestContainer(
    ConnectionFactory connectionFactory,
    MessageListenerAdapter listenerAdapter
  ) {
    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);
    container.setQueueNames(REQUEST_QUEUE_NAME);
    container.setMessageListener(listenerAdapter);
    return container;
  }

  @Bean
  public MessageListenerAdapter listenerAdapter(RabbitReceiver receiver) {
    return new MessageListenerAdapter(receiver, "receiveMessage");
  }

}
