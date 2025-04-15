package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.GetHealthyFastFoodMealsUseCase

fun main() {
    val csvFileReader=CsvFileReader()
    val recipesRepository=CsvRecipesRepository(csvFileReader)
    val getFood=GetHealthyFastFoodMealsUseCase(recipesRepository)
    println( getFood.getHealthyFastFood(5))
}