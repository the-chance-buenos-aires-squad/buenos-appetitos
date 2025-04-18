package org.example.presentation

import org.example.logic.useCases.GetHighCalorieUseCase

class GetHighCalorieCli(
    private val getHighCalorieUseCase: GetHighCalorieUseCase
) {
    fun start() {
        println("\nHigh Calories Recipe")
        println("======================")
        try {
            val highCalorieRecipe = getHighCalorieUseCase.getRandomHighCalorieRecipe()
            println("Suggested Recipe Name: ${highCalorieRecipe.name} with ${highCalorieRecipe.nutrition.calories} calories")

        } catch (exception: Exception) {
            println("Error: ${exception.message}")
            throw exception
        }
    }
}