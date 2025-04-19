package org.example.presentation

import org.example.logic.useCases.SearchRecipesByNameUseCase
import org.example.model.Recipe
import org.example.presentation.customExceptions.EmptyInputException

class SearchMealsByNameCLI(
    private val searchMealsByNameUseCase: SearchRecipesByNameUseCase
) {

    fun start() {
        try {
            displayMessage()
            val nameQuery = handleUserInput()
            if (nameQuery.isEmpty()) {
                println("\nReturning to main menu...")
            }
            val meals = searchMealsByNameUseCase.search(query = nameQuery, useFuzzy = true)
            displayResults(meals)
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
    }

    private fun displayMessage() {
        println("\n--------- Get Meals by Name ------------")
        print("Enter Meal Name (or press Enter to go back): ")
    }

    private fun handleUserInput(): String {
        val userInput = readln().trim()
        if (userInput.isEmpty()) throw EmptyInputException()
        return userInput
    }

    private fun displayResults(meals: List<Recipe>) {
        if (meals.isEmpty()) {
            println("No meals found with this name.")
        }else{
            println("Found ${meals.size} meal(s):")
            meals.forEachIndexed { index, recipe ->
                println("${index + 1}. ${recipe.name} (ID: ${recipe.id})")
            }
        }
    }

}

