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
               it.nutrition[1] > 0&&
               it.nutrition[5] > 0&&
               it.nutrition[6] > 0
           }
           .take(10)
        return recipesPreparedInFiftyMinutesOrLess
    }
    private fun sortedByNormalization(allRecipes:List<Recipe>):List<Recipe>{
        val minFat=allRecipes.minOf { it.nutrition[1] }
        val maxFat=allRecipes.maxOf { it.nutrition[1] }
        val minSaturatedFat=allRecipes.minOf { it.nutrition[5] }
        val maxSaturatedFat=allRecipes.maxOf { it.nutrition[5] }
        val minCarbs=allRecipes.minOf { it.nutrition[6] }
        val maxCarbs=allRecipes.maxOf { it.nutrition[6] }
        return allRecipes.sortedBy {
            val fatNorm = (it.nutrition[1] - minFat) / (maxFat - minFat)
            val saturatedNorm = (it.nutrition[5] - minSaturatedFat) / (maxSaturatedFat - minSaturatedFat)
            val carbsNorm = (it.nutrition[6] - minCarbs) / (maxCarbs - minCarbs)
            fatNorm+saturatedNorm+carbsNorm
        }
    }
}