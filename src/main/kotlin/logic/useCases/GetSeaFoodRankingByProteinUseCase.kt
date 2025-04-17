package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe


class GetSeaFoodRankingByProteinUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getSeaFoodRanking():List<SeaFoodInfo>{
        val allRecipes=recipesRepository.getRecipes()
        val seaFoodMeals =allRecipes
            .filter (::isSeaFood)
            .sortedByDescending {
            it.nutrition.protein
        }.take(10)
            .mapIndexed { index, recipe ->
            SeaFoodInfo(index+1,recipe.name,recipe.nutrition.protein)
        }
        return seaFoodMeals
    }
    private fun isSeaFood(recipe: Recipe):Boolean{
        return recipe.tags.containsSeaFood()||recipe.ingredients.containsSeaFood()
    }
    private fun List<String>.containsSeaFood():Boolean{
        return this.any {
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

data class SeaFoodInfo(
    val rank:Int,
    val name:String,
    val amountOfProtein:Double
)