package com.qdivision.ingredientapi.controller;

import com.qdivision.ingredientapi.entity.IngredientEntity;
import com.qdivision.ingredientapi.repository.IngredientRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
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
    ingredientRepository.save(ingredient);
  }

}
