package org.example.presentation

import org.example.logic.useCases.GetHealthyFastFoodMealsUseCase

class GetHealthyFoodMealsCLI(
    private val getHealthyFastFoodMealsUseCase: GetHealthyFastFoodMealsUseCase
) {
    fun start() {
        println("Do you want a specific number of Recipes? (write the number if yes and if no click 0) ")

        readlnOrNull()?.toIntOrNull()?.let { number ->
            getHealthyFastFoodMealsUseCase.getHealthyFastFood(number).forEach {
                printingRecipes(it)
            }
        } ?: run {
            getHealthyFastFoodMealsUseCase.getHealthyFastFood().forEach {
                printingRecipes(it)
            }
        }
    }
}
