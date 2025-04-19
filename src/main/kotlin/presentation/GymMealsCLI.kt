package org.example.presentation

import org.example.logic.useCases.GymMealsUseCase
import org.example.model.Recipe
import java.util.*

class GymMealsCLI(private val gymMealsUseCase: GymMealsUseCase) {

    private val scanner = Scanner(System.`in`)
    private val form = RecipesForm()

    fun run() {
        println("🍽️ Welcome to the Gym Helper!")

        try {
            val calories = prompt("Enter desired calories: ")
            val protein = prompt("Enter desired protein (grams): ")

            val meals = gymMealsUseCase.findMealsByNutrition(calories, protein)

            if (meals.isEmpty()) {
                println("❌ No meals found matching your fitness needs.")
            } else {
                println("✅ Meals matching your goals:")
                meals.forEach { form.printingRecipes(it) }
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
