package org.example.logic.useCases
import org.example.model.Recipe
import org.example.logic.RecipesRepository
import kotlin.math.abs

class GymMealsUseCase(private val repository: RecipesRepository) {
    fun findMealsByNutrition(
        targetCalories: Int,
        targetProtein: Int,
        tolerance: Int = 50
    ): List<Recipe> {
        return repository.getRecipes()
            .filter { it.matchesNutritionTarget(targetCalories, targetProtein, tolerance) }
            .sortedBy { it.nutritionDifferenceScore(targetCalories, targetProtein) }
    }
    fun printMealList(meals: List<Recipe>) {
        meals.forEachIndexed { index, meal ->
            val cals = meal.nutrition.calories
            val prot = meal.nutrition.protein
            println("${index + 1}. ${meal.name} — Calories: ${cals.toInt()}, Protein: ${"%.1f".format(prot)}g")
        }
    }
    private fun Recipe.matchesNutritionTarget(
        targetCalories: Int,
        targetProtein: Int,
        tolerance: Int
    ): Boolean {
        val cal = this.nutrition.calories
        val prot = this.nutrition.protein
        return abs(cal - targetCalories) <= tolerance &&
                abs(prot - targetProtein) <= tolerance
    }


    private fun Recipe.nutritionDifferenceScore(
        targetCalories: Int,
        targetProtein: Int
    ): Double {
        val cal = this.nutrition.calories
        val prot = this.nutrition.protein
        return abs(cal - targetCalories) + abs(prot - targetProtein)
    }
}
