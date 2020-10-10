package com.qdivision.ingredientapi.rabbit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IngredientRequest {

  private UUID id;

  private String name;

}
