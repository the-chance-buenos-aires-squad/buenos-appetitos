package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class GetHealthyFastFoodMealsUseCase(
    private val recipesRepository:RecipesRepository
) {
    fun getHealthyFastFood():List<Recipe>{
        val allRecipes=recipesRepository.getRecipes()
        val normalizedRecipe=sortedByNormalization(allRecipes)
       val recipesPreparedInFiftyMinutesOrLess= normalizedRecipe
           .filter {
               it.minutes<=15&&
               it.nutrition.fat > 0&&
               it.nutrition.saturatedFat > 0&&
               it.nutrition.carbohydrates > 0
           }
           .take(10)
        return recipesPreparedInFiftyMinutesOrLess
    }
    private fun sortedByNormalization(allRecipes:List<Recipe>):List<Recipe>{
        val minFat=allRecipes.minOf { it.nutrition.fat }
        val maxFat=allRecipes.maxOf { it.nutrition.fat }
        val minSaturatedFat=allRecipes.minOf { it.nutrition.saturatedFat }
        val maxSaturatedFat=allRecipes.maxOf { it.nutrition.saturatedFat }
        val minCarbs=allRecipes.minOf { it.nutrition.carbohydrates }
        val maxCarbs=allRecipes.maxOf { it.nutrition.carbohydrates }
        return allRecipes.sortedBy {
            val fatNorm = (it.nutrition.fat - minFat) / (maxFat - minFat)
            val saturatedNorm = (it.nutrition.saturatedFat - minSaturatedFat) / (maxSaturatedFat - minSaturatedFat)
            val carbsNorm = (it.nutrition.carbohydrates - minCarbs) / (maxCarbs - minCarbs)
            fatNorm+saturatedNorm+carbsNorm
        }
    }
}