package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import kotlin.random.Random

class GetHighCalorieUseCase(private val repository: RecipesRepository) {


    private var highCalorieRecipes = mutableListOf<Recipe>()

    private fun loadHighCalorieRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw IllegalStateException("No meals found") }
        highCalorieRecipes.addAll(recipes.filterHighCalorieRecipes())
    }

    fun suggestRandomHighCalorieRecipe(): Recipe {
        if (highCalorieRecipes.isEmpty()) {
            loadHighCalorieRecipes()
        }
        val randomIndex = Random.nextInt(highCalorieRecipes.size)
        return highCalorieRecipes.removeAt(randomIndex)
    }

    private fun List<Recipe>.filterHighCalorieRecipes(): List<Recipe> {
        return this.filter { it.nutrition.calories > MIN_CALORIE }
    }

    private companion object {
        const val MIN_CALORIE = 700
    }
}

