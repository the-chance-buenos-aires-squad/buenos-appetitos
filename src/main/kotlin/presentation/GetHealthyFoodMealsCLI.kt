package org.example.presentation

import logic.customExceptions.NoRecipeFoundException
import org.example.logic.useCases.GetHealthyFastFoodMealsUseCase
import org.example.presentation.displyUtils.displayDetails

class GetHealthyFoodMealsCLI(
    private val getHealthyFastFoodMealsUseCase: GetHealthyFastFoodMealsUseCase
) {
    fun start() {
        try {
            println("Do you want a specific number of Recipes? (write the number if yes and if no click 0) ")

            readlnOrNull()?.toIntOrNull()?.let { number ->
                getHealthyFastFoodMealsUseCase
                    .getHealthyFastFood(number)
                    .forEach { healthyRecipe ->
                        healthyRecipe.displayDetails()
                    }
            } ?: run {
                getHealthyFastFoodMealsUseCase
                    .getHealthyFastFood()
                    .forEach { healthyRecipe ->
                        healthyRecipe.displayDetails()
                    }
            }


        } catch (exception: NoRecipeFoundException) {
            println("Error: ${exception.message}")
        }
    }
}
