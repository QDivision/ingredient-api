package com.qdivision.ingredientapi.repository;

import com.qdivision.ingredientapi.entity.IngredientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends CrudRepository<IngredientEntity, String> {
}
