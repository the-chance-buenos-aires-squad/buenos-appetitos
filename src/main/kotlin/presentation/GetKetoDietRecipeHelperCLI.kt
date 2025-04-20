package org.example.presentation

import org.example.logic.useCases.GetKetoRecipeUseCase

class GetKetoDietRecipeHelperCLI(
    private val getKetoRecipeUseCase: GetKetoRecipeUseCase
) {

    fun start() {

        val ketoRecipes = getKetoRecipeUseCase.ketoDietMealHelper()
        var currentIndex = 0
        if (ketoRecipes.isEmpty()) {
            println("No keto-friendly recipes found.")
            return
        }
        println("Welcome to Keto Diet Meal Helper!")
        println("We’ll show you one recipe at a time.")

        while (currentIndex < ketoRecipes.size) {
            val meal = ketoRecipes[currentIndex]
            println("${currentIndex + 1}. ${meal.name} - Nutrition: ${meal.nutrition}")
            currentIndex++

            println("Press Enter to see the next recipe...")
            readLine()
        }

        println("That’s all the keto recipes we have!")
    }

}

