package org.example.presentation

import org.example.logic.useCases.GetHealthyFastFoodMealsUseCase

class GetHealthyFoodMealsCLI(
    private val getHealthyFastFoodMealsUseCase: GetHealthyFastFoodMealsUseCase
) {
    fun start() {
        getHealthyFastFoodMealsUseCase.getHealthyFastFood().forEach {
            println(it.name)
        }
    }
}