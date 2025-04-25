package org.example.presentation

import org.example.logic.useCases.GetCustomizedNutritionMealsUseCase
import org.example.presentation.displyUtils.displayDetails
import java.util.*

class GymMealsCLI(private val getCustomizedNutritionMealsUseCase: GetCustomizedNutritionMealsUseCase) {

    private val scanner = Scanner(System.`in`)

    fun start() {
        println("🍽️ Welcome to the Gym Helper!")

        try {
            val calories = prompt("Enter desired calories: ")
            val protein = prompt("Enter desired protein (grams): ")

            val meals = getCustomizedNutritionMealsUseCase.findMealsByNutrition(calories, protein)

            if (meals.isEmpty()) {
                println("❌ No meals found matching your fitness needs.")
            } else {
                println("✅ Meals matching your goals:")
                meals.forEach { it.displayDetails() }
            }

        } catch (e: Exception) {
            println("⚠️ Error: ${e.message}")
        }
    }

    private fun prompt(message: String): Int {
        print(message)
        return scanner.nextLine().toIntOrNull()
            ?: throw IllegalArgumentException("Invalid input.")
    }
}
