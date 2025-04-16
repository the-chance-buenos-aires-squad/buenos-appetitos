package org.example.logic.useCases

import org.example.logic.RecipesRepository
import java.util.*

class UseCaseHolder(val repository:RecipesRepository) {
    fun useCaseImplementation(){

    }
    fun runGymHelper() {
        val scanner = Scanner(System.`in`)

        println("🏋️ Welcome to the Gym Helper!")

        try {
            print("Enter desired calories: ")
            val caloriesInput = scanner.nextLine()
            val calories = caloriesInput.toIntOrNull() ?: throw IllegalArgumentException("Invalid calories input.")

            print("Enter desired protein (grams): ")
            val proteinInput = scanner.nextLine()
            val protein = proteinInput.toIntOrNull() ?: throw IllegalArgumentException("Invalid protein input.")

            val meals = findMealsByNutrition(repository, calories, protein)

            if (meals.isEmpty()) {
                println("❌ No meals found matching your fitness needs.")
            } else {
                println("✅ Meals matching your goals:")
                meals.forEachIndexed { index, meal ->
                    val cals = meal.nutrition.getOrNull(0)?.toInt() ?: 0
                    val prot = meal.nutrition.getOrNull(4) ?: 0.0
                    println("${index + 1}. ${meal.name} — Calories: $cals, Protein: ${"%.1f".format(prot)}g")
                }
            }

        } catch (e: Exception) {
            println("⚠️ Error: ${e.message}")
        }
    }
}