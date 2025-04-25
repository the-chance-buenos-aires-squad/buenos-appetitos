package org.example.presentation

import org.example.logic.useCases.GetRecipesByDateUseCase
import org.example.model.Recipe
import org.example.logic.customExceptions.NoRecipeFoundByDateException
import org.example.logic.utili.DateUtils.parseToLocalDate
import org.example.presentation.displyUtils.displayDetails
import org.example.presentation.inputHandlingUtils.handleUserInput
import java.time.DateTimeException
import java.time.format.DateTimeParseException

class SearchFoodByDateCLI(
    private val searchFoodByAddDateUseCase: GetRecipesByDateUseCase
) {

    fun start() {
        displaySearchByDateHeadMessage()
        val dateQuery: String = handleUserInput()
        getDailyRecipesResult(dateQuery).let { result ->
            if (result.isNotEmpty()) {
                displayDailyRecipes(result, dateQuery)
                getChosenRecipe()
            }
        }
    }

    private fun displayDailyRecipes(result: List<Recipe>, dateQuery: String) {
        println("Recipes result for date $dateQuery")
        println("-------------------------------")
        println("Id        Name")
        println("--        ----")
        result.forEach { dailyRecipe ->
            println("${dailyRecipe.id}        ${dailyRecipe.name}")
        }

    }

    private fun getDailyRecipesResult(dateQuery: String): List<Recipe> {
        try {
            val isValidDate = parseToLocalDate(dateQuery)
            val dailyRecipeResult: List<Recipe> = searchFoodByAddDateUseCase.getRecipesByDate(isValidDate)
            return dailyRecipeResult
        } catch (e: NoRecipeFoundByDateException) {
            println("${e.message}")
            return emptyList()
        } catch (e: DateTimeException) {
            println("incorrect date format")
            return emptyList()
        }
    }


    private fun getChosenRecipe() {
        println("please choose which recipe you want to show more details....\n:")
        val chosenRecipeId: String = handleUserInput()
        val detailedRecipe: Recipe? = searchFoodByAddDateUseCase.getFullRecipeById(chosenRecipeId)
        detailedRecipe.let {
            when (it) {
                null -> println("incorrect input no such id found")
                else -> it.displayDetails()
            }
        }
    }


    private fun displaySearchByDateHeadMessage() {
        println("please insert the required Date to search for Recipes added that date:")
        println("supported format pattern (YYYY-MM-DD) : example 2018-01-19")
        print(":")
    }


}

