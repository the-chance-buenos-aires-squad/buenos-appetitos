package org.example.logic.useCases

import org.example.model.Recipe

class KetoMealsHelperUseCase {
    fun execute(recipes: List<Recipe>): List<Recipe> {
        return recipes.filter { recipe ->
            val carbs = recipe.nutrition.getOrNull(0) ?: 0.0
            val fat = recipe.nutrition.getOrNull(1) ?: 0.0
            carbs < 15 && fat > 20
        }
    }
}
