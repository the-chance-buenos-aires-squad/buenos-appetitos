package org.example.logic.useCases

import org.example.model.Recipe
import kotlin.math.abs

class GymMealsUseCase(private val recipes: List<Recipe>) {

    fun findMealsByNutrition(
        targetCalories: Int,
        targetProtein: Int,
        tolerance: Int = 50
    ): List<Recipe> {
        return recipes
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

    private fun Recipe.matchesNutritionTarget(calories: Int, protein: Int, tolerance: Int): Boolean {
        return abs(nutrition.calories - calories) <= tolerance &&
                abs(nutrition.protein - protein) <= tolerance
    }

    private fun Recipe.nutritionDifferenceScore(calories: Int, protein: Int): Double {
        return abs(nutrition.calories - calories) + abs(nutrition.protein - protein)
    }
}