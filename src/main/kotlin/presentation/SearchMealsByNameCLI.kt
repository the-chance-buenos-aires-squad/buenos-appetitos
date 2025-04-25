package org.example.presentation

import logic.useCases.SearchRecipesByNameUseCase
import org.example.model.Recipe

class SearchMealsByNameCLI(
    private val searchMealsByNameUseCase: SearchRecipesByNameUseCase
) {

    fun start() {
        try {
            val nameQuery = getUserInput()
            if (nameQuery.isNullOrEmpty()) return
            searchAndDisplayMeals(nameQuery)
        } catch (exception: Exception) {
            println(exception.message)
        }
    }

    private fun searchAndDisplayMeals(nameQuery: String) {
        val meals = searchMealsByNameUseCase.searchRecipeName(
            query = nameQuery,
            useFuzzy = true,
            maxTypos = 2
        )
        displayMeals(meals)
    }

    private fun displayMessage() {
        println("\n--------- Get Meals by Name ------------")
        print("Enter Meal Name (or press Enter to go back): ")
    }

    private fun displayMeals(meals: List<Recipe>) {
        if (meals.isEmpty()) {
            println("No meals found with this name.")
        } else {
            println("Found ${meals.size} meal(s):")
            meals.forEachIndexed { index, recipe ->
                println("${index + 1}. ${recipe.name} (ID: ${recipe.id})")
            }
        }
    }

    private fun getUserInput(): String? {
        displayMessage()
        val input = readln().trim()

        return if (input.isEmpty()) {
            println("No Input..")
            println("Returning to main menu...")
            null
        } else {
            input
        }
    }

}

