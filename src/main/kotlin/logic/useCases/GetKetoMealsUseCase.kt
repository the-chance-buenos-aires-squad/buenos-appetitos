package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

@Suppress("UNREACHABLE_CODE")
class GetKetoMealsUseCase (
    private val repository: RecipesRepository) {
    fun execute (): List<Recipe> {
        val recipes = repository.getRecipes()
        return recipes.filter { recipe ->

            val carbs = recipe.nutrition.getOrNull(6) ?: 0.0
            val fat = recipe.nutrition.getOrNull(1) ?: 0.0
            val protein = recipe.nutrition.getOrNull(4) ?: 0.0
            carbs <= 5 || fat >= 70 || protein >= 25

        }
    }
}
