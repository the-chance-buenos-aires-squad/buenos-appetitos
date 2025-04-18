package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

@Suppress("UNREACHABLE_CODE")
class GetKetoMealsUseCase (
    private val repository: RecipesRepository) {
    fun execute (): List<Recipe> {
        val recipes = repository.getRecipes()
        return recipes.filter { recipe ->

            val carbs = recipe.nutrition.carbohydrates
            val fat = recipe.nutrition.fat
            val protein = recipe.nutrition.protein
            carbs <= 5 || fat >= 70 || protein >= 25

        }
    }
}
