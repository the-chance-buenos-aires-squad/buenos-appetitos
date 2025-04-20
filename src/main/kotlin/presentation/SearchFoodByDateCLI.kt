package org.example.presentation

import org.example.logic.useCases.SearchFoodByAddDateUseCase
import org.example.logic.useCases.SearchFoodByAddDateUseCase.DailyRecipe
import org.example.model.Recipe
import org.example.presentation.customExceptions.EmptyInputException
import org.example.presentation.customExceptions.NoRecipeFoundException
import java.time.DateTimeException

class SearchFoodByDateCLI(
    private val searchFoodByAddDateUseCase: SearchFoodByAddDateUseCase
) {

    fun start() {
        displaySearchByDateHeadMessage()
        val dateQuery: String = handleUserInput()
        getDailyRecipesResult(dateQuery).let { result->
            if (result.isNotEmpty()){
                displayDailyRecipes(result,dateQuery)
                getChosenRecipe()
            }

        }
    }

    private fun displayDailyRecipes(result: List<DailyRecipe>, dateQuery: String) {
        println("Recipes result for date $dateQuery")
        println("-------------------------------")
        println("Id        Name")
        println("--        ----")
        result.forEach {dailyRecipe->
            println("${dailyRecipe.id}        ${dailyRecipe.name}")
        }
    }

    private fun getDailyRecipesResult(dateQuery : String):List<DailyRecipe> {
        try {
            val dailyRecipeResult: List<DailyRecipe> = searchFoodByAddDateUseCase.searchFoodByDate(dateQuery)
            return dailyRecipeResult
        } catch (e: NoRecipeFoundException) {
            println("${e.message}")
            return emptyList()
        } catch (e: DateTimeException) {
            println("incorrect date format")
            return emptyList()
        }
    }

    private fun handleUserInput() = try {
        readUserInput()
    } catch (e: EmptyInputException) {
        println("you didn't enter anything redirecting back to main menu")
        ""
    }


    private fun getChosenRecipe() {
        println("please choose which recipe you want to show more details....\n:")
        val chosenRecipeId: String = handleUserInput()
        val detailedRecipe:Recipe = searchFoodByAddDateUseCase.getDetailedRecipeById(chosenRecipeId)
        printingRecipes(detailedRecipe)
    }




    private fun readUserInput(): String {
        val userInput = readln()
        if (userInput.trim().isEmpty()) throw EmptyInputException()
        return userInput
    }


    private fun displaySearchByDateHeadMessage() {
        println("please insert the required Date to search for Recipes added that date:")
        println("supported format pattern (YYYY-MM-DD) : example 2018-01-19")
        print(":")
    }


}

