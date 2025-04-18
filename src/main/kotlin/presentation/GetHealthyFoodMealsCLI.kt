package org.example.presentation

import org.example.logic.useCases.GetHealthyFastFoodMealsUseCase

class GetHealthyFoodMealsCLI(
    private val getHealthyFastFoodMealsUseCase: GetHealthyFastFoodMealsUseCase
) {
    fun start() {
        val recipesForm = RecipesForm()
        println("Do you want a specific number of Recipes? (write the number if yes and if no click 0) ")
        val numOfRecipes = readlnOrNull()?.toInt()
        if( numOfRecipes == null || numOfRecipes > 0 ) {
            getHealthyFastFoodMealsUseCase.getHealthyFastFood().forEach {
                recipesForm.printingRecipes(it)
            }
        }
        else{
            getHealthyFastFoodMealsUseCase.getHealthyFastFood(numOfRecipes).forEach {
                recipesForm.printingRecipes(it)
            }
        }
    }
}