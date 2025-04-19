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
            val calories = recipe.nutrition.calories
            val protein = recipe.nutrition.protein

            abs(calories - targetCalories) <= tolerance &&
                    abs(protein - targetProtein) <= tolerance
        }.sortedBy {
            val calories = it.nutrition.calories
            val protein = it.nutrition.protein
            abs(calories - targetCalories) + abs(protein - targetProtein)
        }
    }
    fun printMealList(meals: List<Recipe>) {
        meals.forEachIndexed { index, meal ->
            val cals = meal.nutrition.calories
            val prot = meal.nutrition.protein
            println("${index + 1}. ${meal.name} — Calories: ${cals.toInt()}, Protein: ${"%.1f".format(prot)}g")
        }
    }

}
