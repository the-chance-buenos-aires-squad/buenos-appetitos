package org.example.presentation

import org.example.logic.useCases.GymMealsUseCase
import org.example.data.CsvFileReader
import org.example.data.RecipeParser
import org.example.data.CsvRecipesRepository
import java.io.File
import java.util.*

class GymMealsCLI {

    private val scanner = Scanner(System.`in`)

    fun run() {
        println("🍽️ Welcome to the Gym Helper!")

        try {
            val calories = promptCalories()
            val protein = promptProtein()

            val recipes = loadRecipes()
            val meals = GymMealsUseCase(recipes).findMealsByNutrition(calories, protein)

            printMealResults(meals)

        } catch (e: Exception) {
            println("⚠️ Error: ${e.message}")
        }
    }

    private fun promptCalories(): Int {
        print("Enter desired calories: ")
        return scanner.nextLine().toIntOrNull()
            ?: throw IllegalArgumentException("Invalid calories input.")
    }

    private fun promptProtein(): Int {
        print("Enter desired protein (grams): ")
        return scanner.nextLine().toIntOrNull()
            ?: throw IllegalArgumentException("Invalid protein input.")
    }

    private fun loadRecipes() = CsvRecipesRepository(
        CsvFileReader(File("data/food.csv")),
        RecipeParser()
    ).getRecipes()

    private fun printMealResults(meals: List<org.example.model.Recipe>) {
        if (meals.isEmpty()) {
            println("❌ No meals found matching your fitness needs.")
        } else {
            println("✅ Meals matching your goals:")
            GymMealsUseCase(meals).printMealList(meals)
        }
    }
}
