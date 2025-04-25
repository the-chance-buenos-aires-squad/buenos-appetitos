package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import logic.customExceptions.NoRecipeFoundException
import java.time.LocalDate


class GetRecipesByDateUseCase(val repository: RecipesRepository) {

    private var lastSearchedRecipes: List<Recipe> = emptyList()

    fun getRecipesByDate(dateQuery: String): List<DailyRecipe> {
        val date = LocalDate.parse(dateQuery)
        val recipesSubmittedOnDate = repository.getRecipes().filter { it.submitted == date }
        recipesSubmittedOnDate.let {
            when {
                it.isEmpty() -> throw NoRecipeFoundException(date = dateQuery)
                else -> {
                    lastSearchedRecipes = it
                }
            }
        }

        val dailyRecipes = recipesSubmittedOnDate.map { fullRecipe ->
            DailyRecipe(id = fullRecipe.id, name = fullRecipe.name)
        }
        return dailyRecipes
    }

    fun getFullRecipeById(chosenRecipeId: String): Recipe? {
        return lastSearchedRecipes.find { it.id == chosenRecipeId }
    }


    data class DailyRecipe(val id: String, val name: String)
}





