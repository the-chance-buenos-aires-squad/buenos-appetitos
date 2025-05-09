package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import org.example.model.SeaFoodInfo

class GetSeaFoodRankingByProteinUseCase(
    private val recipesRepository: RecipesRepository
) {
    fun getSeaFoodRanking(): List<Recipe> {
        val allRecipes = recipesRepository.getRecipes()
        val seaFoodMeals = allRecipes
            .filter(::isSeaFoodContainProtein)
            .sortedByDescending {
                it.nutrition.protein
            }.take(10)

        return seaFoodMeals
    }

    private fun isSeaFoodContainProtein(recipe: Recipe): Boolean {
        return (recipe.tags.containsSeaFood() || recipe.ingredients.containsSeaFood()) && recipe.nutrition.protein > 0
    }

}

private fun List<String>.containsSeaFood(): Boolean {
    val seaFoodKeyWords =
        listOf("seafood", "fish", "shrimp", "crab", "lobster", "salmon", "tuna", "mackerel", "sardine", "oyster")

    return this.any { item ->
        seaFoodKeyWords.any { keyword -> item.contains(keyword, ignoreCase = true) }
    }
}