package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class IngredientGameUseCase(private val repository: RecipesRepository) {
    private val allMeals = repository.getRecipes().filter { it.ingredients.isNotEmpty() }
    private val allIngredients = allMeals.flatMap { it.ingredients }.distinct()
    data class Round(
        val meal: Recipe,
        val options: List<String>,
        val correct: String
    )
}