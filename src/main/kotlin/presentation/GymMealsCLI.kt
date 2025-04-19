package presentation

import org.example.logic.useCases.GymMealsUseCase
import org.example.model.Recipe
import java.util.*

class GymMealsCLI(private val gymMealsUseCase: GymMealsUseCase) {

    private val scanner = Scanner(System.`in`)

    fun run() {
        println("🍽️ Welcome to the Gym Helper!")

        try {
            val calories = promptCalories()
            val protein = promptProtein()

            val meals = fetchMatchingMeals(calories, protein)

            printMealResults(meals)

        } catch (e: Exception) {
            println("⚠️ Error: ${e.message}")
        }
    }

    private fun promptCalories(): Int {
        print("Enter desired calories: ")
        val input = scanner.nextLine()
        return input.toIntOrNull() ?: throw IllegalArgumentException("Invalid calories input.")
    }

    private fun promptProtein(): Int {
        print("Enter desired protein (grams): ")
        val input = scanner.nextLine()
        return input.toIntOrNull() ?: throw IllegalArgumentException("Invalid protein input.")
    }

    private fun fetchMatchingMeals(calories: Int, protein: Int): List<Recipe> {
        return gymMealsUseCase.findMealsByNutrition(calories, protein)
    }

    private fun printMealResults(meals: List<Recipe>) {
        if (meals.isEmpty()) {
            println("❌ No meals found matching your fitness needs.")
            return
        }

        println("✅ Meals matching your goals:")
        meals.forEachIndexed { index, meal ->
            val cals = meal.nutrition.calories.toInt()
            val prot = meal.nutrition.protein
            println("${index + 1}. ${meal.name} — Calories: $cals, Protein: ${"%.1f".format(prot)}g")
        }
    }
}
