package org.example.logic.useCases

import org.example.logic.RecipesRepository


class SuggestMealsUseCases(private val repository: RecipesRepository) {
    var recipe = repository.getRecipes()
    fun suggestRandomMeals(): List<String> {
        return recipe.filter { it ->
            it.minutes <= 30 &&
                    it.numberOfIngredients <= 5 &&
                    it.numberOfSteps <= 6
        }.shuffled().take(10).map { it.name }
    }
}