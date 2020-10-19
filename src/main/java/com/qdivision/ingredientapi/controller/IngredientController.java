package com.qdivision.ingredientapi.controller;

import com.qdivision.ingredientapi.client.EmojiClient;
import com.qdivision.ingredientapi.entity.IngredientEntity;
import com.qdivision.ingredientapi.repository.IngredientRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4003")
public class IngredientController {

  private final IngredientRepository ingredientRepository;

  IngredientController(IngredientRepository ingredientRepository) {
    this.ingredientRepository = ingredientRepository;
  }

  @GetMapping("/ingredients")
  public Iterable<IngredientEntity> getIngredients() {
    return ingredientRepository.findAll();
  }

  @PostMapping("/ingredients")
  public void postIngredient(@RequestBody IngredientEntity ingredient) {
    System.out.println("Creating ingredient: " + ingredient);
    EmojiClient.createEmoji(ingredient.getName(), ingredient.getEmoji());
    ingredientRepository.save(ingredient);
  }

}
