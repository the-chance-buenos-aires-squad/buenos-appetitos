package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.GetKetoMealsUseCase



fun main() {
    val csvFileReader = CsvFileReader ()
    val recipesRepository = CsvRecipesRepository(csvFileReader)
    val useCase = GetKetoMealsUseCase(recipesRepository)
    val ketoMeals = useCase.execute()
    println("Keto-friendly meals: ")
    ketoMeals.forEach {
        ("${it.name} - ${it.nutrition}")

    }
}