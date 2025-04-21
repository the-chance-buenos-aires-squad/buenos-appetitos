package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe


class GetKetoRecipeUseCase(
    private val repository: RecipesRepository
) {
    fun ketoDietMealHelper(): List<Recipe> {
        val recipes = repository.getRecipes()
        return recipes.filterByNutrition()
    }

    fun List<Recipe>.filterByNutrition(): List<Recipe> {
        return this.filter { recipe ->
            val carbs = recipe.nutrition.carbohydrates
            val fat = recipe.nutrition.fat
            val protein = recipe.nutrition.protein
            carbs <= GetKetoRecipeUseCase.MAX_CARBS || fat >= GetKetoRecipeUseCase.MIN_FAT || protein >= GetKetoRecipeUseCase.MIN_PROTEIN
        }

    }

    companion object {
        const val MAX_CARBS = 5
        const val MIN_FAT = 70
        const val MIN_PROTEIN = 25
    }
}
