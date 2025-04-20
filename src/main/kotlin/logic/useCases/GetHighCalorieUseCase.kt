package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe

class GetHighCalorieUseCase(private val repository: RecipesRepository) {

    private val suggestedHighCalorieIds = mutableSetOf<String>()

    fun getRandomHighCalorieRecipe(minCalories: Int = 700): Recipe {
        val allRecipes = repository.getRecipes()

        if (allRecipes.isEmpty()) throw Exception("No recipes found")

        val highCalorieRecipes = allRecipes.highCalorieList(minCalories, suggestedHighCalorieIds)

        if (highCalorieRecipes.isEmpty()) throw Exception("No high-calorie recipes available or all have been suggested")

        val randomHighCalorieRecipe = highCalorieRecipes.random()
        suggestedHighCalorieIds.add(randomHighCalorieRecipe.id)

        return randomHighCalorieRecipe
    }
}

private fun List<Recipe>.highCalorieList(
    minCalories: Int, suggestedIds: Set<String>
): List<Recipe> {
    return this.filter { it.nutrition.calories > minCalories && it.id !in suggestedIds }
}