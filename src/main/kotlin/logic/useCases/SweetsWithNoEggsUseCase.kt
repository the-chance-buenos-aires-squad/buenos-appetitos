package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SweetsWithNoEggsUseCase(private val repository: RecipesRepository) {

    private val suggestedSweetsId = mutableSetOf<String>()

    fun getRandomSweetsNoEggs(): Recipe {

        val allMeals = repository.getRecipes()

        if (allMeals.isEmpty()) throw IllegalStateException("No meals found")

        val sweetNoEggs = allMeals.sweetNoEggsList(suggestedSweetsId)

        if (sweetNoEggs.isEmpty()) throw IllegalStateException("No suitable dessert without eggs found.")

        val randomSweets = sweetNoEggs.random()
        suggestedSweetsId.add(randomSweets.id)

        return randomSweets
    }

}

private fun List<Recipe>.sweetNoEggsList(suggestedId: Set<String>): List<Recipe> {
    return this.filter { recipe ->
        recipe.tags.any { tag -> tag.contains("dessert", ignoreCase = true) } &&
                recipe.ingredients.none { ingredient -> ingredient.contains("egg", ignoreCase = true) } &&
                recipe.id !in suggestedId
    }
}


