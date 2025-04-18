package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import java.time.LocalDate
import kotlin.jvm.Throws

class searchFoodByAddDateUseCase(val repository: RecipesRepository) {
    private val recipes = repository.getRecipes()
    private val noMealFoundException = Exception("no meal found for the given date")


    fun searchFoodByDate(dateQuery:String): List<Recipe>{
        // validate date query
        val date = LocalDate.parse(dateQuery)
//        val result = recipes.filter { it.submitted == date }
//        if (result.isEmpty()) throw noMealFoundException
        return TODO()
    }





}





