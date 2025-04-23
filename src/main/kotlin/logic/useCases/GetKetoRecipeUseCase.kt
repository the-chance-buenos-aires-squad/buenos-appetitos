package org.example.logic.useCases

import logic.customExceptions.NoRecipeFoundException
import org.example.logic.RecipesRepository
import org.example.logic.customExceptions.NoKetoRecipeFoundException
import org.example.model.Recipe
import kotlin.random.Random


class GetKetoRecipeUseCase(
    private val repository: RecipesRepository
) {
    private var ketoRecipes = mutableListOf<Recipe>()

    private fun loadKetoRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw NoRecipeFoundException() }
        ketoRecipes.addAll(recipes.filterByNutrition()).also {
            if(ketoRecipes.isEmpty()) throw NoKetoRecipeFoundException()
        }
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
            loadKetoRecipes()
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
