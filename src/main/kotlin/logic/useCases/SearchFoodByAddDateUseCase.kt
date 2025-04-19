package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.presentation.customExceptions.NoRecipeFoundException
import java.time.LocalDate


class SearchFoodByAddDateUseCase(val repository: RecipesRepository) {
    private val recipes = repository.getRecipes()

    fun searchFoodByDate(dateQuery: String): List<DailyRecipe> {
        val date = LocalDate.parse(dateQuery)
        val result = recipes.filter { it.submitted == date }
        if (result.isEmpty()) throw NoRecipeFoundException(date = dateQuery)
        val dailyRecipesList = result.map { fullRecipe->
            DailyRecipe(id = fullRecipe.id, name = fullRecipe.name)
        }
        return dailyRecipesList
    }
    companion object{
        data class DailyRecipe (val id:String , val name:String)
    }

}





