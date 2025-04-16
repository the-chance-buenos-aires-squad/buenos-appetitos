package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe


class GetSeaFoodRankingByProteinUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getSeaFoodRanking(count:Int):List<String>{
        val allRecipes=recipesRepository.getRecipes()
        val isSeaFood=allRecipes
            .filter (::isSeaFood)
            .sortedByDescending {
            it.nutrition[4]
        }.take(count)
            .mapIndexed { index, recipe ->
            "ranking is ${index+1} - name is ${recipe.name} and protein amount ${recipe.nutrition[4]}"
        }
        return isSeaFood
    }
    fun isSeaFood(recipe: Recipe):Boolean{
            return recipe.tags.any {
                it.contains("seafood", ignoreCase = true)||
                it.contains("fish",ignoreCase = true)||
                it.contains("shrimp",ignoreCase = true)||
                it.contains("crab",ignoreCase = true)||
                it.contains("lobster",ignoreCase = true)||
                it.contains("salmon",ignoreCase = true)||
                it.contains("tuna", ignoreCase = true) ||
                it.contains("mackerel", ignoreCase = true) ||
                it.contains("sardine", ignoreCase = true) ||
                it.contains("oyster", ignoreCase = true)
            }||
            recipe.ingredients.any {
                it.contains("seafood", ignoreCase = true)||
                it.contains("fish",ignoreCase = true)||
                it.contains("shrimp",ignoreCase = true)||
                it.contains("crab",ignoreCase = true)||
                it.contains("lobster",ignoreCase = true)||
                it.contains("salmon",ignoreCase = true)||
                it.contains("tuna", ignoreCase = true) ||
                it.contains("mackerel", ignoreCase = true) ||
                it.contains("sardine", ignoreCase = true) ||
                it.contains("oyster", ignoreCase = true)
                    }
    }
}