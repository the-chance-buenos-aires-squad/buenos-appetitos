package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class GetHighCalorieUseCase(private val repository: RecipesRepository) {

    companion object {
        const val MIN_CALORIE = 700
    }

    private var highCalorieRecipes = mutableListOf<Recipe>()

    private fun getHighCalorieRecipes() {
        val recipes = repository.getRecipes().also { if (it.isEmpty()) throw IllegalStateException("No meals found") }
        highCalorieRecipes = recipes.filterHighCalorieRecipes() as MutableList<Recipe>
    }

    fun suggestRandomHighCalorieRecipe():Recipe{
        if (highCalorieRecipes.isEmpty()){
            try {
                getHighCalorieRecipes()
            } catch (e: IllegalStateException) {
                println(e.message)
            }
        }
        return highCalorieRecipes.removeFirst()
    }

    private fun List<Recipe>.filterHighCalorieRecipes(): List<Recipe> {
        return this.filter { it.nutrition.calories > MIN_CALORIE }.shuffled()
    }
}

