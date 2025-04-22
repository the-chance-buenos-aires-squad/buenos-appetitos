package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class SweetsWithNoEggsUseCase(private val repository: RecipesRepository) {


    private var sweetsNoWggRecipes = mutableListOf<Recipe>()


    private fun getSweetNoEggRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw IllegalStateException("No meals found") }
        sweetsNoWggRecipes = recipes.filterSweetsNoEggsRecipes() as MutableList<Recipe>
    }

    fun suggestSweetNoEggRecipe(): Recipe {
        if (sweetsNoWggRecipes.isEmpty()) {
            try {
                getSweetNoEggRecipes()
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
        return sweetsNoWggRecipes.removeFirst()
    }


}

private fun List<Recipe>.filterSweetsNoEggsRecipes(): List<Recipe> {
    return this.filter { recipe ->
        recipe.tags.any { tag -> tag.contains("dessert", ignoreCase = true) } &&
                recipe.ingredients.none { ingredient -> ingredient.contains("egg", ignoreCase = true) }
    }.shuffled()
}

