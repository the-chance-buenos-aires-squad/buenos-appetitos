package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe


class GetKetoRecipeUseCase(
    private val repository: RecipesRepository
) {
    fun getKetoRecipes(): List<Recipe> {
        val recipes = repository.getRecipes()
        return recipes.filterByNutrition()
    }

    private fun List<Recipe>.filterByNutrition(): List<Recipe> {
        return this.filter { recipe ->
            recipe.nutrition.calories <= MIN_CALORIES &&
                    recipe.nutrition.fat <= MIN_FAT &&
                    recipe.nutrition.protein <= MIN_PROTEIN &&
                    recipe.nutrition.saturatedFat <= MIN_SAT_FAT &&
                    recipe.nutrition.carbohydrates <= MIN_CARBS
        }

    }

    companion object {
        const val MIN_CARBS = 10
        const val MIN_FAT = 70
        const val MIN_PROTEIN = 30
        const val MIN_CALORIES = 600
        const val MIN_SAT_FAT = 20
    }
}
