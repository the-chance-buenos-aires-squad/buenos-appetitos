package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import kotlin.random.Random


class GetKetoRecipeUseCase(
    private val repository: RecipesRepository
) {
    private var ketoRecipes = mutableListOf<Recipe>()

    private fun getKetoRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw IllegalStateException("No recipes found") }
        ketoRecipes = recipes.filterByNutrition() as MutableList<Recipe>
    }

    private fun List<Recipe>.filterByNutrition(): List<Recipe> {
        return this.filter { recipe ->
            recipe.nutrition.carbohydrates <= MAX_CARBS &&
                    recipe.nutrition.fat >= MIN_FAT &&
                    recipe.nutrition.protein >= MIN_PROTEIN &&
                    recipe.nutrition.saturatedFat <= MAX_SAT_FAT
        }

    }


    fun suggestRandomKetoRecipe(): Recipe {
        if (ketoRecipes.isEmpty()) {
            try {
                getKetoRecipes()
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
        val randomIndex = Random.nextInt(ketoRecipes.size)
        return ketoRecipes.removeAt(randomIndex)
    }


    companion object {
        const val MAX_CARBS = 30
        const val MIN_FAT = 40
        const val MIN_PROTEIN = 30
        const val MAX_SAT_FAT = 15
    }

}
