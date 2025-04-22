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

    fun generateRound(): Round {
        val meal = allMeals.random()
        val correct = meal.ingredients.random()

        val wrongOptions = allIngredients
            .filterNot { it.equals(correct, ignoreCase = true) }
            .shuffled()
            .take(2)

        val allOptions = (wrongOptions + correct).shuffled()

        return Round(
            meal = meal,
            options = allOptions,
            correct = correct
        )
    }

    fun checkAnswer(selected: String?, correct: String): Boolean {
        return selected?.equals(correct, ignoreCase = true) == true
    }
}