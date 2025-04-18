package org.example.comandLineInterface

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.data.RecipeParser
import org.example.logic.useCases.GetIraqiMealsUseCase

fun main() {
    val filePath = "src/main/kotlin/data/food.csv"
    val csvFileReader = CsvFileReader(filePath)
    val recipeParser = RecipeParser()
    val repository = CsvRecipesRepository(csvFileReader, recipeParser)

    val iraqiMealsUseCase = GetIraqiMealsUseCase(repository)
    val iraqiMeals = iraqiMealsUseCase.execute()

    println("Iraqi Meals:")
    iraqiMeals.forEach {
        println("${it.name} - ${it.tags} - ${it.description}")
    }
}
