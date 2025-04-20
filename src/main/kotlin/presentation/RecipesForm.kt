package org.example.presentation

import org.example.model.Recipe

fun printingRecipes(recipe: Recipe) {
    println("=== Recipe Details ===")
    println("Name: ${recipe.name}")
    println("ID: ${recipe.id}")
    println("Preparation Time: ${recipe.minutes} minutes")
    println("Contributor ID: ${recipe.contributorId}")
    println("Submitted On: ${recipe.submitted}")
    println("Tags: ${recipe.tags.joinToString(", ")}")
    println("Nutrition Info:")
    println("  - Fat: ${recipe.nutrition.fat}")
    println("  - Saturated Fat: ${recipe.nutrition.saturatedFat}")
    println("  - Carbohydrates: ${recipe.nutrition.carbohydrates}")
    println("Number of Steps: ${recipe.numberOfSteps}")
    println("Steps:")
    recipe.steps.forEachIndexed { index, step ->
        println("  ${index + 1}. $step")
    }
    println("Description: ${recipe.description ?: "No description"}")
    println("Ingredients (${recipe.numberOfIngredients}):")
    recipe.ingredients.forEach { ingredient ->
        println("  - $ingredient")
    }
    println("======================")
}

