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
        return repository.getRecipes().filter { recipe ->
            val calories = recipe.nutrition.calories?.toInt() ?: return@filter false
            val protein = recipe.nutrition.protein ?: return@filter false

            abs(calories - targetCalories) <= tolerance &&
                    abs(protein - targetProtein) <= tolerance
        }.sortedBy {
            val calories = it.nutrition.calories?.toInt() ?: 0
            val protein = it.nutrition.protein ?: 0.0
            abs(calories - targetCalories) + abs(protein - targetProtein)
        }
    }
    fun printMealList(meals: List<Recipe>) {
        meals.forEachIndexed { index, meal ->
            val cals = meal.nutrition.calories?.toInt() ?: 0
            val prot = meal.nutrition.protein ?: 0.0
            println("${index + 1}. ${meal.name} — Calories: $cals, Protein: ${"%.1f".format(prot)}g")
        }
    }

}
