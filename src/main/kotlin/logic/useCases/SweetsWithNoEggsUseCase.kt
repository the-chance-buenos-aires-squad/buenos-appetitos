package org.example.logic.useCases

import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoSweetFreeEggRecipeFoundException
import org.example.model.Recipe
import kotlin.random.Random

class SweetsWithNoEggsUseCase(private val repository: RecipesRepository) {


    private var sweetsNoEggRecipes = mutableListOf<Recipe>()


    private fun loadSweetNoEggRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw NoRecipeFoundException() }
        sweetsNoEggRecipes.addAll(recipes.filterSweetsNoEggsRecipes()).also {
            if (sweetsNoEggRecipes.isEmpty()) throw NoSweetFreeEggRecipeFoundException()
        }
    }

    fun suggestSweetNoEggRecipe(): Recipe {
        if (sweetsNoEggRecipes.isEmpty()) {
                loadSweetNoEggRecipes()
        }
        val randomIndex = Random.nextInt(sweetsNoEggRecipes.size)
        return sweetsNoEggRecipes.removeAt(randomIndex)
    }


}

private fun List<Recipe>.filterSweetsNoEggsRecipes(): List<Recipe> {
    return this.filter { recipe ->
        recipe.tags.any { tag -> tag.contains("dessert", ignoreCase = true) } &&
                recipe.ingredients.none { ingredient -> ingredient.contains("egg", ignoreCase = true) }
    }
}

