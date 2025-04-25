package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import logic.customExceptions.NoRecipeFoundException
import org.example.logic.customExceptions.NoRecipeFoundByDateException
import java.time.LocalDate


class GetRecipesByDateUseCase(val repository: RecipesRepository) {

    private var lastSearchedRecipes: List<Recipe> = emptyList()

    fun getRecipesByDate(date: LocalDate): List<Recipe> {

        val recipesSubmittedOnDate = repository.getRecipes().filter { it.submitted == date }
        recipesSubmittedOnDate.let {
            when {
                it.isEmpty() -> throw NoRecipeFoundByDateException(date = date)
                else -> {
                    lastSearchedRecipes = it
                }
            }
        }

        return recipesSubmittedOnDate
    }

    fun getFullRecipeById(chosenRecipeId: String): Recipe? {
        return lastSearchedRecipes.find { it.id == chosenRecipeId }
    }
}





