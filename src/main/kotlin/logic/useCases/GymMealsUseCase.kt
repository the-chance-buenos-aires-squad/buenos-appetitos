package org.example.logic.useCases

import org.example.logic.RecipesRepository
import org.example.model.Recipe
import java.lang.Math.abs

class GymMealsUseCase(private val repository: RecipesRepository) {

    fun findMealsByNutrition(calories: Int, protein: Int, tolerance: Int = 50): List<Recipe> {
        return repository.getRecipes()
            .filter { it.matchesNutritionTarget(calories, protein, tolerance) }
            .sortedBy { it.nutritionDifferenceScore(calories, protein) }
    }

    private fun Recipe.matchesNutritionTarget(cals: Int, prot: Int, tolerance: Int): Boolean {
        return abs(nutrition.calories - cals) <= tolerance &&
                abs(nutrition.protein - prot) <= tolerance
    }

    private fun Recipe.nutritionDifferenceScore(cals: Int, prot: Int): Double {
        return abs(nutrition.calories - cals) + abs(nutrition.protein - prot)
    }
}