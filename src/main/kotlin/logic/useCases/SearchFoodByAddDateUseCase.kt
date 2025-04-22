package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import logic.customExceptions.NoRecipeFoundException
import java.time.LocalDate


class SearchFoodByAddDateUseCase(val repository: RecipesRepository) {
    private val recipes = repository.getRecipes()

    private var recipesSearchedByDateResultList: List<Recipe> = emptyList()

    fun searchFoodByDate(dateQuery: String): List<DailyRecipe> {
        val date = LocalDate.parse(dateQuery)
        val result = recipes.filter { it.submitted == date }
        result.let {
            when {
                it.isEmpty() -> throw NoRecipeFoundException(date = dateQuery)
                else -> {
                    recipesSearchedByDateResultList = it
                }
            }
        }

        val dailyRecipesList = result.map { fullRecipe ->
            DailyRecipe(id = fullRecipe.id, name = fullRecipe.name)
        }
        return dailyRecipesList
    }

    fun getDetailedRecipeById(chosenRecipeId: String): Recipe? {
        return recipesSearchedByDateResultList.find { it.id == chosenRecipeId }
    }


    data class DailyRecipe(val id: String, val name: String)
}





