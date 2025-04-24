package org.example.logic.useCases

import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.utili.CommonUtilizes.DEFAULT_NUM_OF_RECIPES
import org.example.model.Recipe

class GetHealthyFastFoodMealsUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getHealthyFastFood(numOfRecipe: Int = DEFAULT_NUM_OF_RECIPES): List<Recipe> {
        val allRecipes = recipesRepository.getRecipes()
        if (allRecipes.isEmpty()) throw NoRecipeFoundException()
        val recipesPreparedInFiftyMinutesOrLess = allRecipes
            .filter { healthyFastFoodConstraints(it) }
            .take(numOfRecipe)
        val normalizedRecipe = sortedByNormalization(recipesPreparedInFiftyMinutesOrLess)
        return normalizedRecipe
    }

    private fun healthyFastFoodConstraints(recipe: Recipe): Boolean {
        return recipe.minutes <= 15 &&
                recipe.nutrition.fat > 0 &&
                recipe.nutrition.saturatedFat > 0 &&
                recipe.nutrition.carbohydrates > 0
    }

    private fun sortedByNormalization(allRecipes: List<Recipe>): List<Recipe> {
        val minFat = allRecipes.minOfOrNull { it.nutrition.fat }
        val maxFat = allRecipes.maxOfOrNull { it.nutrition.fat }

        val minSaturatedFat = allRecipes.minOfOrNull { it.nutrition.saturatedFat }
        val maxSaturatedFat = allRecipes.maxOfOrNull { it.nutrition.saturatedFat }

        val minCarbs = allRecipes.minOfOrNull { it.nutrition.carbohydrates }
        val maxCarbs = allRecipes.maxOfOrNull { it.nutrition.carbohydrates }

        fun normalize(value: Double, min: Double, max: Double): Double {
            return if (max != min) (value - min) / (max - min) else 0.0
        }

        return allRecipes.sortedBy {
            val fatNorm = normalize(it.nutrition.fat, minFat!!, maxFat!!)
            val saturatedNorm = normalize(it.nutrition.saturatedFat, minSaturatedFat!!, maxSaturatedFat!!)
            val carbsNorm = normalize(it.nutrition.carbohydrates, minCarbs!!, maxCarbs!!)
            fatNorm + saturatedNorm + carbsNorm
        }
    }
}