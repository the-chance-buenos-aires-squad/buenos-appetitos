package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import org.example.model.SeaFoodInfo

class GetSeaFoodRankingByProteinUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getSeaFoodRanking(): List<SeaFoodInfo> {
        val allRecipes = recipesRepository.getRecipes()
        val seaFoodMeals = allRecipes
            .filter(::isSeaFood)
            .sortedByDescending {
                it.nutrition.protein
            }.take(10)
            .mapIndexed { index, recipe ->
                SeaFoodInfo(index + 1, recipe.name, recipe.nutrition.protein)
            }
        return seaFoodMeals
    }

    private fun isSeaFood(recipe: Recipe): Boolean {
        return recipe.tags.containsSeaFood() || recipe.ingredients.containsSeaFood()
    }

}

private fun List<String>.containsSeaFood(): Boolean {
    val seaFoodKeyWords =
        listOf("seafood", "fish", "shrimp", "crab", "lobster", "salmon", "tuna", "mackerel", "sardine", "oyster")

    return this.any { item ->
        seaFoodKeyWords.any { keyword -> item.contains(keyword, ignoreCase = true) }
    }
}