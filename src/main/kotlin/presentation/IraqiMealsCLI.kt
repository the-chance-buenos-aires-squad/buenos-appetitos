package org.example.comandLineInterface

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.GetIraqiMealsUseCase

fun main() {
    val csvFileReader = CsvFileReader()
    val recipesRepository = CsvRecipesRepository(csvFileReader)

    val iraqiMealsUseCase = GetIraqiMealsUseCase(recipesRepository)
    val iraqiMeals = iraqiMealsUseCase.execute()

    println("Iraqi Meals:")
    iraqiMeals.forEach {
        println("${it.name} - ${it.tags} - ${it.description}")
    }
}
