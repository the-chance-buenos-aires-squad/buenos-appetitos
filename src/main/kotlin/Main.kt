package org.example

import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.KetoMealsHelperUseCase

fun main() {
    val csvFileReader = CsvFileReader ()
    val recipesRepository = CsvRecipesRepository(csvFileReader)
    val recipes = recipesRepository.getRecipes()
    val result = KetoMealsHelperUseCase().execute(recipes)
    println("Keto-friendly meals: ")
    result.forEach {
        ("${it.name} - ${it.nutrition}")

    }
}