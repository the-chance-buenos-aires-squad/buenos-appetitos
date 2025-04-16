package org.example.logic.useCases
import org.example.model.Recipe
import org.example.logic.RecipesRepository
import kotlin.math.abs

fun findMealsByNutrition(
    repository: RecipesRepository,
    targetCalories: Int,
    targetProtein: Int,
    tolerance: Int = 50
): List<Recipe> {
    return repository.getRecipes().filter { recipe ->
        val calories = recipe.nutrition.getOrNull(0)?.toInt() ?: return@filter false
        val protein = recipe.nutrition.getOrNull(4) ?: return@filter false

        abs(calories - targetCalories) <= tolerance &&
                abs(protein - targetProtein) <= tolerance
    }.sortedBy {
        val calories = it.nutrition.getOrNull(0)?.toInt() ?: 0
        val protein = it.nutrition.getOrNull(4) ?: 0.0
        abs(calories - targetCalories) + abs(protein - targetProtein)
    }
}
