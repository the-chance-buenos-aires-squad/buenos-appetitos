package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import java.time.LocalDate


class SearchFoodByAddDateUseCase(val repository: RecipesRepository) {
    private val recipes = repository.getRecipes()

    fun searchFoodByDate(dateQuery: String): List<DailyRecipe> {
        val date = LocalDate.parse(dateQuery)
        val result = recipes.filter { it.submitted == date }
        if (result.isEmpty()) throw NoMealFoundException(date = dateQuery)
        val dailyRecipesList = result.map { fullRecipe->
            DailyRecipe(id = fullRecipe.id, name = fullRecipe.name)
        }
        return dailyRecipesList
    }
    companion object{
        data class DailyRecipe (val id:String , val name:String)




        class NoMealFoundException(
            private val date: String,
            message: String = "No recipe found for date $date"
        ) : RuntimeException(message)

        class NoInputException(
            message: String = "No input given from the user"
        ) : RuntimeException(message)
    }

}





