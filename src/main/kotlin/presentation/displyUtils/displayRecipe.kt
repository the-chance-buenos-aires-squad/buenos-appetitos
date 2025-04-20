package org.example.presentation.displyUtils

import org.example.model.Recipe

fun Recipe.displayDetails() {
    println("=== Recipe Details ===")
    println("Name: ${this.name}")
    println("ID: ${this.id}")
    println("Preparation Time: ${this.minutes} minutes")
    println("Contributor ID: ${this.contributorId}")
    println("Submitted On: ${this.submitted}")
    println("Tags: ${this.tags.joinToString(", ")}")
    println("Nutrition Info:")
    println("  - Fat: ${this.nutrition.fat}")
    println("  - Saturated Fat: ${this.nutrition.saturatedFat}")
    println("  - Carbohydrates: ${this.nutrition.carbohydrates}")
    println("Number of Steps: ${this.numberOfSteps}")
    println("Steps:")
    this.steps.forEachIndexed { index, step ->
        println("  ${index + 1}. $step")
    }
    println("Description: ${this.description ?: "No description"}")
    println("Ingredients (${this.numberOfIngredients}):")
    this.ingredients.forEach { ingredient ->
        println("  - $ingredient")
    }
    println("======================")
}