package org.example.presentation


import org.example.KetoMealSuggester
import org.example.data.CsvFileReader
import org.example.data.CsvRecipesRepository
import org.example.logic.useCases.GetKetoMealsUseCase
import org.example.model.Recipe

fun main() {
    val csvFileReader = CsvFileReader()
    val recipesRepository = CsvRecipesRepository(csvFileReader)
    val useCase = GetKetoMealsUseCase(recipesRepository)
    val ketoMeals = useCase.execute()

    println("Keto-friendly meals (all):")
    ketoMeals.forEach {
        println("${it.name} - ${it.nutrition}")
    }

    println("\nSuggesting one keto-friendly meal at a time:")
    val suggester = KetoMealSuggester(ketoMeals)
    var meal = suggester.suggestNext()

    while (meal != null) {
        println("${meal.name} - ${meal.nutrition}")
        meal = suggester.suggestNext()
    }
}
