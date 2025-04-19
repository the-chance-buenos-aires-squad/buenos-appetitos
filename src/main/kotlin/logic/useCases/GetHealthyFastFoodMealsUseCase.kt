package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.logic.utili.CommonUtilizes
import org.example.model.Recipe

class GetHealthyFastFoodMealsUseCase(
    private val recipesRepository:RecipesRepository
) {
    fun getHealthyFastFood( numOfRecipe: Int = CommonUtilizes.DEFAULT_NUM_OF_RECIPES):List<Recipe>{
        val allRecipes=recipesRepository.getRecipes()
        val recipesPreparedInFiftyMinutesOrLess= allRecipes
            .filter { healthyFastFoodConstraints(it) }
            .take(numOfRecipe)
        val normalizedRecipe=sortedByNormalization(recipesPreparedInFiftyMinutesOrLess)
        return normalizedRecipe
    }

    private fun healthyFastFoodConstraints(recipe: Recipe): Boolean {
        return recipe.minutes <= 15 &&
                recipe.nutrition.fat > 0 &&
                recipe.nutrition.saturatedFat > 0 &&
                recipe.nutrition.carbohydrates > 0
    }

    private fun sortedByNormalization(allRecipes: List<Recipe>): List<Recipe> {

        val minFat = allRecipes.minOf { it.nutrition.fat }
        val maxFat = allRecipes.maxOf { it.nutrition.fat }

        val minSaturatedFat = allRecipes.minOf { it.nutrition.saturatedFat }
        val maxSaturatedFat = allRecipes.maxOf { it.nutrition.saturatedFat }

        val minCarbs = allRecipes.minOf { it.nutrition.carbohydrates }
        val maxCarbs = allRecipes.maxOf { it.nutrition.carbohydrates }

        fun normalize(value: Double, min: Double, max: Double): Double {
            return if (max != min) (value - min) / (max - min) else 0.0
        }

        return allRecipes.sortedBy {
            val fatNorm = normalize(it.nutrition.fat, minFat, maxFat)
            val saturatedNorm = normalize(it.nutrition.saturatedFat, minSaturatedFat, maxSaturatedFat)
            val carbsNorm = normalize(it.nutrition.carbohydrates, minCarbs, maxCarbs)
            fatNorm + saturatedNorm + carbsNorm
        }
    }


}